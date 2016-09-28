// Class of Playing Cards
// By Hank Shorb (Robert Shorb)


public class PlayingCard {
	
	// Initialize all of the instance variables
	private final int SPADES = 1;
	private final int CLUBS = 2;
	private final int DIAMONDS = 3;
	private final int HEARTS = 4;
	// Create public final ints, so that the values are easily accessible from outside the class,
	// but not changeable once they are defined. 
	private final int SUIT;
	private final int CARDVALUE;
	
	// Constructor for the class, creates a card based on the face value input and the suit input
	public PlayingCard(int valueInput, int suitInput) {
		SUIT = suitInput;
		CARDVALUE = valueInput;
	}
	
	// Returns a string that corresponds to the card's face value
	private String showValue() {
		if(CARDVALUE == 1)
			return "Ace";
		else if(CARDVALUE == 2)
			return "2";
		else if(CARDVALUE == 3)
			return "3";
		else if(CARDVALUE == 4)
			return "4";
		else if(CARDVALUE == 5)
			return "5";
		else if(CARDVALUE == 6)
			return "6";
		else if(CARDVALUE == 7)
			return "7";
		else if(CARDVALUE == 8)
			return "8";
		else if(CARDVALUE == 9)
			return "9";
		else if(CARDVALUE == 10)
			return "10";
		else if(CARDVALUE == 11)
			return "Jack";
		else if(CARDVALUE == 12)
			return "Queen";
		else if(CARDVALUE == 13)
			return "King";
		else
			return "N/A";
	}
	
	// Returns a string that relates to the card's suit
	private String showSuit()
	{
		if(SUIT == CLUBS)
			return "Clubs";
		else if(SUIT == SPADES)
			return "Spades";
		else if(SUIT == DIAMONDS)
			return "Diamonds";
		else if(SUIT == HEARTS)
			return "Hearts";
		else
			return "N/A";
	}
	
	// toString method
	public String toString()
	{
		 return showValue() + " of " + showSuit();
	}
	
	public int faceValue(){
		return CARDVALUE;
	}
	
	public int suit(){
		return SUIT;
	}
	
}