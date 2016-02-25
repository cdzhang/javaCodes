package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GCube {
	public static void main(String[] args) throws IOException{
		GCube gc = new GCube();
		gc.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String[] NM = in.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			double[] ai = new double[N];

			String[] aLine = in.readLine().split(" ");
			for(int i=0;i<N;i++){
				ai[i] = Double.parseDouble(aLine[i]);
			}
			String output = "Case #" + caseN+":";
			for(int m=0;m<M;m++){
				String[] RL = in.readLine().split(" ");
				int R = Integer.parseInt(RL[0]);
				int L = Integer.parseInt(RL[1]);
				int D = L - R + 1;
				double edge = 1.0;
				for(int i=R;i<=L;i++){
					edge *= Math.pow(ai[i], 1.0/D);
				}
			    output = output + "\n" + edge;
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
}
