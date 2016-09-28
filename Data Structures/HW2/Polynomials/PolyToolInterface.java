import java.util.Scanner;

//interface that interacts with PolyTool, and allows user to perform operations
public class PolyToolInterface {
	SimpleLinkedList[] polyArray;
	PolyTool polyTool;
	
	
	
	
	
	PolyToolInterface() {
		polyArray = new SimpleLinkedList[10];
		polyTool = new PolyTool();
	}
	
	
	
	
	
	//increases the size of polyArray to store more polynomials
	private void upSize() {
		SimpleLinkedList[] temp = new SimpleLinkedList [polyArray.length+10];
		for(int i=0 ; i<polyArray.length ; i++){
			temp[i]=polyArray[i];
		}
		polyArray = temp;
	}
	
	
	
	
	
	//runs input for user
	public void run(){
		System.out.println("Welcome to Hank's Polynomial Calculator!");
		System.out.println("");
		System.out.println("");
		boolean keepRunning = true;
		while(keepRunning){
			String input = "";
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please Enter your command: ");
			System.out.println("  i to input");
			System.out.println("  a to add");
			System.out.println("  s to subtract");
			System.out.println("  m to multiply");
			System.out.println("  d to divide");
			System.out.println("  e to evaluate");
			System.out.println("  p to print");
			System.out.println("  u to increase storage");
			System.out.println("  c to check size of storage and "
					+ "which indices contain polynomials");
			System.out.println("  q to terminate");
			
			input = scanner.nextLine();
			input.toLowerCase();
			
			if(input.equals("i")){
				input();
			} else if(input.equals("a")) {
				add();
			} else if(input.equals("s")) {
				subtract();
			} else if(input.equals("m")) {
				multiply();
			} else if(input.equals("d")) {
				divide();
			} else if(input.equals("e")) {
				eval();
			} else if(input.equals("p")) {
				print();
			}else if(input.equals("u")){
				upSize();
			}else if(input.equals("c")){
				System.out.println("There are " + polyArray.length + " indices "
						+"for storage, and these indices contain polynomials:");
				for(int i=0 ; i<polyArray.length ; i++){
					if(polyArray[i] != null)
						System.out.print(i + " ");
				}
				System.out.println("");
				System.out.println("");
			} else if(input.equals("q")) {
				keepRunning = false;
				System.out.println("Thanks for coming!");
			} else {
				System.out.println("That was not a valid command.");
			}
		}
	}
	
	
	
	
	
	//creates polynomials of specified number of terms in specified index
	private void input(){
		
		String inputStage1;
		String[] inputStage2;
		int[] input = new int[2];
		Scanner scanner;
		boolean getInput = true;
		while(getInput){
			try{
				System.out.print("Please enter the index of the polynomial and "
						+ "the number of terms: ");
				scanner = new Scanner(System.in);
			 	inputStage1 = scanner.nextLine();
			 	System.out.println("");
				inputStage2 = inputStage1.split(" ");
				input = new int[inputStage2.length];
				input[0] = Integer.parseInt(inputStage2[0]);
				input[1] = Integer.parseInt(inputStage2[1]);
				getInput=false;
			}
			catch(Exception e){
				System.out.println("That was not a valid input, try again.");
			}
		}
		if(input[0]<polyArray.length){
			polyArray[input[0]] = new SimpleLinkedList();
			for(int i=0 ; i<input[1] ; i++){
				
				double coef;
				int exp;
				PolyTerm newTerm;
				getInput = true;
				while(getInput){
					try{
						System.out.print("Please enter the coefficient and "
								+ "exponent of term" + (i+1) + ": ");
						scanner = new Scanner(System.in);
					 	inputStage1 = scanner.nextLine();
					 	System.out.println("");
						inputStage2 = inputStage1.split(" ");
						coef = Double.parseDouble(inputStage2[0]);
						exp = Integer.parseInt(inputStage2[1]);
						newTerm = new PolyTerm(coef,exp);
						polyArray[input[0]] = 
							polyTool.inputInOrder(polyArray[input[0]], newTerm);
						getInput=false;
					}
					catch(Exception e){
						System.out.println("That was not a valid input, try "
								+ "again.");
					}
				}
			}
			polyTool.cleanUp(polyArray[input[0]]);
			polyTool.print(polyArray[input[0]]);
			System.out.println("");
		} else {
			System.out.println("The specified index was out of bounds,"
					+ "\n try increasing the size of the array.");
			System.out.println("");
		}
	}
	
	
	
	
	
