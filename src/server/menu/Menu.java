package server.menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Menu {

	// Represents the list of menu items for our restaurant.
	public static Map<Integer, MItem> instance = new HashMap<Integer, MItem>();

	/**
	 * This is done whenever the program first loads to avoid
	 * constantly loading and reading from the file.
	 */
	public static void loadMenu() {
		instance.clear();
		int index = 0;
		try {
			BufferedReader r = new BufferedReader(new FileReader(new File("./data/menu.txt")));
			String line;
			while((line = r.readLine()) != null) {
				if(line.startsWith("//") || line.equals(""))
					continue;
				String[] tokens = line.split(" ~ ");
				String name = tokens[0];
				double price = Double.parseDouble(tokens[1]);
				String desc = tokens[2];
				int calories = Integer.parseInt(tokens[3]);
				String allergens = tokens[4];
				int type = Integer.parseInt(tokens[5]);
				String menuType = tokens[6];
				String ingredients = tokens[7];
				MItem item = new MItem(name, price, desc, calories, allergens, type, menuType, ingredients);
				item.setIndex(index);
				instance.put(index, item);
				System.out.println(index+" --> "+item.toString());
				index++;
			}
			r.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This is where the manager can add a new item to the menu
	 * It will update at the end of the day to the file, but throughout the remainder
	 * of the program's runtime, the changes will be visible in the menu.
	 */
	public static void add(String name, double price, String description, int calories, String allergens,
			int type, String ingredients) {
		MItem item = new MItem();
		item.name = name;
		item.price = price;
		item.description = description;
		item.calories = calories;
		item.allergens = allergens;
		item.type = type;
		item.ingredients = ingredients;
		int newIndex = instance.size();
		for(MItem currentItem : instance.values()) {
			if(currentItem.name.equals(name)) {
				System.out.println(name+" already exists on the menu.");
				return;
			}
		}
		item.setIndex(newIndex);
		instance.put(newIndex, item);
		System.out.println("Added new menu item: "+name+" at index: "+newIndex);
	}
	
	/**
	 * This is where the manager can add a new item to the menu
	 * It will update at the end of the day to the file, but throughout the remainder
	 * of the program's runtime, the changes will be visible in the menu.
	 */
	public static void add(MItem item) {
		int newIndex = instance.size();
		for(MItem currentItem : instance.values()) {
			if(currentItem.name.equals(item.name)) {
				System.out.println(item.name+" already exists on the menu.");
				return;
			}
		}
		item.setIndex(newIndex);
		instance.put(newIndex, item);
		System.out.println("Added new menu item: "+item.name+" at index: "+newIndex+" --> "+Menu.instance.get(newIndex).toString());
	}
	
	/**
	 * This is where the manager can remove an item from the menu.
	 * It will update at the end of the day to the file, but throughout the remainder
	 * of the program's runtime, the changes will be visible in the menu.
	 */
	public static void remove(String name) {
		int index = -1;
		for(int i = 0; i < instance.size(); i++) {
			MItem currentItem = instance.get(i);
			if(currentItem.name.equals(name)) {
				index = i;
			}
		}
		if(index == -1) {
			System.out.println("Error: could not remove "+name+" from the menu because it does not exist.");
			return;
		}
		else {
			instance.remove(index);
			System.out.println("Removed menu item: "+name+" at index: "+index);
		}
	}

}
