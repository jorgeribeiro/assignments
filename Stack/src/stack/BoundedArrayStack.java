package stack;

public class BoundedArrayStack<T> {
	private T[] stack;
	private int numItems;
	
	@SuppressWarnings("unchecked") 
	public BoundedArrayStack(int cap) {
		stack = (T[]) new Object[cap];
		numItems = 0;
	}
	
	@SuppressWarnings("unchecked")
	public BoundedArrayStack() {
		stack = (T[]) new Object[20];
		numItems = 0;
	}
	
	public boolean isFull() {
		return (numItems == stack.length);
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public void push(T item) {
		if(!isFull() && item != null) {
			stack[numItems] = item;
			numItems++;
		} else throw new RuntimeException("Either stack is full or item is null");
	}
	
	public void pop() {
		if(!isEmpty()) {
			stack[numItems-1] = null;
			numItems--;
		} else throw new RuntimeException("Stack is empty");
	}
	
	public T top() {
		if(!isEmpty()) {
			return stack[numItems-1];
		}
		return null;
	}
	
	public String toString() {
		String toReturn = "";
		if(!isEmpty()) {
			for(int i = numItems - 1; i >= 0; i--)
				toReturn += stack[i].toString() + " ";
		} else toReturn = "Stack is empty!";
		return toReturn;
	}
}
