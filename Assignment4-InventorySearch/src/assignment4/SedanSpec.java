package assignment4;

import java.util.Map;

public class SedanSpec extends AutoSpec {	
	private String numOfDoors;
	
	public SedanSpec() { 
		super(); 
		setupSpecialProperty();
	}
	
	public SedanSpec(String maker, String model, String color, String numOfWheel, 
			String milesPerGallon, String year, String numOfDoors) {
		super(maker, model, color, numOfWheel, milesPerGallon, year);
		this.numOfDoors = numOfDoors;
		properties.put("NumOfDoors", numOfDoors);
		setupSpecialProperty();
	}
	
	public void setupSpecialProperty() {
		this.SPECIAL_PROPERTY_ONE = "2";
		this.SPECIAL_PROPERTY_TWO = "4";
		this.SPECIAL_PROPERTY_KEY = "NumOfDoors";
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
				   "\n\t# of Doors:  " + this.numOfDoors;
		return toReturn; 
	}
	
	@Override
	public String getSpecialProperty() {
		return this.numOfDoors;
	}
	
	@Override
	public void setSpecialProperty(String value) {
		this.numOfDoors = value;
	}

	@Override
	public boolean matches(AutoSpec spec) {
		boolean isValid = false;
		
		if(spec == null)	return false;
		if(!(spec instanceof SedanSpec)) return false;
		
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
