package server.network.packet.decoder;

import server.menu.Order;
import server.menu.OrderQueue;
import server.network.Session;
import server.network.packet.InputStream;
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
				Order.kitchenRequestWaitStaff(user, stream);
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
