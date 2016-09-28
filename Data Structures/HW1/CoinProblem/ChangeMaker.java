public class ChangeMaker {
	//array that keeps track of the value of coins
	private static final int[] COINVALS = {25,10,5};
	
	//function that passes change into the real change making function
	public void makeChange(int change){
		//array that keeps track of the number of coins for each 
		int[] coinNums = {0,0,0};
		//notch keeps track of which coin is the dominant coin in the change making
		//0 means quarter, 1 means dime, 2 means nickle
		//generally indicates the index of COINVALS and coinNums
		int notch = 0;
		//only changes if divisible by 5
		if(change%5 != 0){
			System.out.println(change + " can't be changed.");
		}else{//pass to the real change making function
		coinBreak(change, coinNums, notch, change);
		}
	}
	
	private static void coinBreak(int change, int[] coins, int notch, int originalChange){
		
		if(change==0){//then we're done breaking this change and we print this combo
			print(coins, originalChange);
		} else{
			if(change>=0){//if we can add one more of the dominant coin
				coins[notch]++;
				//break up what's left
				coinBreak(change-COINVALS[notch], coins, notch, originalChange);
				coins[notch]--;//when we finally get here we need to make change
				//with one less of the dominant coin
			}
			if(notch+1<COINVALS.length){//break what's left with smaller coins
			//if there are smaller coins
				coinBreak(change, coins, notch+1, originalChange);
			}
		} 
	}
	
	//custom print function
	private static void print(int[] coins, int change){
		System.out.print("Change for " + change + " = ");
		for(int i=0;i<coins.length;i++){
			for(int j=0;j<coins[i];j++){
				System.out.print(COINVALS[i]+" ");
			}
		}
		System.out.println("");
	}
}
