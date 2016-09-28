public class Word {
	
	String word;
	Word next;
	
	public Word(String w, Word n){
		word = w;
		next = n;
	}
	
	public String toString(){
		return word;
	}
}