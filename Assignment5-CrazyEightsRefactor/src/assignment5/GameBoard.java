package assignment5;

import java.util.Scanner;

public class GameBoard {
	static Scanner  sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("This program lets you play the simple card game, "
				+ "\nCrazy Eights. The purpose of the game is to get rid "
	    		+ "\nof all your cards. You can play any card of the same suit as the one "
	    		+ "\nat the top of the pile."
	    		+ "\nYou can play any card of the same rank as the one at the top of the pile."
	    		+ "\nYou can play multiple cards with the same rank as well."
	    		+ "\nIf you have no cards with either the same rank or suit, you can play"
	    		+ "\na card with the rank 8, choosing the suit for the next play."
	    		+ "\nIf you have no cards with either the same rank or suit or a card with"
	    		+ "\nthe rank 8, you have to pick up cards from the deck. If you pick a valid"
	    		+ "\ncard, you can play it. If not, you can pick up to 3 cards. If none of"
	    		+ "\nthe cards is valid, you lose your play."
	    		+ "\nThe winner is the player who get rid of all cards first.");
	    System.out.println();
	    
	    char playAgain = 'n';
	    int numPlayers;
	    
	    do {
	    	System.out.print("How many players will play?(2-10): ");
	    	numPlayers = sc.nextInt();
	    	while(numPlayers < 2 || numPlayers > 10) {
	    		System.out.println("Number of players invalid.");
	    		System.out.print("Insert again (2-10): ");
	    		numPlayers = sc.nextInt();
	    	}
	    	run(numPlayers);
	    	System.out.print("Play again?(Y/N): ");
	    	sc.nextLine();
	    	playAgain = sc.next().charAt(0);
	    	playAgain = Character.toLowerCase(playAgain);
	    } while(playAgain == 'y');
	    System.out.println("\t\t >> Game Closed<<");
	} // end main
	
	private static void run(int numOfPlayers) {
		Player[] players = new Player[numOfPlayers];
		Controller controller = new Controller(numOfPlayers);
		int countPlayers = 0;
		boolean thereIsWinner = false;
		
		controller.dealCards(players);		
		System.out.println("\t\t >> Game started! <<");		
		do {
			if(countPlayers == numOfPlayers)
				countPlayers = 0;
			System.out.println("\n>> Player " + (countPlayers + 1) + " is in turn.");
			System.out.println("The card on the top is " + controller.getTopCard());
			controller.setPlayerInTurn(players[countPlayers]);
			if(countPlayers == 0)
				thereIsWinner = controller.playHuman();
			else
				thereIsWinner = controller.playMachine();
			countPlayers++;
		} while(!thereIsWinner);
		System.out.println("\t\t >> Game ended! <<");
		System.out.println("\t\t >> Winner: Player " + countPlayers + " <<");
	} // end run

}
