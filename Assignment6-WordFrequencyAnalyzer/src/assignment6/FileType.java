package assignment6;

import java.io.*;

public interface FileType {
	File getFile();
	boolean checkExtension();
	String readTextFile() throws IOException;
}
