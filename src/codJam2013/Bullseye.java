package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Bullseye {
	public static void main(String[] args) throws IOException {
		Bullseye t = new Bullseye();
		//Tools.print(Long.MAX_VALUE);
		t.solve();
	}
	void test(){
		long r = 3;
		long t = 40;
		for(int i=1;i<4;i++)
			TT.println(f(i,r));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] rt = TT.longArray(in.readLine()," ");
			long r = rt[0];
			long t = rt[1];
			
			
			String output = "Case #"+caseN + ": "+maxBlack(r,t);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long maxBlack(long r, long t){
		long[] n01 = getN1(r,t);
		long n0 = n01[0];
		long n1 = n01[1];
		TT.println(n0+","+n1+":"+f(n0,r)+","+f(n1,r));
		while(true){
			if(f(n0+1,r)>t){
				return n0;
			}
			long n = (n0+n1)/2;
			if(f(n,r) > t){
				n1 = n;
			}else{
				n0 = n;
			}
		}
	}
	long[] getN1(long r,long t){
		long n0 = 1;
		long n1 = n0*2;
		while(f(n1,r) <= t){
			n0 = n1;
			n1 = n1*2;
		}
		return new long[]{n0,n1};
	}
	long f(long n, long r){
		return n*(2*r-3) + 2*n*(n+1);
	}

}
