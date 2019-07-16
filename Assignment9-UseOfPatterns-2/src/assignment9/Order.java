package assignment9;

import java.util.ArrayList;

public class Order {
	private int ID;
	private Customer customer;
	private ArrayList<Item> items;
	private double total;
	
	public Order(int iD, Customer customer) {
		ID = iD;
		this.setCustomer(customer);
		items = new ArrayList<Item>();
		total = 0;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item, int quantity) {
		item.setQuantity(quantity);
		for(Item i : items) {
			if(i.getID() == item.getID()) {
				i.setQuantity(i.getQuantity() + item.getQuantity());
				return;
			}
		}
		items.add(item);
	}

	public double getTotal() {
		return total;
	}

	public void setTotal() {
		this.total = 0;
		for(Item i : items)
			this.total += i.getPrice();
	}

}
