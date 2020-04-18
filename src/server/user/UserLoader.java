package server.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import server.utils.Constants;
import server.utils.Logger;


/**
 * Loads the user credentials if they make a rewards account
 * 
 * @author Karimshan
 *
 */
public class UserLoader {

	private static final String PATH = "data/users/current/";
	private static final String EMPLOYEES_PATH = "data/users/employees/";
	private static final String BACKUP_PATH = "data/users/backups/";
	
	public static List<User> employees = new ArrayList<User>();
	
	public synchronized static final boolean containsUser(String id) {
		return containsUser(id, false);
	}

	public synchronized static final boolean containsUser(String id, boolean employee) {
		return new File((employee ? EMPLOYEES_PATH : PATH) + id + ".sgr").exists();
	}
	
	public synchronized static final File getUser(String username) {
		return new File(PATH + username + ".sgr");
	}
	
	public synchronized static List<User> getAllEmployees() {
		employees.clear();
		try {
			for(File file : new File(EMPLOYEES_PATH).listFiles()) {
				if(file != null && file.getAbsolutePath().endsWith(".sgr")) {
					User e = (User) loadSerializedFile(file.getAbsoluteFile());
					if(e != null) {
						employees.add(e);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	public synchronized static List<User> getSavedPlayers() {
		List<User> players = new ArrayList<User>();
		try {
			for(File file : new File(PATH).listFiles()) {
				if(file != null && file.getAbsolutePath().endsWith(".sgr")) {
					User player = (User) loadSerializedFile(file.getAbsoluteFile());
					if(player != null) {
						players.add(player);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return players;
	}
	
	public synchronized static User loadUser(String username) {
		return loadUser(username, false);
	}
	
	public synchronized static User loadUser(String username, boolean employee) {
		try {
			return (User) loadSerializedFile(new File((employee ? EMPLOYEES_PATH : PATH) + username + ".sgr"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Logger.log("Serializable File", "Recovering account: "
					+ username);
			return (User) loadSerializedFile(new File(BACKUP_PATH + username
					+ ".sgr"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean createBackup(String username) {
		return createBackup(username, false);
	}

	public static boolean createBackup(String username, boolean employee) {
		try {
			Constants.copyFile(new File((employee ? EMPLOYEES_PATH : PATH) + username + ".sgr"), new File(
					BACKUP_PATH + username + ".sgr"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public synchronized static void saveUser(User player, boolean employee) {
		try {
			storeSerializableClass(player, new File((employee ? EMPLOYEES_PATH : PATH) + 
					(employee ? player.getId() : player.getEmail()) + ".sgr"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized static void saveUser(User player) {
		saveUser(player, false);
	}

	public static final Object loadSerializedFile(File f) throws IOException,
			ClassNotFoundException {
		if (!f.exists())
			return null;
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
		Object object;
		try {
			object = in.readObject();
		} catch(Exception e) {
			object = null;
			e.printStackTrace();
		}
		in.close();
		return object;
	}

	public static final void storeSerializableClass(Serializable o, File f) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(o);
		out.close();
	}

	private UserLoader() {

	}

	public static boolean deleteUser(String id, boolean employee) {
		if(!containsUser(id, employee)) {
			return false;
		}
		if(new File((employee ? EMPLOYEES_PATH : PATH) + id + ".sgr").delete()) {
			return true;
		}
		return false;
	}

}
