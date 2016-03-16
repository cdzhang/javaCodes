package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Battleship {
	public static void main(String[] args) throws IOException {
		Battleship t = new Battleship();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] rcw = Tools.intArray(in.readLine(), " ");
			int R = rcw[0];
			int C = rcw[1];
			int W = rcw[2];
			String output = "Case #"+caseN + ": "+getOneLine(R,C,W);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long getOneLine(int C, int W){
		if(C==W){
			return W;
		}else if(W==1){
			return C;
		}else if(C<=2*W){
			return W+1;
		}else{
			return 1 + getOneLine(C-W,W);
		}
	}
	long getOneLine(int R, int C, int W){
		return (R-1)*(C/W) + getOneLine(C,W);
	}
}
