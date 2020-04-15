package server.user;

import java.io.Serializable;

import server.network.Session;
import server.network.packet.decoder.PacketDecoder;
import server.network.packet.encoder.PacketEncoder;

/**
 * 
 * @author Karimshan
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -2479666052959496651L;
	
	private String name;
	
	// For customers (Birthday: MM/DD/YYYY)
	private String email;
	private String birthday;
	private int visits;
	
	// Indicates if the customer has a free birthday entree or not.
	private boolean birthdayEntree;
	
	// Free appetizer/side when the customer signs up for rewards.
	private boolean freeSide;
	
	// Free lottery dessert
	private boolean freeDessert;
	
	// For employees only
	private int id;
	private String password;
	
	private String type;
	
	// Session is transient because it changes every
	// time that the person logs in/out
	private transient Session session;

	public User(String type, String email, String birthday, String name) {
		this.setType(type);
		this.setEmail(email);
		this.setBirthday(birthday);
		this.setName(name);
		this.setVisits(0);
		this.setFreeSide(true);
		this.setFreeDessert(false);
	}
	
	public void initialize(Session s) {
		this.setSession(s);
		this.checkBirthday();
	}

	private void checkBirthday() {
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCustomer() {
		return session.isCustomer();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getVisits() {
		return visits;
	}

	public void setVisits(int visits) {
		this.visits = visits;
	}

	public boolean hasBirthdayEntree() {
		return birthdayEntree;
	}

	public void setBirthdayEntree(boolean birthdayEntree) {
		this.birthdayEntree = birthdayEntree;
	}

	public boolean hasFreeSide() {
		return freeSide;
	}

	public void setFreeSide(boolean freeSide) {
		this.freeSide = freeSide;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PacketDecoder getPacketDecoder() {
		return (PacketDecoder) session.getDecoder();
	}
	
	public PacketEncoder getPacketEncoder() {
		return (PacketEncoder) session.getEncoder();
	}

	public boolean hasFreeDessert() {
		return freeDessert;
	}

	public void setFreeDessert(boolean freeDessert) {
		this.freeDessert = freeDessert;
	}

}
