package server.user;

import server.Global;
import server.Server;
import server.utils.JFrameUtils;

/**
 * A class that holds all of the requests.
 * 
 * @author Karimshan.
 *
 */
public class Requests {

	/**
	 * Receives help/refill request from client
	 * @param kioskID
	 * 
	 */
	public static void receiveRequest(int tableID, boolean refill) {
		boolean waitStaffOnline = false;
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("wait")) {
					waitStaffOnline = true;
				}
			}
		}
		if(!waitStaffOnline) {
			for(User u : Global.getUsers()) {
				if(u != null) {
					if(u.getSession().isCustomer()
							&& u.getTableID() == tableID) {
						u.getSession().sendClientPacket("waitstaff_received_request", refill ? "refill" : "help");
						Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, refill ? 1 : 2);
						Server.ui.tablesPanel.requiresRequest[tableID] = true;
						if(refill) {
							JFrameUtils.showMessage("Refill Update", "You have a new refill to take to table "+
								(tableID + 1)+".\nPlease tap on it when you are on your way to the table with the refill.");
						} else {
							JFrameUtils.showMessage("Help Update", "You have a new help request from table "+
								(tableID + 1)+".\nPlease tap on it when you are on your way to the table.");
						}
						break;
					}
				}
			}
			return;
		}
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getSession().isCustomer()
						&& u.getTableID() == tableID) {
					u.getSession().sendClientPacket("waitstaff_received_request", refill ? "refill" : "help");
					Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, refill ? 1 : 2);
					Server.ui.tablesPanel.requiresRequest[tableID] = false;
					continue;
				}
				if(u.getRole().toLowerCase().contains("wait")) {
					u.getPacketEncoder().sendRequest(tableID, refill);
				}
			}
		}
	}

	/**
	 * Marks this request as complete and lets the customer know that waiter
	 * is on the way with help or refill.
	 * @param tableID
	 * @param refill
	 */
	public static void completeRequest(int tableID, boolean refill) {
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getSession().isCustomer()
						&& u.getTableID() == tableID) {
					Server.ui.tablesPanel.table.getModel().setValueAt("X", tableID, refill ? 1 : 2);
					Server.ui.tablesPanel.requiresRequest[tableID] = false;
					u.getSession().sendClientPacket("waitstaff_on_way_with_request", refill ? "refill" : "help");
					break;
				}
			}
		}
	}
	
	

}
