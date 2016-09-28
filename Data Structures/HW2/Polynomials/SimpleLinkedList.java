//simple LinkedList class

public class SimpleLinkedList {
	
	protected Node header;

	public SimpleLinkedList() {
		header=null;
	}
	
	
	
	
	
	// returns the size of the list
	public int size(){ 
		int size=0;
		Node current = header;
		
		//counts nodes in list
		while(current != null){
			size++;
			current = current.next;
		}
		return size;
	}
	
	
	
	
	
	//Adds x to the end of the linked list
	private void push(Object x) {  
		//Creates new node for x
		Node xNode = new Node(x,null);
		Node current = header;

		while(current != null && current.next != null){
				current = current.next;
		}
		if(current == null)
			header = xNode;
		else 
			current.next = xNode;
	}
	public void uniquePush(Object x) { //Adds x only if x is not in list
		if(!contains(x)){
			push(x);
		}
	}
	
	
	
	
	
	// returns node location of searched object x, null if x is not in list
	public Node find(Object x){ 
		Node searchNode = header;
		while(searchNode != null){
			if(searchNode.data == x){
				return searchNode;
			}
			searchNode = searchNode.next;
		}
		return searchNode;
	}
	public boolean contains(Object x){ //returns true if x is in list
		if (find(x) == null)
			return false;
		else
			return true;
	}
	
	
	
	
	
	//Print method that utilizes toString
	public void print(){	System.out.println(toString());	}
	//toString method
	public String toString(){
		String string = "";
		Node current = header;
		
		while(current != null){
			string += current.data.toString() + "\n";
			current = current.next;
		}
		return string;
	}
	
	
	
	
	
	// removes an object x from the linked list
	//assumes that this function will not be called if the link list is empty
	public void remove(Object x){ 

		Node searchNode = header;
		if(searchNode.data == x){
			header = searchNode.next;
		} else {
			while(searchNode.next != null && searchNode.next.data != x){
				searchNode = searchNode.next;
			}
			if(searchNode.next != null && searchNode.next.data == x){
				searchNode.next = searchNode.next.next;
			}
		}
	}
	
	
	
	
	
	//reverse the order of the linked list
	public void reverseOrder(){ 
		Node current = header;
		Node previous = null;
		Node temp = null;
		
		while(current != null){
			temp = current.next;
			current.next = previous;
			previous = current;
			current = temp;
		}
		header = previous;
	}
	
	
	
	
	
	//returns intersection of two lists
	public SimpleLinkedList intersect(SimpleLinkedList listTwo){  
		SimpleLinkedList intersect = new SimpleLinkedList();
		Node searchNode = header;
		while(searchNode != null){
			if(listTwo.contains(searchNode.data)){
				intersect.push(searchNode.data);
			}
			searchNode = searchNode.next;
		}
		return intersect;
	}
	
	

}