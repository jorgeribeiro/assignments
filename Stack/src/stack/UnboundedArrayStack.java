package stack;

public class UnboundedArrayStack<T> {
	private T[] stack;
	private int numItems;
	private final int DEFCAP = 100;
	private int origCap;
	
	@SuppressWarnings("unchecked") 
	public UnboundedArrayStack(int origCap) {
		stack = (T[]) new Object[origCap];
		this.origCap = origCap;
		numItems = 0;
	}
	
	@SuppressWarnings("unchecked")
	public UnboundedArrayStack() {
		stack = (T[]) new Object[DEFCAP];
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
		T[] larger = (T[]) new Object[stack.length + origCap];
		for(int i = 0; i < numItems; i++)
			larger[i] = stack[i];
		stack = larger;
	}
	
	public void push(T item) {
		if(numItems == stack.length)
			enlarge();
		if(item != null) {
			stack[numItems] = item;
			numItems++;
		} else throw new RuntimeException("Item is null");
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
