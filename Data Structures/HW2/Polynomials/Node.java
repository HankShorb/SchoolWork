public class Node{
	
	
	protected Object data;
	protected Node next;
	
	
	public Node(Object x, Node n){
		data=x;
		next=n;
	}
	
	public Node(){
		data=null;
		next=null;
	}
	
}