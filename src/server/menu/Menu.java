package server.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Menu {

	// Represents the list of menu items for our restaurant.
	public static List<MItem> instance = new ArrayList<MItem>();

	/**
	 * This is done whenever the program first loads to avoid constantly loading and
	 * reading from the file.
	 */
	public static void loadMenu() {
		instance.clear();
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File("./data/menu.txt")));
			String line;
			int index = 0;
			List<String> attr = new ArrayList<String>();
			while ((line = r.readLine()) != null) {
				if (line.startsWith("//") || line.equals(""))
					continue;
				if(index == 7) {
					attr.add(line);
					String name = attr.get(0);
					double price = Double.parseDouble(attr.get(1));
					String desc = attr.get(2);
					int calories = Integer.parseInt(attr.get(3));
					String allergens = attr.get(4);
					String type = attr.get(5);
					String menuType = attr.get(6);
					String ingredients = attr.get(7);
					MItem item = new MItem(name, price, desc, calories, allergens, type, menuType);
					String[] iTok = ingredients.split(",");
					for(int i = 0; i < iTok.length; i++) {
						String[] iTok2 = iTok[i].split(":");
						String ingName = iTok2[0];
						int qty = Integer.parseInt(iTok2[1]);
						boolean editable = iTok2[2].equals("t");
						String sub = iTok2[3];
						item.addIng(ingName, qty, editable, sub);
					}
					instance.add(item);
					attr.clear();
					index = 0;
					continue;
				}
				attr.add(line);
				index++;
			}
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * This is where the manager can add a new item to the menu It will update at
	 * the end of the day to the file, but throughout the remainder of the program's
	 * runtime, the changes will be visible in the menu.
	 */
	public static void add(MItem item) {
		int newIndex = instance.size();
		for (MItem currentItem : instance) {
			if (currentItem.name.equals(item.name)) {
				System.out.println(item.name + " already exists on the menu.");
				return;
			}
		}
		instance.add(newIndex, item);
		System.out.println("Added new menu item: " + item.name + " at index: " + newIndex + " --> "
			+ Menu.instance.get(newIndex).toString());
	}

	/**
	 * This is where the manager can remove an item from the menu. It will update at
	 * the end of the day to the file, but throughout the remainder of the program's
	 * runtime, the changes will be visible in the menu.
	 */
	public static void remove(String name) {
		int index = -1;
		for (int i = 0; i < instance.size(); i++) {
			MItem currentItem = instance.get(i);
			if (currentItem.name.equals(name)) {
				index = i;
			}
		}
		if (index == -1) {
			System.out.println("Error: could not remove " + name + " from the menu because it does not exist.");
			return;
		} else {
			instance.remove(index);
			System.out.println("Removed menu item: " + name + " at index: " + index);
		}
	}
	
	/**
	 * This is where the manager can remove an item from the menu. It will update at
	 * the end of the day to the file, but throughout the remainder of the program's
	 * runtime, the changes will be visible in the menu.
	 */
	public static void remove(int index) {
		if (index == -1) {
			//System.out.println("Error: could not remove " + index + " from the menu because it does not exist.");
			return;
		} else {
			String oldName = instance.get(index).name;
			instance.remove(index);
			System.out.println("Removed menu item: " + oldName + " at index: " + index);
		}
	}

}
