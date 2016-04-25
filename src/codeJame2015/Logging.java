package codeJame2015;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class Logging {
	int N;
	int[] X,Y;
	double err = 1e-8;
	public static void main(String[] args) throws IOException {
		Logging t = new Logging();
		t.solve();
		TT.println(Math.asin(0.5)/2/Math.PI*360);
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			N = Integer.parseInt(in.readLine());
			X = new int[N];
			Y = new int[N];
			for(int n=0;n<N;n++){
				int[] xy = TT.intArray(in.readLine()," ");
				X[n] = xy[0];
				Y[n] = xy[1];
			}
			
			String output = "Case #"+caseN + ": ";
			for(int n=0;n<N;n++){
				output = output + "\n" + smallestTrees(n);
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int smallestTrees(int i){
		LinkedList<Double> angles = new LinkedList<Double>();
		int x0 = X[i];
		int y0 = Y[i];
		for(int n=0;n<N;n++){
			if(n==i)
				continue;
			int xi = X[i] - x0;
			int yi = Y[i] - y0;
			if(xi==0){
				if(yi>0)
					angles.add(90.0);
				else
					angles.add(270.0);
			}else{
				double angle = Math.atan(1.0*yi/xi)/2/Math.PI*360;
				if(xi < 0)
					angle = angle + 180;
				if(angle < 0)
					angle = angle + 360;
				angles.add(angle);
			}
		}
		Collections.sort(angles);
		int minCount = -1;
		for(int a=0;a<angles.size();a++){
			int count = 0;
			double angle = angles.get(a);
			for(int b=0;b<angles.size();b++){
				double angle2 = angles.get(b);
				if(angle2 > angle+err && angle2 < angle-err){
					count++;
				}
			}
			if(minCount < 0||minCount > count)
				minCount = count;
		}
		return minCount;
	}
}
