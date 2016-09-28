import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener {

	//initialized text fields and converter
    JTextField f1;
    JTextField f2;
    JTextField f3;
    JTextField f4;
    TSP tsp;
    DisplayPoints dispPoints;
    
    //makes window with buttons and all
    public Window() {
        super("TSP shiz");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel buttonBar = new JPanel(new FlowLayout());
        JButton b1 = new JButton("Display Points");
        b1.addActionListener(this);
        JButton b2 = new JButton("Display Convex Hull");
        b2.addActionListener(this);
        JButton b3 = new JButton("Display Heuristic TSP Tour");
        b3.addActionListener(this);
        JButton b4 = new JButton("Display Optimal TSP Tour");
        b4.addActionListener(this);

        buttonBar.add(b1);
        buttonBar.add(b2);
        buttonBar.add(b3);
        buttonBar.add(b4);

        JPanel fieldBar = new JPanel(new FlowLayout());

        f1 = new JTextField(22);

        fieldBar.add(f1);
        f1.setText("Input number of points here");

        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.add(buttonBar);
        topPanel.add(fieldBar);
        
        
        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.pack();
        this.setVisible(true);
    }

    
    //all buttons self explanatory by title
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Display Points")) {
            //display shit
        	try{
        		tsp = new TSP(Integer.parseInt(f1.getText()));
        		dispPoints = new DisplayPoints(tsp);
        		dispPoints.setVisible(true);
        	} catch (Exception error){
        		f1.setText("Please input a positive integer");
        		System.out.println("Error Message");
        	}
        } else if (action.equals("Display Convex Hull")) {
        	try{
        		dispPoints.dispConHull();
        		dispPoints.setVisible(true);
        	} catch (Exception error) {
        		System.out.println("Error Message");
        	}
        } else if (action.equals("Display Heuristic TSP Tour")) {
        	try{
        		dispPoints.dispTour();
        		dispPoints.setVisible(true);
        	} catch(Exception error){
        		System.out.println("Error Message");
        	}
        	
        }else if (action.equals("Display Optimal TSP Tour")){
        	try{
        		dispPoints.dispOptimal();
        		dispPoints.setVisible(true);
        	} catch (Exception error){
        		System.out.println("Error Message");
        	}
        }
    }
}