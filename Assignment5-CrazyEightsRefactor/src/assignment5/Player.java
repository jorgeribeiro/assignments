package assignment5;

/**
 * Class invariant: id
 */

public class Player {
	private Hand hand;
	private int id;
	
	public Player(int id) {
		hand = new Hand();
		this.id = id;
	}
	
	public Hand getHand() {
		hand.sortByValue();
		return hand;
	}
	
	public int getId() {
		return id;
	}
	
	public void addCardToHand(Card c) {
		hand.addCard(c);
	}

	public boolean emptyHand() {
		return (hand.getCardCount() == 0);
	}
	
	public String printHand() {
		hand.sortByValue();
		return hand.printHand();
	}
	
	public int getCardCount() {
		return hand.getCardCount();
	}

}
	