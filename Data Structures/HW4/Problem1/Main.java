
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] reserved = {"abstract",
		"default", "if", "private", "throw", "boolean", "do", "implements",
		"protected", "throws", "break", "double", "import", "public", "transient",
		"byte", "else", "instanceof", "return", "try", "case", "extends", "int", 
		"short", "void", "catch", "final", "interface", "static", "volatile", 
		"char", "finally", "long", "super", "while", "class", "float", "native", 
		"switch", "true", "const", "for", "new", "synchronized", "false", "null"};
		
		Hasher hash = new Hasher(reserved.length);
		for(int i=0 ; i<reserved.length ; i++){
			hash.hash(reserved[i]);
		}
		LinkedList[] hashTable = hash.hashTable();
		
		System.out.println("Here are the hashing results, with 4 collisions" + "\n");
		
		System.out.println("Index" + "\t" + "Entry(ies)");
		for(int i=0 ; i<hashTable.length ; i++){
			System.out.println(i + "\t" + hashTable[i].toString());
		}
		
		System.out.println("Here is my hash function: " + "\n");
		System.out.println("private int hashFunc(String string){" + "\n" 
		+ "\t" + "int total = 1;"+ "\n" 
		+ "\t" + "for(int i=0 ; i<string.length(); i++){"+ "\n" 
		+ "\t" + "\t" + "total = total*1164305251 + string.charAt(i);"+ "\n" 
		+ "\t" + "}"+ "\n" 
		+ "\t" + "total = Math.abs(total%hashTable.length);"+ "\n" 
		+ "\t" + "return total;"+ "\n" +
		"}");
		
		
	}

}
