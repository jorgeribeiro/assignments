package list;

import java.util.Iterator;

public class CircularLinkedUnsortedList<T> implements Iterator<T> {
	private ListNode<T> head; // last element of the list
	private ListNode<T> currentNode;
	private int numItems;
	
	public CircularLinkedUnsortedList() {
		head = null;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public void add(T item) {
		if(item == null) return;
		ListNode<T> newNode = new ListNode<T>(item);
		if(isEmpty()) {
			head = newNode;
			newNode.setLink(head);
		} else {
			newNode.setLink(head.getLink());
			head.setLink(newNode);
			head = newNode;
		}
		numItems++;
	}
	
	public void remove(T item) {
		if(!isEmpty() && item != null) {
			if(contains(item)) {
				ListNode<T> precursor = head, cursor = head.getLink();
				do {
					if(cursor.getInfo().equals(item)) 
						break;
					precursor = cursor;
					cursor = cursor.getLink();
				} while(precursor != head);
				if(precursor == cursor)
					head = null;
				else if(cursor == head) 
					head = precursor;
				precursor.setLink(cursor.getLink());
				numItems--;
			}
		}
	}
	
	public boolean contains(T item) {
		if(!isEmpty() && item != null) {
			ListNode<T> cursor = head;
			do {
				if(cursor.getInfo().equals(item))
					return true;
				cursor = cursor.getLink();
			} while(cursor != head);
		}
		return false;
	}
	
	public String toString() {
		String toReturn = "";
		if(!isEmpty()) {
			ListNode<T> cursor = head;
			do {
				cursor = cursor.getLink();
				toReturn += cursor.getInfo().toString() + " ";
			} while(cursor != head);
		} else toReturn = "List is empty!";
		return toReturn;
	}
	
	public Iterator<T> getIterator() {
		currentNode = head;
		return this;
	}

	@Override
	public boolean hasNext() {
		return currentNode != null;
	}

	@Override
	public T next() {
		if(currentNode != null) {
			T temp = currentNode.getInfo();
			currentNode = currentNode.getLink();
			return temp;
		}
		return null;		
	}
	
}