import java.util.ArrayList;

public class Letter {
	
	String letter;
	ArrayList<OrderedPair> adjacent;
	
	public Letter(String l){
		letter = l;
		adjacent = new ArrayList<OrderedPair>();
	}
	
	public void addAdjacent(int x, int y, int z){
		OrderedPair op = new OrderedPair(x,y,z);
		adjacent.add(op);
	}
	
}
