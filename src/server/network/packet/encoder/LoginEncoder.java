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
	public void sendClientPacket(String code, Object... params) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(4);
		/////////
		stream.writeString(code);
		
		// write param length, or nothing
		if(params.length > 0) {
			stream.writeByte(params.length);
			for(int i = 0; i < params.length; i++) {
				if(params[i] instanceof Integer)
					stream.writeShort((int) params[i]);
				else if(params[i] instanceof String)
					stream.writeString((String) params[i]);
			}
		}
		
		////////
		stream.endPacketVarShort();
		session.write(stream);
	}

}
