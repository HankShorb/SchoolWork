
public class HashFunction {

	public static void main(String[] args) {
		int collisions=7;
		int hashNum = 76322583; //3414919; //76322583
		String [] reserved = {"abstract",
		"default", "if", "private", "throw", "boolean", "do", "implements",
		"protected", "throws", "break", "double", "import", "public", "transient",
		"byte", "else", "instanceof", "return", "try", "case", "extends", "int", 
		"short", "void", "catch", "final", "interface", "static", "volatile", 
		"char", "finally", "long", "super", "while", "class", "float", "native", 
		"switch", "true", "const", "for", "new", "synchronized", "false", "null"};
		String[] hashTable = new String[46];
		/*
		for (int i=0 ; i<reserved.length ; i++){
			System.out.println(reserved[i]);
		}*/
		while(collisions>4){
			hashNum += 2;
			collisions = 0;
			hashTable = new String[46];
			for(int i=0; i<reserved.length; i++){
				int index = hashFunc(hashNum, reserved[i]);
				if(hashTable[index] == null){
					hashTable[index] = reserved[i];
				} else {
					collisions++;
				}
			}
		}
		System.out.println(hashNum);
	}
	
	public static int hashFunc(int num, String string){
		int total = 1;
		for(int i=0 ; i<string.length(); i++){
			total = total*num + string.charAt(i);
		}
		total = Math.abs(total%46);
		return total;
	}
	
}