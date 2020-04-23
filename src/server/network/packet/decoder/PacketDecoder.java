package server.network.packet.decoder;

import server.Reports;
import server.Server;
import server.menu.Order;
import server.network.Session;
import server.network.packet.InputStream;
import server.user.Payments;
import server.user.Requests;
import server.user.User;

/**
 * Decodes packets received from the client.
 * SERVER SIDED
 * 
 * @author Karimshan
 *
 */
public final class PacketDecoder extends Decoder {

	private User user;

	private static final byte[] SIZES = new byte[256];

	static {
		loadSizes();
	}

	public static void loadSizes() {
		for (int id = 0; id < SIZES.length; id++)
			SIZES[id] = -4;
		SIZES[1] = 2;
	}

	public PacketDecoder(Session session, User player) {
		super(session);
		this.user = player;
	}

	@Override
	public void decode(InputStream stream) {
		while (stream.getRemaining() > 0 && session.getChannel().isConnected()) {
			// && !player.hasFinished()) {
			int packetId = stream.readUnsignedByte();
			if (packetId >= SIZES.length) {
				System.out.println("PacketId " + packetId + " has fake packet id.");
				break;
			}

			int length = SIZES[packetId];
			if (length == -1)
				length = stream.readUnsignedByte();
			else if (length == -2)
				length = stream.readUnsignedShort();
			else if (length == -3)
				length = stream.readInt();
			else if (length == -4) {
				length = stream.getRemaining();
				if (packetId != 255)
					System.out.println("Invalid size for PacketId " + packetId + ". Size guessed to be " + length);
			}
			if (length > stream.getRemaining()) {
				length = stream.getRemaining();
				if (packetId != 0)
					System.out.println("PacketId " + packetId + " has fake size. - expected size " + length);
				break;

			}

			int startOffset = stream.getOffset();
			
			stream.skip(2);

			switch (packetId) {
			
			// Help request from client table
			case 4:
				int kioskID = stream.readUnsignedByte();
				Requests.receiveRequest(kioskID, false);
				break;

			// Sends the menu back to the client.
			case 5:
				user.getPacketEncoder().sendMenu();
				break;
				
			// Order received from client, has table ID.
			case 9:
				Order.receiveOrder(user, stream);
				break;
				
			// Kitchen requests for a waiter to come and get the food
			// for the table whose order is fulfilled.
			case 10:
				int tableID = stream.readUnsignedByte();
				Order.kitchenRequestWaitStaff(tableID);
				break;
				
			// Waiter marks that they've delivered to the table.
			case 11:
				tableID = stream.readUnsignedByte();
				Order.waiterDroppedFoodOff(user, tableID);
				break;
				
			// Customer asks for refill
			case 12:
				kioskID = stream.readUnsignedByte();
				Requests.receiveRequest(kioskID, true);
				break;
				
			// Customer's request is granted.
			case 13:
				boolean refill = stream.readUnsignedByte() == 1;
				kioskID = stream.readUnsignedByte();
				Requests.completeRequest(kioskID, refill);
				break;
				
			// Revenue update from client
			case 14:
				double subtotal = Double.parseDouble(stream.readString());
				double tip = Double.parseDouble(stream.readString());
				Reports.totalRevenue += subtotal;
				Reports.totalTips += tip;
				Server.ui.infoPanel.updateLabels();
				break;

			// Server/waitstaff receives payment from customer
			case 15:
				tableID = stream.readUnsignedByte();
				double cashPayment = Double.parseDouble(stream.readString());
				Payments.processCashPayment(tableID, cashPayment);
				break;

			// Marks the customer's cash payment as complete
			case 16:
				tableID = stream.readUnsignedByte();
				Payments.completePayment(tableID);
				break;
				
			// Marks the customer's order as paid.
			case 17:
				tableID = stream.readUnsignedByte();
				Order.completeOrder(tableID);
				break;
				
			default:
				break;
			}

			stream.setOffset(startOffset + length);
		}
	}
	
	public User getUser() {
		return user;
	}
	
}
