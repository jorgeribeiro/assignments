package arrayqueue;

public class UnboundedQueue<T> {
	private T[] queue;
	private int numItems;
	private int front = 0, rear = -1;
	private final int DEFCAP = 100;
	private int origCap;
	
	@SuppressWarnings("unchecked")
	public UnboundedQueue(int origCap) {
		queue = (T[]) new Object[origCap];
		rear = origCap - 1;
		this.origCap = origCap;
		numItems = 0;
	}
	
	@SuppressWarnings("unchecked")
	public UnboundedQueue() {
		queue = (T[]) new Object[DEFCAP];
		rear = DEFCAP - 1;
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
		T[] larger = (T[]) new Object[queue.length + origCap];
		int currSmaller = front;
		for(int currLarger = 0; currLarger < numItems; currLarger++) {
			larger[currLarger] = queue[currSmaller];
			currSmaller = (currSmaller + 1) % queue.length;
		}		
		queue = larger;
		front = 0;
		rear = numItems - 1;
	}
	
	public void enqueue(T item) {
		if(numItems == queue.length)
			enlarge();
		if(item != null) {
			rear = (rear + 1) % queue.length;
			queue[rear] = item;
			numItems++;
		}
	}
	
	public T dequeue() {
		if(!isEmpty()) {
			T toReturn = queue[front];
			queue[front] = null;
			front = (front + 1) % queue.length;
			numItems--;
			return toReturn;
		}
		return null;
	}
	
	public String toString() {
		String toReturn = "";
		if(!isEmpty()) {
			int cursor = front;
			while(cursor != rear) {
				toReturn += queue[cursor].toString() + " ";
				cursor++;
				if(cursor == queue.length) cursor = 0;
			}
			toReturn += queue[cursor].toString() + " ";
		} else toReturn = "Queue is empty!";
		return toReturn;
	}
}
