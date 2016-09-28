import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DisplayTree extends JFrame {
	JScrollPane scrollpane;
	DisplayPanel panel;

	public DisplayTree(BinaryTree t) {
		panel = new DisplayPanel(t);
		panel.setPreferredSize(new Dimension(600*t.maxheight, 600));
		scrollpane = new JScrollPane(panel);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack(); // cleans up the window panel
	}

}