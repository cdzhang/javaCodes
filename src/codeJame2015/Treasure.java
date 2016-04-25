package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class Treasure {
	LinkedList<Integer> keys = new LinkedList<Integer>();
	LinkedList<Integer> open = new LinkedList<Integer>();
	int N;
	chest[] chests;
	public static void main(String[] args) throws IOException {
		Treasure t = new Treasure();
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
			keys.clear();
			open.clear();
			int[] kn = TT.intArray(in.readLine(), " ");
		    N = kn[1];
			int[] keyarray = TT.intArray(in.readLine(), " ");
			for(int key:keyarray){
				keys.add(key);
			}
			chests = new chest[N+1];
			for(int i=1;i<=N;i++){
				int[] li = TT.intArray(in.readLine(), " ");
				chests[i] = new chest(i,li);
			}
			String s = "";
			boolean can = open();
			if(can){
				for(int ci:open){
					s = s + " " + ci;
				}
			}else{
				s = " IMPOSSIBLE";
			}
			String output = "Case #"+caseN + ":"+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	boolean open(){
		for(int i=1;i<=N;i++){
			chest ci = chests[i];
			if(open.contains(i)){
				continue;
			}
			int Ti = ci.Ti;
			if(keys.contains(Ti)){
				open.add(i);
				if(open.size()==N)
					return true;
				keys.remove((Integer)Ti);
				keys.addAll(ci.keys);
				boolean can = open();
				if(can){
					return true;
				}else{
					open.remove((Integer)i);
					keys.removeAll(ci.keys);
					keys.add(Ti);
				}
			}
		}
		return false;
	}
}
class chest{
	int ID;
	int Ti;
	LinkedList<Integer> keys = new LinkedList<Integer>();
	chest(int id,int[] li){
		ID = id;
		Ti = li[0];
		int Ki = li[1];
		for(int i=0;i<Ki;i++)
			keys.add(li[i+2]);
	}
}
