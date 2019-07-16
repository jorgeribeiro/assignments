package assignment9;

public class Item {
	private int ID;
	private String name;
	private int quantity;
	private double price;
	
	public Item(int iD, String name, int quantity, double price) {
		ID = iD;
		this.name = name;
		this.quantity = quantity;
		this.setPrice(price);
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
