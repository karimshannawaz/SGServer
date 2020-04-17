package server.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

	@BeforeEach
	void setUp() throws Exception {
		User tester = new User();
	}

	@Test
	void testUserStringStringStringString() {
		User tester = new User("kitchen", "tester@gmail.com", "11/04/1997", "testname");
		
		assertEquals("kitchen", tester.getRole());
		assertNotEquals(null, tester.getRole());
	
	}

	@Test
	void testUser() {
		User tester = new User();
		
		assertNotEquals("kitchen", tester.getRole());
		assertEquals(null, tester.getRole(), "Should return NULL");
	}

	@Test
	void testCreateEmployee() {
		User tester = new User();
		
		tester.createEmployee("t1", "test name", "kitchen", "1234");
		
		assertEquals("kitchen", tester.getRole());
	}

	@Test
	void testInitialize() {
		//User.initialize(e);
	}

	@Test
	void testGetRole() {
		
	}

	@Test
	void testSetRole() {
		
	}

	@Test
	void testIsCustomer() {
		
	}

	@Test
	void testGetSession() {
		
	}

	@Test
	void testSetSession() {
		
	}

	@Test
	void testGetEmail() {
		
	}

	@Test
	void testSetEmail() {
		
	}

	@Test
	void testGetPassword() {
		
	}

	@Test
	void testSetPassword() {
		
	}

	@Test
	void testGetId() {
		
	}

	@Test
	void testSetId() {
		
	}

	@Test
	void testGetBirthday() {
		
	}

	@Test
	void testSetBirthday() {
		
	}

	@Test
	void testGetVisits() {
		
	}

	@Test
	void testSetVisits() {
		
	}

	@Test
	void testHasBirthdayEntree() {
		
	}

	@Test
	void testSetBirthdayEntree() {
		
	}

	@Test
	void testHasFreeSide() {
		
	}

	@Test
	void testSetFreeSide() {
		
	}

	@Test
	void testGetName() {
		
	}

	@Test
	void testSetName() {
		
	}

	@Test
	void testGetPacketDecoder() {
		
	}

	@Test
	void testGetPacketEncoder() {
		
	}

	@Test
	void testHasFreeDessert() {
		
	}

	@Test
	void testSetFreeDessert() {
		
	}

	@Test
	void testGetIndex() {
		
	}

	@Test
	void testSetIndex() {
		
	}

	@Test
	void testClose() {
		
	}

	@Test
	void testGetTableID() {
		
	}

	@Test
	void testIsAvailable() {
		
	}

	@Test
	void testSetAvailable() {
		
	}

}
