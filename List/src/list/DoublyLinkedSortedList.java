package list;

interface ICloneable {
	public abstract Object deepClone();
}

public class DoublyLinkedSortedList<T extends ICloneable> {
	@SuppressWarnings("unused")
	private DLListNode<T> head, tail;
	private int numItems;
	
	public DoublyLinkedSortedList() {
		head = tail = null;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	@SuppressWarnings("unchecked")
	public void add(T item) {
		if(item == null) return;
		DLListNode<T> newNode = new DLListNode<T>((T)item.deepClone());
		DLListNode<T> cursor = head, precursor = null;
		// 1. find where insertion takes place
		while(cursor != null) {
			if(((Comparable<T>)cursor.getInfo()).compareTo(item) < 0) {
				precursor = cursor;
				cursor = cursor.getLink();
			} else break;
		}
		// 2. do insert
		if(head == null) { // if the list is empty
			head = tail = newNode;
			numItems++;
			return;
		}
		if(precursor == null) {
			newNode.setLink(head);
			head.setBack(newNode);
			head = newNode;
		} else { 
			precursor.setLink(newNode);
			newNode.setBack(precursor);
			if(cursor != null) {
				cursor.setBack(newNode);
				newNode.setLink(cursor);
			} else {
				tail = newNode;
			}
		}
		numItems++;
 	}
	
	public void remove(T item) {
		if(item == null || isEmpty()) return;
		if(contains(item)) {
			DLListNode<T> cursor = head, precursor = null;
			while(cursor != null) {
				if(cursor.getInfo().equals(item))
					break;
				precursor = cursor;
				cursor = cursor.getLink();
			}
			if(precursor == null) { // remove head
				head = head.getLink();
				if(head == null) tail = null;
				else head.setBack(null);
			} else {
				precursor.setLink(cursor.getLink());
				if(cursor.getLink() != null) {
					cursor.getLink().setBack(precursor);
				}
				else {
					tail = precursor;
				}
			}
		}
	}
	
	public boolean contains(T item) {
		if(!isEmpty() && item != null) {
			DLListNode<T> cursor = head;
			while(cursor != null) {
				if(cursor.getInfo().equals(item))
					return true;
				cursor = cursor.getLink();
			}
		}
		return false;
	}
	
	public String toString() {
		String toReturn = "";
		DLListNode<T> cursor = head;
		while(cursor != null) {
			toReturn += cursor.getInfo().toString() + " ";
			cursor = cursor.getLink();
		}
		return toReturn;
	}	
}

class DLListNode<T> {
	private T info;
	private DLListNode<T> link, back;
	
	public DLListNode(T info) {
		this.info = info;
		link = back = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public DLListNode<T> getLink() {
		return link;
	}

	public void setLink(DLListNode<T> link) {
		this.link = link;
	}

	public DLListNode<T> getBack() {
		return back;
	}

	public void setBack(DLListNode<T> back) {
		this.back = back;
	}
	
}

class Name implements Comparable<Object>, ICloneable {
	String first, last;
	
	public Name(String f, String l) {
		first = f;
		last = l;
	}
	
	// copy constructor
	public Name(Name n) {
		first = n.first;
		last = n.last;
	}

	@Override
	public int compareTo(Object o) {
		Name other = (Name)o;
		if(last.equals(other.last))
			return last.compareTo(other.last);
		else return first.compareTo(other.first);
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(this == o) return true;
		if(!(o instanceof Name)) return false;
		Name other = (Name)o;
		return first.equals(other.first) && last.equals(other.last);
	}
	
	public String toString() {
		return last + ", " + first + ";";
	}

	@Override
	public Object deepClone() {
		return new Name(first, last);
	}
	
}
