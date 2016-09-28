
public class HuffNode {
	
	private int character;
	private int frequency;
	String path = "";
	HuffNode left;
	HuffNode right;
	int xpos; // stores x and y position of the node in the tree
	int ypos;

	public HuffNode(int c, int f, HuffNode l, HuffNode r){
		character = c;
		frequency = f;
		left = l;
		right = r;
	}
	
	public int characVal(){
		return character;
	}
	
	public char character(){
		return (char)character;
	}
	
	public int frequency(){
		return frequency;
	}
	
	public HuffNode left(){
		return left;
	}
	
	public HuffNode right(){
		return right;
	}
	
	public int compareTo(HuffNode other) {
		// TODO Auto-generated method stub
		if(frequency>other.frequency()){
			return 1;
		}
		else if (frequency<other.frequency()){
			return -1;
		}
		else
			return 0;
	}
	
	public String toString(){
		return (char)character + "\t" + character + "\t" + frequency;
	}

}
