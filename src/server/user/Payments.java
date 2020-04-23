package server.user;

import server.Global;
import server.Server;
import server.utils.Constants;
import server.utils.JFrameUtils;

/**
 * Holds information about payments made.
 * 
 * @author Karimshan
 *
 */
public class Payments {
	
	/**
	 * Processes a cash payment from the customer
	 * @param tableID
	 * @param total
	 */
	public static void processCashPayment(int tableID, double total) {
		boolean waitStaffOnline = false;
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("wait")) {
					waitStaffOnline = true;
				}
			}
		}
		if(!waitStaffOnline) {
			Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, 4);
			Server.ui.tablesPanel.requiresPayment[tableID] = true;
			JFrameUtils.showMessage("Payments", "Table "+(tableID + 1)+" has requested to make a cash payment of "
				+ Constants.decimalF(total)+"\nPlease mark the payment as complete once you've received it.");
		}
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("wait") && waitStaffOnline) {
					Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, 4);
					Server.ui.tablesPanel.requiresPayment[tableID] = false;
					u.getSession().sendClientPacket("cash_payment", tableID, ""+total);
				}
			}
		}
	}

	/**
	 * Marks this payment as complete.
	 * @param tableID
	 * @param refill
	 */
	public static void completePayment(int tableID) {
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getSession().isCustomer()
						&& u.getTableID() == tableID) {
					Server.ui.tablesPanel.table.getModel().setValueAt("X", tableID, 4);
					Server.ui.tablesPanel.requiresPayment[tableID] = false;
					u.getSession().sendClientPacket("cash_payment_confirmed");
					break;
				}
			}
		}
	}

}
