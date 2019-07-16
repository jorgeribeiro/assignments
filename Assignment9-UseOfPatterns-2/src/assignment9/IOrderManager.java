package assignment9;

public interface IOrderManager {
	public void receiveNewOrder(Order order);
	public void creditVerification();
	void shipOrder(Order order);
}

class ManageOrderChain implements IOrderManager { // using Chain of Responsibility
	private Retailer retailer;
	private Handler credit;
	private Handler mailing;
	
	public ManageOrderChain(Retailer retailer) { 
		this.retailer = retailer;
		credit = new ProcessCredit();
		mailing = new ProcessMailing();
		credit.setSuccessor(mailing);
		mailing.setSuccessor(null);
	}

	@Override
	public void receiveNewOrder(Order order) {
		Customer customer = order.getCustomer();
		retailer.newOrder(order);
		customer.addOrder(order);
		if(retailer.isNewCustomer(customer)) {
			if(retailer.addToWaitingList(customer)) // returns true if added, false if customer is already in the list
				System.out.println(">> New customer ID " + customer.getID() + " added to waiting list");
		} else {
			System.out.println(">> Old customer ID " + customer.getID());
			shipOrder(order); // ships immediately because old customers already had their credit approved  
		}	
	}

	@Override
	public void creditVerification() {
		System.out.println(">> Processing customers on the waiting list...");
		while(!retailer.getWaitingList().isEmpty()) {
			Customer c = retailer.removeFromWaitingList();
			credit.processRequest(c);
			if(c.getTypeOfMail() != Mail.REJECTED) {
				retailer.addCustomer(c);  // adds the customer to the list because it has been approved
				shipOrder(c.getOrder(0)); // ships its first order from its list of orders
			} else
				System.out.println("\t Not shipped!");
		}
	}

	@Override
	public void shipOrder(Order order) {
		Customer c = order.getCustomer();
		System.out.println("\t Order is shipped with " + Util.printMail(c.getTypeOfMail()));
	}
	
}

class ManageOrderVisitor implements IOrderManager { // using Visitor
	private Retailer retailer;
	private IVisitor visitor;
	
	public ManageOrderVisitor(Retailer retailer) { 
		this.retailer = retailer; 
		visitor = new ProcessVisitor();
	}

	@Override
	public void receiveNewOrder(Order order) {
		Customer customer = order.getCustomer();
		retailer.newOrder(order);
		customer.addOrder(order);
		if(retailer.isNewCustomer(customer)) {
			if(retailer.addToWaitingList(customer)) // returns true if added, false if customer is already in the list
				System.out.println(">> New customer ID " + customer.getID() + " added to waiting list");
		} else {
			System.out.println(">> Old customer ID " + customer.getID());
			shipOrder(order); // ships immediately because old customers already had their credit approved  
		}
	}

	@Override
	public void creditVerification() {
		System.out.println(">> Processing customers on the waiting list...");
		while(!retailer.getWaitingList().isEmpty()) {
			Customer c = retailer.removeFromWaitingList();
			c.accept(visitor);
			if(c.getTypeOfMail() != Mail.REJECTED) {
				retailer.addCustomer(c);
				shipOrder(c.getOrder(0));
			} else
				System.out.println("\t Not shipped!");
		}
	}

	@Override
	public void shipOrder(Order order) {
		Customer c = order.getCustomer();
		System.out.println("\t Order is shipped with " + Util.printMail(c.getTypeOfMail()));
	}
	
}