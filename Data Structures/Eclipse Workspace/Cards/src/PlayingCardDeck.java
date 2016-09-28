// Class of a Deck of Cards
// by Hank Shorb (Robert Shorb)

import java.util.ArrayList;

public class PlayingCardDeck {
	
	// Initialize all of the variables, and the ArrayList used to store the deck
	private int deckSize;
	private ArrayList<PlayingCard> thisDeck = new ArrayList<PlayingCard>();
	private final int DECKSIZEORIGINAL;
	
	// Constructor method for the deck, allows the user to make a deck composed of
	// any positive integer number of decks
	public PlayingCardDeck(int numberOfDecks) {
		//  for loop that adds 52 cards for each deck the deck will be composed of
		for(int j=0; j<numberOfDecks ; j++) {
			// Nested loop that goes through the different suits and facevalues of
			// cards and adds them to the deck
			for(int i=1; i<5 ; i++) {
				for(int k=1 ; k<14 ; k++) {
					PlayingCard newCard = new PlayingCard(k,i);
					thisDeck.add(newCard);
				}
			}
		}
		// Depending on how many decks were used, determine the size of the deck
		deckSize = (52*numberOfDecks);
		DECKSIZEORIGINAL = deckSize;
	}
	
	//public method that shuffles the deck
	public void shuffle() {
		// cycles through each card and switches it with another random card in the deck
		for(int i = 0 ; i < deckSize ; i++) {
			int random = (int) (Math.random()*52);
			PlayingCard temp = thisDeck.get(i);
			thisDeck.set(i, thisDeck.get(random));
			thisDeck.set(random, temp);
		}
	}
	
	// public method that draws a card, returning an instance of PlayingCard
	public PlayingCard drawCard() {
		deckSize--;
		return thisDeck.get(deckSize);
	}
	
	// public method that allows the user to "kill" cards. Discard any number without them
	// actually being drawn
	public void killCards(int numberOfCards) {
		deckSize = deckSize - numberOfCards;
	}
	
	// public method that deals the entire deck out, and prints each card
	public void showDeck() {
		for(int i = 0 ; i < deckSize ; i++)
		{
			System.out.println(thisDeck.get(i));
		}
	}
	
	// public method that resets the deck by returning the decksize to normal
	// it is assumed if this method is use, all player hands will be reset as well
	public void resetDeck() {
		deckSize = DECKSIZEORIGINAL;
	}
	
	// public int that returns how many cards are left in the deck
	public int deckSize() {
		return deckSize;
	}
	
}