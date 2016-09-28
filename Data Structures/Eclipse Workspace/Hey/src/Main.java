import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(") + -"));
		tokenizer.eolIsSignificant(true);
		try {
			while(tokenizer.nextToken() != tokenizer.TT_EOF){
				System.out.println(tokenizer.ttype);
			}
		} catch (IOException e) {
		}
		

	}

}
