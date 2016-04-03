package codeJame2015;

import googleCodeJam.Template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class Haircut {
	long B, N;
	int[] M;
	public static void main(String[] args) throws IOException {
		Haircut t = new Haircut();
		
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] BN = Tools.longArray(in.readLine(), " ");
			B = BN[0];
			N = BN[1];
			M = Tools.intArray(in.readLine(), " ");
			String output = "Case #"+caseN + ": "+getBarbar();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int getBarbar(){
		if(N<=B){
			return (int)N;
		}
		double speed = 0;
		for(int k=0;k<B;k++){
			speed += 1.0/M[k];
		}
		long t1 = 1;
		long t2 = (long) (2*N/speed);
		long[] f1 = finished(t1);
		while(!(f1[0] < N && f1[0] + f1[1] >=N)){
			long tm = (t1+t2)/2;
			long[] fm = finished(tm);
			if(fm[0] + fm[1] < N){
				t1 = tm;
				f1 = fm;
			}else if(fm[0] >= N){
				t2 = tm;
			}else{
				t1 = tm;
				f1 = fm;
				break;
			}
		}
		long fN = f1[0];
		for(int k=0;k<B;k++){
			if(t1 % M[k] == 0){
				fN++;
				if(fN == N){
					return k+1;
				}
			}
		}
		return -1;
	}
	
	long[] finished(long time){//finished Q
		long finished = 0;
		long justA = 0;
		for(int k=0;k<B;k++){
			int Mk = M[k];
			finished += time / Mk;
			if(time % Mk == 0){
				justA++;
			}else{
				finished++;
			}
		}
		return new long[]{finished,justA};
	}
}
