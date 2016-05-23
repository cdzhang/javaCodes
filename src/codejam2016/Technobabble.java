package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tools.TT;
import tools.Union;

public class Technobabble {
	Map<String,V> f1 = new HashMap<String,V>();
	Map<String,V> f2 = new HashMap<String,V>();
	V[][] E;
	int N;
	public static void main(String[] args) throws IOException {
		new Technobabble().solve();
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
			E = new V[N][2];
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
				E[n][0] = v1;
				E[n][1] = v2;
			}
			cutLoop();
			String output = "Case #"+caseN + ": "+(N-countLeaf());
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	void cutLoop(){
		for(int n=0;n<N;n++){
			V v1 = E[n][0];
			V v2 = E[n][1];
			if(v1.same(v2)){
				v1.cn.remove(v2);
				v2.cn.remove(v1);
			}else{
				v1.unite(v2);
			}
		}
	}
	int connect(){
		cutLoop();
		int count = countLeaf();
		if(!f1.isEmpty()){
			V v1 = f1.entrySet().iterator().next().getValue();
			V v2 = v1.cn.get(0);
			remove(f1,v1);
			remove(f2,v2);
			return 1+count+connect();
		}else{
			return count;
		}
	}
	int countLeaf(){
		V v1 = getFirstV1();
		if(v1!=null){
			remove(f1,v1);
			if(v1.cn.size()==1)
				remove(f2,v1.cn.get(0));
			return 1+countLeaf();
		}
		V v2 = getFirstV2();
		if(v2!=null){
			remove(f2,v2);
			if(v2.cn.size()==1)
				remove(f2,v2.cn.get(0));
			return 1+countLeaf();
		}
		return 0;
	}
	V getFirstV1(){
		for(V v1:f1.values()){
			if(v1.cn.size()==0)
				return v1;
			if(v1.cn.size()==1)
				return v1;
		}
		return null;
	}
	V getFirstV2(){
		for(V v2:f2.values()){
			if(v2.cn.size()==0)
				return v2;
			if(v2.cn.size()==1)
				return v2;
		}
		return null;
	}
	int remove(Map<String, V> f,V v){
		int i=0;
		v.choosen = true;
		for(V vc:v.cn){
			vc.cn.remove(v);
			i++;
		}
		f.remove(v.w, v);
		return i;
	}
	int remove(V v){
		int i = 0;
		v.choosen = true;
		for(V vc:v.cn){
			vc.cn.remove(v);
			i++;
		}
		return i;
	}
	
}
class V extends Union{
	int i=0;
	String w = "";
	boolean choosen = false;
	V(){
	}
	V(String w1){
		w = w1;
	}
	LinkedList<V> cn = new LinkedList<V>();
	LinkedList<Integer> en = new LinkedList<Integer>();
	public int hashCode(){
		return w.hashCode();
	}
	public boolean equals(V a){
		return w.equals(a.w);
	}
}
class E {
	int i=0;
	V v1, v2;
	E(V v1,V v2){
		this.v1 = v1;
		this.v2 = v2;
	}
}