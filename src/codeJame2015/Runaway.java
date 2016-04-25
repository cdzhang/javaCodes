package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import tools.TT;

public class Runaway {
	int Y, N;
	int[] P, V;
	double INF = -1;
	LinkedList<Integer> left = new LinkedList<Integer>();
	LinkedList<Integer> right = new LinkedList<Integer>();
	Set<Integer> caught = new HashSet<Integer>();
	public static void main(String[] args) throws IOException {
		Runaway t = new Runaway();
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
			left.clear();
			right.clear();
			caught.clear();
			int[] yn = TT.intArray(in.readLine()," ");
			Y = yn[0];
			N = yn[1];
			P = TT.intArray(in.readLine(), " ");
			V = TT.intArray(in.readLine(), " ");
			initLeftRight();
			String output = "Case #"+caseN + ": "+getTime(0,0);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	double getTime(double pos,double time){//at time time, I am position pos
		if(caught.size() == N)
			return time;
		Set<Integer> tmp_caught = new HashSet<Integer>();
		//right
		int rI = 0;
		double rtime = INF;
		while(rI<right.size()){
			if(!caught.contains(right.get(rI)))
				break;
			rI++;
		}
		//Tools.println("rI="+rI);
		if(rI < right.size()){
			int index = right.get(rI);
			double Pi = P[index]*1.0;
			double Vi = V[index]*1.0;
			double newTime = (Pi-pos+time*Y)/(Y-Vi);
			double newPos = Pi + newTime*Vi;
			tmp_caught.add(index);
			for(int fI:right){
				double Px = P[fI]*1.0;
				double Vx = V[fI]*1.0;
				if(!caught.contains(fI) && (Px+Vx*newTime <= newPos)){
					tmp_caught.add(fI);
				}
			}
			caught.addAll(tmp_caught);
			rtime = getTime(newPos,newTime);
			caught.removeAll(tmp_caught);
			tmp_caught.clear();
		}
		//left
		int lI = 0;
		double ltime = INF;
		while(lI < left.size()){
			if(!caught.contains(left.get(lI)))
				break;
			lI++;
		}
	//	Tools.println("lI="+lI);
		if(lI<left.size()){
			int index = left.get(lI);
			double Pi = P[index]*1.0;
			double Vi = -V[index]*1.0;
			double newTime = (Pi-pos-time*Y)/(-Y-Vi);
			double newPos = Pi + newTime*Vi;
			tmp_caught.add(index);
			for(int fI:left){
				double Px = P[fI]*1.0;
				double Vx = -V[fI]*1.0;
				if(!caught.contains(fI) && (Px+Vx*newTime >= newPos)){
					tmp_caught.add(fI);
				}
			}
			caught.addAll(tmp_caught);
			ltime = getTime(newPos,newTime);
			caught.removeAll(tmp_caught);//
		}
		if(rtime == INF)
			return ltime;
		else if(ltime == INF)
			return rtime;
		else
			return Math.min(rtime,ltime);
	}
	void initLeftRight(){
		for(int n=0;n<N;n++){
			if(P[n]<0){
				left.add(n);
			}else{
				right.add(n);
			}
		}
		int L = left.size();
		for(int i=0;i<L-1;i++){
			for(int j=i+1;j<L;j++){
				int ai = left.get(i);
				int aj = left.get(j);
				if(V[ai] < V[aj]){
					left.set(i, aj);
					left.set(j,ai);
				}
			}
		}
		int R = right.size();
		for(int i=0;i<R-1;i++){
			for(int j=i+1;j<R;j++){
				int ai = right.get(i);
				int aj = right.get(j);
				if(V[ai] < V[aj]){
					right.set(i, aj);
					right.set(j,ai);
				}
			}
		}
	}
}
