package assignment6;

import java.io.File;

public class FileHandler {
	private static FileType file;
	
	/**
	 * Creates the file based on its extension
	 * @param f File to create
	 * @return File created
	 */
	public static FileType createFile(File f) {
		file = null;
		String extension = f.getName().substring(f.getName().lastIndexOf(".") + 1, 
				f.getName().length());
		if(extension.equalsIgnoreCase("txt")) 
			file = new FileTxt(f);
		/**
		else if(extension.equalsIgnoreCase("pdf"))
			file = new FilePdf(f);
		else if(extension.equalsIgnoreCase("docx"))
			file = new FileDoc(f);
		**/
		return file;
	}

}
