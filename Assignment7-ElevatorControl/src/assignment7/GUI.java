	package assignment7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.frameSetup();
	}
	
	public GUI() {}
	
	public void frameSetup() {
		JFrame frame = new JFrame("Elevator System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		FloorPanel firstFloor = new FirstFloorPanel();
		firstFloor.setBorder(BorderFactory.createTitledBorder("1st Floor"));
		FloorPanel secondFloor = new SecondFloorPanel();
		secondFloor.setBorder(BorderFactory.createTitledBorder("2nd Floor"));
		FloorPanel thirdFloor = new ThirdFloorPanel();
		thirdFloor.setBorder(BorderFactory.createTitledBorder("3rd Floor"));
		
		Comm c = new Comm(firstFloor, secondFloor, thirdFloor);
		firstFloor.setC(c);
		secondFloor.setC(c);
		thirdFloor.setC(c);
		
		frame.add(thirdFloor, BorderLayout.NORTH);
		frame.add(secondFloor, BorderLayout.CENTER);
		frame.add(firstFloor, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

class FloorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public int FLOOR;
	
	JButton btnCallElevator;
	JButton btnFirstOption;
	JButton btnSecondOption;
	JTextField txtInfo;
	Comm c;
	
	public FloorPanel() {
		setup();
	}
	
	private void setup() {
		btnCallElevator = new JButton("Call Elevator");
		btnFirstOption = new JButton("");
		btnSecondOption = new JButton("");
		txtInfo = new JTextField(25);
		txtInfo.setPreferredSize(btnCallElevator.getPreferredSize());
		txtInfo.setEditable(false);
		
		this.add(btnCallElevator);
		this.add(btnFirstOption);
		this.add(btnSecondOption);
		this.add(txtInfo);
		
		this.btnCallElevator.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.callElevator(FLOOR);
			}
		});
		
		this.setLayout(new SpringLayout());
		SpringUtilities.makeCompactGrid(this, 
										4, 1, 
										6, 6, 
										6, 6);
	}

	public void setTxtInfo(String txtInfo) {
		this.txtInfo.setText(txtInfo);
	}
	
	public void setStatusButtons(boolean status) {
		btnFirstOption.setEnabled(status);
		btnSecondOption.setEnabled(status);
		btnCallElevator.setEnabled(!status);
	}
	
	public void resetFloorPanel() {
		this.setTxtInfo("Door Closed");
		this.setStatusButtons(false);
	}

	public void setC(Comm c) {
		this.c = c;
	}
}

class FirstFloorPanel extends FloorPanel {
	private static final long serialVersionUID = 1L;
		
	public FirstFloorPanel() {
		super();
		setup();
		this.FLOOR = 1;
	}
	
	private void setup() {
		this.btnFirstOption.setText("GO TO 3rd FLOOR");
		this.btnSecondOption.setText("GO TO 2nd FLOOR");
		
		this.btnFirstOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.goTo(ElevatorState.THIRD_FLOOR);
			}
		});
		
		this.btnSecondOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.goTo(ElevatorState.SECOND_FLOOR);
			}
		});
	}
}

class SecondFloorPanel extends FloorPanel {
	private static final long serialVersionUID = 1L;
	
	public SecondFloorPanel() {
		super();
		setup();
		this.FLOOR = 2;
	}
	
	private void setup() {
		this.btnFirstOption.setText("GO UP");
		this.btnSecondOption.setText("GO DOWN");
		
		this.btnFirstOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.goTo(ElevatorState.THIRD_FLOOR);
			}
		});
		
		this.btnSecondOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.goTo(ElevatorState.FIRST_FLOOR);
			}
		});
	}
}

class ThirdFloorPanel extends FloorPanel {
	private static final long serialVersionUID = 1L;
	
	public ThirdFloorPanel() {
		super();
		setup();
		this.FLOOR = 3;
	}
	
	private void setup() {
		this.btnFirstOption.setText("GO TO 1st FLOOR");
		this.btnSecondOption.setText("GO TO 2nd FLOOR");
		
		this.btnFirstOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.goTo(ElevatorState.FIRST_FLOOR);
			}
		});
		
		this.btnSecondOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				c.goTo(ElevatorState.SECOND_FLOOR);
			}
		});
	}
}
