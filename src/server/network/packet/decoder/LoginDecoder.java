package server.network.packet.decoder;

import server.Global;
import server.Reports;
import server.Server;
import server.menu.Order;
import server.network.Session;
import server.network.packet.InputStream;
import server.network.packet.encoder.LoginEncoder;
import server.user.User;
import server.user.UserLoader;

public class LoginDecoder extends Decoder {
	
	private User user;

	public LoginDecoder(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decode(InputStream stream) {
		int packetId = stream.readUnsignedByte();
		stream.skip(2);
		switch (packetId) {
			// Second handshake from client to set login encoder.
			case 2:
				System.out.println("Received second handshake from client session.");
				session.setEncoder(1);
				break;
			// Acknowledges that a customer has connected to the client and
			// assigns them a free table.
			case 3:
				if (session.getEncoder() instanceof LoginEncoder) {
					for (int i = 0; i < Global.tableIds.length; i++) {
						if (Global.tableIds[i] == 0) {
							Global.tableIds[i] = 1;
							session.setTableID(i);
							session.setCustomer(true);
							session.getLoginPackets().assignKioskID(i);
							createTemporaryUser(i);
							break;
						}
					}
				}
				break;
			// Help request from client table
			case 4:
				int kioskID = stream.readUnsignedByte();
				System.out.println("Help requested from table: " + kioskID);
				/*
				 * for(User user : Global.getAllUsers()) { if(user.isWaiter() ||
				 * user.isManager()) {
				 * user.getSession().getPacketEncoder().updateTable(kioskID); } }
				 */
				break;
	
			// Sends the menu back to the client.
			case 5:
				session.getLoginPackets().sendMenu();
				break;
	
			// Lets the customer know if they can create the specified email
			// rewards account or not.
			case 6:
				String email = stream.readString();
				String birthday = stream.readString();
				String name = stream.readString();
				// Checks to see if the email the customer is trying to use already exists
				// and if it does, then they are notified that they won't be able to use it.
				if(UserLoader.containsUser(email)) {
					session.sendClientPacket("email_exists");
					return;
				}
				// User can make the email. This makes a new file for the user
				// Lets them know that their request was successful and that
				// the new account was created.
				System.out.println("Customer rewards email created successfully "+name+" - "+email+" with birthday: "+birthday);
				session.sendClientPacket("email_created", email, birthday, name);
				session.loginToRewards(email, birthday, name);
				break;
				
			// Requests email rewards login.
			case 7:
				email = stream.readString();
				
				if(!UserLoader.containsUser(email)) {
					session.sendClientPacket("email_does_not_exist");
					return;
				}
			
				session.loginToRewards(email);
				break;
			
			// Requests employee login
			case 8:
				String id = stream.readString();
				String password = stream.readString();

				if(!UserLoader.containsUser(id, true)) {
					session.sendClientPacket("employee_id_does_not_exist");
					return;
				}

				session.employeeLogin(id, password);
				break;
				
			// Order received from client, has table ID.
			case 9:
				Order.receiveOrder(user, stream);
				break;
		}
	}
	
	public User getUser() {
		return user;
	}

	/**
	 * Creates a temporary user with the specified table ID.
	 */
	private void createTemporaryUser(int tableID) {
		User tempUser = new User("customer", "table"+tableID, null, null);
		tempUser.initialize(session);
		this.user = tempUser;
		Global.addUser(user);
		Reports.activeTables++;
		Server.ui.infoPanel.updateLabels();
	}

}
