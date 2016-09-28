
public class HuffmanEncoder {
	
	int[] frequencies;
	HuffNode[] huffArray;
	
	public HuffmanEncoder(){
		frequencies = new int[(int) Math.pow(2, 16)];
	}
	
	public void huffTree(String input){
		countFreq(input);
		
		BinaryTree tree = buildTree();
		
		tree.computeNodePositions();
		tree.findPaths();
		tree.maxheight=tree.treeHeight(tree.root());
		DisplayTree dispTree = new DisplayTree(tree);
		dispTree.setVisible(true);
	}
	
	private void countFreq(String input){
		frequencies = new int[(int) Math.pow(2, 16)];
		int numChars = 0;
		for (int i=0 ; i<input.length() ; i++){
			if (frequencies[input.charAt(i)] == 0)
				numChars++;
			frequencies[input.charAt(i)]++;
		}
		
		huffArray = new HuffNode[numChars];
		int index = 0;
		
		for (int i=0 ; i<frequencies.length ; i++){
			if(frequencies[i]>0){
				huffArray[index] = new HuffNode((char) i, frequencies[i], null, null);
				index++;
			}
		}
	}
	
	public void printFreq(String input){
		countFreq(input);
		System.out.println("CHAR" + "\t" + "ASCII" + "\t" + "FREQ");
		for (int i=0 ; i<huffArray.length ; i++){
			System.out.println(huffArray[i]);
		}
	}
	
	public BinaryTree buildTree(){
		BinaryHeap heap = new BinaryHeap(huffArray);
		HuffNode left;
		HuffNode right;
		HuffNode current;
		while(heap.currentSize()>1){
			left = heap.deleteMin();
			right = heap.deleteMin();
			current = new HuffNode(-1, left.frequency()+right.frequency(), left, right);
			heap.insert(current);
		}
		BinaryTree tree = new BinaryTree();
		tree.setRoot(heap.deleteMin());
		return tree;
	}
	
	public String encoding(String input){
		countFreq(input);
		BinaryTree tree = buildTree();
		tree.findPaths();
		String output = "";
		for(int i=0 ; i<input.length() ; i++){
			int j=0;
			while((char)huffArray[j].character() != input.charAt(i)){
				j++;
			}
			output += huffArray[j].path;
		}
		
		return output;
	}

}
