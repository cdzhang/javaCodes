package googleCodeJam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreCreditCase {
	int index; //the index of this case, 1st, 2nd, 3rd case
	double credit;
	int I;//number of items in the store
	double error = 1e-10;
	List<Double> items = new ArrayList<Double>();
	int item1;
	int item2;
	StoreCreditCase(int indexIn,String l1, String l2, String l3){
		index = indexIn;
		credit = Double.parseDouble(l1);
		I = Integer.parseInt(l2);
		List<String> ls = new ArrayList<String>(Arrays.asList(l3.split(" ")));
		for(int i=0;i<I;i++){
			items.add(Double.parseDouble(ls.get(i)));
		}
		getItems();
	}
	//return "Case #" + index + ": " + i1 + " " + i2;
	private void getItems(){
		boolean found = false;
		for(int i1=0;i1<I-1;i1++){
			for(int i2=i1+1;i2<I;i2++){
				if(Math.abs(items.get(i1) + items.get(i2) - credit) < error){
		            item1 = i1 + 1;
		            item2 = i2 + 1;
		            found = true;
		            break;
				}
			}
			if (found){
				break;
			}
		}
	}
	public String toString(){
		return "Case #" + index + ": " + item1 + " " + item2;
	}
}
