package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Template;
import tools.TT;

public class Pogo {
	public static void main(String[] args) throws IOException {
		Pogo t = new Pogo();
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
			
			
			
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getJump(int X,int Y){//|X| <= |Y|
		int aX = Math.abs(X);
		LinkedList<int[]> jumps = new LinkedList<int[]>();
		
		for(int i=1;i<=aX-1;i++){
			int[] jy = new int[]{1,i};
			jumps.add(jy);
		}
		int Y0 = (aX-1)*aX/2;
	    jumps.add(new int[]{0,aX});
	    if(Y0>Y){
	    	int d = Y0 - Y;
	    	
	    }
		
		return "";
	}
	int[] getNK(int X, int Y){
		int sum = Math.abs(X)+Math.abs(Y);
		int n = 1;
		while(n*(n+1)<=sum)
			n++;
		n--;
		int K = sum - n*(n+1)/2;
		return new int[]{n,K};
	}
}
