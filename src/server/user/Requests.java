package server.user;

import server.Global;

/**
 * A class that holds all of the requests.
 * 
 * @author Karimshan.
 *
 */
public class Requests {

	/**
	 * Receives help request from client
	 * @param kioskID
	 * 
	 */
	public static void receiveHelpRequest(int tableID) {
		System.out.println("received help request from table "+(tableID + 1));
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
						u.getSession().sendClientPacket("waitstaff_not_available_for_request", "help");
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
					u.getSession().sendClientPacket("waitstaff_received_request", "help");
					continue;
				}
				if(u.getRole().toLowerCase().contains("wait")) {
					u.getPacketEncoder().sendRequest(tableID, false);
				}
			}
		}
	}

	/**
	 * Receives refill request from client
	 * @param kioskID
	 */
	public static void receiveRefillRequest(int tableID) {
		System.out.println("received refill request from table "+(tableID + 1));
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
						u.getSession().sendClientPacket("waitstaff_not_available_for_request", "refill");
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
					u.getSession().sendClientPacket("waitstaff_received_request", "refill");
					continue;
				}
				if(u.getRole().toLowerCase().contains("wait")) {
					u.getPacketEncoder().sendRequest(tableID, true);
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
					u.getSession().sendClientPacket("waitstaff_on_way_with_request", refill ? "refill" : "help");
					break;
				}
			}
		}
	}
	
	

}
