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

public class Tech2 {
	Map<String,V> f1 = new HashMap<String,V>();
	Map<String,V> f2 = new HashMap<String,V>();
	int N;
	E[] es;
	public static void main(String[] args) throws IOException {
		new Tech2().solve();
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
			String output = "Case #"+caseN + ": "+(N-(selectLeaf()+smallest(0)));
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int selectLeaf(){
		int count = 0;
		for(int n=0;n<N;n++){
			if(es[n].v1.cn.size()==1 || es[n].v2.cn.size()==1){
				es[n].i = 1;
				es[n].v1.i = 1;
				es[n].v2.i = 1;
				count++;
			}
		}
		return count;
	}
	int smallest(int j){
		int count = 0;
		if(j>=N){
			return 0;
		}
		for(int i=j;i<N;i++){
			if(es[i].i==1)
				continue;
			if(es[i].v1.i!=0 && es[i].v2.i!=0){
			}else{
				Set<V> ch = new HashSet<V>();
				if(es[i].v1.i==0)
					ch.add(es[i].v1);
				if(es[i].v2.i==0)
					ch.add(es[i].v2);
				es[i].i = 2;
				for(V v:ch)
					v.i = 2;
				int count1 = 1+smallest(i+1);
				es[i].i = 0;
				for(V v:ch)
					v.i = 0;
				if((es[i].v1.i==0 && es[i].v1.en.getLast()==i) ||
						(es[i].v2.i==0 && es[i].v2.en.getLast()==i)){
					return count1;
				}
				int count2 = smallest(i+1);
				return Math.min(count1, count2);
			}
		}
		return count;
	}
}
