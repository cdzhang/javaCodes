package codejam2016;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tools.TT;


public class Tech3 {
	Map<String,V> f1 = new HashMap<String,V>();
	Map<String,V> f2 = new HashMap<String,V>();
	int N;
	E[] es;
	Set<V> set1 = new HashSet<V>();
	Set<V> set2 = new HashSet<V>();
	public static void main(String[] args) throws IOException {
		new Tech3().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			f1.clear();
			f2.clear();
			N = Integer.parseInt(in.readLine());
			es = new E[N];
			set1.clear();
			set2.clear();
			for(int n=0;n<N;n++){
				String[] s = in.readLine().split(" ");
				V v1 = f1.get(s[0]);
				V v2 = f2.get(s[1]);
				if(v1==null){
					v1 = new V(s[0]);
					f1.put(s[0], v1);
				}
				if(v2==null){
					v2 = new V(s[1]);
					f2.put(s[1], v2);
				}
				v1.cn.add(v2);
				v2.cn.add(v1);
				es[n] = new E(v1,v2);
				v1.en.add(n);
				v2.en.add(n);
			}
			String output = "Case #"+caseN + ": "+getCount();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int getCount(){
		int cut = 0;
		for(String s:f1.keySet()){
			V v1 = f1.get(s);
			if(v1.cn.size()==1)
				set1.add(v1);
			else
				set2.add(v1);
		}
		for(String s:f2.keySet()){
			V v2 = f2.get(s);
			if(v2.cn.size()==1)
				set1.add(v2);
			else
				set2.add(v2);
		}
		while(set2.size()>0){
			V v3 = null;
			V v2 = null;
			int j = 0;
			for(V v:set2){
				v2 = v;
				for(int i=0;i<v2.cn.size();i++){
					V v1 = v2.cn.get(i);
					if(set1.contains(v1))
						continue;
					else{
						j=i;
						v3 = v2;
						break;
					}
				}
			}

			if(v3==null)
				set2.remove(v2);
			else{
				int n = v2.en.get(j);
				V v1 = v2.cn.get(j);
				v2.cn.remove(v1);
				v1.cn.remove(v2);
				v2.en.remove(j);
				v1.en.remove(new Integer(n));
				if(v1.cn.size()==1){
					set2.remove(v1);
					set1.add(v1);
				}
				if(v2.cn.size()==1){
					set2.remove(v2);
					set1.add(v1);
				}
			}
		}
		return cut;
	}
}
