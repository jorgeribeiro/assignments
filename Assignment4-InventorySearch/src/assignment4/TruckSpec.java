package assignment4;

import java.util.Map;

public class TruckSpec extends AutoSpec {
	private String loadCapacity;
	
	public TruckSpec() { 
		super(); 
		setupSpecialProperty();
	}
	
	public TruckSpec(String maker, String model, String color, String numOfWheel, 
			String milesPerGallon, String year, String loadCapacity) {
		super(maker, model, color, numOfWheel, milesPerGallon, year);
		this.loadCapacity = loadCapacity;
		properties.put("LoadCapacity", loadCapacity);
		setupSpecialProperty();
	}
	
	public void setupSpecialProperty() {
		this.SPECIAL_PROPERTY_ONE = "2";
		this.SPECIAL_PROPERTY_TWO = "3";
		this.SPECIAL_PROPERTY_KEY = "LoadCapacity";
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
				   "\n\tCapacity:    " + this.loadCapacity + " tons";
		return toReturn; 
	}
	
	@Override
	public String getSpecialProperty() {
		return this.loadCapacity;
	}
	
	@Override
	public void setSpecialProperty(String value) {
		this.loadCapacity = value;
	}

	@Override
	public boolean matches(AutoSpec spec) {
		boolean isValid = false;
		
		if(spec == null)	return false;
		if(!(spec instanceof TruckSpec)) return false;
		
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
