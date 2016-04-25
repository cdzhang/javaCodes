package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Password {
	int A, B;
	double[] p,mp;
	public static void main(String[] args) throws IOException {
		Password t = new Password();
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
			int[] ab = TT.intArray(in.readLine()," ");
			A = ab[0];
			B = ab[1];
			p = TT.doubleArray(in.readLine(), " ");
			mp = new double[(int)A];
			mp[0] = p[0];
			for(int a = 1;a<A;a++){
				mp[a] = p[a]*mp[a-1];
			}
			//Tools.print(mp);
			String output = "Case #"+caseN + ": "+getExpectation();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	double getExpectation(){
		double min = 1 + B + 1;
		for(int k=0;k<A;k++){
			double EK = k + (B-A+k+1)*mp[A-k-1] + (B-A+k+1+B+1)*(1-mp[A-k-1]);
			if(min > EK)
				min = EK;
		}
		double EA = A + B+1;
		return Math.min(EA,min);
	}
	
}
