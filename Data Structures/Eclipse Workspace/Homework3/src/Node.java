public class Node { // standard Binary Tree node
	String data;
	Node left;
	Node right;
	int xpos; // stores x and y position of the node in the tree
	int ypos;

	Node(String x, Node l, Node r) {
		left = l;
		right = r;
		data = (String) x;
	}
}
