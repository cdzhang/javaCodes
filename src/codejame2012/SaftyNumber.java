package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Template;
import tools.TT;

public class SaftyNumber {
	int N;
	int[] s;
	public static void main(String[] args) throws IOException {
		new SaftyNumber().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			s = TT.intArray(in.readLine(), " ");
			N = s[0];
			s[0] = -1;
		/*	int[] sIndex = new int[N+1];
			boolean[] choosen = new boolean[N+1];
			int rank = 1;
			while(rank < N){
				int min = -1;
				int iI = -1;
				for(int i=1;i<=N;i++){
					if(choosen[i])
						continue;
					int si = s[i];
					if(min<0 || min > si){
						min = si;
						iI = i;
					}
				}
				choosen[iI] = true;
				sIndex[rank] = iI;
				rank++;
			}*/
			int[] s1 = s.clone();
			s1[0] = -1;
			TT.sort(s1);
			int[] sum1 = new int[N+1];
			for(int i=1;i<=N;i++){
				sum1[i] = sum1[i-1] + s1[i];
			}
			int Y = sum1[N];
			int sI = N;
			for(int i=2;i<=N;i++){
				if(i*s1[i] >= Y+sum1[i]){
					sI = i-1;
					break;
				}
			}
			double fline = 1.0*(Y+sum1[sI])/sI;
			double[] minF = new double[N+1];
			for(int i=1;i<=N;i++){
				int si = s[i];
				if(si<fline){
					minF[i] = 100.0*(fline-si)/Y;
				}else{
					minF[i] = 0.0;
				}
			}
			String ss = "";
			for(int i=1;i<=N;i++)
				ss = ss + " " + minF[i];
			String output = "Case #"+caseN + ":" + ss;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
}
