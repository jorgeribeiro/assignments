package assignment4;

import java.util.Map;

public class SUVSpec extends AutoSpec {
	private String numOfPassengers;
	
	public SUVSpec() { 
		super(); 
		setupSpecialProperty();
	}
	
	public SUVSpec(String maker, String model, String color, String numOfWheel, 
			String milesPerGallon, String year, String numOfPassengers) {
		super(maker, model, color, numOfWheel, milesPerGallon, year);
		this.numOfPassengers = numOfPassengers;
		properties.put("NumOfPassengers", numOfPassengers);
		setupSpecialProperty();
	}
	
	public void setupSpecialProperty() {
		this.SPECIAL_PROPERTY_ONE = "5";
		this.SPECIAL_PROPERTY_TWO = "7";
		this.SPECIAL_PROPERTY_KEY = "NumOfPassengers";
	}

	@Override
	public String toString() {
		String toReturn = "";
		toReturn = "\n\tCar:         " + this.getModel() + 
				   "\n\tMaker:       " + this.getMaker() + 
				   "\n\tColor:       " + this.getColor() +
				   "\n\t# of Wheels: " + this.getNumOfWheel() + 
				   "\n\tMil p/ Gal:  " + this.getMilesPerGallon() +
				   "\n\tYear:        " + this.getYear() + 
				   "\n\tCapacity:    " + this.numOfPassengers + " passengers";
		return toReturn; 
	}
	
	@Override
	public String getSpecialProperty() {
		return this.numOfPassengers;
	}
	
	@Override
	public void setSpecialProperty(String value) {
		this.numOfPassengers = value;
	}
	
	@Override
	public boolean matches(AutoSpec spec) {
		boolean isValid = false;
		
		if(spec == null)	return false;
		if(!(spec instanceof SUVSpec)) return false;
		
		for(Map.Entry<String, String> entry : this.getProperties().entrySet()) {
			for(Map.Entry<String, String> toCompare : spec.getProperties().entrySet()) {
				if(entry.getKey().equals(toCompare.getKey())) {
					if(entry.getValue().equals(toCompare.getValue()) || entry.getValue().equals(""))
						isValid = true;
					else
						return false;
					break;
				}
			}
		}
		return isValid;
	}
	
}
