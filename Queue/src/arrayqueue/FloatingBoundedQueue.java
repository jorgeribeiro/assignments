package arrayqueue;

public class FloatingBoundedQueue<T> {
	private T[] queue;
	private int numItems;
	private int front = 0, rear;
	
	@SuppressWarnings("unchecked")
	public FloatingBoundedQueue(int cap) {
		queue = (T[]) new Object[cap];
		numItems = 0;
		rear = cap - 1;
	}
	
	@SuppressWarnings("unchecked")
	public FloatingBoundedQueue() {
		queue = (T[]) new Object[100];
		numItems = 0;
		rear = 99;
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
