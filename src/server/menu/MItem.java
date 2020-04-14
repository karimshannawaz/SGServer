package server.menu;

/**
 * Represents a single item on the menu with its respective attributes. SERVER
 * SIDED
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
	public String ingredients;

	public MItem(String name, double price, String description, int calories, String allergens,
			String type, String menuType, String ingredients) {
		this.name = name;
		this.price = price;
		this.description = description;
		this.calories = calories;
		this.allergens = allergens;
		this.type = type;
		this.menuType = menuType;
		this.ingredients = ingredients;
	}

	public MItem() {
		this.name = "NOTHING";
		this.price = 0;
		this.description = null;
		this.calories = 0;
		this.allergens = "none";
		this.type = "default";
		this.menuType = "entree";
		this.ingredients = null;
	}

	@Override
	public String toString() {
		return name + "~" + price + "~" + description + "~" + calories + "~" + allergens + "~" + type
				+ "~" + menuType + "~" + ingredients;
	}

}
