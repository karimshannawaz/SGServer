package server.network;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import server.Global;
import server.Reports;
import server.Server;
import server.network.packet.OutputStream;
import server.network.packet.decoder.ClientLaunchDecoder;
import server.network.packet.decoder.Decoder;
import server.network.packet.decoder.LoginDecoder;
import server.network.packet.decoder.PacketDecoder;
import server.network.packet.encoder.ClientLaunchEncoder;
import server.network.packet.encoder.Encoder;
import server.network.packet.encoder.LoginEncoder;
import server.network.packet.encoder.PacketEncoder;
import server.user.User;
import server.user.UserLoader;

public class Session {

	private Channel channel;
	private Decoder decoder;
	private Encoder encoder;
	private int tableID = -1;
	private boolean isCustomer;
	private String userEmail;

	public Session(Channel channel) {
		this.channel = channel;
		setDecoder(0);
	}

	public final ChannelFuture write(OutputStream outStream) {
		if (channel.isConnected()) {
			ChannelBuffer buffer = ChannelBuffers.copiedBuffer(outStream.getBuffer(), 0, outStream.getOffset());
			synchronized (channel) {
				return channel.write(buffer);
			}
		}
		return null;
	}

	public final ChannelFuture write(ChannelBuffer outStream) {
		if (outStream == null)
			return null;
		if (channel.isConnected()) {
			synchronized (channel) {
				return channel.write(outStream);
			}
		}
		return null;
	}

	public final Channel getChannel() {
		return channel;
	}
	
	public LoginEncoder getLoginPackets() {
		return (LoginEncoder) encoder;
	}
	
	public PacketDecoder getGlobalPackets() {
		return (PacketDecoder) decoder;
	}
	
	public LoginDecoder getLoginDecoder() {
		return (LoginDecoder) decoder;
	}

	public final Decoder getDecoder() {
		return decoder;
	}

	public final Encoder getEncoder() {
		return encoder;
	}

	public final void setDecoder(int stage) {
		setDecoder(stage, null);
	}

	public final void setDecoder(int stage, Object attachment) {
		switch (stage) {
		case 0:
			decoder = new ClientLaunchDecoder(this);
			break;
		case 1:
			decoder = new LoginDecoder(this);
			break;
		case 2:
			decoder = new PacketDecoder(this, (User) attachment);
			break;
		case -1:
		default:
			decoder = null;
			break;
		}
	}

	public final void setEncoder(int stage) {
		setEncoder(stage, null);
	}

	public final void setEncoder(int stage, Object attachement) {
		switch (stage) {
		case 0:
			encoder = new ClientLaunchEncoder(this);
			break;
		case 1:
			encoder = new LoginEncoder(this);
			break;
		case 2:
			encoder = new PacketEncoder(this, (User) attachement);
			break;
		case -1:
		default:
			encoder = null;
			break;
		}
	}
	
	public void loginToRewards(String email) {
		loginToRewards(email, null, null);
	}
	
	public void loginToRewards(String email, String birthday, String name) {
		User user;
		if (!UserLoader.containsUser(email)) {
			user = new User("customer", email, birthday, name);
			Reports.newRewardMembers++;
			Reports.totalNewRewardMembers++;
			Server.ui.infoPanel.updateLabels();
		} else {
			user = UserLoader.loadUser(email);
			if (user == null) {
				sendClientPacket("nulled_account");
				return;
			}
			if (!UserLoader.createBackup(email)) {
				sendClientPacket("nulled_account");
				return;
			}
		}
		
		for(User u : Global.getUsers()) {
			if(u == null)
				continue;
			if(u.isCustomer() && u.getEmail().equals("table"+this.getTableID())) {
				u.setRole(user.getRole());
				u.setName(user.getName());
				u.setEmail(user.getEmail());
				u.setBirthday(user.getBirthday());
				u.setFreeSide(user.hasFreeSide());
				u.setFreeDessert(user.hasFreeDessert());
				u.setBirthdayEntree(user.hasBirthdayEntree());
				
				if(u.getName() != null && u.getBirthday() != null)
					u.checkBirthday();
				setDecoder(2, u);
				setEncoder(2, u);
				
				u.getPacketEncoder().sendDetailsUpdate(false);
				
				System.out.println("Customer "+u.getName()+" logged in with rewards. Email: "+u.getEmail());
				UserLoader.saveUser(u);
				break;
			}
		}
	}
	
	public void employeeLogin(String id, String password) {
		User employee;
		employee = UserLoader.loadUser(id, true);
		
		if (employee == null) {
			sendClientPacket("nulled_account");
			return;
		}
		
		if (!UserLoader.createBackup(id, true)) {
			sendClientPacket("nulled_account");
			return;
		}
		
		if(!password.equals(employee.getPassword())) {
			sendClientPacket("incorrect_password");
			return;
		}
		
		employee.initialize(this);
		setDecoder(2, employee);
		setEncoder(2, employee);
		
		employee.getPacketEncoder().sendDetailsUpdate(true);
		
		Global.addUser(employee);
		System.out.println("Employee account logged in but has not clocked in yet: "+
			employee.getId()+" with password: "+employee.getPassword());
		
		UserLoader.saveUser(employee, true);
	}

	public String getIP() {
		return channel == null ? "" : channel.getRemoteAddress().toString().split(":")[0].replace("/", "");

	}

	public String getLocalAddress() {
		return channel.getLocalAddress().toString();
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int i) {
		this.tableID = i;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
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
		write(stream);
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void sendPopularItems(int size) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(9);
		stream.writeByte(size);
		for(int i = 0; i < size; i++) {
			stream.writeString(Reports.popularItemsByType.get(i));
		}
		stream.endPacketVarShort();
		write(stream);
	}

}
