import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener {

	//initialized text fields and converter
    JTextField f1;
    JTextField f2;
    HuffmanEncoder encoder;
    
    //makes window with buttons and all
    public Window() {
        super("Huffman!");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel buttonBar = new JPanel(new FlowLayout());
        JButton b1 = new JButton("Compute Character Frequencies");
        b1.addActionListener(this);
        JButton b2 = new JButton("Display Huffman Tree");
        b2.addActionListener(this);
        JButton b3 = new JButton("Display Encoded message");
        b3.addActionListener(this);

        buttonBar.add(b1);
        buttonBar.add(b2);
        buttonBar.add(b3);

        JPanel fieldBar = new JPanel(new FlowLayout());

        encoder = new HuffmanEncoder();
        f1 = new JTextField(22);
        f2 = new JTextField(22);

        fieldBar.add(f1);
        f1.setText("Input string to be encoded here");
        fieldBar.add(f2);
        f2.setText("Encoded message will display here");

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
        if (action.equals("Compute Character Frequencies")) {
        	try{
        		encoder.printFreq(f1.getText());
        	} catch (Exception error) {
        		f1.setText("Input needed");
        	}
        } else if (action.equals("Display Huffman Tree")) {
        	
        	encoder.huffTree(f1.getText());
        	
        } else if (action.equals("Display Encoded message")) {
        	try{
        		f2.setText(encoder.encoding(f1.getText()));
        	} catch(Exception error){
        		f2.setText("Input needed");
        	}
        	
        }
    }

}