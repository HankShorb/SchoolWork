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
		Font MyFont = new Font("SansSerif", Font.PLAIN, 12);
		g.setFont(MyFont);
		xs = 10; // where to start printing on the panel
		ys = 20;
		g.drawString("Binary Tree evaluates to: "+
				tree.evaluate(tree.root()), xs, ys);
		ys = ys + 10;
		;
		int start = 0;
		// print input string on panel, 150 chars per line
		// if string longer than 23 lines don't print
		if (tree.inputString.length() < 23 * 150) {
			while ((tree.inputString.length() - start) > 150) {
				g.drawString(tree.inputString.substring(start, start + 150), xs,
						ys);
				start += 151;
				ys += 15;
			}
			g.drawString(
					tree.inputString.substring(start, tree.inputString.length()), xs,
					ys);
		}
		MyFont = new Font("SansSerif", Font.BOLD, 20); // bigger font for tree
		g.setFont(MyFont);
		this.drawTree(g, tree.root); // draw the tree
		revalidate(); // update the component panel
	}

	public void drawTree(Graphics g, Node root) {// actually draws the tree
		int dx, dy, dx2, dy2;
		int SCREEN_WIDTH = 450; // screen size for panel
		int SCREEN_HEIGHT = 450;
		int XSCALE, YSCALE;
		XSCALE = SCREEN_WIDTH / tree.totalnodes; // scale x by total nodes in tree
		YSCALE = (SCREEN_HEIGHT - ys) / (tree.maxheight + 1); // scale y by tree
															// height

		if (root != null) { // inorder traversal to draw each node
			drawTree(g, root.left); // do left side of inorder traversal
			dx = root.xpos * XSCALE; // get x,y coords., and scale them
			dy = root.ypos * YSCALE + ys;
			String s = (String) root.data; // get the word at this node
			g.drawString(s, dx, dy); // draws the word
			// this draws the lines from a node to its children, if any
			if (root.left != null) { // draws the line to left child if it
										// exists
				dx2 = root.left.xpos * XSCALE;
				dy2 = root.left.ypos * YSCALE + ys;
				g.drawLine(dx, dy, dx2, dy2);
			}
			if (root.right != null) { // draws the line to right child if it
										// exists
				dx2 = root.right.xpos * XSCALE;// get right child x,y scaled
												// position
				dy2 = root.right.ypos * YSCALE + ys;
				g.drawLine(dx, dy, dx2, dy2);
			}
			drawTree(g, root.right); // now do right side of inorder traversal
		}
	}
}