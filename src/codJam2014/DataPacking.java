package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Template;
import tools.TT;

public class DataPacking {
	public static void main(String[] args) throws IOException {
		DataPacking t = new DataPacking();
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
			int[] nx = TT.intArray(in.readLine(), " ");
			int N = nx[0];
			int X = nx[1];
			int[] files = TT.intArray(in.readLine()," ");
			TT.sort(files);
			int count = 0;
			boolean[] stored = new boolean[N];
			for(int i= N-1;i >=0;i--){
				if(stored[i])
					continue;
				stored[i] = true;
				count++;
				int fi = files[i];
				for(int j=i-1;j>=0;j--){
					if(stored[j]==false && fi+files[j]<=X){
						stored[j] = true;
						break;
					}
				}
			}
			
			String output = "Case #"+caseN + ": "+count;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	
}
