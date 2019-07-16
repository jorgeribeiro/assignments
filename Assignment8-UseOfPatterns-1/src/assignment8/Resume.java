package assignment8;

import java.util.HashMap;

public class Resume {
	public final static String HEADING = "Heading";
	public final static String OBJECTIVE = "Objective";
	public final static String EDUCATION = "Education";
	public final static String EXPERIENCE = "Experience";
	public final static String SKILLS = "Skills";
	public final static String REFERENCES = "References";
	// add new sections
	
	private HashMap<String, Section> sections;
	
	public Resume() {
		sections = new HashMap<String, Section>();
	}
	
	public void addSection(String key, Section section) {
		sections.put(key, section);
	}
	
	public String printSection(String key) {
		return sections.get(key).toString();
	}
}
