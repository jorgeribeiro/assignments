package assignment3;

import java.awt.*;
import javax.swing.*;

public class GraphicsApp {

	public static void main(String[] args) {
		new GraphicsApp();
	}

	public GraphicsApp() { frameSetup(); }
	
	private void frameSetup() {
		JFrame frame = new JFrame("Graphic Program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 1));
		
		JPanel mainPanel = new MainPanel();
		frame.add(mainPanel);
		
		Comm c = new Comm();
		((MainPanel)mainPanel).setPoint(c);
		
		frame.setSize(450, 300);
		frame.setVisible(true);
	}
}

class Comm {
	private Point p;
	public Point getP() { return p; }
	public void setP(Point p) { this.p = p; }
	public int getX() { return (int)p.getX(); }
	public int getY() { return (int)p.getY(); }
}
