package server.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import server.Server;

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
					MItem item = new MItem(name, price, desc, calories, allergens, type, menuType, ingredients);
					
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
	
	/**
	 * Returns the menu item with the specified name
	 * @param itemName
	 * @return
	 */
	public static MItem getItem(String itemName) {
		for(MItem i : instance)
			if(i.name.equals(itemName))
				return i;
		return null;
	}

	/**
	 * Removes all menu items which contain the ingredient
	 * specified with @ing
	 * @param ingg
	 */
	public static void removeItemsWith(String ing) {
		List<Integer> indices = new ArrayList<Integer>();
		for(int index = 0; index < Menu.instance.size(); index++) {
			MItem i = Menu.instance.get(index);
			String[] split = i.ingredients.split(",");
			for(int j = 0; j < split.length; j++) {
				String[] attr = split[j].split(":");
				String ingName = attr[0];
				String subName = attr[3];
				if(ingName.equalsIgnoreCase(ing) ||
					subName.equalsIgnoreCase(ing)) {
					System.out.println(index+" is: "+ing+" - "+ingName+" - "+subName);
					indices.add(index);
					break;
				}
			}
		}
		for(int index = indices.size() - 1; index >= 0; index--) {
			Server.ui.menuPanel.deleteMenuItem(indices.get(index), false);
		}
	}

}
