package assignment8;


public class Main {
	
	public static void main(String[] args) {
		SectionBuilder sectionBuilder = new SectionBuilder();
		Resume resume = new Resume();
		GUI gui = new GUI(sectionBuilder, resume);
		
		gui.createGUI();
	}
}
