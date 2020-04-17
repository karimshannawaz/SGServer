package server.menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest {

	@BeforeEach
	void setUp() throws Exception {
		Order tester = new Order();
		tester.addItem("Cheeseburger", 6.75, 2, "none", "beef_patty:1:t:vegan_patty,burger_buns:1:t:veggie_buns,cheese:1:t:vegan_cheese");

	}

	@Test
	void testAddItem() {
		Order tester = new Order();
		tester.addItem("Cheeseburger", 6.75, 2, "none", "beef_patty:1:t:vegan_patty,burger_buns:1:t:veggie_buns,cheese:1:t:vegan_cheese");
		tester.addItem("Classic Fried Wings", 8.99, 3, "none", "wings:10:f:n,buffalo_f:1:t:bbq_f");
		
		assertEquals(2, tester.items.size(), "equals 2 because 2 items were added");
	}

	@Test
	void testReceiveOrder() {
		Order tester = new Order();
		//recieveOrder();
	}



}
