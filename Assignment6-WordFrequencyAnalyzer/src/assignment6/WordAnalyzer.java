package assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class WordAnalyzer {
	private WordCollection wordCollection;
	
	public WordAnalyzer() {
		wordCollection = new WordCollection();
	}
	
	public void analyzeText(File file) {
		try {
			Scanner sc = new Scanner(file);
			sc.useDelimiter("[^a-zA-Z']+");
			while(sc.hasNext()) {
				String word = sc.next();
				word = word.toLowerCase();
				int num = wordCollection.get(word);
				if(num != 0)
					wordCollection.add(word, num+1);
				else
					wordCollection.add(word, 1);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println(">> File not found.");
		}
	}
	
	public void sortWords() {
		HashMap<String, Integer> words = wordCollection.getWords();
		ArrayList<String> mapKeys = new ArrayList<String>(words.keySet());
		ArrayList<Integer> mapValues = new ArrayList<Integer>(words.values());
		Collections.sort(mapValues);
		LinkedHashMap<String, Integer> tempMap = new LinkedHashMap<String, Integer>(); // used a linkedhashmap because hashmap sort by order
																					   // when putting into the collection
		Iterator<Integer> valueIt = mapValues.iterator();
		while(valueIt.hasNext()) {
			int value = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();
			
			while(keyIt.hasNext()) {
				String key = keyIt.next();
				int comp = words.get(key);
				
				if(comp == value) {
					words.remove(key);
					mapKeys.remove(key);
					tempMap.put(key, value);
					break;
				}
			}
		}
		wordCollection.setWords(tempMap);
	}
	
	public WordCollection getResults() {
		return wordCollection;
	}
	
	public int size() {
		return wordCollection.size();
	}
}
