package googleCodeJam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MilkshakesCustomer {
	int index = 0;//customer index
	int T;//number of milkshakes this customer likes
	int[] likes;
	MilkshakesCustomer(String line){
		List<String> sL = new ArrayList<String>(Arrays.asList(line.split(" ")));
		int L = sL.size();
		T = Integer.parseInt(sL.get(0));
		likes = new int[2*T];
		for(int i=1;i<L;i++){
			likes[i-1] = Integer.parseInt(sL.get(i));
		}
	}
	boolean isSatisfied(int[] seq){
		for (int i=0;i<T;i++){
			if (seq[likes[2*i]-1] == likes[2*i+1]){
				return true;
			}
		}
		return false;
	}
	public void setIndex(int I){
		index = I;
	}
	int maltedIndex(){
		for(int i=0;i<T;i++){
			if(likes[2*i + 1] == 1){
				return likes[2*i];
			}
		}
		return -1;
	}
}
