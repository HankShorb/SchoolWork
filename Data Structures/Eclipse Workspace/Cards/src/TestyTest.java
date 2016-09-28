
public class TestyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PlayingCardDeck deck = new PlayingCardDeck(1);
		deck.shuffle();
		PlayingCardHand hand = new PlayingCardHand();
		for(int i=0;i<10;i++){
			hand.addCard(deck.drawCard());
		}
		hand.showHand();
	}

}
