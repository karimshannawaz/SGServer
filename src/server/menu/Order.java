package server.menu;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import server.Global;
import server.Reports;
import server.Server;
import server.network.packet.InputStream;
import server.user.User;
import server.utils.JFrameUtils;

/**
 * Holds information for a customer's order.
 * SERVER SIDED
 * 
 * @author Karimshan
 *
 */
public class Order {
	
	private int tableID;
	
	public double subtotal;
	
	public List<MItem> items = new ArrayList<MItem>();
	
	public MItem addItem(String name, double price, int qty, 
			String specialRequests, String ingredients) {
		MItem item = new MItem();
		item.name = name;
		item.price = price;
		item.qty = qty;
		item.specialReqs = specialRequests;
		item.ingredients = ingredients;
		items.add(item);
		return item;
	}
	
	public void clear() {
		items.clear();
	}

	public static void receiveOrder(User user, InputStream stream) {
		
		boolean kitchenStaffOnline = false;
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("kitchen")) {
					kitchenStaffOnline = true;
				}
			}
		}
		
		int tableID = stream.readUnsignedByte();
		
		boolean managerAcceptsOrder = false;
		
		if(!kitchenStaffOnline)
			managerAcceptsOrder = true;
		
		if(!kitchenStaffOnline && !managerAcceptsOrder) {
			user.getSession().sendClientPacket("cannot_process_order");
			return;
		}
		
		double subtotal = Double.parseDouble(stream.readString());
		int orderSize = stream.readUnsignedByte();
		Order order = new Order();
		for(int i = 0; i < orderSize; i++) {
			String mItem = stream.readString();
			String[] tok = mItem.split("~");
			String mItemName = tok[0];
			double price = Double.parseDouble(tok[1]);
			int qty = Integer.parseInt(tok[2]);
			String specReq = tok[3];
			String ing = tok[4];
			MItem item = order.addItem(mItemName, price, qty, specReq, ing);
			if(!Inventory.updateInventory(item)) {
				user.getSession().sendClientPacket("out_of_stock", mItemName, i);
				return;
			}
		}
		order.subtotal = subtotal;
		order.setTableID(tableID);

		OrderQueue.unfulfilledOrders.add(order);
		System.out.println("Took order from table: "+tableID+" with subtotal: "+subtotal);
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getTableID() == tableID) {
					u.getSession().sendClientPacket("order_submitted");
					continue;
				}
				if(u.getRole().toLowerCase().contains("kitchen")
					&& kitchenStaffOnline) {
					u.getPacketEncoder().sendOrder(tableID, order);
				}
			}
		}
		
		if(managerAcceptsOrder && !kitchenStaffOnline) {
			Server.ui.kitchenPanel.addToTable(tableID);
			JFrameUtils.showMessage("Order Update", "You have a new order to fulfill for table: "+(tableID + 1));
		}
		
	}
	
	public static void kitchenRequestWaitStaff(int tableID) {
		boolean waitStaffOnline = false;
		boolean kitchenStaffOnline = false;
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("wait")) {
					waitStaffOnline = true;
				}
				if(u.getRole().toLowerCase().contains("kitchen")) {
					kitchenStaffOnline = true;
				}
			}
		}
		
		Order currOrder = null;
		int orderIndex = 0;
		for(Order o : OrderQueue.unfulfilledOrders) {
			if(o.getTableID() == tableID) {
				currOrder = o;
				break;
			}
			orderIndex++;
		}
		
		for(MItem item : currOrder.items) {
			String name = item.name;
			int quantity = 0;
			if(Reports.mostPopularMI.containsKey(name)) {
				quantity = Reports.mostPopularMI.get(name);
			}
			Reports.mostPopularMI.put(name, quantity + item.qty);
		}
		
		Reports.updateMostPopularMI();
		
		OrderQueue.unpaidOrders.add(currOrder);
		OrderQueue.unfulfilledOrders.remove(orderIndex);
		
		String waitStaffName = null;
		
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("kitchen")) {
					u.getSession().sendClientPacket("waitstaff_got_order", tableID);
					continue;
				}
				if(u.getRole().toLowerCase().contains("wait") && waitStaffOnline) {
					waitStaffName = u.getName();
					u.getPacketEncoder().sendOrder(tableID);
					Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, 3);
					Server.ui.tablesPanel.requiresOrder[tableID] = false;
				}
				if(u.getSession().isCustomer()
						&& u.getTableID() == tableID) {
					u.getSession().sendClientPacket("on_the_way", (waitStaffOnline ? waitStaffName : "A manager") +
						 " is headed your way with your order!");
					continue;
				}
			}
		}
		
		/**
		 * Manager will take food if wait staff can't.
		 */
		if(!waitStaffOnline) {
			if(!kitchenStaffOnline) {
				DefaultTableModel tab = (DefaultTableModel) 
					Server.ui.kitchenPanel.table.getModel();
				int row = 0;
				for(int i = 0; i < tab.getRowCount(); i++) {
					if(((int) tab.getValueAt(row, 0) - 1) == tableID)
						break;
					row++;
				}
				tab.removeRow(row);
			}
			Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, 3);
			Server.ui.tablesPanel.requiresOrder[tableID] = true;
			JFrameUtils.showMessage("Order Update", "You have a new order to take to table "+
			(tableID + 1)+".\nPlease mark it as delivered to the table once you've delivered it.");
		}
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public static void waiterDroppedFoodOff(User user, int tableID) {
		if(user != null)
			user.setAvailable(true);
		for(User u : Global.getUsers()) {
			if(u != null) {
				System.out.println(u.getTableID()+" and from client "+tableID);
				if(u.getTableID() == tableID) {
					Server.ui.tablesPanel.table.getModel().setValueAt("X", tableID, 3);
					Server.ui.tablesPanel.requiresOrder[tableID] = false;
					u.getSession().sendClientPacket("waiter_delivered");
					break;
				}
			}
		}
	}
}
