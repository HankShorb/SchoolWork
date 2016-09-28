import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	BinaryTree tree;
	int xs;
	int ys;

	public DisplayPanel(BinaryTree t) {
		this.tree = t; // allows dispay routines to access the tree
		setBackground(Color.white);
		setForeground(Color.black);
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground()); // colors the window
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground()); // set color and fonts
		Font MyFont = new Font("SansSerif", Font.PLAIN, 20);
		g.setFont(MyFont);
		xs = 30; // where to start printing on the panel
		ys = 20;
		ys = ys + 10;
		g.drawString("You may need to scroll right!",xs,ys);
		int start = 0;
		// print input string on panel, 150 chars per line
		// if string longer than 23 lines don't print
		MyFont = new Font("SansSerif", Font.BOLD, 80/(tree.maxheight)); // bigger font for tree
		g.setFont(MyFont);
		this.drawTree(g, tree.root); // draw the tree
		revalidate(); // update the component panel
	}

	public void drawTree(Graphics g, HuffNode root) {// actually draws the tree
		int dx, dy, dx2, dy2;
		int SCREEN_WIDTH = 450; // screen size for panel
		int SCREEN_HEIGHT = 450;
		int XSCALE, YSCALE;
		XSCALE = SCREEN_WIDTH*tree.maxheight / (tree.totalnodes); // scale x by total nodes in tree
		YSCALE = (SCREEN_HEIGHT - ys) / (tree.maxheight + 1); // scale y by tree
															// height

		if (root != null) { // inorder traversal to draw each node
			drawTree(g, root.left); // do left side of inorder traversal
			dx = root.xpos * XSCALE +xs; // get x,y coords., and scale them
			dy = root.ypos * YSCALE + ys;
			String s;
			String p;
			if(root.characVal() != -1){ // get the word at this node
				s = Character.toString(root.character());
				s += ": " + root.frequency();
				p = root.path;
				g.drawOval(dx-40/tree.maxheight, dy-100/(tree.maxheight), (80/tree.maxheight)*max(3,root.path.length()), 240/(tree.maxheight));
			} else {
				s = root.frequency() + "";
				p = "";
				g.drawOval(dx-40/tree.maxheight, dy-100/(tree.maxheight), 240/(tree.maxheight), 240/(tree.maxheight));
			}
			g.drawString(s, dx, dy); // draws the word
			g.drawString(p, dx, dy+(80/tree.maxheight));
			// this draws the lines from a node to its children, if any
			if (root.left != null) { // draws the line to left child if it
										// exists
				dx2 = root.left.xpos * XSCALE + xs;
				dy2 = root.left.ypos * YSCALE + ys;
				g.drawLine(dx, dy, dx2, dy2);
			}
			if (root.right != null) { // draws the line to right child if it
										// exists
				dx2 = root.right.xpos * XSCALE + xs;// get right child x,y scaled
												// position
				dy2 = root.right.ypos * YSCALE + ys;
				g.drawLine(dx, dy, dx2, dy2);
			}
			drawTree(g, root.right); // now do right side of inorder traversal
		}
		
	}
	public int max(int a, int b){
		if(a>b)
			return a;
		else
			return b;
	}
}