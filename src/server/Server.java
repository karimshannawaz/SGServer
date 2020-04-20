package server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import server.core.CoresManager;
import server.menu.Inventory;
import server.menu.Menu;
import server.network.ServerChannel;
import server.ui.MainUI;
import server.user.User;
import server.utils.Constants;
import server.utils.JFrameUtils;
import server.utils.Logger;
import server.utils.STime;

public class Server {

	// This represents the main server user interface
	// used by the managers.
	public static MainUI ui;

	public static void main(String[] args) {
		long currentTime = STime.getCurrent();
		Logger.log("Server", "Starting up " + Constants.NAME + "...");
		try {
			CoresManager.init();
			ServerChannel.openChannel();
			// Loads the inventory for this day
			Inventory.loadInventory();
			Logger.log("Server", "Loaded the inventory (" + Inventory.instance.size() + " different items)");
			// Loads the menu
			Menu.loadMenu();
			Logger.log("Server", "Loaded the menu.");
			Logger.log("Server", "Launching User Interface for Managers");
			ui = new MainUI();
			ui.setVisible(true);
			
		} catch (Exception e) {
			Logger.log("Server", "Failed to load the channel which accepts client requests. See details.");
			e.printStackTrace();
			System.exit(1);
			return;
		}
		Logger.log("Server",
				Constants.NAME + " launched in " + ((double) (STime.getCurrent() - currentTime) / 1000) + " seconds.");
	}

	/**
	 * Safely shuts down the server channel and cores manager,
	 * and generates a report.
	 */
	public static void shutdown() {
		boolean choice = JFrameUtils.confirmDialog("WARNING", 
				"Are you sure you want to shut down the seven guys restaurant? This will close every client too."
					+ "\nThis action cannot be undone.");
			if(!choice) {
				return;
			}
		try {
			Reports.generateReport(true);
			for(User u : Global.getUsers()) {
				if(u != null) {
					u.getSession().sendClientPacket("terminate");
				}
			}
			System.out.println("Safely exited all users and generated a report.");
			ServerChannel.shutdown();
			CoresManager.shutdown();
			// Server stuff
		} finally {
			System.exit(0);
		}
	}

}
