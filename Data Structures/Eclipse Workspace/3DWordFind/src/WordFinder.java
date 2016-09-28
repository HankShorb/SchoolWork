import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class WordFinder {
	
	Letter[][][] lMat;
	Hasher hash;
	
	public WordFinder(){
		
	}
	
	public WordFinder(String file, String dict) throws IOException{
		
		//creates the letter matrix
		lMat = new Letter[3][3][2];
		for (int i=0 ; i<3 ; i++){
			for (int j=0 ; j<3 ; j++){
				for (int k=0 ; k<2 ; k++){
					lMat[i][j][k] = new Letter(" ");
				}
			}
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Letter letter = null;
		while((line = reader.readLine()) != null) {
			String [] data;
			if(line.length() == 1){
				letter = new Letter(line);
				line = reader.readLine();
				data = line.split("\\s+");
				int x = Integer.parseInt(data[0]);
				int y = Integer.parseInt(data[1]);
				int z = Integer.parseInt(data[2]);
				lMat[x][y][z] = letter;
			} else {
				data = line.split("\\s+");
				int x = Integer.parseInt(data[0]);
				int y = Integer.parseInt(data[1]);
				int z = Integer.parseInt(data[2]);
				letter.addAdjacent(x, y, z);
			}
		}
		
		//creates the dictionary
		hash = new Hasher(304961);
		reader = new BufferedReader(new FileReader(dict));
		line = null;
		while((line = reader.readLine()) != null){
			String[] data = line.split("\\s+");
			for (int i=0 ; i<data.length ; i++){
				hash.hash(data[i]);
			}
		}
		
	}
	public ArrayList<String> find(int n){
		ArrayList<String> found = new ArrayList<String>();
		
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				for(int k=0 ; k<2 ; k++){
					//DFS starting at each letter for words of length <= n
					Stack<WordSearch> s = new Stack<WordSearch>();
					s.push(new WordSearch("",new OrderedPair(i,j,k)));
					
					WordSearch current;
					while(!s.isEmpty()){
						current = s.pop();
						if(hash.contains(current.word))
							found.add(current.word);
						if(current.word.length() < n){
							ArrayList<OrderedPair> adjacent = 
									lMat[current.x][current.y][current.z].adjacent;
							for(int p=0 ; p<adjacent.size() ; p++){
								Letter l = lMat(adjacent.get(p));
								s.push(new WordSearch(current.word + l.letter, adjacent.get(p)));
							}
						}
					}
				}
			}
		}
		
		
		
		return found;
	}
	private Letter lMat(OrderedPair op){
		return lMat[op.x][op.y][op.z];
	}

}
