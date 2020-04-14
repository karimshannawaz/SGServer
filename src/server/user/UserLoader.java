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
	private static final String BACKUP_PATH = "data/users/backups/";

	public synchronized static final boolean containsUser(String id) {
		return new File(PATH + id + ".sgr").exists();
	}

	public synchronized static final File getPlayer(String username) {
		return new File(PATH + username + ".sgr");
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
	
	public synchronized static User loadPlayer(String username) {
		try {
			return (User) loadSerializedFile(new File(PATH + username + ".sgr"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Logger.log("SerializableFilesManager", "Recovering account: "
					+ username);
			return (User) loadSerializedFile(new File(BACKUP_PATH + username
					+ ".sgr"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean createBackup(String username) {
		try {
			Constants.copyFile(new File(PATH + username + ".sgr"), new File(
					BACKUP_PATH + username + ".sgr"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public synchronized static void savePlayer(User player, boolean offline) {
		try {
			storeSerializableClass(player, new File(PATH + player.getEmail() + ".sgr"));
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
