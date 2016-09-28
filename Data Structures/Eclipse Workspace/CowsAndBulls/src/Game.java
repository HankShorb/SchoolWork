import java.util.Scanner;

//*********************
// Game class for BullsCows
//
//*********************
public class Game {
	Scanner input = new Scanner(System.in);
	public String again = "no";
	private int[] guess;
	public int Bull = 0;
	public int Cow = 0;
	public int l=1;
	public int[] test;
	public int[] next;
	public int[] guessStr;
	public int[] find;
	public boolean count=false;
	public int[] give;
	boolean playAg = false;
	public String repeat;
	boolean valid = true;
	private GenNum comp;
	
	public Game(){
		comp = new GenNum();
	}
	
	public void play(){
		nextGuess();
		resultsBulls();
		resultsCows();
	}
	
	public void ha(){
		System.out.println("asdfadsf");
	}
	
	private void nextGuess(){
		System.out.println("What is your four-digit guess?");
		int guessed = input.nextInt();
		int i = 1000;
		guess = new int [4];
		for (int place = 0; place<4;place++){				
			guess[place] = guessed/i;
			guessed = guessed%i;
			i/=10;
		}
		for(int k=0 ; k<4 ; k++){
			System.out.print(guess[k]);
		}
		System.out.println("");
	}
	
	public int resultsBulls(){
		count = true;
		int[] tester = comp.findArray();
		for (int i=0;i<4;i++){
			if (guess[i]==tester[i]){
				Bull++;
			}}
		System.out.println("Number of Bulls: " +Bull);
		return Bull;
	}
	
	
	public int resultsCows(){
		int[] tester = comp.findArray();
		int status = 1;
		for (int j=0; j<4; j++){
			int k=0;
			if (k<4){
				if (guess[k]==tester[j] && status==1){
					Cow++;
					status=2;
					}
				else
					k++;
			}
			status=1;}
		
		System.out.println("Number of Cows: " +Cow);
		return Cow;
		
	}
	
}