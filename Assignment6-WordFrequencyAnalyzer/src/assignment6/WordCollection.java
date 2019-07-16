package assignment6;

import java.util.*;

public class WordCollection {
	private HashMap<String, Integer> words;
	
	public WordCollection() {
		words = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> getWords() {
		return words;
	}
	
	public void setWords(HashMap<String, Integer> words) {
		this.words = words;
	}

	public int get(String key) {
		key = key.toLowerCase();
		if(words.containsKey(key)) return words.get(key);
		return 0;		
	}
	
	public void add(String word, int num) {
		words.put(word, num);
	}
	
	public int size() {
		return words.size();
	}
	
	public void clear() {
		words.clear();
	}
	
	public String toString() {
		String toReturn = "";
		for(Map.Entry<String, Integer> entry : words.entrySet()) {
			toReturn += entry.getKey() + " " + entry.getValue() + "\n";
		}
		return toReturn;
	}
}
