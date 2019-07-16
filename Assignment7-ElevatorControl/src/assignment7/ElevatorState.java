package assignment7;

public class ElevatorState {
	public static final int FIRST_FLOOR  = 1;
	public static final int SECOND_FLOOR = 2;
	public static final int THIRD_FLOOR  = 3;
	
	private int currentFloor;
	
	public ElevatorState() {
		currentFloor = FIRST_FLOOR;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}
	
}

class Comm {
	private ElevatorState elevatorState;
	private FloorPanel firstFloor, secondFloor, thirdFloor;
	private FloorPanel currentFloor;
	
	public Comm(FloorPanel firstFloor, FloorPanel secondFloor, FloorPanel thirdFloor) {
		elevatorState = new ElevatorState();
		this.firstFloor = firstFloor;
		this.secondFloor = secondFloor;
		this.thirdFloor = thirdFloor;
		initialState();
	}

	public int getElevatorState() {
		return elevatorState.getCurrentFloor();
	}

	public void setElevatorState(int floor) {
		elevatorState.setCurrentFloor(floor);
	}
	
	public void initialState() {
		resetPanels();
		firstFloor.setTxtInfo("Door Open");
		firstFloor.setStatusButtons(true);
		setElevatorState(ElevatorState.FIRST_FLOOR);
	}
	
	public void goTo(int newFloor) {
		resetPanels();
		setCurrentFloor(newFloor);
		currentFloor.setTxtInfo("elevator arrives - door opens - user exits");
		currentFloor.setStatusButtons(true);
		setElevatorState(newFloor);
	}
	
	public void callElevator(int callingFloor) {
		resetPanels();
		setCurrentFloor(callingFloor);
		if(callingFloor > getElevatorState())
			currentFloor.setTxtInfo("elevator called up - door opens");
		else
			currentFloor.setTxtInfo("elevator called down - door opens");
		currentFloor.setStatusButtons(true);
		setElevatorState(callingFloor);
	}
	
	private void setCurrentFloor(int currentFloor) {
		if(currentFloor == ElevatorState.FIRST_FLOOR)
			this.currentFloor = firstFloor;
		else if(currentFloor == ElevatorState.SECOND_FLOOR)
			this.currentFloor = secondFloor;
		else if(currentFloor == ElevatorState.THIRD_FLOOR)
			this.currentFloor = thirdFloor;
	}
	
	private void resetPanels() {
		firstFloor.resetFloorPanel();
		secondFloor.resetFloorPanel();
		thirdFloor.resetFloorPanel();
	}
	
}