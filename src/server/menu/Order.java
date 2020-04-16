package server.menu;

import java.util.ArrayList;
import java.util.List;

import server.network.packet.InputStream;

/**
 * Holds information for a customer's order.
 * SERVER SIDED
 * 
 * @author Karimshan
 *
 */
public class Order {
	
	public double subtotal;
	
	public List<MItem> items = new ArrayList<MItem>();
	
	public void addItem(String name, double price, int qty, 
			String specialRequests, String ingredients) {
		MItem item = new MItem();
		item.name = name;
		item.price = price;
		item.qty = qty;
		item.specialReqs = specialRequests;
		item.ingredients = ingredients;
		items.add(item);
	}
	
	public void clear() {
		items.clear();
	}

	public static void receiveOrder(InputStream stream) {
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
			order.addItem(mItemName, price, qty, specReq, ing);
		}
		order.subtotal = subtotal;
		OrderQueue.orders.put(tableID, order);
		System.out.println("Took order from table: "+tableID+" with "+order.items.get(0).name);
	}
}
