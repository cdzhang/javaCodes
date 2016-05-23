package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class CostyBinarySearch {
	int N;
	int[] cs;
	int[][] dp;
	public static void main(String[] args) throws IOException {
		new CostyBinarySearch().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String s = in.readLine();
			char[] csc = s.toCharArray();
			N = csc.length;
			cs = new int[N];
			for(int i=0;i<N;i++)
				cs[i] = csc[i] - '0';
			dp = new int[N][N];
			int cost = getCost(0,N-1);
			//TT.print(dp);
			String output = "Case #"+caseN + ": "+cost;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int getCost(int i,int j){
		if(i>j)
			return 0;
		if(dp[i][j]!=0)
			return dp[i][j];
		int cost = Integer.MAX_VALUE;
		for(int k=i;k<=j;k++){
			int left = getCost(i,k-1);
			int right = getCost(k+1,j);
			int ck = Math.max(left, right)+cs[k];
			if(ck < cost)
				cost = ck;
		}
		dp[i][j] = cost;
		return cost;
	}
}
