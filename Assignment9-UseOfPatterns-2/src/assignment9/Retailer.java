package assignment9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Retailer {
	private ArrayList<Customer> customers; // customers with credit approved
	private ArrayList<Order> orders; // all the orders in the system
	private Queue<Customer> waitingList;
	
	public Retailer() {
		customers = new ArrayList<Customer>();
		orders = new ArrayList<Order>();
		waitingList = new LinkedList<Customer>();
	}

	public void newOrder(Order order) {
		orders.add(order);
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	public Customer getCustomer(int customerID) {
		for(Customer c : customers) {
			if(c.getID() == customerID)
				return c;
		}
		return null;
	}
	
	public boolean addToWaitingList(Customer customer) {
		for(Customer c : waitingList) {
			if(c.getID() == customer.getID())
				return false;
		}
		waitingList.add(customer);
		return true;
	}
	
	public Customer removeFromWaitingList() {
		return waitingList.remove();
	}
	
	public Queue<Customer> getWaitingList() {
		return waitingList;
	}
	
	public boolean isNewCustomer(Customer customer) {
		for(Customer c : customers) {
			if(c.getID() == customer.getID())
				return false;
		}
		return true;
	}
}
