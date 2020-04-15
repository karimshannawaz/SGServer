package server.network.packet.decoder;

import server.Global;
import server.network.Session;
import server.network.packet.InputStream;
import server.network.packet.encoder.LoginEncoder;
import server.user.UserLoader;

public class LoginDecoder extends Decoder {

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
							((LoginEncoder) session.getEncoder()).assignKioskID(i);
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
				((LoginEncoder) session.getEncoder()).sendMenu();
				break;
	
			// Lets the customer know if they can create the specified email
			// rewards account or not.
			case 6:
				String email = stream.readString();
				String birthdate = stream.readString();
				// Checks to see if the email the customer is trying to use already exists
				// and if it does, then they are notified that they won't be able to use it.
				if(UserLoader.containsUser(email)) {
					System.out.println("Email exists.");
					((LoginEncoder) session.getEncoder()).sendClientPacket("email_exists");
					return;
				}
				// User can make the email. This makes a new file for the user
				// Lets them know that their request was successful and that
				// the new account was created.
				System.out.println("Customer rewards email created successfully "+email+" with birthdate "+birthdate);
				((LoginEncoder) session.getEncoder()).sendClientPacket("email_created", email, birthdate);
				break;
		}
	}

}
