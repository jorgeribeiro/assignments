	package assignment4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Inventory i = new Inventory();
	
	public static void main(String[] args) {
		initData();
		run();
	}
	
	public static void run() {
		boolean running = true;
		while(running) {
			System.out.println("\n\t >> Auto Inventory <<");
			System.out.println("\t1. Add an auto product");
			System.out.println("\t2. Get an auto product by serial number");
			System.out.println("\t3. Search an auto product by specifications");
			System.out.println("\t0. Exit");
			System.out.print("\tOption >> ");
			int opt = sc.nextInt();
			while(opt < 0 || opt > 3) {
				System.out.println("\tInvalid option. Insert values between 0 and 3.");
				System.out.print("\tOption >> ");
				opt = sc.nextInt();
			}
			running = runApp(opt);
		}
	}
	
	public static boolean runApp(int opt) {
		switch(opt) {
		case 0 :
			System.out.println("\n\tClosing...");
			return false;
		case 1 :
			runOptOne();
			break;
		case 2 :
			runOptTwo();
			break;
		case 3 :
			for(Auto a : i.search(runOptThree()))
				System.out.print(a);
			break;
		}
		return true;
	}
	
	public static void runOptOne() {
		int input;
		int serialNum;
		double price;
		Auto newAuto = null;
		AutoSpec newCar = null;
		Map<String, String> properties = new HashMap<String, String>();
		
		System.out.print("\t> Serial Number: ");
		serialNum = sc.nextInt();
		while(i.get(serialNum) != null) {
			System.out.print("\tSerial already in use."
					+ "\n\tInsert another: ");
			serialNum = sc.nextInt();
		}
		System.out.print("\t> Price: ");
		price = sc.nextDouble();		
		
		IO.printTypes();
		input = IO.validInput(1, 4);
		newCar = instantiateSpec(input); // instantiates the spec
		
		IO.printSpecialProperty(input);
		input = IO.validInput(1, 2);
		setProperty(input, newCar, properties);
		
		IO.setSpecifications(newCar);
		setMap(newCar, properties);
		
		newAuto = new Auto(serialNum, price, newCar);
		newAuto.setProperties(properties);
		i.add(newAuto);
	}
	
	public static void runOptTwo() {
		Auto toGet = null;
		
		System.out.print("\tInsert the serial number: ");
		toGet = i.get(sc.nextInt());
		while(toGet == null) {
			System.out.print("\tInvalid serial number."
					+ "\n\tInsert again: ");
			toGet = i.get(sc.nextInt());
		}
		System.out.println(toGet);
	}
	
	public static AutoSpec runOptThree() {
		int input;
		AutoSpec newCar = null;
		Map<String, String> properties = new HashMap<String, String>();
		
		System.out.println("\n\tTo ignore the car type and/or property, enter 0.");
		IO.printTypes();
		input = IO.validInput(0, 4);
		newCar = instantiateSpec(input); // instantiates the spec
		
		if(input != 0) {
			IO.printSpecialProperty(input);
			input = IO.validInput(0, 2);
		}
		setProperty(input, newCar, properties);
		
		System.out.println("\n\tTo ignore a specification, press enter.");
		IO.setSpecifications(newCar);
		setMap(newCar, properties);
		
		newCar.setProperties(properties);
		return newCar;
	}
	
	public static AutoSpec instantiateSpec(int type) {
		AutoSpec newCar = null;
		switch(type) {
		case 1 : 
			newCar = new SedanSpec(); 
			break;
		case 2 : 
			newCar = new TruckSpec(); 
			break;
		case 3 : 
			newCar = new SUVSpec();  
			break;
		case 4 : 
			newCar = new VanSpec(); 
			break;
		default : 
			newCar = new GeneralSpec(); 
			break;
		}
		return newCar;
	}
	
	public static void setProperty(int type, AutoSpec newCar, Map<String, String> properties) {
		switch(type) {
		case 1 : 
			newCar.setSpecialProperty(newCar.SPECIAL_PROPERTY_ONE);
			properties.put(newCar.SPECIAL_PROPERTY_KEY, newCar.SPECIAL_PROPERTY_ONE);
			break;
		case 2 :
			newCar.setSpecialProperty(newCar.SPECIAL_PROPERTY_TWO);
			properties.put(newCar.SPECIAL_PROPERTY_KEY, newCar.SPECIAL_PROPERTY_TWO);
			break;
		}
	}
	
	public static void setMap(AutoSpec newCar, Map<String, String> properties) {
		properties.put("Maker", newCar.getMaker());
		properties.put("Model", newCar.getModel());
		properties.put("Color", newCar.getColor());
		properties.put("NumOfWheel", newCar.getNumOfWheel());
		properties.put("MilesPerGallon", newCar.getMilesPerGallon());
		properties.put("Year", newCar.getYear());
	}
	
	public static void initData() {
		AutoSpec spec1 = new SedanSpec("Ford", "Fusion", "Gray", "4", "40", "2012", "4");
		Auto auto1 = new Auto(11111, 20000, spec1);
		AutoSpec spec2 = new SedanSpec("Ford", "Fiesta", "Blue", "2", "35", "2013", "2");
		Auto auto2 = new Auto(22222, 15000, spec2);
		AutoSpec spec3 = new TruckSpec("Volvo", "CM-400", "Black", "4", "50", "2014", "3");
		Auto auto3 = new Auto(33333, 50000, spec3);
		AutoSpec spec4 = new TruckSpec("Chevrolet", "Silverado", "Black", "4", "40", "2011", "2");
		Auto auto4 = new Auto(44444, 32000, spec4);
		AutoSpec spec5 = new TruckSpec("GMC", "Sierra", "Gray", "4", "60", "2015", "3");
		Auto auto5 = new Auto(55555, 36000, spec5);
		AutoSpec spec6 = new SUVSpec("Jeep", "Patriot", "Gray", "4", "45", "2013", "5");
		Auto auto6 = new Auto(66666, 28000, spec6);
		AutoSpec spec7 = new SUVSpec("Mitsubishi", "Outlander", "Black", "4", "40", "2016", "6");
		Auto auto7 = new Auto(77777, 31000, spec7);
		AutoSpec spec8 = new SUVSpec("Honda", "CR-V", "White", "4", "45", "2015", "5");
		Auto auto8 = new Auto(88888, 34000, spec8);
		AutoSpec spec9 = new SedanSpec("Toyota", "Corolla", "Red", "4", "35", "2013", "4");
		Auto auto9 = new Auto(99999, 23000, spec9);
		AutoSpec spec10 = new SedanSpec("Volkswagen", "Jetta", "Black", "2", "30", "2012", "4");
		Auto auto10 = new Auto(10101, 20000, spec10);
		AutoSpec spec11 = new VanSpec("Ford", "Cargo", "White", "2", "45", "2011", "Full");
		Auto auto11 = new Auto(11001, 27000, spec11);
		AutoSpec spec12 = new VanSpec("Nissan", "Passenger", "Gray", "40", "40", "2012", "Mini");
		Auto auto12 = new Auto(11101, 29000, spec12);
		AutoSpec spec13 = new VanSpec("GMC", "Savana", "Yellow", "4", "35", "2014", "Full");
		Auto auto13 = new Auto(11110, 28000, spec13);
		i.add(auto1);
		i.add(auto2);
		i.add(auto3);
		i.add(auto4);
		i.add(auto5);
		i.add(auto6);
		i.add(auto7);
		i.add(auto8);
		i.add(auto9);
		i.add(auto10);
		i.add(auto11);
		i.add(auto12);
		i.add(auto13);
	}

}
