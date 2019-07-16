package assignment2;

public class Dealer {
	
	private Player[] players;
	
	public Dealer(int numOfPlayers) {
		players = new Player[numOfPlayers];
	}
	
	public Player getPlayer(int index) {
		return players[index];
	}

	public void dealCards(Deck deck, int numOfPlayers) {
		deck.shuffle();
		for(int i = 0; i < numOfPlayers; i++) 
			players[i] = new Player(i+1);
		for(int i = 0; i < 7; i++) {
			for(Player p : players)
				p.getHand().addCard(deck.dealCard());
		}
	}
	
	public static void rebuildDeck(Deck regularDeck, Deck discardDeck) {
		discardDeck.shuffle();
		regularDeck = discardDeck;
	}

}
