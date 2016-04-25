package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class Inheritance {
	int N;
	di[] dis;
	public static void main(String[] args) throws IOException {
		new Inheritance().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			N = Integer.parseInt(in.readLine());
			dis = new di[N+1];
			for(int i=1;i<=N;i++){
				int[] line = TT.intArray(in.readLine()," ");
				int Mi = line[0];
				dis[i] = new di(i,Mi);
				for(int j=1;j<=Mi;j++){
					dis[i].point.add(line[j]);
				}
			}
			String output = "Case #"+caseN + ": "+inheritance();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String inheritance(){
		for(int i=1;i<=N;i++){
			if(spread(dis[i],i))
				return "YES";
		}
		return "NO";
	}
	boolean spread(di d1,int i){
		for(int j:d1.point){
			di dij = dis[j];
			if(dij.status==i)
				return true;
			else
				dij.status = i;
		}
		for(int j:d1.point){
			di dij = dis[j];
			if(spread(dij,i))
				return true;
		}
		return false;
	}
}
class di{
	int id;
	int M;
	int status = 0;
	LinkedList<Integer> point = new LinkedList<Integer>();
	di(int id1,int M1){
		id = id1;
		M = M1;
	}
}