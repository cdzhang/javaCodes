package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Template {
	
	public static void main(String[] args) throws IOException {
		Template t = new Template();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
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
	}

}