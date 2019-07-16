package assignment8;

public class Section {
	private String title;
	private String content;
	
	public Section(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return content;
	}
}

class Heading extends Section {
	public Heading(String title, String content) { super(title, content); }
}

class Objective extends Section {
	public Objective(String title, String content) { super(title, content); }
}

class Education extends Section {
	public Education(String title, String content) { super(title, content); }
}

class Experience extends Section {
	public Experience(String title, String content) { super(title, content); }
}

class Skills extends Section {
	public Skills(String title, String content) { super(title, content); }
}

class References extends Section {
	public References(String title, String content) { super(title, content); }
}
// add new sections
