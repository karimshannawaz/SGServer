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
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testAdd() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testRemoveString() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testRemoveInt() {
		//fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetItem() {
		//fail("Not yet implemented"); // TODO
	}

}
