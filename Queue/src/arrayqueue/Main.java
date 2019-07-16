package arrayqueue;

import linkedqueue.LinkedQueue;

public class Main {

	public static void main(String[] args) {
		FloatingBoundedQueue<Integer> floatingBQ = new FloatingBoundedQueue<Integer>(4);
		FixedBoundedQueue<Integer> fixedBQ = new FixedBoundedQueue<Integer>(4);
		UnboundedQueue<Integer> unboundedQ = new UnboundedQueue<Integer>(4);
		LinkedQueue<Integer> linkedQ = new LinkedQueue<Integer>();
		
		floatingBQ.enqueue(1);
		floatingBQ.enqueue(2);
		floatingBQ.enqueue(3);
		floatingBQ.enqueue(4);
		System.out.println(floatingBQ);
		floatingBQ.dequeue();
		floatingBQ.enqueue(5);
		System.out.println(floatingBQ);
		
		System.out.println("=======");
		
		fixedBQ.enqueue(1);
		fixedBQ.enqueue(2);
		fixedBQ.enqueue(3);
		fixedBQ.enqueue(4);
		System.out.println(fixedBQ);
		fixedBQ.dequeue();
		fixedBQ.enqueue(5);
		System.out.println(fixedBQ);
		
		System.out.println("=======");
		
		unboundedQ.enqueue(1);
		unboundedQ.enqueue(2);
		unboundedQ.enqueue(3);
		unboundedQ.enqueue(4);
		System.out.println(unboundedQ);
		unboundedQ.dequeue();
		unboundedQ.enqueue(5);
		System.out.println(unboundedQ);
		
		System.out.println("=======");
		
		linkedQ.enqueue(1);
		linkedQ.enqueue(2);
		linkedQ.enqueue(3);
		linkedQ.enqueue(4);
		System.out.println(linkedQ);
		linkedQ.dequeue();
		linkedQ.enqueue(5);
		System.out.println(linkedQ);
		
	}

}
