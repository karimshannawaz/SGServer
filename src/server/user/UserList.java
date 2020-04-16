package server.user;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * Holds a collection of users with synchronization
 * when adding or removing from the list.
 *
 * @param <T>
 */
public class UserList<T extends User> extends AbstractCollection<T> {
	
	private static final int MIN_VALUE = 1;
	public Object[] users;
	public Set<Integer> indices = new HashSet<Integer>();
	public int curIndex = MIN_VALUE;
	public int capacity;
	private final Object lock = new Object();

	public UserList(int capacity) {
		users = new Object[capacity];
		this.capacity = capacity;
	}

	public boolean add(T entity) {
		synchronized (lock) {
			add(entity, curIndex);
			return true;
		}
	}

	public void remove(T entity) {
		synchronized (lock) {
			users[entity.getIndex()] = null;
			indices.remove(entity.getIndex());
			decreaseIndex();
		}
	}

	@SuppressWarnings("unchecked")
	public T remove(int index) {
		synchronized (lock) {
			Object temp = users[index];
			users[index] = null;
			indices.remove(index);
			decreaseIndex();
			return (T) temp;
		}
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		synchronized (lock) {
			if (index >= users.length)
				return null;
			return (T) users[index];
		}
	}

	public void add(T entity, int index) {
		if (users[curIndex] != null) {
			increaseIndex();
			add(entity, curIndex);
		} else {
			users[curIndex] = entity;
			entity.setIndex(index);
			indices.add(curIndex);
			increaseIndex();
		}
	}

	public Iterator<T> iterator() {
		synchronized (lock) {
			return new UserListIterator<T>(users, indices, this);
		}
	}

	public void increaseIndex() {
		curIndex++;
		if (curIndex >= capacity) {
			curIndex = MIN_VALUE;
		}
	}

	public void decreaseIndex() {
		curIndex--;
		if (curIndex <= capacity)
			curIndex = MIN_VALUE;
	}

	public boolean contains(T entity) {
		return indexOf(entity) > -1;
	}

	public int indexOf(T entity) {
		for (int index : indices) {
			if (users[index].equals(entity)) {
				return index;
			}
		}
		return -1;
	}

	public int size() {
		return indices.size();
	}
}
