package server.network.packet.encoder;

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
	
	/**
	 * Sends the client an updated version of the user's current
	 * details loaded server sided.
	 */
	public void sendDetailsUpdate() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(5);
		stream.writeString(user.getEmail());
		stream.writeString(user.getBirthday());
		stream.writeString(user.getName());
		stream.writeShort(user.getVisits());
		stream.writeByte(user.hasFreeSide() ? 1 : 0);
		stream.writeByte(user.hasBirthdayEntree() ? 1 : 0);
		stream.writeByte(user.hasFreeDessert() ? 1 : 0);
		stream.endPacketVarShort();
		session.write(stream);
	}

}