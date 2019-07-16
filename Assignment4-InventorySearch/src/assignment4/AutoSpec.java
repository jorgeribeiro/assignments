package assignment4;

import java.util.*;

public abstract class AutoSpec {
	public String SPECIAL_PROPERTY_ONE;
	public String SPECIAL_PROPERTY_TWO;
	public String SPECIAL_PROPERTY_KEY;
	
	Map<String, String> properties;
	private String maker;
	private String model;
	private String color;
	private String numOfWheel;
	private String milesPerGallon;
	private String year;
	
	public AutoSpec() {}
	
	public AutoSpec(String maker, String model, String color, String numOfWheel,
			String milesPerGallon, String year) {
		this.maker = maker;
		this.model = model;
		this.color = color;
		this.numOfWheel = numOfWheel;
		this.milesPerGallon = milesPerGallon;
		this.year = year;
		
		properties = new HashMap<String, String>();
		properties.put("Maker", maker);
		properties.put("Model", model);
		properties.put("Color", color);
		properties.put("NumOfWheel", numOfWheel);
		properties.put("MilesPerGallon", milesPerGallon);
		properties.put("Year", year);
	}
	
	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNumOfWheel() {
		return numOfWheel;
	}

	public void setNumOfWheel(String numOfWheel) {
		this.numOfWheel = numOfWheel;
	}

	public String getMilesPerGallon() {
		return milesPerGallon;
	}

	public void setMilesPerGallon(String milesPerGallon) {
		this.milesPerGallon = milesPerGallon;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public int size() {
		return properties.size();
	}

	abstract boolean matches(AutoSpec spec);
	
	abstract String getSpecialProperty();
	
	abstract void setSpecialProperty(String value);
	
}
