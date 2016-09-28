import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame implements ActionListener {

	//initialized text fields and converter
    JTextField f1;
    JTextField f2;
    JTextField f3;
    JTextField f4;
    ExpressionConverter converter;
    
    //makes window with buttons and all
    public Window() {
        super("Fuck Yeah Expressions");
        this.setSize(800, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel buttonBar = new JPanel(new FlowLayout());
        JButton b1 = new JButton("Convert Infix to Postfix");
        b1.addActionListener(this);
        JButton b2 = new JButton("Display Expression Tree");
        b2.addActionListener(this);
        JButton b3 = new JButton("Evaluate Postfix Expression");
        b3.addActionListener(this);
        JButton b4 = new JButton("Convert Postfix to Infix");
        b4.addActionListener(this);

        buttonBar.add(b1);
        buttonBar.add(b2);
        buttonBar.add(b3);
        buttonBar.add(b4);

        JPanel fieldBar = new JPanel(new FlowLayout());

        converter = new ExpressionConverter();
        f1 = new JTextField(22);
        f2 = new JTextField(22);
        f3 = new JTextField(22);
        f4 = new JTextField(22);

        fieldBar.add(f1);
        f1.setText("Input infix expressions here");
        fieldBar.add(f2);
        f2.setText("Postfix expressions display here");
        fieldBar.add(f3);
        f3.setText("Post fix expressions evaluate here");
        fieldBar.add(f4);
        f4.setText("Parenthesized infix displays here");

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
        if (action.equals("Convert Infix to Postfix")) {
            f2.setText(converter.inToPost(f1.getText()));
        } else if (action.equals("Display Expression Tree")) {
        	try{
        		BinaryTree tree = converter.postToTree(f2.getText());
        		
        		tree.computeNodePositions();
        		tree.maxheight=tree.treeHeight(tree.root());
        		DisplayTree dispTree = new DisplayTree(tree);
        		dispTree.setVisible(true);
        	} catch (Exception error) {
        		f2.setText("Proper postfix expression needed");
        	}
        } else if (action.equals("Evaluate Postfix Expression")) {
        	try{
        		BinaryTree tempTree = converter.postToTree(f2.getText());
        		f3.setText(tempTree.evaluate(tempTree.root())+"");
        	} catch(Exception error){
        		f3.setText("Proper postfix Expression needed");
        	}
        	
        }else if (action.equals("Convert Postfix to Infix")){
        	try{
        		f4.setText(converter.postToInfix(f2.getText()));
        	} catch (Exception error){
        		f4.setText("Proper postfix expression needed");
        	}
        }
    }

}