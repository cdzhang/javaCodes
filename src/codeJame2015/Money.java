package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Money {
	long[] deno;
	long C, D, V;
	public static void main(String[] args) throws IOException {
		Money t = new Money();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] lo = Tools.longArray(in.readLine(), " ");
			C = lo[0];
			D = lo[1];
			V = lo[2];
			deno = Tools.longArray(in.readLine(), " ");
			String output = "Case #"+caseN + ": "+ needAdd(C);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long needAdd(){
		int dIndex = 0;
		long maxCover=0;
		int added=0;
		while(maxCover<V){
			long ad = maxCover+1;
			if(dIndex <D && ad==deno[dIndex]){
				dIndex++;
			}else{
				added++;
			}
			maxCover = 2*ad - 1;
			if(maxCover>=V)
				return added;
			while(dIndex < D && maxCover >= deno[dIndex]){
				maxCover = deno[dIndex]+maxCover;
				dIndex++;
				if(maxCover>=V)
					return added;
			}//after this deno[dIndex] > maxCover
		}
		return added;
	}
	long needAdd(long c){
		int dIndex = 0;
		long maxCover=0;
		int added=0;
		while(maxCover<V){
			long ad = maxCover+1;
			if(dIndex <D && ad==deno[dIndex]){
				dIndex++;
			}else{
				added++;
			}
			maxCover = c*ad+ad-1;
			if(maxCover>=V)
				return added;
			while(dIndex < D && maxCover >= deno[dIndex]){
				maxCover = c*deno[dIndex]+maxCover;
				dIndex++;
				if(maxCover>=V)
					return added;
			}//after this deno[dIndex] > maxCover
		}
		return added;
	}
}
