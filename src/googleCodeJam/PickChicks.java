package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class PickChicks {
	public static void main(String[] arg) throws IOException{
		PickChicks pc = new PickChicks();
		pc.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int C = Integer.parseInt(in.readLine());//first line
		for(int caseIndex=1;caseIndex<=C;caseIndex++){
			String[] NKBT = in.readLine().split(" ");
			int N = Integer.parseInt(NKBT[0]);
			int K = Integer.parseInt(NKBT[1]);
			int B = Integer.parseInt(NKBT[2]);
			int T = Integer.parseInt(NKBT[3]);
			String[] Xs = in.readLine().split(" ");
			int[] X = new int[N];
			for(int i=0;i<N;i++){
				X[i] = Integer.parseInt(Xs[i]);
			}
			String[] Vs = in.readLine().split(" ");
			int[] V = new int[N];
			for(int i=0;i<N;i++){
				V[i] = Integer.parseInt(Vs[i]);
			}
			int canReach = 0;//chicks that can reach the bar in time T
			int cannotReach = 0;
			int totalSwitch = 0;
			//if chicks i and j can reach the barn and Xi < Xj, then Si <= Sj, Si is the switches needed for
			//check i. So count the total switches the first K chicks nearest to the barn needed
			for(int i=N-1;i>=0;i--){
				double time = 1.0*(B - X[i])/V[i];//time needed to reach the barn
				if(time <= T){
					canReach++;
					totalSwitch += cannotReach;
					if(canReach >= K){
						break;
					}
				}else{
					cannotReach++;
				}
			}
			String output = "Case #"+caseIndex+": ";
			if(canReach < K){
				output += "IMPOSSIBLE";
			}else{
				output += totalSwitch;
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
}
