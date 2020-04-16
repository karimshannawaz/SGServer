package server.menu;

import java.util.ArrayList;
import java.util.List;

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
}
