package assignment4;

import java.util.Map;

public class GeneralSpec extends AutoSpec {

	public GeneralSpec() { super(); }
	
	public GeneralSpec(String maker, String model, String color, String numOfWheel, String milesPerGallon,
			String year) {
		super(maker, model, color, numOfWheel, milesPerGallon, year);
	}
	
	@Override
	String getSpecialProperty() { return null; } // GeneralSpec doesn't need a special property

	@Override
	void setSpecialProperty(String value) {} // GeneralSpec doesn't need a special property

	@Override
	public boolean matches(AutoSpec spec) {
		boolean isValid = false;
		
		if(spec == null) return false;
		
		for(Map.Entry<String, String> entry : spec.getProperties().entrySet()) {
			for(Map.Entry<String, String> toCompare : this.getProperties().entrySet()) {
				if(entry.getKey().equals(toCompare.getKey())) {
					if(entry.getValue().equals(toCompare.getValue()) || toCompare.getValue().equals(""))
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
