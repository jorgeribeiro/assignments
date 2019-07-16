package assignment4;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Auto> inventory;
	
	public Inventory() {
		inventory = new ArrayList<Auto>();
	}
	
	public void add(Auto auto) {
		inventory.add(auto);
	}
	
	public Auto get(int serialNumber) {
		for(Auto a : inventory) {
			if(a.getSerialNumber() == serialNumber)
				return a;
		}
		return null;
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		for(Auto a : inventory) {
			toReturn += a.toString();
		}
		return toReturn;
	}

	ArrayList<Auto> search(AutoSpec spec) {
		ArrayList<Auto> toReturn = new ArrayList<Auto>();
		AutoSpec auxSpec;
		for(Auto a : inventory) {
			auxSpec = a.getSpec();
			if(spec.matches(auxSpec))
				toReturn.add(a);
		}
		return toReturn;
	}

}
