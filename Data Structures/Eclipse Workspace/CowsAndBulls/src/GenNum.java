import java.util.Random;
public class GenNum {
int[] arrayNew = new int[4];


public GenNum(){
	generate();
}

private void generate(){
	for (int place = 0; place<4; place++){
		Random rand = new Random();
		boolean needNum = true;
		while (needNum){
		arrayNew[place] =  rand.nextInt(10);
		for(int k=0 ; k<place ; k++){
			if(arrayNew[place] == arrayNew[k])
				needNum = true;
			else
				needNum = false;
		}
		if(place == 0)
			needNum=false;
		}
	}
	for(int i=0 ; i<4 ; i++)
	{
		System.out.print(arrayNew[i]);
	}
	System.out.println("");
}

public int[] findArray(){
	return arrayNew;
}


}