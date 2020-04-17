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
		User tester = new User("customer", "tester@gmail.com", "11/04/1997", "testname");
		
		assertEquals("customer", tester.getRole());
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
		User tester = new User();
		
		tester.createEmployee("t1", "test name", "kitchen", "1234");
		
		assertEquals("kitchen", tester.getRole());
	}

	@Test
	void testSetRole() {
		User tester = new User();
		
		tester.setRole("kitchen");
		assertEquals("kitchen", tester.getRole());
	}

	@Test
	void testGetEmail() {
		User tester = new User("customer", "tester@gmail.com", "11/04/1997", "testname");
		
		assertEquals("tester@gmail.com", tester.getEmail());
		assertNotEquals(null, tester.getEmail());
	}

	@Test
	void testSetEmail() {
		User tester = new User();
		tester.setEmail("tester@gmail.com");
		
		assertEquals("tester@gmail.com", tester.getEmail());
		assertNotEquals(null, tester.getEmail());
	}

	@Test
	void testGetPassword() {
		User tester = new User();
		
		tester.createEmployee("t1", "test name", "kitchen", "1234");
		
		assertEquals("1234", tester.getPassword());
	}

	@Test
	void testSetPassword() {
		User tester = new User();
		
		tester.setPassword("243");
		
		assertEquals("243", tester.getPassword());
	}
	

	@Test
	void testGetId() {
		User tester = new User();
		
		tester.createEmployee("t1", "test name", "kitchen", "1234");
		
		assertEquals("t1", tester.getId());
		assertNotEquals("kitchen", tester.getId());
	}

	@Test
	void testSetId() {
		User tester = new User();
		
		tester.setId("t1");
		
		assertEquals("t1", tester.getId());
	}

	@Test
	void testGetBirthday() {
		User tester = new User("customer", "tester@gmail.com", "11/04/1997", "testname");
		
		assertEquals("11/04/1997", tester.getBirthday());
		assertNotEquals(null, tester.getBirthday());
	}

	@Test
	void testSetBirthday() {
		User tester = new User("customer", "tester@gmail.com", "11/04/1997", "testname");
		
		tester.setBirthday("04/13/20");
		
		assertEquals("04/13/20", tester.getBirthday());
		assertNotEquals(null, tester.getBirthday());
	}

	@Test
	void testGetVisits() {
		User tester = new User("customer", "tester@gmail.com", "11/04/1997", "testname");
		
		
		assertEquals(0, tester.getVisits(), "visits equal 0 at initialization");
		assertNotEquals(1, tester.getVisits());
	}

	@Test
	void testSetVisits() {
		User tester = new User("customer", "tester@gmail.com", "11/04/1997", "testname");
		
		tester.setVisits(5);
		
		assertEquals(5, tester.getVisits());
		assertNotEquals(0, tester.getVisits(), "should no longer equal 0");
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
