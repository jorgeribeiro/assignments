package assignment5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Controller {
	private Player playerInTurn;
	private Dealer dealer;
	private Deck regularDeck;
	private Deck discardDeck;
	private Card topCard;
	private Scanner sc;
	
	public Controller(int numOfPlayers) {
		dealer = new Dealer(numOfPlayers);
		regularDeck = dealer.getDeck();
		discardDeck = new Deck(numOfPlayers, true);
		sc = new Scanner(System.in);
	}
	
	public void setPlayerInTurn(Player p) {
		this.playerInTurn = p;
	}
	
	public Card getTopCard() {
		return topCard;
	}
	
	public void dealCards(Player[] players) {
		// Preconditions:
		// dealer instantiated
		dealer.dealCards(players);
		topCard = regularDeck.dealCard();
		while(topCard.getValue() == 8) {
			regularDeck.addCard(topCard);
			regularDeck.shuffle();
			topCard = regularDeck.dealCard();
		}
		// Postconditions:
		// players with 7 cards each
		// first card set to start the game
	}
	
	public boolean playHuman() {
		System.out.println("Your turn to play");
		System.out.println(playerInTurn.printHand());
		if(!validPlay()) {
			System.out.println("There are no available cards in your hand. "
					+ "\nYou must pick from the deck.");
			int countPick = 0;
			while(countPick != 3) {
				System.out.print("Press enter to pick a card...");
				sc.nextLine();
				Card cardPicked = pickCard();
				countPick++;
				System.out.println("You picked " + cardPicked);
				if(cardPicked.getValue() == topCard.getValue() || cardPicked.getValue() == 8 || 
						cardPicked.getSuit() == topCard.getSuit()) {
					System.out.println("Card playable!");
					System.out.println(playerInTurn.printHand());
					break;
				} else System.out.println("Card not playable.");
				
			}
		}
		if(validPlay()) {
			System.out.print("Card #> ");
			int cardChosen = sc.nextInt();
			Card cardDraw = verifyDraw(cardChosen);
			while(cardDraw == null) {
				System.out.println("Invalid draw.");
				System.out.print("Insert again #> ");
				cardChosen = sc.nextInt();
				cardDraw = verifyDraw(cardChosen);
			}
			ArrayList<Card> multipleCards = verifyMultiple(cardDraw);
			if(multipleCards.size() > 0) {
				int count = 1;
				for(Card c : multipleCards) {
					System.out.println("\t " + count + ". " + c);
					count++;
				}
				if(multipleCards.size() == 1) {
					System.out.print("The card above has the same rank. "
							+ "Would you like to draw it?(Y/N): ");
				} else {
					System.out.print("The cards above have the same rank. "
							+ "Would you like to draw them?(Y/N): ");
				}
				char opt = sc.next().charAt(0);
				opt = Character.toLowerCase(opt);
				if(opt == 'y') {
					if(multipleCards.size() > 1) {
						System.out.println("Which one do you want to go on the top?");
						System.out.print("Card #> ");
						cardChosen = sc.nextInt();
						while(cardChosen < 1 || cardChosen > count) {
							System.out.println("Invalid option.");
							System.out.print("Insert again #> ");
							cardChosen = sc.nextInt();
						}
						int countLoops = 1;
						for(Card c : multipleCards) {
							if(countLoops != cardChosen)
								discardDeck.addCard(c);
							countLoops++;
						}
					}
					discardDeck.addCard(multipleCards.get(cardChosen-1));
					multipleCards.get(cardChosen-1).copyCard(topCard);
				} else {
					for(Card c : multipleCards)
						playerInTurn.addCardToHand(c);
				}
			}
			if(topCard.getValue() == 8) {
				System.out.println("Choose the suit for the next turn: ");
				System.out.println("\t 1. Spades"
						+ "\n\t 2. Hearts"
						+ "\n\t 3. Diamonds"
						+ "\n\t 4. Clubs");
				System.out.print("Suit #> ");
				cardChosen = sc.nextInt();
				while(cardChosen < 1 || cardChosen > 4) {
					System.out.println("Invalid option.");
					System.out.print("Insert again #> ");
					cardChosen = sc.nextInt();	
				}
				topIs8(cardChosen);
			}
		} else System.out.println("There are no available cards in your hand. "
				+ "\nNext player will play.");
		return (playerInTurn.emptyHand());
	}
	
	public boolean playMachine() {
		System.out.print("Press enter to continue...");
		sc.nextLine();
		sc.nextLine();
		if(!validPlay()) {
			int countPick = 0;
			while(countPick != 3) {
				Card cardPicked = pickCard();
				countPick++;
				if(cardPicked.getValue() == topCard.getValue() || cardPicked.getValue() == 8 || 
						cardPicked.getSuit() == topCard.getSuit()) {
					break;
				}
			}
		}
		if(validPlay()) {
			Iterator<Card> it;
			int index = 0;
			while(verifyDraw(index) == null)
				index++;
			ArrayList<Card> multipleByValue = verifyMultiple(topCard);
			ArrayList<Card> multipleBySuit = verifyMultiple(topCard);
			if(multipleByValue.size() >= multipleBySuit.size()) {
				it = multipleByValue.iterator();
				while(it.hasNext()) {
					Card c = it.next();
					discardDeck.addCard(c);
					c.copyCard(topCard);
					it.remove();
				}
				it = multipleBySuit.iterator();
				while(it.hasNext()) {
					Card c = it.next();
					playerInTurn.addCardToHand(c);
					it.remove();
				}
			} else {
				it = multipleBySuit.iterator();
				while(it.hasNext()) {
					Card c = it.next();
					discardDeck.addCard(c);
					c.copyCard(topCard);
					it.remove();
				}
				it = multipleByValue.iterator();
				while(it.hasNext()) {
					Card c = it.next();
					playerInTurn.addCardToHand(c);
					it.remove();
				}
			}
			if(topCard.getValue() == 8)
				topIs8(randomSuit());
		}
		System.out.println(">> Player " + playerInTurn.getId() + " has " + playerInTurn.getCardCount() + " cards.");
		return (playerInTurn.emptyHand());
	}
	
	private boolean validPlay() {
		Iterator<Card> cards = playerInTurn.getHand().getIterator();
		while(cards.hasNext()) {
			Card card = cards.next();
			if(card.getValue() == topCard.getValue() || card.getValue() == 8)
				return true;
			else if(card.getSuit() == topCard.getSuit())
				return true;
		}
		return false;
	}
	
	private Card pickCard() {
		if(regularDeck.cardsLeft() == 0)	
			dealer.rebuildDeck(discardDeck);
		Card pickCard = regularDeck.dealCard();
		playerInTurn.addCardToHand(pickCard);
		return pickCard;
	}
	
	private Card verifyDraw(int cardChosen) {
		Iterator<Card> cards = playerInTurn.getHand().getIterator();
		Card cardToDraw = null;
		if(cardChosen < 1 || cardChosen > playerInTurn.getCardCount())
			return null;
		for(int i = 0; i < cardChosen; i++)
			cardToDraw = cards.next();
		if((cardToDraw.getSuit() == topCard.getSuit()) 
				|| (cardToDraw.getValue() == topCard.getValue()) 
				|| (cardToDraw.getValue() == 8)) {
			cardToDraw.copyCard(topCard);
			discardDeck.addCard(cardToDraw);
			cards.remove();
			return cardToDraw;
		} else return null;
	}
	
	private ArrayList<Card> verifyMultiple(Card toCompare) {
		ArrayList<Card> multipleCards = new ArrayList<Card>();
		Iterator<Card> cards = playerInTurn.getHand().getIterator();
		Card multipleToDraw = null;
		while(cards.hasNext()) {
			multipleToDraw = cards.next();
			if(multipleToDraw.getValue() == toCompare.getValue()) {
				multipleCards.add(multipleToDraw);
				cards.remove();
			}
		}
		return multipleCards;
	}
	
	private void topIs8(int cardChosen) {
		Card newTopCard = null;
		switch(cardChosen) {
		case 1 : newTopCard = new Card(0, Card.SPADES);
		break;
		case 2 : newTopCard = new Card(0, Card.HEARTS);
		break;
		case 3 : newTopCard = new Card(0, Card.DIAMONDS);
		break;
		case 4 : newTopCard = new Card(0, Card.CLUBS);
		break;
		}
		newTopCard.copyCard(topCard);
	}
	
	private int randomSuit() {
		int suits = 4;
		int rSuit = (int) (Math.random() * suits) + 1;
		return rSuit;
	}
	
}
