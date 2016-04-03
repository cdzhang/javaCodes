package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Tools;

public class Pancake {
	int D;
	int[] P;
	public static void main(String[] args) throws IOException {
		Pancake t = new Pancake();
		//t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			D = Integer.parseInt(in.readLine());
			P = Tools.intArray(in.readLine(), " ");
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}

	boolean canFinish(int t){
		LinkedList<Integer> maxs = findMax();
		int max = P[maxs.get(0)];
		if(max<=t)
			return true;
		int uT = 0;
		while(true){
			int L = maxs.size();
			
			break;
		}
		return false;
	}
	LinkedList<Integer> findMax(){
		int max = P[0];
		LinkedList<Integer> maxs = new LinkedList<Integer>();
		for(int i=0;i<D;i++){
			if(P[i] > max)
				max = P[i];
		}
		for(int i=0;i<D;i++){
			if(P[i]==max)
				maxs.add(i);
		}
		return maxs;
	}

}

