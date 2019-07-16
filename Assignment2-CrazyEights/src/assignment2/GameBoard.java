package assignment2;

import java.util.Iterator;
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
		Deck regularDeck = new Deck(numOfPlayers, false);  // Deck to pick
		Deck discardDeck = new Deck(numOfPlayers, true);   // Deck to draw
		Dealer dealer = new Dealer(numOfPlayers);   // Dealer of the game
		Card topCard;							    // Card at the top of the discard pile
		Player playerInTurn = null;				    // Player in turn
		int countPlayers = 0;
		
		dealer.dealCards(regularDeck, numOfPlayers);
		topCard = regularDeck.dealCard();
		while(topCard.getValue() == 8) {
			regularDeck.addCard(topCard);
			regularDeck.shuffle();
			topCard = regularDeck.dealCard();
		} // end while loop
		System.out.println("\t\t >> Game started! <<");
		
		while(true) { // Loop ends when there is a winner
			if(countPlayers == numOfPlayers)
				countPlayers = 0;
			playerInTurn = dealer.getPlayer(countPlayers);
			System.out.println("\n>> Player " + playerInTurn.getId() + " is in turn.");
			System.out.println("The card on the top is " + topCard.toString());
			/* The game starts with the player 1, which is the human player */
			if(playerInTurn.getId() == 1) {
				System.out.println("You turn to play. ");
				System.out.println(playerInTurn.printHand());	
				playerOnePlay(topCard, playerInTurn, discardDeck, regularDeck);
			} else {
				System.out.print("Press enter to continue...");
				sc.nextLine();
				sc.nextLine();
				playerInTurn.play(topCard, discardDeck, regularDeck);
				System.out.println("Player " + playerInTurn.getId() + " has "
						+ playerInTurn.getCardCount() + " card(s).");				
			}
			if(playerInTurn.emptyHand())
				break;
			countPlayers++;
		} // end while loop
		System.out.println("\t\t >> Game ended! <<");
		System.out.println("\t\t >> Winner: Player " + playerInTurn.getId() + " <<");
		
	} // end run
	
	public static void playerOnePlay(Card topCard, Player playerInTurn, Deck discardDeck, Deck regularDeck) {
		Iterator<Card> cards = playerInTurn.getHand().getIterator();
		Card cardToDraw = null;
		Card multipleToDraw = null;
		boolean validPlay = false;
		int cardChosen;
		
		// Verifies if there is a valid card in the player's hand
		while(cards.hasNext()) {
			Card card = cards.next();
			if(card.getValue() == topCard.getValue() || card.getValue() == 8)
				validPlay = true;
			else if(card.getSuit() == topCard.getSuit())
				validPlay = true;
		}
		if(!validPlay) {
			System.out.println("There are no available cards in your hand. "
					+ "\nYou must pick from the deck.");
			int countPick = 0;
			while(countPick != 3 && !validPlay) {
				System.out.print("Press enter to pick a card...");
				sc.nextLine();
				if(regularDeck.cardsLeft() != 0) {
					Card pickCard = regularDeck.dealCard();
					playerInTurn.getHand().addCard(pickCard);
					countPick++;
					System.out.println("You picked " + pickCard.toString());
					if(pickCard.getValue() == topCard.getValue() || pickCard.getValue() == 8) {
						System.out.println("Card playable!");
						validPlay = true;
					} else if(pickCard.getSuit() == topCard.getSuit()) {
						System.out.println("Card playable!");
						validPlay = true;
					} else
						System.out.println("Card not playable.");
				} else {
					System.out.println(">> Empty deck! Rebuilding it...");
					Dealer.rebuildDeck(regularDeck, discardDeck);
					System.out.println(">> Rebuilt!");
				}	
			} // end while loop
			if(countPick == 3 && !validPlay) {
				System.out.println("There are no available cards in your hand. "
						+ "\nNext player will play.");
				return;
			}
			else if(validPlay)
				System.out.println(playerInTurn.printHand());
		}
		
		while(validPlay) {
			System.out.print("Card #> ");
			cardChosen = sc.nextInt();
			while(cardChosen < 1 || cardChosen > playerInTurn.getCardCount()) {
				System.out.println("Invalid option.");
				System.out.print("Insert again (1-" + playerInTurn.getCardCount() + ")> ");
				cardChosen = sc.nextInt();				
			} // end while loop
			cards = playerInTurn.getHand().getIterator();
			for(int i = 0; i < cardChosen; i++)
				cardToDraw = cards.next();
			if((cardToDraw.getSuit() == topCard.getSuit()) 
					|| (cardToDraw.getValue() == topCard.getValue()) 
					|| (cardToDraw.getValue() == 8))
				 break;
			else
				System.out.println("Invalid draw.");
		} // end while loop
		cardToDraw.copyCard(topCard);
		discardDeck.addCard(cardToDraw);
		cards.remove();
		
		cards = playerInTurn.getHand().getIterator();
		int count = 0;
		Card[] multipleCards = new Card[20];
		while(cards.hasNext()) {
			multipleToDraw = cards.next();
			if(multipleToDraw.getValue() == cardToDraw.getValue()) {
				multipleCards[count] = multipleToDraw;
				count++;
				System.out.println("\t " + count + ". " + multipleToDraw.toString());
				cards.remove();
			}	
		} // end while loop
		if(count == 1) {
			System.out.print("The card above has the same rank. "
					+ "Would you like to draw it?(Y/N): ");
			char opt;
			sc.nextLine();
			opt = sc.next().charAt(0);
			opt = Character.toLowerCase(opt);
			if(opt == 'y') {
				discardDeck.addCard(multipleCards[0]);
				multipleCards[0].copyCard(topCard);
			} else
				playerInTurn.getHand().addCard(multipleCards[0]);
		} else if(count > 1) {
			System.out.print("The cards above have the same rank. "
					+ "Would you like to draw them?(Y/N): ");
			char opt;
			sc.nextLine();
			opt = sc.next().charAt(0);
			opt = Character.toLowerCase(opt);
			if(opt == 'y') {
				System.out.println("Which one do you want to go on the top? ");
				System.out.print("Card #> ");
				cardChosen = sc.nextInt();
				while(cardChosen < 1 || cardChosen > count) {
					System.out.println("Invalid option.");
					System.out.print("Insert again (1-" + count + ")> ");
					cardChosen = sc.nextInt();				
				} // end while loop	
				int countLoops = 0;
				for(Card c : multipleCards) {
					if(countLoops+1 != cardChosen)
						discardDeck.addCard(c);
					countLoops++;
				}
				discardDeck.addCard(multipleCards[cardChosen-1]);
				multipleCards[cardChosen-1].copyCard(topCard);
			} else {
				for(Card c : multipleCards)
					playerInTurn.getHand().addCard(c);
			}
		}
		
		if(validPlay && topCard.getValue() == 8) {
			Card newTopCard = null;
			System.out.println("Choose the suit for the next turn: ");
			System.out.println("\t 1. Spades"
					+ "\n\t 2. Hearts"
					+ "\n\t 3. Diamonds"
					+ "\n\t 4. Clubs");
			System.out.print("Card #> ");
			cardChosen = sc.nextInt();
			while(cardChosen < 1 || cardChosen > 4) {
				System.out.println("Invalid option.");
				System.out.print("Insert again (1-4)> ");
				cardChosen = sc.nextInt();	
			} // end while loop
			switch(cardChosen) {
			case 1: newTopCard = new Card(0, Card.SPADES);
			break;
			case 2: newTopCard = new Card(0, Card.HEARTS);
			break;
			case 3: newTopCard = new Card(0, Card.DIAMONDS);
			break;
			case 4: newTopCard = new Card(0, Card.CLUBS);
			break;
			}
			newTopCard.copyCard(topCard);
		}
	} // end playerOnePlay

}
