package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.Tools;


public class Repeater {
	int N;
	LinkedList<LinkedList<Char>> charss = new LinkedList<LinkedList<Char>>();
	String[] ss;
	public static void main(String[] args) throws IOException {
		Repeater t = new Repeater();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			charss.clear();
			N = Integer.parseInt(in.readLine());
			ss = new String[N];
			for(int i=0;i<N;i++){
				ss[i] = in.readLine();
				LinkedList<Char> chs = parseChar(ss[i]);

				if(caseN==24){
					Tools.println(ss[i]);
						for(Char ch:chs){
						Tools.print(ch.c+","+ch.n+" ");
					}
					Tools.println();
				}
				charss.add(chs);
			}

			String output = "Case #"+caseN + ": "+result();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String result(){
		if(!can())
			return "Fegla Won";
		return countOperation()+"";
	}
	boolean can(){
		LinkedList<Char> cc0 = charss.get(0);
		int L0 = charss.get(0).size();
		for(int i=1;i<N;i++){
			LinkedList<Char> cci = charss.get(i);
			if(cci.size()!=L0)
				return false;
			for(int l=0;l<L0;l++){
				if(cci.get(l).c!=cc0.get(l).c)
					return false;
			}
		}
		return true;
	}
	int countOperation(){
		int count = 0;
		LinkedList<Char> cc0 = charss.get(0);
		int L0 = cc0.size();
		for(int l=0;l<L0;l++){
			LinkedList<Integer> nl = new LinkedList<Integer>();
			for(int i=0;i<N;i++){
				nl.add(charss.get(i).get(l).n);
			}
			Collections.sort(nl);
			int nk = nl.get((N-1)/2);
			for(int n:nl){
				count += Math.abs(n-nk);
			}
		}
		return count;
	}
	LinkedList<Char> parseChar(String s){
		LinkedList<Char> chs = new LinkedList<Char>();
		int L = s.length();
		char c = s.charAt(0);
		int count = 0;
		for(int i=0;i<L;i++){
			char ci = s.charAt(i);
			if(ci!=c){
				chs.add(new Char(c,count));
				c = ci;
				count = 0;
			}
			count++;
			if(i==L-1){
				chs.add(new Char(c,count));
			}
		}
		return chs;
	}
}
class Char{
	char c;
	int n;
	Char(char c, int n){
		this.c = c;
		this.n = n;
	}
}