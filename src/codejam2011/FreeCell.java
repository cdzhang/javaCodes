package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class FreeCell {
	public static void main(String[] args) throws IOException {
		new FreeCell().solve();
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
			long N = line[0];
			int pD = (int) line[1];
			int pG = (int) line[2];
			int gd = TT.gcd(pD, 100);
			int X = 100/gd;
			String s = "";
			if((pG==100 && pD!=100) || (pG==0&&pD!=0)){
				s = "Broken";
			}else if(pD==0){
					s = "Possible";
			}else{
				if(N<X)
					s = "Broken";
				else{
					s = "Possible";
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

}
