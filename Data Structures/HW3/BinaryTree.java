public class BinaryTree {
	String inputString = new String();
	Node root;
	int totalnodes = 0; // keeps track of the inorder number for horiz. scaling
	int maxheight = 0;// keeps track of the depth of the tree for vert. scaling

	BinaryTree() {
		root = null;
	}
	
	public Node root(){
		return root;
	}

	public int treeHeight(Node t) {
		if (t == null)
			return -1;
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

	public void inorder_traversal(Node t, int depth) {
		if (t != null) {
			inorder_traversal(t.left, depth + 1); // add 1 to depth (y
													// coordinate)
			t.xpos = totalnodes++; // x coord is node number in inorder
									// traversal
			t.ypos = depth; // mark y coord as depth
			inorder_traversal(t.right, depth + 1);
		}
	}

	/* below is standard Binary Search tree insert code, creates the tree */

	/*
	 * public Node insert(Node root, String s) { // Binary Search tree insert if
	 * (root == null) { root = new Node(s, null, null); return root; } else { if
	 * (s.compareTo((String) (root.data)) == 0) { return root; /* duplicate word
	 * found - do nothing } else if (s.compareTo((String) (root.data)) < 0)
	 * root.left = insert(root.left, s); else root.right = insert(root.right,
	 * s); return root; } }
	 */
	public void setRoot(Node node) {
		root = node;
		computeNodePositions();
	}
	
	
	//recursively calls itself to develop a parenthesized infix string
	//from a binary expression tree
	public String infixString(Node node){
		if(node.left !=null && node.right != null)
			return infixStringParens(node.left) + node.data + 
					infixStringParens(node.right);
		else
			return node.data;
	}
	private String infixStringParens(Node node){
		if(node.left !=null && node.right != null)
			return "("+infixStringParens(node.left) + node.data + 
					infixStringParens(node.right)+")";
		else
			return node.data;
	}
	
	
	//evaluates recursively based on operator
	public double evaluate(Node node){
		
		double output;
		
		if(node.left == null && node.right == null)
			return Double.parseDouble(node.data);
		else{
			if(node.data.equals("+"))
				return evaluate(node.left)+evaluate(node.right); 
			else if(node.data.equals("-"))
				return evaluate(node.left)-evaluate(node.right);
			else if(node.data.equals("*"))
				return evaluate(node.left)*evaluate(node.right);
			else if(node.data.equals("/"))
				return evaluate(node.left)/evaluate(node.right);
			else
				return Math.pow(evaluate(node.left), evaluate(node.right));
		}
	}
	
}