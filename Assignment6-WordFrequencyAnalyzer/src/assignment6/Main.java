package assignment6;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		WordAnalyzer wordAnalyzer = new WordAnalyzer();
		IO io = new GUI(wordAnalyzer);
		// to use a console application, the user has to just
		// create a class that implements IO, and instantiates it
		// IO io = new Console(wordAnalyzer);
		io.createInterface();
	}

}
