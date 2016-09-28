import java.util.Stack;
import java.io.*;

public class ExpressionConverter {
	Stack<Character> stack;
	Stack<BinaryTree> treeStack;
	boolean[][] table; //holds precedence values for all operators

	public ExpressionConverter() {
		//stack of chars to hold operators
		stack = new Stack<Character>();
		
		//initialize precedence table
		table = new boolean[256][256];
		
		table['(']['*']=false;
		table['(']['/']=false;
		table['(']['-']=false;
		table['(']['+']=false;
		table['(']['^']=false;
		table['(']['(']=false;
		table['('][012]=false;
		table['('][')']=true;
		
		table['+']['*']=false;
		table['+']['/']=false;
		table['+']['-']=true;
		table['+']['+']=true;
		table['+']['^']=false;
		table['+']['(']=false;
		table['+'][012]=true;
		table['+'][')']=true;
		
		table['-'][')']=true;
		table['-']['*']=false;
		table['-']['/']=false;
		table['-']['-']=true;
		table['-']['+']=true;
		table['-']['^']=false;
		table['-']['(']=false;
		table['-'][012]=true;
		
		table['*']['*']=true;
		table['*']['/']=true;
		table['*']['+']=true;
		table['*']['-']=true;
		table['*']['^']=false;
		table['*']['(']=false;
		table['*'][012]=true;
		table['*'][')']=true;
		
		table['/']['*']=true;
		table['/']['/']=true;
		table['/']['+']=true;
		table['/']['-']=true;
		table['/']['^']=false;
		table['/']['(']=false;
		table['/'][012]=true;
		table['/'][')']=true;
		
		table['^']['*']=true;
		table['^']['/']=true;
		table['^']['-']=true;
		table['^']['+']=true;
		table['^']['^']=false;
		table['^']['(']=false;
		table['^'][012]=true;
		table['^'][')']=true;
		
		table['#']['*']=false;
		table['#']['/']=false;
		table['#']['-']=false;
		table['#']['+']=false;
		table['#']['^']=false;
		table['#']['(']=false;
		table['#'][012]=true;
		table['#'][')']=false;
	}

	public String inToPost(String infix) {
		String post =""; //postfix string, starts empty
		char top;
		
		stack.push('#'); //mark the bottom of the stack
		
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(infix));
		tokenizer.eolIsSignificant(true);
		tokenizer.ordinaryChar('/'); //read in the infix string
		
		try {
			int pastEntry = tokenizer.TT_WORD;
			while (tokenizer.nextToken() != tokenizer.TT_EOF) {
				if (tokenizer.ttype == tokenizer.TT_WORD) {
					//do nothing, ignores letters
				
				//if it's a number add it to the string
				} else if (tokenizer.ttype == tokenizer.TT_NUMBER) {
					//if it's negative, reference the last entry to see
					//what to do
					if (tokenizer.nval < 0 && (pastEntry == tokenizer.TT_NUMBER
							|| pastEntry == ')')){
						top = stack.pop();
						while(precedes('-',top)){
							post += top + " ";
							top = stack.pop();
						}
						stack.push(top);
						stack.push('-');
						post += (-tokenizer.nval) + " ";
					} else
						post += tokenizer.nval + " ";
					
					
				} else { // case of operator +,-,*,/,^
					
					top = stack.pop();
					boolean notParens = true;//track parens
					
					//push or pop based on precedence
					while(precedes(tokenizer.ttype,top) && notParens){
						if(top == '(' && tokenizer.ttype == ')')
							notParens = false;
						else
							post += top + " ";
						top = stack.pop();
					}
					stack.push(top);
					if(notParens)
						stack.push((char) tokenizer.ttype);//gets rid of parens
				}
				pastEntry = tokenizer.ttype; //keeps track of last entry
			} // end while
			
			//when only operators are left, add them to the postfix string
			top = stack.pop();
			while(top != '#'){
				if(!(top == '(' || top == ')'))
					post += top + " ";
				top = stack.pop();
			}
			
		} catch (IOException e) { // Error reading in nextToken()
		}
		
		return post;
	}
	
	
	//references the table of operator precession
	private boolean precedes(int current, char onStack){
				
		return table[onStack][current];
		
	}
	
	
	
	//converts postfix to a binary expression tree
	public BinaryTree postToTree(String postfix){
		BinaryTree tree;
		BinaryTree temp;
		Node right;
		Node left;
		treeStack = new Stack<BinaryTree>();
		
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(postfix));
		tokenizer.eolIsSignificant(true);
		tokenizer.ordinaryChar('/');//reads string
		
		try {
			while (tokenizer.nextToken() != tokenizer.TT_EOF) {
				if (tokenizer.ttype == tokenizer.TT_WORD) {
					//do nothing, ignores letters
					
				//push to stack
				} else if (tokenizer.ttype == tokenizer.TT_NUMBER) {
					temp = new BinaryTree();
					temp.setRoot(new Node(tokenizer.nval+"",null,null));
					treeStack.push(temp);
				//pop last two entries, form new node
				} else { // case of operator +,-,*,/,^
					right = treeStack.pop().root();
					left = treeStack.pop().root();
					temp = new BinaryTree();
					temp.setRoot(new Node(Character.
							toString((char)tokenizer.ttype),left,right));
					treeStack.push(temp);
				}
			} // end while
		} catch (IOException e) { // Error reading in nextToken()
		}
		
		//pop final entry, gets full tree
		tree = treeStack.pop();
		return tree;
	}
	
	//converts postfix to infix using the binary tree of the postfix expression
	public String postToInfix(String post){
		String infix;
		BinaryTree tree = postToTree(post);
		infix = tree.infixString(tree.root());
		return infix;
	}


}
