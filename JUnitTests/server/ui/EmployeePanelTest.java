package server.ui;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import server.user.User;
import server.user.UserLoader;

class EmployeePanelTest {

	@BeforeEach
	void setUp() throws Exception {
		EmployeePanel tester = new EmployeePanel();
		
	}

	@Test
	void testAddEmployee() {
		EmployeePanel tester = new EmployeePanel();
		String id = "akw";
		String name = "asha";
		String role = "waitstaff";
		String pwd = "1234";
		
		User employee = new User();
		employee.createEmployee(id, name, role, pwd);
		UserLoader.saveUser(employee, true);
		
		assertEquals("akw",employee.getId());
		assertEquals("asha",employee.getName());
		assertEquals("waitstaff",employee.getRole());
		assertEquals("1234",employee.getPassword());
	}

	@Test
	void testRemoveEmployee() {
		EmployeePanel tester = new EmployeePanel();
		
		String id = "akw";
		String name = "asha";
		String role = "waitstaff";
		String pwd = "1234";
		
		User employee = new User();
		employee.createEmployee(id, name, role, pwd);
		UserLoader.saveUser(employee, true);
		
		assertFalse(UserLoader.deleteUser("notAnID", true));
		assertTrue(UserLoader.deleteUser(employee.getId(), true));
	
		
	}

}
