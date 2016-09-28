import java.util.ArrayList;

public class PlayingCardHand {
	
	private ArrayList<PlayingCard> hand;
	
	public PlayingCardHand(){
		hand = new ArrayList<PlayingCard>();
	}
	
	public void addCard(PlayingCard card){
		hand.add(card);
		sort(hand);
	}
	
	private void sort(ArrayList<PlayingCard> thisHand){
		//Sort by suit
		for(int i=1; i<thisHand.size() ; i++){
			PlayingCard temp = thisHand.get(i);
			int k;
			for(k=i-1 ; k>=0 && temp.suit() < thisHand.get(k).suit() ; k--){
				thisHand.set(k+1,thisHand.get(k));
			}
			thisHand.set(k+1, temp);
		}
		
		//Sort by faceValue (dominant sorting factor by coming second)
		for(int i=1 ; i<thisHand.size() ; i++){
			PlayingCard temp = thisHand.get(i);
			int k;
			for(k=i-1 ; k>=0 && temp.faceValue()<thisHand.get(k).faceValue() ; k--){
				thisHand.set(k+1, thisHand.get(k));
			}
			thisHand.set(k+1 , temp);
		}
	}
	
	public void showHand(){
		for(int i = 0 ; i<hand.size() ; i++)
			System.out.println(hand.get(i));
	}

}
