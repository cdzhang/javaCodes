package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Slides {
	int B;
	long M;
	int[][] cn;
	long[] dp;
	Slides(){
		int bm = 51;
		dp = new long[bm];
		for(int b=2;b<bm;b++){
			dp[b] = 1;
			for(int i = 1;i<=b-1;i++){
				dp[b] = dp[i]+dp[b];
			}
			//TT.println(b+","+dp[b]);
		}
	}
	public static void main(String[] args) throws IOException {
		//Slides a = new Slides();
		new Slides().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] line = TT.longArray(in.readLine(), " ");
			B = (int) line[0];
			M = line[1];
			cn = new int[B][B];
			String s = getOutput(M);
			if(s=="POSSIBLE"){
				for(int i=0;i<B;i++){
					s=s+"\n";
					for(int j=0;j<B;j++){
						s=s+cn[B-1-i][B-1-j];
						if(j!=B-1)
							s=s+" ";
					}
				}
			}
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getOutput(long M1){
		String s = "";
		if(dp[B] < M1)
			return "IMPOSSIBLE";
		s = "POSSIBLE";
		long count = 1;
		int bi = 1;
		while(count < M1){
			bi++;
			count = count + dp[bi];
		}
		if(count==M1){
			construct(B,bi);
			return s;
		}else{
			construct(B,bi-1);
			return getOutput(M1-(count-dp[bi]));
		}
	}
	
	void construct(int b,int j){
		for(int i=1;i<=j;i++){
			cn[b-1][i-1] = 1;
			construct(i,i-1);
		}
	}
	
}