	//adds two polynomials
	private void add(){
		String inputStage1;
		String[] inputStage2;
		int[] input = new int[3];
		Scanner scanner;
		boolean getInput = true;
		while(getInput){
			try{
				System.out.println("");
				System.out.print("Please enter the indices of the polynomials "
						+ "to be added and stored: ");
				scanner = new Scanner(System.in);
			 	inputStage1 = scanner.nextLine();
			 	if(inputStage1.equals("r")){ break; }
			 	System.out.println("");
				inputStage2 = inputStage1.split(" ");
				input = new int[inputStage2.length];
				input[0] = Integer.parseInt(inputStage2[0]);
				input[1] = Integer.parseInt(inputStage2[1]);
				input[2] = Integer.parseInt(inputStage2[2]);
				SimpleLinkedList temp1 = polyArray[input[0]];
				SimpleLinkedList temp2 = polyArray[input[1]];
				polyArray[input[2]] = 
					polyTool.addPoly(polyArray[input[0]],polyArray[input[1]]);
				System.out.println("the sum of");
				polyTool.print(temp1);
				System.out.println("and");
				polyTool.print(temp2);
				System.out.println("is");
				polyTool.print(polyArray[input[2]]);
				System.out.println("");
				getInput=false;
			}
			catch(Exception e){
				System.out.println("That was not a valid input. Try again, or "
						+ "enter r to return to the menu.");
			}
		}
	}
	
	
	
	
	
	//subtracts two polynomials
	private void subtract(){
		String inputStage1;
		String[] inputStage2;
		int[] input = new int[3];
		Scanner scanner;
		boolean getInput = true;
		while(getInput){
			try{
				System.out.print("Please enter the indices of the polynomials "
						+ "to be subtracted and stored: ");
				scanner = new Scanner(System.in);
			 	inputStage1 = scanner.nextLine();
			 	if(inputStage1.equals("r")){ break; }
			 	System.out.println("");
				inputStage2 = inputStage1.split(" ");
				input = new int[inputStage2.length];
				input[0] = Integer.parseInt(inputStage2[0]);
				input[1] = Integer.parseInt(inputStage2[1]);
				input[2] = Integer.parseInt(inputStage2[2]);
				SimpleLinkedList temp1 = polyArray[input[0]];
				SimpleLinkedList temp2 = polyArray[input[1]];
				polyArray[input[2]] = 
						polyTool.subtractPoly(polyArray[input[0]],polyArray[input[1]]);
				System.out.println("the difference of");
				polyTool.print(temp1);
				System.out.println("and");
				polyTool.print(temp2);
				System.out.println("is");
				polyTool.print(polyArray[input[2]]);
				System.out.println("");
				getInput=false;
			}
			catch(Exception e){
				System.out.println("That was not a valid input. Try again, or "
						+ "enter r to return to the menu.");
			}
		}
	}
	
	
	
	
	
	//multiplies two polynomials
	private void multiply(){
		String inputStage1;
		String[] inputStage2;
		int[] input = new int[3];
		Scanner scanner;
		boolean getInput = true;
		while(getInput){
			try{
				System.out.print("Please enter the indices of the polynomials "
						+ "to be multiplied and stored: ");
				scanner = new Scanner(System.in);
			 	inputStage1 = scanner.nextLine();
			 	if(inputStage1.equals("r")){ break; }
			 	System.out.println("");
				inputStage2 = inputStage1.split(" ");
				input = new int[inputStage2.length];
				input[0] = Integer.parseInt(inputStage2[0]);
				input[1] = Integer.parseInt(inputStage2[1]);
				input[2] = Integer.parseInt(inputStage2[2]);
				SimpleLinkedList temp1 = polyArray[input[0]];
				SimpleLinkedList temp2 = polyArray[input[1]];
				polyArray[input[2]] = 
					polyTool.multPoly(polyArray[input[0]],polyArray[input[1]]);
				System.out.println("the product of");
				polyTool.print(temp1);
				System.out.println("and");
				polyTool.print(temp2);
				System.out.println("is");
				polyTool.print(polyArray[input[2]]);
				System.out.println("");
				getInput=false;
			}
			catch(Exception e){
				System.out.println("That was not a valid input. Try again, or "
						+ "enter r to return to the menu.");
			}
		}
	}
	
	
	
	
	
