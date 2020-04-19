package server.menu;

import java.util.ArrayList;
import java.util.List;

import server.Global;
import server.Reports;
import server.Server;
import server.network.Session;
import server.network.packet.InputStream;
import server.user.User;

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
		boolean waitStaffOnline = false;
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("kitchen")) {
					kitchenStaffOnline = true;
				}
				else if(u.getRole().toLowerCase().contains("wait")) {
					waitStaffOnline = true;
				}
			}
		}
		if(!kitchenStaffOnline) {// || !waitStaffOnline) {
			user.getSession().sendClientPacket("cannot_process_order");
			return;
		}
		int tableID = stream.readUnsignedByte();
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
		System.out.println("Took order from table: "+tableID+" with subtotal: "+subtotal+". Sending to kitchen staff...");
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("kitchen")) {
					u.getPacketEncoder().sendOrder(tableID, order);
				}
			}
		}
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getSession().isCustomer()
						&& u.getTableID() == tableID) {
					u.getSession().sendClientPacket("order_submitted");
					break;
				}
				
			}
		}
		
	}
	
	public static void kitchenRequestWaitStaff(User user, InputStream stream) {
		boolean waitStaffOnline = false;
		boolean availableWaitStaff = false;
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.isAvailable()) {
					availableWaitStaff = true;
				}
				if(u.getRole().toLowerCase().contains("wait")) {
					waitStaffOnline = true;
				}
			}
		}
		if(!waitStaffOnline || !availableWaitStaff) {
			user.getSession().sendClientPacket("no_waitstaff_available");
			return;
		}
		
		int tableID = stream.readUnsignedByte();
		
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
			Reports.mostPopularMI.put(name, Reports.mostPopularMI.get(name) + item.qty);
		}
		
		Reports.updateMostPopularMI();
		
		OrderQueue.unpaidOrders.add(currOrder);
		OrderQueue.unfulfilledOrders.remove(orderIndex);
		
		String waitStaffName = null;
		
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("wait")) {
					waitStaffName = u.getName();
					u.getPacketEncoder().sendOrder(tableID);
					Server.ui.tablesPanel.table.getModel().setValueAt("O", tableID, 3);
					System.out.println("Kitchen handed off table "+
						(tableID + 1)+"'s order (index: "+orderIndex+") to waitstaff: "+u.getId()+" - "+u.getName());
				}
			}
		}
		
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getSession().isCustomer()
					&& u.getTableID() == tableID) {
					u.getSession().sendClientPacket("on_the_way", 
						waitStaffName+" is headed your way with your order!");
					break;
				}
			}
		}
		
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getRole().toLowerCase().contains("kitchen")) {
					System.out.println("Waitstaff got order from kitchen end: "+tableID+" and order index: "+orderIndex);
					u.getSession().sendClientPacket("waitstaff_got_order", tableID);
				}
			}
		}
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public static void waiterDroppedFoodOff(User user, InputStream stream) {
		int tableID = stream.readUnsignedByte();
		user.setAvailable(true);
		
		for(User u : Global.getUsers()) {
			if(u != null) {
				if(u.getSession().isCustomer()
					&& u.getTableID() == tableID) {
					Server.ui.tablesPanel.table.getModel().setValueAt("X", tableID, 3);
					u.getSession().sendClientPacket("waiter_delivered");
					break;
				}
			}
		}
	}
}
