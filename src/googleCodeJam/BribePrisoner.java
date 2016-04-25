package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class BribePrisoner {
	LinkedList<PQ> ch = new LinkedList<PQ>();
	public static void main(String[] arg) throws IOException{
		BribePrisoner bp = new BribePrisoner();
		bp.solve();
	}
	public void test(){
		int P = 100;
		int[] Q1 = {3,7,45,60,85};
		System.out.println(minCoins(P,Q1));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
        for(int X=1;X<=T;X++){
        	ch = new LinkedList<PQ>();
        	String[] Pq = in.readLine().split(" ");
        	int P = Integer.parseInt(Pq[0]);
        	int q = Integer.parseInt(Pq[1]);
        	String[] Qs = in.readLine().split(" ");
        	int[] Q = new int[q];
        	for(int i=0;i<q;i++){
        		Q[i] = Integer.parseInt(Qs[i]);
        	}
        	String output = "Case #"+X+": "+minCoins(P,Q);
        	System.out.println(output);
        	out.println(output);
        }
		in.close();
		out.close();
	}
	public int find(int P,int[] Q){
		int value = -1;
		for(PQ pq:ch){
			if(pq.P == P && isEqual(Q,pq.Q)){
				value = pq.value;
				break;
			}
		}
		return value;
	}
	private boolean isEqual(int[] Q1, int[] Q2){
		if(Q1.length != Q2.length){
			return false;
		}else{
			for(int i=0;i<Q1.length;i++)
				if(Q1[i] != Q2[i])
					return false;
		}
		return true;
		
	}
	public int minCoins(int P, int[] Q){
		int q = Q.length;
		int r = -1;
		if(q==0){
			return 0;
		}else if(q==1){
			return P - 1;
		}else{
			int value = find(P,Q);
			if(value!=-1) return value;
			for(int index = 0;index<q;index++){
				int P1 = Q[index] -1;
				int P2 = P - Q[index];
				int[] Q1 = new int[index];
				for(int i=0;i<index;i++){
					Q1[i] = Q[i];
				}
				int[] Q2 = new int[q - index-1];
				for(int i=0;i<q-index-1;i++){
					Q2[i] = Q[index+i+1] - Q[index];
				}
				int count = minCoins(P1,Q1) + minCoins(P2,Q2) + P-1;
				if(r==-1||r>count){
					r = count;
				}
			}
			ch.add(new PQ(P,Q,r));
		}
	return r;
	}
}

class PQ{
	int P;
	int[] Q;
	int value;
	PQ(int P, int[] Q, int value){
		this.P = P;
		this.Q = new int[Q.length];
		for(int i=0;i<Q.length;i++){
			this.Q[i] = Q[i];
		}
		this.value = value;
	}
}
