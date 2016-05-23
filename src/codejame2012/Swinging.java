package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class Swinging {
	int N;
	long[][] vines;
	long D;
	public static void main(String[] args) throws IOException {
		new Swinging().solve();
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
			vines = new long[N][2];
			for(int i=0;i<N;i++)
				vines[i] = TT.longArray(in.readLine(), " ");
			D = Long.parseLong(in.readLine());
			
			String output = "Case #"+caseN + ": "+getResult();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getResult(){
		long[] h = new long[N];
		for(int i=N-1;i>=0;i--){
			if(vines[i][0]+vines[i][1]>=D)
				h[i] = D - vines[i][0];
			for(int j=i+1;j<N;j++){
				if(h[j]==0)
					continue;
				if(vines[i][0]+vines[i][1]<vines[j][0])
					break;
				long dij = vines[j][0]-vines[i][0];
				long hij = Math.min(vines[j][1], dij);
				if(hij<h[j]) continue;
				if(h[i]==0 || h[i]>dij)
					h[i] = dij;
			}
		}
		//TT.print(h);
		if(h[0]>0 && h[0] <=vines[0][0]) return "YES";
		return "NO";
	}
}
