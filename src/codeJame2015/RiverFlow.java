package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class RiverFlow {
	public static void main(String[] args) throws IOException {
		RiverFlow t = new RiverFlow();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] nd = Tools.intArray(in.readLine(), " ");
			int N = nd[0];
			int D = nd[1];
			int[] d = Tools.intArray(in.readLine(), " ");
			int min = d[0];
			int max = d[0];
			for(int n=1;n<N;n++){
				if(min > d[n]){
					min = d[n];
				}
				if(max < d[n]){
					max = d[n];
				}
			}
			for(int n=0;n<N;n++){
				d[n] = d[n] - min;
			}
			max = max - min;
			//Tools.print(d);
			//Tools.println("D="+D);
			int minF = minFarmers(d,D);
			String s = "";
			if(minF < 0)
				s = "CHEATERS!";
			else
				s = minF+"";
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		Tools.println("total execution time is "+timeUsed);
	}
	int minFarmers(int[] d,int D){
		int L = minPeriod(d,D);
		if(L>2*D)
			return -1;
		else if(L<=1)
			return 0;
		int N = d.length;
		int min = -1;
		int[] d1 = init(N,L);
		for(int l=1;l<L;l++){
			for(int n=0;n<N;n++)
				d[n] = d[n] + d1[n];
			int ml = minFarmers(d,D);
			if(ml < 0)
				continue;
			ml++;
			if(min<0 || min > ml)
				min = ml;
			for(int n=0;n<N;n++)
				d[n] = d[n] - d1[n];
		}
		return min;
	}
	
	void shift(int[] d,int period){
		int N = d.length;
		int d0 = d[period-1];
		for(int n=1;n<N;n++){
			d[n] = d[n-1];
		}
		d[0] = d0;
	}
	int[] init(int N, int period){
		int[] d1 = new int[N];
		int p2 = period / 2;
		for(int i=0;i<N;i++){
			if(i<period){
				d1[i] = i<p2?0:1;
			}else{
				d1[i] = d1[i-period];
			}
		}
		return d1;
	}
	int minPeriod(int[] d, int D){
		int L = 2*D;
		while(L>0 && isPeriod(d,L)){
			L = L / 2;
		}
		return L*2;
	}
	boolean isPeriod(int[] d,int L){
		int N = d.length;
		for(int l=0;l<N-L;l++){
			if(d[l]!=d[l+L])
				return false;
		}
		return true;
	}

}
