package stack;

public class LinkedStack<T> {
	private StackNode<T> top;
	private int numItems;
	
	public LinkedStack() {
		top = null;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public void push(T item) {
		if(item == null) return;
		StackNode<T> newNode = new StackNode<T>(item);
		newNode.setLink(top);
		top = newNode;
		numItems++;
	}
	
	public T pop() {
		T toReturn = null;
		if(!isEmpty()) {
			toReturn = top.getInfo();
			top = top.getLink();
			numItems--;
		}
		return toReturn;
	}
	
	public T top() {
		if(!isEmpty()) {
			return top.getInfo();
		}
		return null;
	}
	
	public String toString() {
		String toReturn = "";
		if(!isEmpty()) {
			StackNode<T> cursor = top;
			while(cursor != null) {
				toReturn += cursor.getInfo().toString() + " ";
				cursor = cursor.getLink();
			}
		} else toReturn = "Stack is empty!";
		return toReturn;
	}
	
}

class StackNode<T> {
	private T info;
	private StackNode<T> link;
	
	public StackNode(T info) {
		this.info = info;
		link = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public StackNode<T> getLink() {
		return link;
	}

	public void setLink(StackNode<T> link) {
		this.link = link;
	}
}
