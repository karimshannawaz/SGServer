package server.user;

import java.io.Serializable;

import server.network.Session;

public class User implements Serializable {

	private static final long serialVersionUID = -2479666052959496651L;
	
	private String email;
	private String password;
	
	private int type;
	private transient Session session;

	public User(int type, Session session) {
		this.setType(type);
		this.setSession(session);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isManager() {
		return type == 0;
	}

	public boolean isKitchen() {
		return type == 1;
	}

	public boolean isWaiter() {
		return type == 2;
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

}
