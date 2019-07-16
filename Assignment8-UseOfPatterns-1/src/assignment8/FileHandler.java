package assignment8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	public static String readFile(File file) {
		String text = "";
		if(checkExtension(file)) {
			String line = "";
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				while((line = reader.readLine()) != null)
					text += line + "\r\n";
				reader.close();
			} catch (IOException e) {
				System.err.println(">> Error reading file.");
			}
		} else System.err.println(">> Invalid file.");
		return text;
	}
	
	public static void writeFile(String resume) {
		try {
			if(resume.length() > 0) {
				BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Jorge\\Desktop\\Resume\\Resume.txt"));
				writer.write(resume);
				writer.close();
			}
		} catch(IOException e) {
			System.err.println(">> Error writing file.");
		}
	}
	
	private static boolean checkExtension(File file) {
		if(file != null)
			return (file.getName().endsWith(".txt"));
		return false;
	}
}
