package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CaptainHammer {
	double g = 9.8;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        CaptainHammer ch = new CaptainHammer();
        ch.solve();

	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			String[] s = in.readLine().split(" ");
			double V = Double.parseDouble(s[0]);
			double D = Double.parseDouble(s[1]);
			//System.out.println("D="+D+",V="+V+",Dg/V^2="+D*g/V/V);
			double sin2t = D*g/V/V;
			if(sin2t > 1 && sin2t < 1+1e-5) sin2t = 1;
			double theta = Math.asin(sin2t);
			theta = Math.toDegrees(theta) / 2;
			
			String output = "Case #" + caseN + ": " + theta;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}

}
