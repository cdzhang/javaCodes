package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;
import tools.Template;

public class ManyPrize {
	long[] p2 = TT.power2(50);
	public static void main(String[] args) throws IOException {
		//new ManyPrize().solve();
		TT.println(Math.pow(2, 50));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] pn = TT.longArray(in.readLine()," ");
			long N = pn[0];
			long P = pn[1];
			
			
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long largestMustWin(){//
		
		return 0;
	}
}
