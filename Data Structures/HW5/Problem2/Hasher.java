public class Hasher {
	
	String[] hashTable;
	
	public Hasher(int size){
		hashTable = new String[size];
	}
	
	public void hash(String word){
		int index = hashFunc(word);
		while(hashTable[index] != null){
			index++;
			index = index%hashTable.length;
		}
		hashTable[index]=word;
	}

	private int hashFunc(String string){
		int total = 1;
		for(int i=0 ; i<string.length(); i++){
			total = total*1164305251 + string.charAt(i);
		}
		total = Math.abs(total%hashTable.length);
		return total;
	}
	
	public boolean contains(String word){
		int index = hashFunc(word);
		while(hashTable[index] != null){
			if(word.equals(hashTable[index]))
				return true;
			index++;
		}
		return false;
	}
	
}
