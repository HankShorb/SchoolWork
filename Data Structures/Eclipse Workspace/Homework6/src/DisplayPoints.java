import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class DisplayPoints extends JFrame {
	JScrollPane scrollpane;
	DisplayPanel panel;
	TSP tsp;

	public DisplayPoints(TSP tsp0) {
		tsp = tsp0;
		panel = new DisplayPanel(tsp, false, false, false);
		panel.setPreferredSize(new Dimension(600, 600));
		scrollpane = new JScrollPane(panel);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack(); // cleans up the window panel
	}
	
	public void dispConHull(){
		panel = new DisplayPanel(tsp, true, false, false);
		panel.setPreferredSize(new Dimension(600, 600));
		scrollpane = new JScrollPane(panel);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public void dispTour(){
		panel = new DisplayPanel(tsp, false, true, false);
		panel.setPreferredSize(new Dimension(600, 600));
		scrollpane = new JScrollPane(panel);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
	
	public void dispOptimal(){
		panel = new DisplayPanel(tsp, false, true, true);
		panel.setPreferredSize(new Dimension(600, 600));
		scrollpane = new JScrollPane(panel);
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
	}
}