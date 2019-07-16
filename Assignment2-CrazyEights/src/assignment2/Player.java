package assignment2;

import java.util.Iterator;

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
	
	/**
	 * Return if the hand is empty
	 * @return true if the hand has 0 cards,
	 * or false if the hand has > 0 hards
	 */
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

	/**
	 * Make the play. 
	 * There are four plays: first, if there are multiple cards to play
	 * second, if there is one card with the same rank
	 * third, if there is one card with the same suit
	 * and fourth, if there is an 8 in hand.
	 * The play is made in this order.
	 * If there is no card to play, the player pick up up to three cards
	 * If one of those is able to play, draw it.
	 * @param topCard card on top that defines the next play
	 * @param discardDeck deck to receive the card to be discarded
	 * @param regularDeck deck to pick a card if there is no card able to play
	 */
	public void play(Card topCard, Deck discardDeck, Deck regularDeck) {
		hand.sortByValue();
		boolean alreadyPlayed = false;
		Card handCard;
		Iterator<Card> it = hand.getIterator();
		
		while(it.hasNext() && !alreadyPlayed) { // Run through the cards in hand 
			handCard = it.next(); 
			int value = handCard.getValue();	
			if(value == topCard.getValue()) { // Check value
				if(it.hasNext() && it.next().getValue() == topCard.getValue()) { // Check multiple card
					it = hand.getIterator();
					while(it.hasNext()) {
						handCard = it.next();
						value = handCard.getValue();
						if(value == topCard.getValue()) { // Discard multiple cards
							discardDeck.addCard(handCard);
							handCard.copyCard(topCard);
							it.remove();
							alreadyPlayed = true;
						}
					}
				} else { // Discard single card by value
					discardDeck.addCard(handCard);
					handCard.copyCard(topCard);
					hand.removeCard(handCard);
					alreadyPlayed = true;
				}
			}
		} // end while loop
		it = hand.getIterator();
		while(it.hasNext() && !alreadyPlayed) {
			handCard = it.next();
			int suit = handCard.getSuit();
			if(suit == topCard.getSuit()) { // Check suit
				discardDeck.addCard(handCard);
				handCard.copyCard(topCard);
				it.remove();
				it = hand.getIterator();
				while(it.hasNext()) { // Check multiple card
					handCard = it.next();
					int value = handCard.getValue();
					if(value == topCard.getValue()) { // Discard multiple cards
						discardDeck.addCard(handCard);
						handCard.copyCard(topCard);
						it.remove();
					}
				}
				alreadyPlayed = true;
			}
		} // end while loop
		it = hand.getIterator();
		while(it.hasNext() && !alreadyPlayed) {
			handCard = it.next();
			if(handCard.getValue() == 8) { // Discard single 8 card
				discardDeck.addCard(handCard);
				handCard.copyCard(topCard);
				it.remove();
				alreadyPlayed = true;
			}
		} // end while loop
		int countPick = 0;
		while(countPick != 3 && !alreadyPlayed) { // Pick cards from deck
			if(regularDeck.cardsLeft() != 0) {
				Card pickCard = regularDeck.dealCard();
				countPick++;
				if(pickCard.getValue() == topCard.getValue() || pickCard.getValue() == 8) {
					discardDeck.addCard(pickCard);
					pickCard.copyCard(topCard);
					alreadyPlayed = true;
				} else if(pickCard.getSuit() == topCard.getSuit()) {
					discardDeck.addCard(pickCard);
					pickCard.copyCard(topCard);
					alreadyPlayed = true;
				} else
					hand.addCard(pickCard);
				if(alreadyPlayed) {
					it = hand.getIterator();
					while(it.hasNext()) {
						handCard = it.next();
						if(handCard.getValue() == pickCard.getValue()) {
							discardDeck.addCard(handCard);
							handCard.copyCard(topCard);
							it.remove();
						}
					} // end while loop
				}
			} else 
				Dealer.rebuildDeck(regularDeck, discardDeck);
		} // end while loop
		if(alreadyPlayed && topCard.getValue() == 8) {
			Card newTopCard = new Card(8, randomSuit());
			newTopCard.copyCard(topCard);
		}
	} // end play
	
	/**
	 * Generates a random suit chosen by the computer player
	 * when it plays a card 8
	 * @return the random suit generated
	 */
	public int randomSuit() {
		int suits = 4;
		int rSuit = (int) (Math.random() * suits);
		return rSuit;
	}

}
	