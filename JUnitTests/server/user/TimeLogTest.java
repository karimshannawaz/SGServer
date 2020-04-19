package server.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeLogTest {

	@BeforeEach
	void setUp() throws Exception {
		TimeLog tester = new TimeLog();
	}

	@Test
	void testTimeLog() {
		TimeLog tester = new TimeLog();
		tester.getPunchIns();
		tester.getPunchOuts();
		
		assertEquals(tester.getPunchIns(), tester.getPunchOuts());
	}

	@Test
	void testSetId() {
		TimeLog tester = new TimeLog();
		tester.setId("mn");
		assertEquals("mn", tester.getId());
	}

	@Test
	void testGetPunchIns() {
		TimeLog tester = new TimeLog();
		tester.getPunchIns();
		
		assertNotNull(tester.getPunchIns());
		
	}

	@Test
	void testGetPunchOuts() {
		TimeLog tester = new TimeLog();
		tester.getPunchOuts();
		
		assertNotNull(tester.getPunchOuts());
		
	}


	@Test
	void testGetLog() {
		TimeLog tester = new TimeLog();
		tester.getLog("k2");
		
		assertNull(tester.getLog("k2"));
		
		
	}


}
