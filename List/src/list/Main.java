package list;

public class Main {

	public static void main(String[] args) {
		BoundedArraySortedList<Integer> boundedASL = new BoundedArraySortedList<Integer>(4);
		UnboundedArraySortedList<Integer> unboundedASL = new UnboundedArraySortedList<Integer>(4);
		LinkedSortedList<Integer> linkedSL = new LinkedSortedList<Integer>();
		CircularLinkedUnsortedList<Integer> circularLL = new CircularLinkedUnsortedList<Integer>();
		DoublyLinkedSortedList<Name> doublyLL = new DoublyLinkedSortedList<Name>();
		
		boundedASL.add(4);
		boundedASL.add(2);
		boundedASL.add(1);
		boundedASL.add(3);
		System.out.println(boundedASL.contains(2));
		System.out.println(boundedASL);
		boundedASL.remove(3);
		boundedASL.add(3);
		boundedASL.add(5);
		System.out.println(boundedASL);
		
		unboundedASL.add(4);
		unboundedASL.add(2);
		unboundedASL.add(1);
		unboundedASL.add(3);
		System.out.println(unboundedASL.contains(2));
		System.out.println(unboundedASL);
		unboundedASL.remove(3);
		unboundedASL.add(3);
		unboundedASL.add(5);
		System.out.println(unboundedASL);
		
		linkedSL.add(4);
		linkedSL.add(2);
		linkedSL.add(1);
		linkedSL.add(3);
		System.out.println(linkedSL.contains(2));
		System.out.println(linkedSL);
		linkedSL.remove(3);
		linkedSL.add(3);
		linkedSL.add(5);
		System.out.println(linkedSL);
		
		circularLL.add(1);
		circularLL.add(2);
		circularLL.add(3);
		circularLL.add(4);
		System.out.println(circularLL.contains(2));
		System.out.println(circularLL);
		circularLL.remove(4);
		circularLL.add(4);
		circularLL.add(5);
		System.out.println(circularLL);
		
		Name n = new Name("Francisca", "Maria");
		doublyLL.add(new Name("Jorge", "Ribeiro"));
		doublyLL.add(new Name("Lucas", "Raphael"));
		doublyLL.add(new Name("Marco", "Andre"));
		doublyLL.add(n);
		System.out.println(doublyLL);
		doublyLL.remove(new Name("Lucas", "Raphael"));
		System.out.println(doublyLL);
		
		Name n2 = new Name("Francisca", "Maria");
		Name n3 = new Name("Francisca", "Maria");
		System.out.println(n2.compareTo(n3));
		
		System.exit(0);
	}

}
