package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class Mushroom {
	int[] numbers;
	int N;
	public static void main(String[] args) throws IOException {
		Mushroom t = new Mushroom();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			N = Integer.parseInt(in.readLine());
			numbers = Tools.intArray(in.readLine(), " ");
			String output = "Case #"+caseN + ": " +min1() + " " + min2();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long min1(){
		long min = 0;
		for(int i=1;i<N;i++){
			int k = numbers[i-1] - numbers[i];
			if(k>0)
				min+=k;
		}
		return min;
	}
	long min2(){
		int minSpeed = 0;
		for(int i=1;i<N;i++){
			int d = numbers[i-1] - numbers[i];
			if(d > minSpeed){
				minSpeed = d;
			}
		}
		long min = 0;
		for(int i=0;i<N-1;i++){
			min += Math.min(numbers[i],minSpeed);
		}
		return min;
	}
}
