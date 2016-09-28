import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WordFinder finder = new WordFinder();
		
		try {
			finder = new WordFinder(args[0], args[1]);
			boolean keepRunning = true;
			while(keepRunning){
				String input = "";
				Scanner scanner = new Scanner(System.in);
				System.out.print("Find words up to what length? ");
				int n = scanner.nextInt();
				System.out.println("");
				ArrayList<String> words = finder.find(n);
				System.out.println("Got here");
				for(int i=0 ; i<words.size() ; i++){
					System.out.println(words.get(i));
				}
				System.out.println("");
				System.out.print("Would you like to search for words of other lengths? (y/n): ");
				String ans = scanner.next();
				if(!ans.equals("y"))
					keepRunning = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("please enter a valid file name.");;
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Program takes two command line arguements of the form: ");
			System.out.println("word_search_data.txt dictionary_file.txt");
		}
		
		

		
	}

}
