public class ChangeMaker {
	
	private static final int[] COINVALS = {25,10,5};
	
	public void makeChange(int change){
		int[] coinNums = {0,0,0};
		int notch = 0;
		if(change%5 != 0){
			System.out.println(change + " can't be changed.");
		}else{
		coinBreak(change, coinNums, notch, change);
		}
	}
	
	private static void coinBreak(int change, int[] coins, int notch, int originalChange){
		
		if(change==0){
			print(coins, originalChange);
		} else{
			if(change>=0){
				coins[notch]++;
				coinBreak(change-COINVALS[notch], coins, notch, originalChange);
				coins[notch]--;
			}
			if(notch+1<COINVALS.length){
				coinBreak(change, coins, notch+1, originalChange);
			}
		} 
	}
	
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
