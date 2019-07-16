package assignment3;

import java.awt.Graphics;

public class ShapeFactory {
	private static int status;
	
	public static void createShape(Graphics g, Comm c) {
		status = MainPanel.getStatus(); // Status of the selected shape
		switch(status) {
		case 1: // Triangle
			g.drawPolygon(new int[]{c.getX()+20,c.getX(),c.getX()-20}, 
					new int[]{c.getY()+20,c.getY()-20,c.getY()+20}, 3);
			break;
		case 2: // Rectangle
			g.drawRect(c.getX()-30, c.getY()-15, 60, 30);
			break;
		case 3: // Circle
			g.drawOval(c.getX()-25, c.getY()-25, 50, 50);
			break;
		}
	}
}
