package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Int;
import tools.TT;

public class Harmony {
	int N;
	Int L,H;
	Int[] f;
	public static void main(String[] args) throws IOException {
		new Harmony().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] line = TT.longArray(in.readLine()," ");
			N = (int)line[0];
			L = new Int(line[1]);
			H = new Int(line[2]);
			long[] fr = TT.longArray(in.readLine()," ");
			String s = "";
			if(L.longValue()==1)
				s = "1";
			else{
				f = new Int[N];
				for(int i=0;i<N;i++)
					f[i] = new Int(fr[i]);
				Int g = f[0];
				for(int n=1;n<N;n++)
					g = g.gcd(f[n]);
				s = getRe(g);
			}
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getRe(Int g){
		if(g.compareTo(L)<0) return "NO";
		Int max = new Int(100000000);
		Int k = g.divide(L);
		if(k.compareTo(max) <=0){
			for(Int i=new Int(k);i.compareTo(new Int(1))>=0;i=i.minus(1)){
				if(g.mod(i).longValue()==0){
					Int v = g.divide(i);
					if(v.compareTo(H)<=0)
						return v.toString();
					else
						return "NO";
				}
			}
		}else{
			for(Int i=new Int(L);i.compareTo(H)<=0&&i.multiply(i).compareTo(g)<=0;i=i.add(1)){
				if(g.mod(i).longValue()==0)
					return i+"";
			}
		}
		return "NO";
	}
}
