package assignment3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final int TRIANGLE = 1;
	private static final int RECTANGLE = 2;
	private static final int CIRCLE = 3;
	public static int status = 1;
	private JButton btnTriangle;
	private JButton btnRectangle;
	private JButton btnCircle;
	private JPanel pnlButtons;
	private DrawingPanel drawingPanel;
	
	public MainPanel() {
		super();
		setup();
	}
	
	public static int getStatus() { return status; }
	
	public void setPoint(Comm c) { drawingPanel.setP(c); }
	
	private void setup() {
		this.setLayout(new BorderLayout());
		btnTriangle = new JButton("Triangle");
		btnRectangle = new JButton("Rectangle");
		btnCircle = new JButton("Circle");
		pnlButtons = new JPanel();
		drawingPanel = new DrawingPanel();
		
		pnlButtons.add(btnTriangle);
		pnlButtons.add(btnRectangle);
		pnlButtons.add(btnCircle);
		this.setLayout(new BorderLayout());
		this.add(pnlButtons, BorderLayout.NORTH);
		this.add(drawingPanel, BorderLayout.CENTER);
		
		btnTriangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { status = TRIANGLE; }
		});
		btnRectangle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) { status = RECTANGLE; }
		});
		btnCircle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {	status = CIRCLE; }
		});
	}
	
	
}
