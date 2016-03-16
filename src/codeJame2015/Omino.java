package codeJame2015;

import googleCodeJam.Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Omino {
	int X,R,C;
	public static void main(String[] args) throws IOException {
		Omino t = new Omino();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] line = Tools.intArray(in.readLine(), " ");
			X = line[0];
			R = line[1];
			C = line[2];
			String output = "Case #"+caseN + ": "+winner();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String winner(){
		if(X==1)
			return "GABRIEL";
		if(R*C%X!=0)
			return "RICHARD";
		if(X==2)
			return "GABRIEL";

		int L = Math.min(R,C);
		if(X==3){
			if(L>1)
				return "GABRIEL";
			else
				return "RICHARD";
		}
		if(X==4){
			if(L>2)
				return "GABRIEL";
			else{
			  int M = Math.max(R, C);
			    if(M >=8)
			    	return  "GABRIEL";
			    else
			    	return "RICHARD";
			}
		}
		if(X==5){
			if(L>3)
				return "GABRIEL";
			else if(L<=2)
				return "RICHARD";
			else{
			  int M = Math.max(R, C);
			    if(M >=10)
			    	return  "GABRIEL";
			    else
			    	return "RICHARD";
			}
		}
		if(X==6){
			if(L>3){
				return "GABRIEL";
			}else
				return "RICHARD";
		}
		return "RICHARD";
	}
}
