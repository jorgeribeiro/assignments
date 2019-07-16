package assignment6;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

public class GUI implements IO {
	private WordAnalyzer wordAnalyzer;
	
	public GUI(WordAnalyzer wordAnalyzer) {
		this.wordAnalyzer = wordAnalyzer;
	}

	@Override
	public void createInterface() {
		frameSetup(500, 360);
	}
	
	private void frameSetup(int width, int height) {
		JFrame frame = new JFrame("Word Frequency");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		SplitPanePanel pnlSplitPane = new SplitPanePanel();
		ButtonPanel pnlButton = new ButtonPanel(pnlSplitPane, wordAnalyzer);
		SearchPanel pnlSearch = new SearchPanel(wordAnalyzer);
		SortPanel pnlSort = new SortPanel(pnlSplitPane, wordAnalyzer);
		
		frame.getContentPane().add(pnlButton, BorderLayout.NORTH);
		frame.getContentPane().add(pnlSearch, BorderLayout.CENTER);
		frame.getContentPane().add(pnlSort, BorderLayout.SOUTH);
		
		frame.setSize(width, height);
		frame.setVisible(true);
	}
	
}

class ButtonPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JTextArea pathFile;
	JButton btnChoose;
	JButton btnAnalyze;
	JFileChooser fc;
	SplitPanePanel splitPanePanel;
	WordAnalyzer analyzer;
	
	public ButtonPanel(SplitPanePanel pnlSplitPane, WordAnalyzer wordAnalyzer) {
		this.splitPanePanel = pnlSplitPane;
		this.analyzer = wordAnalyzer;
		setup();
	}
	
	private void setup() {
		pathFile = new JTextArea(1, 10);
		pathFile.setEditable(false);
		btnChoose = new JButton("Choose file...");
		btnAnalyze = new JButton("Get file & analyze");
		fc = new JFileChooser("C:\\Users\\Jorge Ribeiro\\Desktop");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt"); // new extensions can be added
		fc.setFileFilter(filter);
		
		JPanel btnPanel = new JPanel();
		JPanel splitPanel = new JPanel();
		btnPanel.add(pathFile);
		btnPanel.add(btnChoose);
		btnPanel.add(btnAnalyze);
		splitPanel.add(splitPanePanel);
		this.setLayout(new BorderLayout());
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(splitPanel, BorderLayout.CENTER);
		
		btnChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pathFile.setText("");
				splitPanePanel.setTxtWords("");
				splitPanePanel.setTxtFile("");
				analyzer.getResults().clear();
				int rVal = fc.showOpenDialog(ButtonPanel.this);
				if(rVal == JFileChooser.APPROVE_OPTION)
					pathFile.setText(fc.getSelectedFile().getName());
			}
		});
		
		btnAnalyze.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(pathFile.getText().length() > 0) {
					analyzer.getResults().clear();
					FileType file = FileHandler.createFile(fc.getSelectedFile());
					if(file != null && file.checkExtension()) {
						splitPanePanel.setTxtWords(getWords(file));
						splitPanePanel.setTxtFile(getText(file));
					}
				}
			}
		});
	}
	
	public String getWords(FileType file) {
		analyzer.analyzeText(file.getFile());
		String txtWords = "Number of unique words: \n" + analyzer.getResults().size() + "\n";
		txtWords += analyzer.getResults().toString();
		return txtWords;
	}
	
	public String getText(FileType file) {
		String txtText = "";
		try {
			txtText = file.readTextFile();
		} catch (IOException e) { }
		return txtText;
	}

}

class SplitPanePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JTextArea txtWords;
	JTextArea txtFile;
	JScrollPane leftScroll;
	JScrollPane rightScroll;
	JSplitPane splitPane;
	
	public SplitPanePanel() {
		setup();
	}
	
	private void setup() {
		txtWords = new JTextArea(10, 10);
		txtFile = new JTextArea(10, 20);
		leftScroll = new JScrollPane(txtWords);
		rightScroll = new JScrollPane(txtFile);
		setupTextArea();
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, 
								   leftScroll, rightScroll);
		splitPane.setDividerLocation(150);
		
		Dimension minimumSize = new Dimension(100, 50);
		leftScroll.setPreferredSize(minimumSize);
		rightScroll.setPreferredSize(minimumSize);
		splitPane.setPreferredSize(new Dimension(400, 200));
		this.add(splitPane);
	}
	
	private void setupTextArea() {
		txtWords.setEditable(false);
		txtWords.setWrapStyleWord(true);
		txtWords.setLineWrap(true);
		DefaultCaret caret = (DefaultCaret)txtWords.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
		txtFile.setEditable(false);
		txtFile.setWrapStyleWord(true);
		txtFile.setLineWrap(true);
		caret = (DefaultCaret)txtFile.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE); 
		leftScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rightScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	}

	public void setTxtWords(String txtWords) {
		this.txtWords.setText(txtWords);
	}

	public void setTxtFile(String txtFile) {
		this.txtFile.setText(txtFile);
	}
	
}

class SearchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JTextField txtSearch;
	JTextArea txtFrequency;
	WordCollection words;
	
	public SearchPanel(WordAnalyzer wordAnalyzer) {
		this.words = wordAnalyzer.getResults();
		setup();
	}
	
	private void setup() {
		txtSearch = new JTextField(10);
		txtFrequency = new JTextArea(1,10);
		txtFrequency.setEditable(false);
		
		this.add(txtSearch);
		this.add(txtFrequency);
		
		txtSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtFrequency.setText("");
				int frequency = words.get(txtSearch.getText());
				if(frequency != 0)
					txtFrequency.setText(Integer.toString(frequency));
				else if(frequency == 0 && txtSearch.getText().length() > 0)
					txtFrequency.setText("Word not found.");
			}
		});
	}
	
}

class SortPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JButton btnSort;
	SplitPanePanel splitPanePanel;
	WordAnalyzer analyzer;

	public SortPanel(SplitPanePanel pnlSplitPane, WordAnalyzer wordAnalyzer) {
		setup();
		this.splitPanePanel = pnlSplitPane;
		this.analyzer = wordAnalyzer;
	}
	
	private void setup() {	
		btnSort = new JButton("Sort words based on frequency");
		this.add(btnSort);
		
		btnSort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(analyzer.size() > 0) {
					analyzer.sortWords();
					String txtWords = "Number of unique words: \n" + analyzer.size() + "\n";
					txtWords += analyzer.getResults().toString();
					splitPanePanel.setTxtWords(txtWords);
				}
			}
		});
	}
}
