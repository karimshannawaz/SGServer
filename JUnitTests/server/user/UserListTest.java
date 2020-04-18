package server.user;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserListTest {

	@BeforeEach
	void setUp() throws Exception {
		UserList tester = new UserList(5);
	}

	@Test
	void testSize() {
		UserList tester = new UserList(5);
		tester.indices.size();
		
		assertEquals(0, tester.indices.size());
	}

	@Test
	void testUserList() {
		UserList tester = new UserList(5);
		
		assertEquals(5,tester.capacity);
			
	}

	@Test
	void testAddT() {
		UserList tester = new UserList(5);
		User entity = new User();
		entity.getIndex();
		UserLoader.saveUser(entity, true);
		
		assertTrue(tester.add(entity));
		
	}

	@Test
	void testRemoveT() {
		UserList tester = new UserList(5);
		User entity = new User();
		entity.getIndex();
		UserLoader.saveUser(entity, true);
	
		tester.add(entity);
		assertEquals(1,tester.indices.size());
		
		tester.remove(entity);
		assertEquals(0, tester.indices.size());
	}

	@Test
	void testRemoveInt() {
		UserList tester = new UserList(5);
		User entity = new User();
		entity.getIndex();
		UserLoader.saveUser(entity, true);
	
		tester.add(entity);
		assertEquals(1,tester.indices.size());
		
		tester.remove(entity);
		assertEquals(0, tester.indices.size());
		
	}

	@Test
	void testGet() {
		UserList tester = new UserList(5);
		tester.get(3);
		
		assertNull(tester.get(3));
		
	}

	@Test
	void testAddTInt() {
		UserList tester = new UserList(5);
		User entity = new User();
		entity.getIndex();
		//entity.indices.add();
		//UserLoader.saveUser(entity, true);
		tester.add(entity, 6);
		
		assertEquals(1, tester.indices.size());
		
	}

	@Test
	void testIterator() {
		UserList tester = new UserList(5);
		
		tester.iterator();
		assertNotEquals(0, tester.iterator());
		
	}

	@Test
	void testIncreaseIndex() {
		UserList tester = new UserList(5);
		tester.increaseIndex();
		
		assertNotEquals(0,tester.curIndex);
		
		
	}

	@Test
	void testDecreaseIndex() {
		UserList tester = new UserList(5);
		tester.decreaseIndex();
		
		assertEquals(1,tester.curIndex);
	}

	@Test
	void testContainsT() {
		UserList tester = new UserList(5);
		User entity = new User();
		entity.getIndex();
		
		tester.indexOf(entity);
		
		assertNotNull(tester.indexOf(entity));
	}

	@Test
	void testIndexOf() {
		UserList tester = new UserList(5);
		User entity = new User();
		entity.getIndex();
		tester.indexOf(entity );
		
		assertNotEquals(0, tester.indexOf(entity));
		
	}

}
