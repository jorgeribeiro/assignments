package linkedqueue;

public class LinkedQueue<T> {
	private QueueNode<T> front, rear;
	private int numItems;
	
	public LinkedQueue() {
		front = rear = null;
		numItems = 0;
	}
	
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public void enqueue(T item) {
		QueueNode<T> newNode = new QueueNode<T>(item);
		if(isEmpty()) {
			front = newNode;
		} else {
			rear.setLink(newNode);
		}
		rear = newNode;
		numItems++;
	}
	
	public T dequeue() {
		T toReturn = null;
		if(!isEmpty()) {
			toReturn = front.getInfo();
			front = front.getLink();
			numItems--;
			if(isEmpty()) rear = null;
		}
		return toReturn;
	}
	
	public String toString() {
		String toReturn = "";
		QueueNode<T> cursor = front;
		while(cursor != null) {
			toReturn += cursor.getInfo().toString() + " ";
			cursor = cursor.getLink();
		}
		return toReturn;
	}
}

class QueueNode<T> {
	private T info;
	private QueueNode<T> link;
	
	public QueueNode(T info) {
		this.info = info;
		link = null;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public QueueNode<T> getLink() {
		return link;
	}

	public void setLink(QueueNode<T> link) {
		this.link = link;
	}

}