public class Hasher {
	
	LinkedList[] hashTable;
	
	public Hasher(int size){
		hashTable = new LinkedList[size];
		for(int i=0 ; i<hashTable.length ; i++){
			hashTable[i] = new LinkedList();
		}
	}
	
	public void hash(String word){
		int index = hashFunc(word);
		hashTable[index].insert(word);
	}

	private int hashFunc(String string){
		int total = 1;
		for(int i=0 ; i<string.length(); i++){
			total = total*1164305251 + string.charAt(i);
		}
		total = Math.abs(total%hashTable.length);
		return total;
	}
	
	public LinkedList[] hashTable(){
		return hashTable;
	}
	
}
