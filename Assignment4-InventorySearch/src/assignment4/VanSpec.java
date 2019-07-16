package assignment4;

import java.util.Map;

public class VanSpec extends AutoSpec {
	private String vanType;
	
	public VanSpec() { 
		super(); 
		setupSpecialProperty();
	}
	
	public VanSpec(String maker, String model, String color, String numOfWheel, 
			String milesPerGallon, String year, String vanType) {
		super(maker, model, color, numOfWheel, milesPerGallon, year);
		this.vanType = vanType;
		properties.put("VanType", vanType);
		setupSpecialProperty();
	}
	
	public void setupSpecialProperty() {
		this.SPECIAL_PROPERTY_ONE = "Mini";
		this.SPECIAL_PROPERTY_TWO = "Full";
		this.SPECIAL_PROPERTY_KEY = "VanType";
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
				   "\n\tType:        " + this.vanType + "van";
		return toReturn; 
	}
	
	@Override
	public String getSpecialProperty() {
		return this.vanType;
	}
	
	@Override
	public void setSpecialProperty(String value) {
		this.vanType = value;
	}

	@Override
	public boolean matches(AutoSpec spec) {
		boolean isValid = false;
		
		if(spec == null)	return false;
		if(!(spec instanceof VanSpec)) return false;
		
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
