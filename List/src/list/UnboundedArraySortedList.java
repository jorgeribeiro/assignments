package list;

public class UnboundedArraySortedList<T> {
	private T[] list;
	private int numItems;
	private final int DEFCAP = 100;
	private int origCap;
	
	@SuppressWarnings("unchecked")
	public UnboundedArraySortedList(int origCap) {
		list = (T[]) new Object[origCap];
		this.origCap = origCap;
		numItems = 0;
	}
	
	@SuppressWarnings("unchecked")
	public UnboundedArraySortedList() {
		list = (T[]) new Object[DEFCAP];
		origCap = DEFCAP;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	private void enlarge() {
		@SuppressWarnings("unchecked")
		T[] larger = (T[]) new Object[list.length + origCap];
		for(int i = 0; i < numItems; i++)
			larger[i] = list[i];
		list = larger;
	}
	
	@SuppressWarnings("unchecked")
	public void add(T item) {
		if(numItems == list.length)
			enlarge();
		if(item != null) {
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
