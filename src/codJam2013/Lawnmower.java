package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Template;
import tools.TT;

public class Lawnmower {
	public static void main(String[] args) throws IOException {
		Lawnmower t = new Lawnmower();
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
			int[] nm = TT.intArray(in.readLine(), " ");
			int N = nm[0];
			int M = nm[1];
			int[][] field = new int[N][M];
			for(int n=0;n<N;n++){
				int[] ln = TT.intArray(in.readLine(), " ");
				field[n] = ln;
			}
			String output = "Case #"+caseN + ": "+validPattern(field);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String validPattern(int[][] field){
		int N = field.length;
		int M = field[0].length;
		int[] hmax = new int[M];
		for(int m=0;m<M;m++){
			int max = field[0][m];
			for(int n=1;n<N;n++){
				if(max < field[n][m])
					max = field[n][m];
			}
			hmax[m] = max;
		}
		int[] vmax = new int[N];
		for(int n=0;n<N;n++){
			int max = field[n][0];
			for(int m=1;m<M;m++){
				if(max<field[n][m])
					max = field[n][m];
			}
			vmax[n] = max;
		}
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				int k = field[i][j];
				if(k<vmax[i] && k< hmax[j])
					return "NO";
			}
		}
		return "YES";
	}

}
