package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class RankAndFile {
	int[] count;
	int N;
	public static void main(String[] args) throws IOException {
		RankAndFile t = new RankAndFile();
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
			N = Integer.parseInt(in.readLine());
			count = new int[2501];
			for(int i=0;i<2*N-1;i++){
				int[] h = TT.intArray(in.readLine(), " ");
				//TT.println(N+","+h.length);
				for(int k=0;k<N;k++){
					int j = h[k];
					count[j] = count[j]+1;
				}
			}
			TT.println("N="+N);
			LinkedList<Integer> array = new LinkedList<Integer>();
			for(int i=1;i<=2500;i++){
				if(count[i]%2!=0){
					TT.println(i+":"+count[i]);
					array.add(i);
				}
			}
			Collections.sort(array);
			String s = "";
			for(int a:array){
				s = s+" "+a;
			}
			String output = "Case #"+caseN + ":"+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}


}
