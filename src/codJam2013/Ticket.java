package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tools.TT;

public class Ticket {
	long N, M;
	long income;
	long mod = 1000002013;
	Map<Long,Long> ins = new HashMap<Long,Long>();
	Map<Long,Long> ous = new HashMap<Long,Long>();
	LinkedList<station> lin = new LinkedList<station>();
	LinkedList<station> lout = new LinkedList<station>();
	public static void main(String[] args) throws IOException {
		new Ticket().solve();
		//TT.print(Integer.MAX_VALUE);
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			ins.clear();
			ous.clear();
			lin.clear();
			lout.clear();
			income = 0;
			long[] nm = TT.longArray(in.readLine(), " ");
			N = nm[0];
			M = nm[1];
			for(int m=0;m<M;m++){
				long[] line = TT.longArray(in.readLine(), " ");
				long instation = line[0];
				long outstation = line[1];
				long p = line[2];
				long k = outstation - instation;
				income += ((k*N - (k-1)*k/2)%mod)*p % mod;
				income = income % mod;
				Long pi = ins.get(instation);
				pi = (pi==null?p:pi+p);
				ins.put(instation,pi);
				Long po = ous.get(outstation);
				po = (po==null?p:po+p);
				ous.put(outstation, po);
			}
			for(long key:ins.keySet()){
				long value = ins.get(key);
				lin.add(new station(key,value));
			}
			for(long key:ous.keySet()){
				long value = ous.get(key);
				lout.add(new station(key,value));
			}
			Collections.sort(lin);
			Collections.sort(lout);
			String output = "Case #"+caseN + ": "+getLoss();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long getLoss(){
		long min = 0;
		while(lout.size() > 0){
			station fe = lout.getFirst();
			long fo = fe.i;
			long pe = fe.p;
			int lastI = lin.size()-1;
			for(int i=0;i<lin.size();i++){
				if(lin.get(i).i > fe.i){
					lastI = i-1;
					break;
				}
			}
			while(pe > 0){
				station lastIn = lin.get(lastI);
				long k = fo - lastIn.i;
				long price = (k*N - (k-1)*k/2)%mod;
				long pi = lastIn.p;
				if(pi>pe){
					min += pe*price%mod;
					min = min %mod;
					lastIn.p = pi - pe;
					pe = 0;
					break;
				}else if(pi == pe){
					min += pe*price%mod;
					min = min % mod;
					pe = 0;
					lin.remove(lastI);
					break;
				}else{
					min += pi*price % mod;
					min = min %mod;
					pe = pe - pi;
					lin.remove(lastI);
					lastI--;
				}
			}
			lout.removeFirst();
		}
		long res = income - min;
		if(res < 0)
			res = res + mod;
		return res;
	}
}
class station implements Comparable<station>{
	long i;
	long p;
	station(long i1, long p1){
		i = i1;
		p = p1;
	}
	public int compareTo(station s) {
		long si = s.i;
		if(i > si)
			return 1;
		else if(i<si)
			return -1;
		else
			return 0;
	}
	
}
