package assignment8;

public class SectionBuilder {
	public Section buildSection(String title, String content) {
		if(title.equals(Resume.HEADING))
			return buildHeading(title, content);
		else if(title.equals(Resume.OBJECTIVE))
			return buildObjective(title, content);
		else if(title.equals(Resume.EDUCATION))
			return buildEducation(title, content);
		else if(title.equals(Resume.EXPERIENCE))
			return buildExperience(title, content);
		else if(title.equals(Resume.SKILLS))
			return buildSkills(title, content);
		else if(title.equals(Resume.REFERENCES))
			return buildReferences(title, content);
		return null;
	}
	
	private Section buildHeading(String title, String content) {
		return new Heading(title, content);
	}
	
	private Section buildObjective(String title, String content) {
		return new Objective(title, content);
	}
	
	private Section buildEducation(String title, String content) {
		return new Education(title, content);
	}
	
	private Section buildExperience(String title, String content) {
		return new Experience(title, content);
	}
	
	private Section buildSkills(String title, String content) {
		return new Skills(title, content);
	}
	
	private Section buildReferences(String title, String content) {
		return new References(title, content);
	}
	// add new builders for new sections
}
