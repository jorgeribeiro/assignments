package list;

public class BoundedArraySortedList<T> {
	private T[] list;
	private int numItems;
	
	@SuppressWarnings("unchecked")
	public BoundedArraySortedList(int cap) {
		list = (T[]) new Object[cap];
		numItems = 0;
	}
	
	@SuppressWarnings("unchecked")
	public BoundedArraySortedList() {
		list = (T[]) new Object[20];
		numItems = 0;
	}
	
	public boolean isFull() {
		return (numItems == list.length);
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	@SuppressWarnings("unchecked")
	public void add(T item) {
		if(!isFull() && item != null) {
			int where = 0;
			while(where < numItems) {
				if(((Comparable<T>)list[where]).compareTo(item) < 0)
					where++;
				else break;
			}
			for(int i = numItems; i > where; i--) {
				list[i] = list[i-1];
			}
			list[where] = item;
			numItems++;
		}
	}
	
	public void remove(T item) {
		if(!isEmpty() && item != null) {
			if(contains(item)) {
				int where = -1;
				for(int i = 0; i < numItems; i++) {
					if(list[i].equals(item)) {
						where = i;
						break;
					}
				}
				for(int i = where; i < numItems - 1; i++) {
					list[i] = list[i+1];
				}
				numItems--;
			}
		}
	}
	
	public boolean contains(T item) {
		if(!isEmpty() && item != null) {
			for(int i = 0; i < numItems; i++) {
				if(list[i].equals(item))
					return true;
			}
		}
		return false;
	}
	
	public String toString() {
		String toReturn = "";
		if(!isEmpty()) {
			for(int i = 0; i < numItems; i++) {
				toReturn += list[i].toString() + " ";
			}
		} else toReturn = "List is empty!";
		return toReturn;
	}
	
}
