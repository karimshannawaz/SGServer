package server.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single item on the menu with its respective attributes. 
 * SERVER SIDED
 * 
 * @author Karimshan Nawaz
 *
 */
public class MItem {

	public String name;
	public double price;
	public String description;
	public int calories;
	public String allergens;
	public String type; // indicates if the menu item is vegan, vegetarian or neither (default).
	public String menuType; // indicates if the item is an entree, drink, dessert or side.
	public List<Ingredient> ingredients; // Represents a list of ingredients for this menu item

	public MItem(String name, double price, String description, int calories, String allergens,
			String type, String menuType) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.calories = calories;
		this.allergens = allergens;
		this.type = type;
		this.menuType = menuType;
		this.ingredients = new ArrayList<Ingredient>();
	}

	public MItem() {
		this.name = "NOTHING";
		this.price = 0;
		this.description = null;
		this.calories = 0;
		this.allergens = "none";
		this.type = "default";
		this.menuType = "entree";
		this.ingredients = new ArrayList<Ingredient>();
	}
	
	/**
	 * Adds an ingredient to this menu item.
	 * @param name
	 * @param qty
	 * @param editable
	 * @param substitutions
	 */
	public void addIng(String name, int qty, boolean editable, String substitutions) {
		Ingredient ing = new Ingredient(name, qty, editable);
		ing.addSub(substitutions);
		ingredients.add(ing);
	}

	@Override
	public String toString() {
		return name + "~" + price + "~" + description + "~" + calories + "~" + allergens + "~" + type
				+ "~" + menuType + "~" + ingredients;
	}

	/**
	 * Returns ingredients to string.
	 * @return
	 */
	public String getIngsToString() {
		String output = "";
		for(int i = 0; i < ingredients.size(); i++) {
			output += ingredients.get(i).toString();
			if(i < ingredients.size() - 1)
				output += ",";
		}
		System.out.println("output is "+output);
		return output;
	}

}
