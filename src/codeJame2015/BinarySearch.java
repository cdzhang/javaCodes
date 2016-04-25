package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class BinarySearch {
	int[] cost;
	long[] left;
	long[] right;
	long[][] record;
	public static void main(String[] args) throws IOException {
		BinarySearch t = new BinarySearch();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			cost = TT.intArray(in.readLine(), "");
			int N = cost.length;
			left = new long[N];
			right = new long[N];
			record = new long[N][N];
			long minCost = 0;
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					minCost(i,j);
				}
			}
			String output = "Case #"+caseN + ": "+minCost;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long minCost(int i,int j){
		if(record[i][j]!=0)
			return record[i][j];
		long min;
		if(i==j)
			min = cost[i];
		else if(Math.abs(i-j)==1)
			min = cost[i] + cost[j];
		else{
		    min = Math.min(cost[i]+minCost(i+1,j), cost[j]+minCost(i,j-1));
			for(int k=i+1;k<=j-1;k++){
				long mk = cost[k] + Math.max(minCost(i,k-1), minCost(k+1,j));
				if(mk < min)
					min = mk;
			}
		}
		record[i][j] = min;
		return min;
	}
	
	
}
