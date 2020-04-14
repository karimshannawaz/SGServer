package server.network.packet.encoder;

import server.menu.Menu;
import server.network.Session;
import server.network.packet.OutputStream;

public class LoginEncoder extends Encoder {

	public LoginEncoder(Session session) {
		super(session);
	}

	public void assignKioskID(int kioskID) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(2);
		/////////
		stream.writeByte(kioskID);
		////////
		stream.endPacketVarShort();
		session.write(stream);
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
	 * Sends the specified client packet to the client
	 * to indicate successful/unsucessful login attempts
	 * @param string
	 */
	public void sendClientPacket(String code) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(4);
		/////////
		stream.writeString(code);
		////////
		stream.endPacketVarShort();
		session.write(stream);
	}

}
