package assignment1;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public interface Book {
	void add(String key, String value);
	public int getSize();
	void remove(String key);
	Iterator<Map.Entry<String, String>> getIterator();
	Set<String> getKeySet();
	boolean hasKey(String contact);
}
