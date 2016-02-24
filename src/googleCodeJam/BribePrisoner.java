package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class BribePrisoner {
	public static void main(String[] arg) throws IOException{
		BribePrisoner bp = new BribePrisoner();
		bp.test();
	}
	public void test(){
		int P = 100;
		int[] Q1 = {3,7,45,60,85};
		System.out.println(minCoins(P,Q1));
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
        for(int X=1;X<=T;X++){
        	String[] Pq = in.readLine().split(" ");
        	int P = Integer.parseInt(Pq[0]);
        	int q = Integer.parseInt(Pq[1]);
        	String[] Qs = in.readLine().split(" ");
        	int[] Q = new int[q];
        	for(int i=0;i<q;i++){
        		Q[i] = Integer.parseInt(Qs[i]);
        	}
        	String output = "Case #"+X+": "+minCoins(P,Q);
        	System.out.print("P="+P+",Q=[");
        	for(int a:Q){
        		System.out.print(a+",");
        	}
        	System.out.println("]");
        	System.out.println(output);
        	out.println(output);
        }
		in.close();
		out.close();
	}
	public int minCoins(int P, int[] Q){
		int q = Q.length;
		if(q==0){
			return 0;
		}else if(q==1){
			return P - 1;
		}else{
			//find the first split point,max(min(Q[i]-1,P-Q[i]))
			int max = 0;
			int index = 0;
			for(int i=0;i<q;i++){
				int a = Math.min(Q[i]-1, P-Q[i]);
				if(max < a){
					max = a;
					index = i;
				}
			}
			//System.out.println(Q[index]);
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
        	System.out.print("P="+P+",Q=[");
        	for(int a:Q){
        		System.out.print(a+",");
        	}
        	System.out.println("],value="+count);
			return count;
		}
	}
	
}
