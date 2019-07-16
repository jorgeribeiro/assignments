package stack;

public class Main {
	public static void main(String[] args) {
		BoundedArrayStack<Integer> boundedS = new BoundedArrayStack<Integer>(4);
		UnboundedArrayStack<Integer> unboundedS = new UnboundedArrayStack<Integer>(4);
		LinkedStack<Integer> linkedStk = new LinkedStack<Integer>();
		
		boundedS.push(4);
		boundedS.push(5);
		boundedS.push(1);
		boundedS.push(8);		
		System.out.println(boundedS);
		boundedS.pop();
		System.out.println(boundedS);
		
		unboundedS.push(4);
		unboundedS.push(5);
		unboundedS.push(1);
		unboundedS.push(8);		
		System.out.println(unboundedS);
		unboundedS.pop();
		System.out.println(unboundedS);
		
		linkedStk.push(4);
		linkedStk.push(5);
		linkedStk.push(1);
		linkedStk.push(8);		
		System.out.println(linkedStk);
		linkedStk.pop();
		System.out.println(linkedStk);
		
		System.exit(0);
	}
}
