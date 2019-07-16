package assignment9;

import java.util.ArrayList;

public class Main {
	static Retailer retailer = new Retailer();
	static ArrayList<Customer> customers = new ArrayList<Customer>();
	static ArrayList<Item> items = new ArrayList<Item>();
	static ArrayList<Order> orders = new ArrayList<Order>();

	public static void main(String[] args) {
		//IOrderManager chainManager = new ManageOrderChain(retailer);
		IOrderManager visitorManager = new ManageOrderVisitor(retailer);
		
		System.out.println(">> Creating data...");
		initData();
		System.out.println(">> Visitor Pattern <<");
		//System.out.println(">> Chain of Resposibility <<");
		System.out.println("\n>> Receiving new orders...");
		for(Order o : orders) {
			visitorManager.receiveNewOrder(o);
		}
		System.out.println("\n>> Verifying waiting list...");
		visitorManager.creditVerification();
		
		System.out.println("\n>> Receiving new orders by old customer...");
		orders.add(new Order(6, customers.get(2))); // index = 4
		orders.get(4).addItem(items.get(2), 1);
		orders.get(4).setTotal();
		visitorManager.receiveNewOrder(orders.get(4));
		
	}
	
	private static void initData() {
		customers.add(new Customer(1, "1234")); // index = 0
		customers.add(new Customer(2, "4321")); // index = 1
		customers.add(new Customer(3, "3214")); // index = 2
		customers.add(new Customer(4, "1243")); // index = 3
		
		items.add(new Item(1, "Beatles", 1, 79));       // index = 0
		items.add(new Item(2, "Elvis Presley", 1, 45)); // index = 1
		items.add(new Item(3, "Von Karajan", 1, 40)); 	// index = 2
		items.add(new Item(4, "Ray Matthews", 1, 60));  // index = 3
		
		orders.add(new Order(1, customers.get(0))); // index = 0
		orders.add(new Order(2, customers.get(1))); // index = 1
		orders.add(new Order(3, customers.get(2))); // index = 2
		orders.add(new Order(4, customers.get(3))); // index = 3
		
		orders.get(0).addItem(items.get(0), 1);
		orders.get(0).addItem(items.get(1), 2);
		orders.get(1).addItem(items.get(2), 1);
		orders.get(2).addItem(items.get(3), 2);
		orders.get(3).addItem(items.get(0), 3);
		
		for(Order o : orders) 
			o.setTotal(); // calculates total of the order		
	}

}
