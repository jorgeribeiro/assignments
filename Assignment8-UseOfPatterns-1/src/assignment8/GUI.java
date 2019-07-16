package assignment8;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI {
	private final String newLine = "\r\n";
	private SectionBuilder sectionBuilder;
	private Resume resume;
	private JFrame frame;
	
	public GUI(SectionBuilder sectionBuilder, Resume resume) {
		this.sectionBuilder = sectionBuilder;
		this.resume = resume;
	}
	
	public void createGUI() {
		frameSetup();
	}
	
	private void frameSetup() {
		frame = new JFrame("Resume Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		MainPanel mainPanel = new MainPanel(frame);
		frame.add(mainPanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MainPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		String[] sections = {"Heading", "Objective", "Education", 
							 "Experience", "Skills", "References"}; // add new sections
		
		JButton btnChooseFile;
		JTextField txtPath;
		JFileChooser fcOpen;
		JCheckBox heading, objective, education,
				  experience, skills, references;
		JCheckBox[] checkBoxes;
		JRadioButton rdBtnFormatOne, rdBtnFormatTwo;
		JButton btnPrintResume;
		JButton btnCreateResume;
		JDialog dialogDropDown;
		String sectionSelected;
		
		public MainPanel(Frame frame) { setup(); }
		
		private void setup() {
			this.add(setupTopButtons());
			this.add(setupCheckBoxes());
			this.add(setupFormatOptions());
			this.add(setupBottomButtons());
			this.setLayout(new GridLayout(4, 1));
		}
		
		private JPanel setupTopButtons() {
			JPanel topPanel = new JPanel();
			
			btnChooseFile = new JButton("Choose file...");
			txtPath = new JTextField();
			txtPath.setPreferredSize(btnChooseFile.getPreferredSize());
			txtPath.setEditable(false);
			fcOpen = new JFileChooser("C:\\Users\\Jorge\\Desktop\\Resume");
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
			fcOpen.setFileFilter(filter);
			
			topPanel.add(txtPath);
			topPanel.add(btnChooseFile);
			
			btnChooseFile.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					txtPath.setText("");
					int rVal = fcOpen.showOpenDialog(topPanel);
					if(rVal == JFileChooser.APPROVE_OPTION) {
						txtPath.setText(fcOpen.getSelectedFile().getName());
						setupDialog();
					}
				}
			});
			
			return topPanel;
		}
		
		private JPanel setupCheckBoxes() {
			JPanel checkBoxesPanel = new JPanel();
			
			checkBoxes = new JCheckBox[6]; // 6 is the number of sections
			heading = new JCheckBox(sections[0]);
			heading.setEnabled(false);
			checkBoxes[0] = heading;
			objective = new JCheckBox(sections[1]);
			objective.setEnabled(false);
			checkBoxes[1] = objective;
			education = new JCheckBox(sections[2]);
			education.setEnabled(false);
			checkBoxes[2] = education;
			experience = new JCheckBox(sections[3]);
			experience.setEnabled(false);
			checkBoxes[3] = experience;
			skills = new JCheckBox(sections[4]);
			skills.setEnabled(false);
			checkBoxes[4] = skills;
			references = new JCheckBox(sections[5]);
			references.setEnabled(false);
			checkBoxes[5] = references;
			// add new checkboxes
			
			for(int i = 0; i < checkBoxes.length; i++) {
				checkBoxesPanel.add(checkBoxes[i]);
			}
			
			return checkBoxesPanel;
		}
		
		private JPanel setupFormatOptions() {
			JPanel formatOptPanel = new JPanel();
			
			rdBtnFormatOne = new JRadioButton("Style Format I");
			rdBtnFormatTwo = new JRadioButton("Style Format II");
			
			ButtonGroup group = new ButtonGroup();
			group.add(rdBtnFormatOne);
			group.add(rdBtnFormatTwo);
			rdBtnFormatOne.setSelected(true);
			formatOptPanel.add(rdBtnFormatOne);
			formatOptPanel.add(rdBtnFormatTwo);
			
			return formatOptPanel;
		}
		
		private JPanel setupBottomButtons() {
			JPanel bottomPanel = new JPanel();
			
			btnPrintResume = new JButton("Print Resume");
			btnCreateResume = new JButton("Create Resume");	
			
			bottomPanel.add(btnPrintResume);
			bottomPanel.add(btnCreateResume);
			
			btnPrintResume.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					printResume();
				}
			});
			
			btnCreateResume.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					createResume();
				}
			});
			
			return bottomPanel;
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void setupDialog() {
			dialogDropDown = new JDialog(frame, "Choose section", Dialog.ModalityType.DOCUMENT_MODAL);
			Container container = dialogDropDown.getContentPane();
			container.setLayout(new GridLayout(1, 2));
			JComboBox sectionList = new JComboBox(sections);
			JButton btnOk = new JButton("OK");
			container.add(sectionList);
			container.add(btnOk);
			
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					sectionSelected = (String) sectionList.getSelectedItem();
					buildSection();
					dialogDropDown.dispose();
				}
			});
			
			dialogDropDown.pack();
			dialogDropDown.setLocationRelativeTo(null);
			dialogDropDown.setVisible(true);
		}
		
		private void buildSection() {
			for(JCheckBox cb : checkBoxes) {
				if(sectionSelected.equals(cb.getText())) {
					cb.setSelected(true);
					cb.setEnabled(true);
					resume.addSection(sectionSelected, 
									  sectionBuilder.buildSection(sectionSelected, FileHandler.readFile(fcOpen.getSelectedFile())));
				}
			}
		}
		
		private void printResume() {
			ITextFormat formatText = new TextFormatOne();
			if(rdBtnFormatOne.isSelected())
				formatText = new TextFormatOne();
			else if(rdBtnFormatTwo.isSelected())
				formatText = new TextFormatTwo();
			// add new formats
			
			boolean anotherPrint = false;
			boolean printHeader = false;
			for(JCheckBox cb : checkBoxes) {
				if(cb.isSelected()) {
					if(!printHeader) {
						System.out.println(formatText.formatHeader("Resume"));
						printHeader = true;
					}
					if(anotherPrint)
						System.out.println(formatText.formatBorder("*****************************************************************"));
					System.out.println(formatText.formatHeader(cb.getText()));
					System.out.println(resume.printSection(cb.getText()));
					anotherPrint = true;
				}
			}
		}
		
		private void createResume() {
			String textResume = "";
			ITextFormat formatText = new TextFormatOne();
			if(rdBtnFormatOne.isSelected())
				formatText = new TextFormatOne();
			else if(rdBtnFormatTwo.isSelected())
				formatText = new TextFormatTwo();
			// add new formats
			
			boolean anotherPrint = false;
			boolean printHeader = false;
			for(JCheckBox cb : checkBoxes) {
				if(cb.isSelected()) {
					if(!printHeader) {
						textResume += formatText.formatHeader("Resume");
						textResume += newLine;
						printHeader = true;
					}
					if(anotherPrint) 
						textResume += formatText.formatBorder("*****************************************************************");
					textResume += formatText.formatHeader(cb.getText());
					textResume += resume.printSection(cb.getText());
					anotherPrint = true;
				}
			}
			FileHandler.writeFile(textResume);
		}
		
	} // end MainPanel
} // end GUI
