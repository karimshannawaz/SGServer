package server;

import server.user.User;
import server.user.UserList;

/**
 * Holds global variables to be used across the whole restaurant system.
 * 
 * @author Karimshan
 *
 */
public class Global {
	
	private static final UserList<User> users = new UserList<User>(2000);

	// Holds the list of tables in the restaurant (kiosk ids corresponding
	// to the array's index). Each time a customer connects, they are assigned
	// a random table.
	public static byte[] tableIds;

	static {
		tableIds = new byte[20];
		for (int i = 0; i < tableIds.length; i++) {
			tableIds[i] = 0;
		}
	}
	
	public static final UserList<User> getUsers() {
		return users;
	}
	
	public static final void addUser(User user) {
		users.add(user);
	}

	public static void removeUser(User user) {
		users.remove(user);
	}
	
	public static User getUser(String id, boolean staff) {
		for (User user : users) {
			if (user == null)
				continue;
			if(staff) {
				if(user.getId().equals(id)) {
					return user;
				}
			}
			else {
				if(user.getEmail().equals(id)) {
					return user;
				}
			}
		}
		return null;
	}
	
}
