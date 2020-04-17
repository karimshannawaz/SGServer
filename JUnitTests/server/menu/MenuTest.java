package server.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {

	@BeforeEach
	void setUp() throws Exception {
		Menu tester = new Menu();
		tester.loadMenu();
	}

	@Test
	void testLoadMenu() {
		Menu tester = new Menu();
		tester.loadMenu();
		assertEquals(11, tester.instance.size(),"11 Menu items are in menu.txt");
	}

	@Test
	void testAdd() {
		Menu tester = new Menu();
		tester.loadMenu();
		MItem newMI = new MItem();
		newMI.name = "Cheeseburger";
		tester.add(newMI);
		assertNotEquals(12, tester.instance.size(), "Item should not add because it already exists");
		
		newMI.name = "Pizza";
		newMI.price = 12.99;
		newMI.description = "From Italy";
		newMI.type = "default";
		newMI.menuType = "entree";
		newMI.allergens = "wheat, gluten";
		newMI.calories = 655;
		newMI.ingredients = "cheese:1";
		tester.add(newMI);
		assertEquals(12, tester.instance.size(), "total menu items should now be 12");
	}

	@Test
	void testRemoveString() {
		Menu tester = new Menu();
		tester.loadMenu();
		tester.remove("Cheeseburger");
		assertEquals(10, tester.instance.size(), "11 minus 1 equals 10");
	}

	@Test
	void testRemoveInt() {
		Menu tester = new Menu();
		tester.loadMenu();
		tester.remove(2);
		assertEquals(10, tester.instance.size(), "11 minus 1 equals 10");
		
	}

	@Test
	void testGetItem() {
		Menu tester = new Menu();
		tester.loadMenu();
		MItem i = tester.getItem("Juicy Lucy");
		assertEquals("Juicy Lucy", i.name);
		assertEquals(9.50, i.price);
		assertEquals("A deliciously juicy burger", i.description);
		assertEquals("default", i.type);
		assertEquals("entree", i.menuType);
		assertEquals("wheat, gluten", i.allergens);
		assertEquals(1140, i.calories);
		assertEquals("beef_patty:1:t:vegan_patty,burger_buns:1:t:veggie_buns,cheese:1:t:vegan_cheese", i.ingredients);
	}

}
