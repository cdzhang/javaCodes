package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class HotDog {
	long D;
	int C;
	long[][] vendors;
	public static void main(String[] args) throws IOException {
		new HotDog().solve();
		TT.println(Long.MIN_VALUE);
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] cd = TT.longArray(in.readLine(), " ");
			C = (int) cd[0];
			D = cd[1];
			vendors = new long[C][2];
			for(int c=0;c<C;c++)
				vendors[c] = TT.longArray(in.readLine(), " ");
			
			
			String output = "Case #"+caseN + ": "+getTime();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	double getTime(){
		double t1 = 0.0;
		double t2 = 1e6*D+1;
		while(!spread(t2))
			t2 = t2*1.1;
		double err = 1e-8;
		while(t2 - t1 > err*t2){
			double t = 1.0*(t1+t2)/2;
			if(spread(t)){
				t2 = t;
			}else{
				t1 = t;
			}
			/*
			if(spread(t1)&&spread(t2)){
				TT.println("both OK");
				return t;
			}
			if(!spread(t1)&&!spread(t2)){
				TT.println("both not OK");
				return t;
			}*/
			//TT.println("t1,t2,t="+t1+","+t2+","+t);
		}
		return t1;
	}
	boolean spread(double time){
		double left = Long.MIN_VALUE;
		int i = 0;
		for(i=0;i<C;i++){
			double li = D*(vendors[i][1]-1);
			if(2*time < li)
				return false;
			left = Math.max(left, vendors[i][0]-time);
			left = left + li + D;
			if(left - D > vendors[i][0]+time)
				return false;
		}
		return true;
	}
}
