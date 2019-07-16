package arrayqueue;

public class FixedBoundedQueue<T> {
	private T[] queue;
	private int numItems;
	private int front = 0, rear = -1;
	
	@SuppressWarnings("unchecked")
	public FixedBoundedQueue(int cap) {
		queue = (T[]) new Object[cap];
		numItems = 0;
	}
	
	@SuppressWarnings("unchecked")
	public FixedBoundedQueue() {
		queue = (T[]) new Object[100];
		numItems = 0;
	}
	
	public boolean isFull() {
		return (numItems == queue.length);
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}

	public void enqueue(T item) {
		if(item == null) return;
		if(!isFull()) {
			rear++;
			queue[rear] = item;
			numItems++;
		}
	}
	
	public T dequeue() {
		if(!isEmpty()) {
			T toReturn = queue[front];
			queue[front] = null;
			for(int i = 0; i < rear; i++)
				queue[i] = queue[i+1];
			rear--;
			numItems--;
			return toReturn;
		}
		return null;
	}
	
	public String toString() {
		String toReturn = "";
		if(!isEmpty()) {
			for(int i = 0; i <= rear; i++)
				toReturn += queue[i].toString() + " ";
		} else toReturn = "Queue is empty!";
		return toReturn;
	}
}
