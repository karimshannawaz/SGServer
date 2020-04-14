package server.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

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
	private static boolean randomize = false;

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
				int quantity = Integer.parseInt(tokens[1]);
				// Randomizes the quantity of the ingredient if randomize is true.
				// This will be enabled in the final version of the system.
				if (randomize) {

					if (name.contains("ketchup")) {
						quantity = Constants.generateNumber(200, 400);
					}
				} else {
					instance.put(name, quantity);
				}
			}
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
