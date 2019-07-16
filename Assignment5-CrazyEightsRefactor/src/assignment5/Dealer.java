package assignment5;

/**
 * Deck included in this class,
 * array of players removed.
 */

public class Dealer {
	private Deck deck;
	
	public Dealer(int numOfPlayers) {
		deck = new Deck(numOfPlayers, false);
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void dealCards(Player[] players) {
		// Preconditions:
		// array of players instantiated
		deck.shuffle();
		for(int i = 0; i < players.length; i++) 
			players[i] = new Player(i+1);
		for(int i = 0; i < 7; i++) {
			for(Player p : players)
				p.getHand().addCard(deck.dealCard());
		}
		// Postconditions:
		// deck shuffled
		// players with 7 cards each
	}
	
	public void rebuildDeck(Deck discardDeck) {
		// Preconditions:
		// discardDeck instantiated and with cards in it
		discardDeck.shuffle();
		deck = discardDeck;
		// Postconditions:
		// deck renewed
	}

}
