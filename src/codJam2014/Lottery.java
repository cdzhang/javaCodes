package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Lottery {
	long[] power2;
	long A, B,K;
	Lottery(){
		power2 = TT.power2(30);
	}
	public static void main(String[] args) throws IOException {
		Lottery t = new Lottery();
		t.solve();
		t.A = 2;
		t.B = 2;
		t.K = 2;
		TT.println(t.countK(0, 0, 1, 1));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] line = TT.longArray(in.readLine(), " ");
			A = line[0];
			B = line[1];
			K = line[2];
			int i = 0;
			while(true){
				long Ai = A / power2[i+1];
				long Bi = B / power2[i+1];
				long Ki = B / power2[i+1];
				if(Ai>0 || Bi>0 || Ki>0){
					i++;
				}else
					break;
			}
			//Tools.println(i);
			String output = "Case #"+caseN + ": "+countK(0,0,K,i);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	
	long countK(long A1, long B1, long K1,int b){
		if(A1 >= A || B1 >=B || (A1&B1)>=K1){
			return 0;
		}
		if(b==-1)
			return 1;
		long Ab_m = A1 / power2[b+1];
		long Bb_m = B1 / power2[b+1];
		long Kb_m = K1 / (power2[b+1]);
		if((Ab_m&Bb_m) < Kb_m){
			return Math.min(A-A1, power2[b+1])*Math.min(B-B1, power2[b+1]);
		}else{
			long A10 = A1;
			long A11 = A1+power2[b];
			long B10 = B1;
			long B11 = B10+power2[b];
			long c00, c01, c10, c11;
			c00 = countK(A10,B10,K1,b-1);
			c01 = countK(A10,B11,K1,b-1);
			c10 = countK(A11,B10,K1,b-1);
			c11 = countK(A11,B11,K1,b-1);
			return c00 + c01 + c10 + c11;
		}
	}
}
