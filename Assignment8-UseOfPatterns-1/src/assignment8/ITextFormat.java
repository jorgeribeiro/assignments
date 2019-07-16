package assignment8;

public interface ITextFormat {
	String formatHeader(String header);
	String formatBorder(String border);
}

class TextFormatOne implements ITextFormat {
	@Override
	public String formatHeader(String header) {
		return header.toUpperCase() + "\r\n";
	}

	@Override
	public String formatBorder(String border) {
		String toReturn = "";
		for(int i = 0; i < border.length(); i++)
			toReturn += "-";
		return toReturn + "\r\n";
	}
}

class TextFormatTwo implements ITextFormat {
	@Override
	public String formatHeader(String header) {
		String toReturn = "=== " + header + " ===";
		return toReturn + "\r\n";
	}

	@Override
	public String formatBorder(String border) {
		String toReturn = "";
		for(int i = 0; i < border.length(); i++)
			toReturn += "_";
		return toReturn + "\r\n";
	}
}

// add new formats
