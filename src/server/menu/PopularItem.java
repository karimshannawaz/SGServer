package server.menu;

import java.util.HashMap;
import java.util.Map;

import server.Server;

/**
 * Represents a list of most popular menu item per category
 * 
 * @author Karimshan
 *
 */
public class PopularItem {
	
	public Map<String, Integer> items = new HashMap<String, Integer>();
	
	private String menuType;
	
	public PopularItem(String menuType) {
		this.menuType = menuType;
	}
	
	public String getMenuType() {
		return menuType;
	}
	
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	/**
	 * Updates the most popular menu item for this
	 * popular item.
	 */
	public String getMostPopularMI() {
		int mostQty = 0;
		String mostPopular = "";
		for(Map.Entry<String, Integer> entry : items.entrySet()) {
			String name = entry.getKey();
			int qty = entry.getValue();
			if(qty > mostQty) {
				mostQty = qty;
				mostPopular = ""+name;
			}
		}
		return ""+mostPopular;
	}

}
