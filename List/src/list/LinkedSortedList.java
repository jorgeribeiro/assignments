package list;

import java.util.Iterator;

public class LinkedSortedList<T> implements Iterator<T> {
	private ListNode<T> head;
	private ListNode<T> currentNode;
	private int numItems;
	
	public LinkedSortedList() {
		head = null;
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
		ListNode<T> newNode = new ListNode<T>(item);
		ListNode<T> precursor = null, cursor = head;
		while(cursor != null) {
			if(((Comparable<T>) cursor.getInfo()).compareTo(item) < 0) {
				precursor = cursor;
				cursor = cursor.getLink();
			} else break;
		}
		if(precursor != null) {
			precursor.setLink(newNode);
			newNode.setLink(cursor);
		} else {
			newNode.setLink(cursor);
			head = newNode;
		}
		numItems++;
	}
	
	public void remove(T item) {
		if(!isEmpty() && item != null) {
			if(contains(item)) {
				ListNode<T> precursor = null, cursor = head;
				while(cursor != null) {
					if(cursor.getInfo().equals(item))
						break;
					precursor = cursor;
					cursor = cursor.getLink();
				}
				if(precursor != null)
					precursor.setLink(cursor.getLink());
				else 
					head = cursor.getLink();
				numItems--;
			}
		}
	}
	
	public boolean contains(T item) {
		if(!isEmpty() && item != null) {
			ListNode<T> cursor = head;
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
		if(!isEmpty()) {
			ListNode<T> cursor = head;
			while(cursor != null) {
				toReturn += cursor.getInfo().toString() + " ";
				cursor = cursor.getLink();
			}
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

class ListNode<T> {
	private T info;
	private ListNode<T> link;
	
	public ListNode(T info) {
		this.info = info;
		link = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public ListNode<T> getLink() {
		return link;
	}

	public void setLink(ListNode<T> link) {
		this.link = link;
	}
}
