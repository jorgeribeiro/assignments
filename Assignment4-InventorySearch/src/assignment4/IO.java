package assignment4;

import java.util.Scanner;

public class IO {
	static Scanner sc = new Scanner(System.in);
	
	public static int validInput(int min, int max) {
		System.out.print("\tOption: ");
		int input = sc.nextInt();
		while(input < min || input > max) {
			System.out.println("\tInvalid input. (" + min + "-" + max + ")");
			System.out.print("\tOption: ");
			input = sc.nextInt();
		} 
		return input;
	}
	
	public static void printTypes() {
		System.out.println("\t> Type: "
				+ "\n\t1. Sedan"
				+ "\n\t2. Truck"
				+ "\n\t3. SUV"
				+ "\n\t4. Van");
	}
	
	public static void printSpecialProperty(int type) {
		System.out.println("\n\t> Special Property: ");
		switch(type) {
		case 1 :
			System.out.println("\t1. 2 doors"
					+ "\n\t2. 4 doors");
			break;
		case 2 :
			System.out.println("\t1. Load 2 tons"
					+ "\n\t2. Load 3 tons");
			break;
		case 3 :
			System.out.println("\t1. Carry 5 passengers"
					+ "\n\t2. Carry 7 passengers");
			break;
		case 4 :
			System.out.println("\t1. Minivan"
					+ "\n\t2. Fullvan");
			break;
		}
	}
	
	public static void setSpecifications(AutoSpec spec) {
		sc.nextLine(); // to clean the buffer
		System.out.print("\t1> Maker: ");
		spec.setMaker(sc.nextLine());
		System.out.print("\t2> Model: ");
		spec.setModel(sc.nextLine());
		System.out.print("\t3> Wheel (2 or 4): ");
		spec.setNumOfWheel(sc.nextLine());
		System.out.print("\t4> Color: ");
		spec.setColor(sc.nextLine());
		System.out.print("\t5> Miles per Gallon: ");
		spec.setMilesPerGallon(sc.nextLine());
		System.out.print("\t6> Year: ");
		spec.setYear(sc.nextLine());
	}
	
}
