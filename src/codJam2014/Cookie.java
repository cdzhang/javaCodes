package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Template;
import tools.TT;

public class Cookie {
	
	public static void main(String[] args) throws IOException {
		Cookie t = new Cookie();
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
			double[] cfx = TT.doubleArray(in.readLine(), " ");
			double C = cfx[0];
			double F = cfx[1];
			double X = cfx[2];
			
			
			String output = "Case #"+caseN + ": "+minTime(C,F,X);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	double minTime(double C, double F, double X){
		double rate = 2.0;
		double vtime = C/F;
		if(X <= C)
			return X/rate;
		double time = C/rate;
		double rtime = (X-C)/rate;
		while(rtime > vtime){
			rate += F;
			time += C/rate;
			rtime = (X-C)/rate;
		}
		return time + rtime;
	}
}
