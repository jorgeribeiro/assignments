package assignment1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CarBook implements Book {
	private HashMap<String, String> cars;
	
	public CarBook() {
		cars = new HashMap<String, String>();
	}
	
	@Override
	public void add(String car, String description) {
		if(!hasKey(car))
			cars.put(car, description);
	}
	
	@Override
	public int getSize() {
		return cars.size();
	}
	
	@Override
	public void remove(String car) {
		cars.remove(car);
	}
	
	@Override
	public Iterator<Map.Entry<String, String>> getIterator() {
		return cars.entrySet().iterator();
	}
	
	@Override
	public Set<String> getKeySet() {
		return cars.keySet();
	}
	
	@Override
	public boolean hasKey(String car) {
		Set<String> cars = getKeySet();
		return cars.contains(car.toLowerCase());
	}
	
	public HashMap<String, String> getDescription(int i) {
		HashMap<String, String> toReturn = new HashMap<String, String>();
		int count = 1;
		for(Map.Entry<String, String> entry : cars.entrySet()) {
			if(count == i) {
				toReturn.put(entry.getKey(), entry.getValue());
				break;
			}
			count++;
		}
		return toReturn;
	}
	
	public String getCarsName() {
		String toReturn = "";
		int count = 1;
		for(Map.Entry<String, String> entry : cars.entrySet()) {
			toReturn += "\t " + count + ". " + entry.getKey().toString() + "\n";
			count++;
		}
		return toReturn;
	}
}
