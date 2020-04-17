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
	}

	/**
	 * Receives refill request from client
	 * @param kioskID
	 */
	public static void receiveRefillRequest(int tableID) {
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
	}
	
	

}
