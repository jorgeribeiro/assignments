package assignment1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class CarAppOps implements AppOps {
	private Scanner sc;
	String input;
	CarBook carBook;
	IntentBook intentBook;
	
	public CarAppOps() {
		appInit();
	}

	@Override
	public String getInput() {
		input = sc.nextLine();
		return input;
	}

	@Override
	public String getResult(String opt) {
		int i = Integer.parseInt(opt);
		HashMap<String, String> result = carBook.getDescription(i);
		String toReturn = "";
		Iterator<Map.Entry<String, String>> it = result.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			toReturn += "> Here is the description of " + entry.getKey() + ": " + entry.getValue() + "\n";
		}
		return toReturn;
	}

	@Override
	public void appInit() {
		sc = new Scanner(System.in);
		carBook = new CarBook();
		intentBook = new IntentBook();
		DataHandler.getData("cars.txt", carBook);
		DataHandler.getData("intents.txt", intentBook);
	}

	@Override
	public void run() {
		do {
			System.out.print("> Query model info (q) or Enter a vehicle purchase intent (i) or Exit (e): ");
			input = getInput();
			if(input.equalsIgnoreCase("q")) {
				System.out.println("> Pick a model from the following list (by typing the index number) :");
				System.out.println(carBook.getCarsName());
				System.out.print("#> ");
				String opt = getInput();
				System.out.println(getResult(opt));
				
			} else if(input.equalsIgnoreCase("i")) {
				System.out.print("> Enter your contact info: ");
				String contact = getInput();
				System.out.print("> Enter your purchase intent: ");
				String description = getInput();
				intentBook.add(contact, description);
				
			} else {
				DataHandler.saveData("cars.txt", carBook);
				DataHandler.saveData("intents.txt", intentBook);
				break;
			}
		} while(true);
	}

}
