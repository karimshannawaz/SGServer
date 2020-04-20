package server.user;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import server.Global;
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
	
	private transient int index;
	
	private String name;
	
	// For customers (Birthday: MM/DD/YYYY)
	private String email;
	private String birthday;
	private int visits;
	
	// Indicates if the customer has a free birthday entree or not.
	private boolean birthdayEntree;
	private int claimedYear;
	
	// Free appetizer/side when the customer signs up for rewards.
	private boolean freeSide;
	
	// Free lottery dessert
	private boolean freeDessert;
	
	// For employees only
	private String id;
	private String password;
	private String role;
	private transient boolean available;
	
	// Session is transient because it changes every
	// time that the person logs in/out
	private transient Session session;

	public User(String role, String email, String birthday, String name) {
		this.setRole(role);
		this.setEmail(email);
		this.setBirthday(birthday);
		this.setName(name);
		this.setVisits(0);
		this.setFreeSide(true);
		this.setFreeDessert(false);
	}
	
	public User() {
		
	}
	
	public void createEmployee(String id, String name, String role, String password) {
		this.setId(id);
		this.setRole(role);
		this.setName(name);
		this.setPassword(password);
	}
	
	public void initialize(Session s) {
		this.setSession(s);
		if(name != null && birthday != null)
			this.checkBirthday();
		if(isEmployee()) {
			setAvailable(true);
		}
	}

	private boolean isEmployee() {
		return !role.equals("customer");
	}

	private void checkBirthday() {
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if(claimedYear == year) {
			this.setBirthdayEntree(false);
		}
		else {
			String[] date = this.getBirthday().split("/");
			int birthMonth = Integer.parseInt(date[0]);
			int birthDay = Integer.parseInt(date[1]);
			System.out.println(month+" and "+day+" bday: "+birthMonth+" and "+birthDay);
			if(birthMonth == month && birthDay == day) {
				this.setBirthdayEntree(true);
			}
		}
	}

	public String getRole() {
		return role;
	}

	public void setRole(String type) {
		this.role = type;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void close() {
		System.out.println((role.equals("customer") ? email : id)+" has logged out of the client.");
		Global.removeUser(this);
	}

	public int getTableID() {
		return session.getTableID();
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getClaimedYear() {
		return claimedYear;
	}

	public void setClaimedYear(int claimedYear) {
		this.claimedYear = claimedYear;
	}

}
