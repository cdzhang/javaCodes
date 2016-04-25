package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Template;
import tools.TT;

public class ManageEnergy {
	long E, R;
	int N;
	long[] V;
	public static void main(String[] args) throws IOException {
		ManageEnergy t = new ManageEnergy();
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
			long[] ern = TT.longArray(in.readLine(), " ");
			E = ern[0];
			R = ern[1];
			N = (int)ern[2];
			V = TT.longArray(in.readLine(), " ");
			int[] nL = new int[N];
			String output = "Case #"+caseN + ": "+totalGain(0,E);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long totalGain(int n,long e){
		if(n==N-1){
			return V[n]*e;
		}
		int nn = nextLarger(n);
		if(nn==-1){
			return V[n]*e + totalGain(n+1,Math.min(R,E));
		}
		if(R*(nn-n)+e<E){
			return totalGain(nn,R*(nn-n)+e);
		}else if(R*(nn-n)>=E){
			return V[n]*e + totalGain(n+1,Math.min(R, E));
		}else{//R*(nn-n)+e >= E but R*(nn-n)<e;
			return V[n]*(R*(nn-n)+e-E) + totalGain(nn,E);
		}
	}
	int nextLarger(int n){
		long vn = V[n];
		for(int i=n+1;i<N;i++){
			if(V[i] > vn)
				return i;
		}
		return -1;
	}
}
