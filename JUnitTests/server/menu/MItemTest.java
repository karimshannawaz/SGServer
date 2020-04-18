package server.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MItemTest {

	@BeforeEach
	void setUp() throws Exception {
		MItem newMI = new MItem();
		assertEquals("NOTHING", newMI.name, "equals nothing b/c its a constructor");
	}

	@Test
	void testMItemStringDoubleStringIntStringStringStringString() {
		
		MItem newMI = new MItem();
		newMI.name = "Pizza";
		newMI.price = 12.99;
		newMI.description = "From Italy";
		newMI.type = "default";
		newMI.menuType = "entree";
		newMI.allergens = "wheat, gluten";
		newMI.calories = 655;
		newMI.ingredients = "cheese:1";
		newMI.sub = "vegan_patty";
		
		assertNotEquals("Nothing", newMI.name);
		assertNotEquals(0, newMI.price);
	}

	
	@Test
	void testToString() {
		MItem newMI = new MItem();
		newMI.name = "Pizza";
		newMI.price = 12.99;
		newMI.description = "From Italy";
		newMI.type = "default";
		newMI.menuType = "entree";
		newMI.allergens = "wheat, gluten";
		newMI.calories = 655;
		newMI.ingredients = "cheese:1";
		newMI.sub = "vegan_patty";
		
		String test = newMI.toString();
		
		assertEquals("Pizza~12.99~From Italy~655~wheat, gluten~default~entree~cheese:1", test);
	}

	@Test
	void testAsOrder() {
		MItem newMI = new MItem();
		newMI.name = "Pizza";
		newMI.price = 12.99;
		newMI.description = "From Italy";
		newMI.type = "default";
		newMI.menuType = "entree";
		newMI.allergens = "wheat, gluten";
		newMI.calories = 655;
		newMI.ingredients = "cheese:1";
		newMI.sub = "vegan_patty";
		newMI.specialReqs = "none";
		newMI.qty = 3;
		
		String test = newMI.asOrder();
		
		assertEquals("Pizza~12.99~3~none~cheese:1", test);
	}
}
