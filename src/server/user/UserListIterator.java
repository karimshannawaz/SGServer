
package server.user;

import java.util.Iterator;
import java.util.Set;

/**
 * 
 * Iterated list of entities, in this case for a user.
 *
 * @param <E>
 */
public class UserListIterator<E extends User> implements Iterator<E> {
	
	private Integer[] indices;
	private Object[] users;
	@SuppressWarnings("rawtypes")
	private UserList entityList;
	private int curIndex = 0;

	public UserListIterator(Object[] entities, Set<Integer> indicies,
			@SuppressWarnings("rawtypes") UserList entityList) {
		this.users = entities;
		this.indices = indicies.toArray(new Integer[indicies.size()]);
		this.entityList = entityList;
	}

	public boolean hasNext() {
		return indices.length != curIndex;
	}

	@SuppressWarnings("unchecked")
	public E next() {
		Object temp = users[indices[curIndex]];
		curIndex++;
		return (E) temp;
	}

	public void remove() {
		if (curIndex >= 1) {
			entityList.remove(indices[curIndex - 1]);
		}
	}
}
