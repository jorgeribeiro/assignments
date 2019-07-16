package assignment4;

import java.text.DecimalFormat;
import java.util.Map;

public class Auto {
	private int serialNumber;
	private double price;
	private AutoSpec spec;
	
	public Auto(int serialNumber, double price, AutoSpec spec) {
		this.serialNumber = serialNumber;
		this.price = price;
		this.spec = spec;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public AutoSpec getSpec() {
		return spec;
	}

	public void setSpec(AutoSpec spec) {
		this.spec = spec;
	}
	
	public Map<String, String> getProperties() {
		return this.spec.getProperties();
	}
	
	public void setProperties(Map<String, String> properties) {
		this.spec.properties = properties;
	}
	
	@Override
	public String toString() {
		DecimalFormat money = new DecimalFormat("$#####.00");
		String toReturn = this.spec.toString();
		toReturn += "\n\tPrice:       " + money.format(this.getPrice()) + "\n";
		return toReturn;
	}
	
}
