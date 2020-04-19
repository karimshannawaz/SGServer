package server.network.packet.encoder;

import server.menu.Menu;
import server.menu.Order;
import server.network.Session;
import server.network.packet.OutputStream;
import server.user.User;

/**
 * Sends packets to the client.
 * SERVER SIDED.
 * 
 * @author Karimshan
 *
 */
public class PacketEncoder extends Encoder {

	private User user;

	public PacketEncoder(Session session, User user) {
		super(session);
		this.user = user;
	}
	
	public void sendMenu() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(3);
		/////////
		int size = Menu.instance.size();
		stream.writeByte(size);
		for (int i = 0; i < size; i++) {
			stream.writeString(Menu.instance.get(i).toString());
		}
		////////
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	/**
	 * Sends the client an updated version of the user's current
	 * details loaded server sided.
	 */
	public void sendDetailsUpdate(boolean employee) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(5);
		stream.writeByte(employee ? 1 : 0);
		if(!employee) {
			stream.writeString(user.getEmail());
			stream.writeString(user.getBirthday());
			stream.writeString(user.getName());
			stream.writeShort(user.getVisits());
			stream.writeByte(user.hasFreeSide() ? 1 : 0);
			stream.writeByte(user.hasBirthdayEntree() ? 1 : 0);
			stream.writeByte(user.hasFreeDessert() ? 1 : 0);
		}
		else { 
			stream.writeString(user.getId());
			stream.writeString(user.getName());
			stream.writeString(user.getRole());
			stream.writeString(user.getPassword());
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * Sends customer order to any kitchen staff that's online.
	 * @param tableID
	 * @param order
	 */
	public void sendOrder(int tableID, Order order) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(6);
		stream.writeByte(tableID);
		stream.writeString(""+order.subtotal);
		stream.writeByte(order.items.size());
		for(int i = 0; i < order.items.size(); i++) {
			stream.writeString(order.items.get(i).asOrder());
		}
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * Sends customer order to waitstaff
	 * @param name
	 * @param tableID
	 * @param orderIndex
	 */
	public void sendOrder(int tableID) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(7);
		stream.writeByte(tableID);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * Sends the waiter the table ID of who has a request.
	 * @param tableID
	 */
	public void sendRequest(int tableID, boolean refill) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(8);
		stream.writeByte(refill ? 1 : 0);
		stream.writeByte(tableID);
		stream.endPacketVarShort();
		session.write(stream);
	}


}