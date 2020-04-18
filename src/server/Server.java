package server;

import server.core.CoresManager;
import server.menu.Inventory;
import server.menu.Menu;
import server.network.ServerChannel;
import server.ui.MainUI;
import server.user.TimeLog;
import server.utils.Constants;
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
			
			/*
			TimeLog log = new TimeLog();
			for(int i = 0; i < 7; i++)  {
				log.getPunchIns().add("12:53:39");
				log.getPunchOuts().add("17:09:04");
				log.getPunchIns().add("12:53:39");
				log.getPunchOuts().add("17:09:04");
			}
			System.out.println(log.getTotalWorkedHours());
			*/
			
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
	 * Shuts down the server channel and cores manager.
	 */
	public static void shutdown() {
		try {
			ServerChannel.shutdown();
			CoresManager.shutdown();
			// Server stuff
		} finally {
			System.exit(0);
		}
	}

}
