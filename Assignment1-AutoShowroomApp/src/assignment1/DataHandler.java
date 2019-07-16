package assignment1;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class DataHandler {
	public static void getData(String filename, Book book) {
        String name = null, description = null;
        try {
            FileReader fileReader = new FileReader(filename);            
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((name = bufferedReader.readLine()) != null && (description = bufferedReader.readLine()) != null) {
             	book.add(name, description);
            } 
            bufferedReader.close();
        }
        catch(IOException ex) {
            System.out.println("Error reading file"); 
            ex.printStackTrace();                  
        }
	}
	
	public static void saveData(String filename, Book book) {
	    FileWriter fileWriter;  
	    BufferedWriter bufferedWriter; 
		try {
			fileWriter = new FileWriter(filename);
			bufferedWriter = new BufferedWriter(fileWriter); 
            Iterator<Map.Entry<String, String>> it = book.getIterator();
            while ( it.hasNext() ) {
            	Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
            	bufferedWriter.write( pair.getKey() );
            	bufferedWriter.write(System.getProperty ( "line.separator" ));
            	bufferedWriter.write( pair.getValue() );
            	bufferedWriter.write(System.getProperty ( "line.separator" ));
    		}
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file");
            ex.printStackTrace();
        }
	}

}
