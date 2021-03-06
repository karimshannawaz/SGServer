package server.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import server.menu.Inventory;

class InventoryPanelTest {

	@Test
	void testAddIngredient() {
	
		Inventory.instance.put("swiss",500);
		assertTrue(Inventory.instance.containsKey("swiss"));
		assertEquals(500, Inventory.instance.get("swiss"));
	}

	@Test
	void testUpdateInventory() {
	
		Integer quantity = Inventory.instance.get("cheese");
		Inventory.instance.put("cheese", 10);
		assertNotEquals(quantity, Inventory.instance.get("cheese"));
	}

}
