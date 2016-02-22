package googleCodeJam;

import java.util.LinkedList;

public class MilkshakesCase {
	int N;//number of milkshakes
	int customerCount;
	MilkshakesCase(int N, int M){
		this.N = N;
		customerCount = M;
	}
	LinkedList<MilkshakesCustomer> customers = new LinkedList<MilkshakesCustomer>();
	public void addCustomer(MilkshakesCustomer aCustomer){
		aCustomer.setIndex(customers.size());
		customers.add(aCustomer);
	}
	public String getOutput(){
		String output = "";
		customerCount = customers.size();
		int[] seq = new int[N];
		for(int i:seq){
			i = 0;
		}
		int index = -1;
		while((index =areAllSatisfied(seq)) != -1 ){
			System.out.println(index);
			MilkshakesCustomer unc = customers.get(index);
			int Nindex = unc.maltedIndex();
			if(Nindex == -1){
				return " IMPOSSIBLE";
			}
			seq[Nindex-1] = 1;
		}
		for(int i:seq){
			output = output + " " +i;
		}
		return output;
	}
	public int areAllSatisfied(int[] seq){
		for(int i=0;i<customers.size();i++){
			if(!customers.get(i).isSatisfied(seq)){
				return i;
			}
		}
		return -1;
	}
	
}
