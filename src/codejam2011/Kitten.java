package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class Kitten {
	int N,M;
	int[] U,V;
	int[] color;
	LinkedList<room> sr = new LinkedList<room>();
	room center;
	public static void main(String[] args) throws IOException {
		new Kitten().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			sr.clear();
			int[] nm = TT.intArray(in.readLine(), " ");
			N = nm[0];
			M = nm[1];
			color = new int[N];
			U = TT.intArray(in.readLine(), " ");
			V = TT.intArray(in.readLine(), " ");
			TT.println("N="+N);
			TT.print(U);
			TT.print(V);
			center = new room();
			for(int i=1;i<=N;i++)
				center.V.add(i);
			for(int m=0;m<M;m++){
				int um = U[m];
				int vm = V[m];
				room rm = new room();
				rm.um = um;
				rm.vm = vm;
				if(isIn(um,vm,U,V)){
					for(int i=vm;i<=um+N;i++){
						int vi = i%N;
						if(vi==0) vi = N;
						rm.V.add(vi);
						if(vi!=um && vi!=vm)
							center.V.remove(new Integer(vi));
					}
					sr.add(rm);
				}else{
					for(int i=um;i<=vm;i++){
						rm.V.add(i);
						if(i!=um && i!=vm)
							center.V.remove(new Integer(i));
					}
					sr.add(rm);
				}
			}
			Collections.sort(center.V);
			String output = "Case #"+caseN + ": "+paint()+"\n";
			for(int i=0;i<N;i++){
				if(i==0)
					output += color[i];
				else
					output += " "+color[i];
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int paint(){
		int count = 2001;
		for(room r:sr)
			if(count > r.V.size())
				count = r.V.size();
		if(count > center.V.size())
			count = center.V.size();
		LinkedList<Integer> cv = center.V;
		
		for(int i=0;i<cv.size();i++){
			int co = (i+1)%count;
			if(co==0) co = count;
			if(i==cv.size()-1 && co == color[cv.get(0)-1]){
				for(int j=1;j<=count;j++){
					if(j!=color[cv.get(i-1)-1] && j!=color[cv.getFirst()-1]){
						co = j;
					}
				}
			}
			color[cv.get(i)-1] = co;
		}
		for(room rm:sr){
			LinkedList<Integer> unused = new LinkedList<Integer>();
			for(int i=1;i<=count;i++)
				unused.add(i);
			unused.remove(new Integer(color[rm.um-1]));
			unused.remove(new Integer(color[rm.vm-1]));
			for(int vi:rm.V){
				if(vi==rm.um||vi==rm.vm) continue;
				int co = unused.getFirst();
				color[vi-1] = co;
				if(unused.size() > 1)
					unused.removeFirst();
			}
		}
		return count;
	}
	boolean isIn(int um, int vm,int[] U,int[] V){
		for(int n=um+1;n<vm;n++){
			for(int i=0;i<M;i++){
				if(U[i]==n || V[i]==n)
					return true;
			}
		}
		return false;
	}

}
class room{
	int um;
	int vm;
	LinkedList<Integer> V = new LinkedList<Integer>();
}