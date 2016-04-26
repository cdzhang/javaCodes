package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;

import tools.TT;

public class KillerWord {
	int	N, M;
	LinkedList<group> initialGroups = new LinkedList<group>();
	String[] dict;
	public static void main(String[] args) throws IOException {
		new KillerWord().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] nm = TT.intArray(in.readLine(), " ");
			N = nm[0];
			M = nm[1];
			dict = new String[N];
			initialGroups.clear();
			loop1:for(int n=0;n<N;n++){
				String wordn = in.readLine();
				dict[n] = wordn;
				int L = wordn.length();
				for(int i=0;i<initialGroups.size();i++){
					group g = initialGroups.get(i);
					if(dict[g.words.getFirst()].length() == L){
						g.add(n);
						continue loop1;
					}
				}
				group newG = new group(0,-1);
				newG.add(n);
				initialGroups.add(newG);
			}
			
			String ms = "";
			for(int i=0;i<M;i++){
				ms = ms + " " + getWord(in.readLine());
			}
			String output = "Case #"+caseN + ":" + ms;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getWord(String seq){
		LinkedList<group> gs1 = new LinkedList<group>();
		LinkedList<group> gs2 = new LinkedList<group>();
		for(group gi:initialGroups){
			gs1.add(gi.clone());
		}
		for(int i=0;i<seq.length();i++){
			char ci = seq.charAt(i);
			for(group g1:gs1){
				gs2.addAll(divideGroup(g1,ci));
			}
			gs1 = gs2;
			gs2 = new LinkedList<group>();
			if(gs1.size()==N){
				break;
			}
		}
		LinkedList<Integer> pq = new LinkedList<Integer>();
		int maxErr = -1;
		for(group g:gs1){
			if(g.err > maxErr){
				maxErr = g.err;
			}
		}
		for(group g:gs1){
			if(g.err==maxErr)
				pq.addAll(g.words);
		}
		int minInt = N+1;
		for(int I:pq){
			if(I<minInt)
				minInt = I;
		}
		return dict[minInt];
	}
	LinkedList<group>  divideGroup(group g,char c){
		LinkedList<group> grs = new LinkedList<group>();
		group err = new group(g.err+1,g.i+1);
		loop1:for(int gi:g.words){
			if(dict[gi].indexOf(c)<0)
				err.add(gi);
			else{
				for(int i=0;i<grs.size();i++){
					group gri = grs.get(i);
					int ii = gri.words.getFirst();
					if(match(dict[ii],dict[gi],c)){
						gri.add(gi);
						continue loop1;
					}
				}
				group newG = new group(g.err,g.i+1);
				newG.add(gi);
				grs.add(newG);
			}
		}
		if(grs.size()==0)
			err.err = err.err - 1;
		if(err.words.size()!=0)
			grs.add(err);
		return grs;
	}
	boolean match(String s1,String s2,char c){
		int L = s1.length();
		if(s2.length()!=L)
			return false;
		for(int i=0;i<L;i++){
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if(c1==c && c2!=c)
				return false;
			if(c2==c && c1!=c)
				return false;
		}
		return true;
	}
}
class group{
	int err;
	int i; //1-26
	LinkedList<Integer> words = new LinkedList<Integer>();
	group(int err,int i){
		this.err = err;
		this.i = i;
	}
	group(group g){
		this(g.err,g.i);
		this.words.addAll(g.words);
	}
	void add(int wI){
		words.add(wI);
	}
	protected group clone(){
		group g = new group(err,i);
		g.words.addAll(words);
		return g;
	}
}