	//divides two polynomials
	private void divide(){
		String inputStage1;
		String[] inputStage2;
		int[] input = new int[3];
		Scanner scanner;
		boolean getInput = true;
		SimpleLinkedList[] answer = new SimpleLinkedList[2];
		while(getInput){
			try{
				System.out.print("Please enter the indices of the polynomials "
						+ "to be divided and stored: ");
				scanner = new Scanner(System.in);
			 	inputStage1 = scanner.nextLine();
			 	if(inputStage1.equals("r")){ break; }
			 	System.out.println("");
				inputStage2 = inputStage1.split(" ");
				input = new int[inputStage2.length];
				input[0] = Integer.parseInt(inputStage2[0]);
				input[1] = Integer.parseInt(inputStage2[1]);
				input[2] = Integer.parseInt(inputStage2[2]);
				input[3] = Integer.parseInt(inputStage2[3]);
				if(input[2] == input[3])
					throw new IndexException("wrong");
				SimpleLinkedList temp1 = polyArray[input[0]];
				SimpleLinkedList temp2 = polyArray[input[1]];
				answer = 
					polyTool.dividePoly(polyArray[input[0]],polyArray[input[1]]);
				polyArray[input[2]] = answer[0];
				polyArray[input[3]] = answer[1];
				System.out.println("the quotient of");
				polyTool.print(temp1);
				System.out.println("and");
				polyTool.print(temp2);
				System.out.println("is");
				polyTool.print(polyArray[input[2]]);
				System.out.println("And the remainder is");
				polyTool.print(polyArray[input[3]]);
				System.out.println("");
				getInput=false;
			} catch(IndexException e) {
				System.out.println("Please input seperate arrays in which to "
						+ "store the quotient and the remainder.");
			} catch(Exception e) {
				System.out.println("That was not a valid input. Try again, or "
						+ "enter r to return to the menu.");
			}
		}
	}
	
	
	
	
	
	//evaluates a polynomial at a given input
	private void eval(){
		String inputStage1;
		String[] inputStage2;
		Scanner scanner;
		boolean getInput = true;
		while(getInput){
			try{
				System.out.print("Please enter the index of the polynomial to "
						+ "to be evaluated, \nand the value at which it will "
						+ "be evaluated: ");
				scanner = new Scanner(System.in);
			 	inputStage1 = scanner.nextLine();
			 	if(inputStage1.equals("r")){ break; }
			 	System.out.println("");
				inputStage2 = inputStage1.split(" ");
				int index = Integer.parseInt(inputStage2[0]);
				double value = Double.parseDouble(inputStage2[1]);
				System.out.print(" the value of ");
				polyTool.print(polyArray[index]);
				System.out.print("evaluated at " + value + " is: ");
				System.out.println(polyTool.evalPoly(polyArray[index], value));
				System.out.println("");
				getInput = false;
			}
			catch(Exception e){
				System.out.println("That was not a valid input. Try again, or "
						+ "enter r to return to the menu.");
			}
		}
	}
	
	
	
	
	
	//prints the polynomial in given index
	private void print(){
		String input;
		Scanner scanner;
		boolean getInput = true;
		while(getInput){
			try{
				System.out.print("Please enter the index of the polynomial to "
						+ "to be printed: ");
				scanner = new Scanner(System.in);
			 	input = scanner.nextLine();
			 	if(input.equals("r")){ break; }
			 	int index = Integer.parseInt(input);
			 	System.out.println("");
			 	polyTool.print(polyArray[index]);
			 	System.out.println();
			 	getInput=false;
			}
			catch(Exception e){
				System.out.println("That was not a valid input. Try again, or "
						+ "enter r to return to the menu.");
			}
		}
	}
	
	
}