public class LinkedList {
	
	Word header;
	
	public LinkedList(){
		header = null;
	}
	
	public void insert(String word){
		Word temp = header;
		header = new Word(word, temp);
	}
	
	public String toString(){
		String output = "";
		
		Word current = header;
		if (current != null){
			while(current.next!=null){
				output += current.toString() + ", ";
				current = current.next;
			}
			output += current.toString();
		}
		
		return output;
	}

}
