package assignment1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class IntentBook implements Book {
	private HashMap<String, String> intents;
	
	public IntentBook() {
		intents = new HashMap<String, String>();
	}
	
	@Override
	public void add(String contact, String description) {
		intents.put(contact, description);
	}
	
	@Override
	public int getSize() {
		return intents.size();
	}
	
	@Override
	public void remove(String contact) {
		intents.remove(contact);
	}
	
	@Override
	public Iterator<Map.Entry<String, String>> getIterator() {
		return intents.entrySet().iterator();
	}
	
	@Override
	public Set<String> getKeySet() {
		return intents.keySet();
	}
	
	@Override
	public boolean hasKey(String contact) {
		Set<String> intents = getKeySet();
		return intents.contains(contact.toLowerCase());
	}
	
}
