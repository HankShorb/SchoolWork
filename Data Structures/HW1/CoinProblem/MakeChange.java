
public class MakeChange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//parse command line arg into an int
		int change=0;
		if (args.length > 0) {
		    try {
		        change = Integer.parseInt(args[0]);
		    } catch (NumberFormatException e) {
		        System.err.println("Argument" + " must be an integer");
		        System.exit(1);
		    }
		}

		//make change
		ChangeMaker Dood = new ChangeMaker();
		Dood.makeChange(change);
		
	}

}
