package assignment9;

import java.util.ArrayList;

enum Mail {
	REGULAR, PRIORITY, EXPRESS, REJECTED
}

enum Credit {
	ADEQUATE, GOOD, EXCELLENT, INADEQUATE
}

public class Customer {
	private int ID;
	private String creditCard;
	private Mail typeOfMail;
	private Credit typeOfCredit;
	private ArrayList<Order> orders; // orders that belong to this customer

	public Customer(int iD, String creditCard) {
		ID = iD;
		this.creditCard = creditCard;
		orders = new ArrayList<Order>();
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public Credit getTypeOfCredit() {
		return typeOfCredit;
	}

	public void setTypeOfCredit(Credit typeOfCredit) {
		this.typeOfCredit = typeOfCredit;
	}
	
	public Mail getTypeOfMail() {
		return typeOfMail;
	}

	public void setTypeOfMail(Mail typeOfMail) {
		this.typeOfMail = typeOfMail;
	}

	public void addOrder(Order order) {	
		orders.add(order);
	}
	
	public Order getOrder(int index) {
		return orders.get(index);
	}
	
	public void accept(IVisitor v) {
		v.visit(this);
	}
}
