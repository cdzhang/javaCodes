package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tools.TT;

public class LogSet {
	Map<Long,Long> map = new HashMap<Long,Long>();
	long[] E,F;
	int PI,N;
	public static void main(String[] args) throws IOException {
		LogSet t = new LogSet();
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
			map.clear();
			PI = Integer.parseInt(in.readLine());
			E = TT.longArray(in.readLine(), " ");
			F = TT.longArray(in.readLine(), " ");
			long tN = 0;
			for(long f:F){
				tN+=f;
			}
			N = 1;
			int PN = 2;
			while(PN < tN){
				N++;
				PN*=2;
			}
			int L = E.length;
			for(int l=0;l<L;l++){
				map.put(E[l], F[l]);
			}
			String output = "Case #"+caseN + ":"+getSet();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getSet(){
		LinkedList<Long> set = new LinkedList<Long>();
		Map<Long,Long> tmp_map = new HashMap<Long,Long>();
		tmp_map.putAll(map);
		while(set.size()<N){
			LinkedList<Long> keyList = new LinkedList<Long>();
			keyList.addAll(tmp_map.keySet());
			Collections.sort(keyList);
			int i=0;
			Long X = keyList.get(i);
			Map<Long,Long> halfMap = setRemove(tmp_map,X);
			while(halfMap==null){
				i++;
				X = keyList.get(i);
				halfMap = setRemove(tmp_map,X);
			}
			set.add(X);
			tmp_map = halfMap;
		}
		String s = "";
		for(Long l:set)
			s+= " "+l;
		return s;
	}
	Map<Long,Long> setRemove(Map<Long,Long> P,long X){
		if(P.get(X)==null)
			return null;
		Map<Long,Long> Po = new HashMap<Long,Long>();
		Po.putAll(P);
		Map<Long,Long> P1 = new HashMap<Long,Long>();
		if(X > 0){
			while(Po.size()>0){
			   Long max = maxKey(Po);
			   Long value = Po.get(max);
			   Long key = max - X;
			   Long value1 = Po.get(key);
			   if(value1==null || value1 < value){
				   return null;
			   }
			   P1.put(key, value);
			   Po.remove(max);
			   value1 = value1 - value;
			   if(value1==0)
				   Po.remove(key);
			   else
				   Po.put(key, value1);
			}
			return P1;
		}else if(X<0){
			while(Po.size()>0){
				Long min = minKey(Po);
				Long value = Po.get(min);
				Long key = min - X;
				Long value1 = Po.get(key);
				if(value1==null||value1<value)
					return null;
				P1.put(key, value);
				Po.remove(min);
				value1 = value1 - value;
				if(value1==0)
					Po.remove(key);
				else
					Po.put(key, value1);
			}
			return P1;
		}else{
			for(Long key:Po.keySet()){
				Long value = Po.get(key);
				if(value % 2 !=0 )
					return null;
				P1.put(key, value/2);
			}
			return P1;
		}
	}
	Long maxKey(Map<Long,Long> P){
		if(P.size()==0)
			return null;
		Long max = -100000000000L;
		for(Long key:P.keySet()){
			if(key>max)
				max=key;
		}
		return max;
	}
	Long minKey(Map<Long,Long> P){
		if(P.size()==0)
			return null;
		Long min = 100000000000L;
		for(Long key:P.keySet()){
			if(key<min)
				min=key;
		}
		return min;
	}
}
