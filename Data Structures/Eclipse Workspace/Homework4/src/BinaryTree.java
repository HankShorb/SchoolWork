public class BinaryTree {
	String inputString = new String();
	HuffNode root;
	int totalnodes = 0; // keeps track of the inorder number for horiz. scaling
	int maxheight = 0;// keeps track of the depth of the tree for vert. scaling

	BinaryTree() {
		root = null;
	}
	
	public void findPaths(){
		findPath(root, "");
	}
	
	private void findPath(HuffNode current, String currPath){
		if(current!=null){
			current.path = currPath;
			findPath(current.left, currPath+"1");
			findPath(current.right, currPath+"0");
		}
	}
	
	
	public HuffNode root(){
		return root;
	}

	public int treeHeight(HuffNode t) {
		if (t == null)
			return 0;
		else
			return 1 + max(treeHeight(t.left), treeHeight(t.right));
	}

	public int max(int a, int b) {
		if (a > b)
			return a;
		else
			return b;
	}

	public void computeNodePositions() {
		int depth = 1;
		inorder_traversal(root, depth);
	}

	// traverses tree and computes x,y position of each node, stores it in the
	// node

	public void inorder_traversal(HuffNode t, int depth) {
		if (t != null) {
			inorder_traversal(t.left, depth + 1); // add 1 to depth (y
													// coordinate)
			t.xpos = totalnodes++;// x coord is node number in inorder
									// traversal
			t.ypos = depth; // mark y coord as depth
			inorder_traversal(t.right, depth + 1);
		}
	}

	public void setRoot(HuffNode node) {
		root = node;
		computeNodePositions();
	}
	
}