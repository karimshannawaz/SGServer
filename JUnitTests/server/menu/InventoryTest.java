package server.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventoryTest {

	@BeforeEach
	void setUp() throws Exception {
		Inventory tester = new Inventory();
		tester.loadInventory();
	}

	@Test
	void testLoadInventory() {
		Inventory tester = new Inventory();
		tester.loadInventory();
		assertEquals(28,tester.instance.size());
	}

}
