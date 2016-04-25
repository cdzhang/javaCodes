package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class PartElf {
	long[] p2;
	PartElf(){
		p2 = TT.power2(41);
		//Tools.print(p2);
	}
	public static void main(String[] args) throws IOException {
		PartElf t = new PartElf();
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
			long[] pq = TT.longArray(in.readLine(), "/");
			long P = pq[0];
			long Q = pq[1];
			String output = "Case #"+caseN + ": "+minGeneration(P,Q);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String minGeneration(long P, long Q){
		boolean possible = false;
		long g = TT.gcd(P, Q);
		P /= g;
		Q /= g;
		for(int i=0;i<=40;i++){
			if(Q==p2[i])
				possible = true;
		}
		if(!possible)
			return "impossible";
		for(int i=1;i<=40;i++){
			if(P*p2[i] >= Q)
				return i+"";
		}
		return "";
	}

}
