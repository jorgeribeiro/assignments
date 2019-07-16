package assignment6;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTxt implements FileType {
	private File f;

	public FileTxt(File f) {
		this.f = f;
	}
	
	@Override
	public File getFile() {
		return f;
	}
	
	@Override
	public boolean checkExtension() {
		if(f != null)
			return (f.getName().endsWith(".txt"));
		return false;
	}
	
	@Override
	public String readTextFile() {
		String line = "";
		String text = "";
		Path path = Paths.get(this.f.getAbsolutePath());
		try {
			BufferedReader reader = Files.newBufferedReader(path);
			while((line = reader.readLine()) != null)
				text += line;
			reader.close();
		} catch (IOException e) {
			System.err.println(">> Error reading file.");
		}
		return text;
	}	

}
