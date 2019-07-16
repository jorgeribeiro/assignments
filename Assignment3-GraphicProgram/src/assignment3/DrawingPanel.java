package assignment3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Comm c;
	
	public DrawingPanel() {
		super();
		this.setBackground(Color.WHITE);
		this.addMouseListener(new MsListener());
	}
	
	public void setP(Comm c) {
		this.c = c;
	}
	
	class MsListener extends MouseAdapter {		
		public void mouseClicked(MouseEvent e) {
			c.setP(e.getPoint());
			ShapeFactory.createShape(getGraphics(), c);
		}
	}
}
