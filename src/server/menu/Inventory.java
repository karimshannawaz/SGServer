package server.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import server.Server;
import server.utils.Constants;

/**
 * 
 * @author Karimshan
 *
 */
public class Inventory {

	// If the inventory is being randomized, then the numbers
	// will be randomly chosen for each inventory item
	// depending on the inventory item's type (entree, topping, side, condiment,
	// dessert, drink)
	private static boolean randomize = true;

	// Represents the instance of the inventory for any particular day
	public static Map<String, Integer> instance = new HashMap<String, Integer>();

	/**
	 * This is done whenever the program first loads to avoid constantly loading and
	 * reading from the file.
	 */
	public static void loadInventory() {
		instance.clear();
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File("./data/inventory.txt")));
			String line;
			while ((line = r.readLine()) != null) {
				if (line.startsWith("//") || line.equals(""))
					continue;
				String[] tokens = line.split(" - ");
				String name = tokens[0];
				String type = tokens[1];
				int quantity = Integer.parseInt(tokens[2]);
				// Randomizes the quantity of the ingredient if randomize is true.
				// This will be enabled in the final version of the system.
				if (randomize) {
					if(type.contains("side")) {
						quantity = Constants.generateNumber(200, 4000);
					}
					else if(type.contains("entree")) {
						quantity = Constants.generateNumber(300, 1000);
					}
					else if(type.contains("drink")) {
						quantity = Constants.generateNumber(500, 3000);
					}
					else if(type.contains("condiment")) {
						quantity = Constants.generateNumber(1000, 4000);
					}
					else if(type.contains("topping")) {
						quantity = Constants.generateNumber(1000, 4000);
					}
					else if(type.contains("dessert")) {
						quantity = Constants.generateNumber(400, 1000);
					}
				}
				instance.put(name, quantity);
			}
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean updateInventory(MItem item) {
		String ingredients = item.ingredients;
		String[] ingTok = ingredients.split(",");
		
		for(int i = 0; i < ingTok.length; i++) {
			String[] ing = ingTok[i].split(":");
			String name = ing[0];
			int qty = Integer.parseInt(ing[1]);
			System.out.println("ing name is "+name);
			int getQty = instance.get(name);
			int newQty = (getQty - qty);
			if(newQty < 0) {
				System.out.println(name);
				// Removes all items on the menu which use this ingredient.
				Menu.removeItemsWith(name);
				instance.put(name, 0);
				return false;
			}
			else if(newQty >= 0) {
				if(newQty == 0) {
					Menu.removeItemsWith(name);
				}
				instance.put(name, newQty);
				Server.ui.inventoryPanel.updateInventory(name, newQty);
			}
		}
		return true;
	}

}
