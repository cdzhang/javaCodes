package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class KillerWordSmall {
	int	N, M;
	container[] dict;
	String cWord = "";
	LinkedList<String> dict1 = new LinkedList<String>();
	public static void main(String[] args) throws IOException {
		new KillerWordSmall().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			dict1.clear();
			cWord="";
			int[] nm = TT.intArray(in.readLine(), " ");
			N = nm[0];
			M = nm[1];
			dict = new container[11];
			for(int n=1;n<=10;n++)
				dict[n] = new container();
			for(int n=1;n<=N;n++){
				String wordn = in.readLine();
				dict1.add(wordn);
				int L = wordn.length();
				dict[L].add(wordn);
			}
			String ms = "";
			for(int m=1;m<=M;m++){
				String seq = in.readLine();
				cWord = "";
				getErrCount(seq);
				ms = ms +" "+cWord;
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
	long getErrCount(String seq){
		long count = -1;
		for(String aword:dict1){
			long ec = getErrCount(aword,seq);
			if(count < ec){
				count = ec;
				cWord = aword;
			}
		}
		return count;
	}
	long getErrCount(String word,String seq){
		int L = word.length();
		char[] se = seq.toCharArray();
		int sN = se.length;
		LinkedList<String> pw = new LinkedList<String>();
		pw.addAll(dict[L].words);
		long wCount = 0;
		char[] s = new char[L];
		for(int i=0;i<L;i++)
			s[i] = '_';
		for(int i=0;i<sN;i++){
			char ci = se[i];
			boolean hasCi = false;
			for(String aword:pw){
				if(aword.indexOf(ci) >=0){
					hasCi = true;
					break;
				}
			}
			if(!hasCi) continue;
			if(word.indexOf(ci)<0){
				wCount++;
				LinkedList<String> pw1 = new LinkedList<String>();
				for(String spw:pw){
					if(spw.indexOf(ci)>=0)
						pw1.add(spw);
				}
				pw.removeAll(pw1);
				if(pw.size()==1)
					return wCount;
			}else{
				for(int j=0;j<L;j++){
					if(word.charAt(j) == ci)
						s[j] = ci;
				}
				LinkedList<String> pw1 = new LinkedList<String>();
				for(String spw:pw){
					if(!match(spw,s))
						pw1.add(spw);
				}
				pw.removeAll(pw1);
				if(pw.size()==1)
					return wCount;
			}
		}
		return wCount;
	}
	boolean match(String word,char[] s){
		int L = word.length();
		String ss = "";
		for(char c:s)
			ss += c;
		if(s.length!=L)
			return false;
		for(int i=0;i<L;i++){
			if(s[i]=='_'){
				if(ss.indexOf(word.charAt(i)) >=0)
					return false;
				continue;
			}
				
			if(s[i]!=word.charAt(i))
				return false;
		}
		return true;
	}
}
class container{
	int L;
	LinkedList<String> words = new LinkedList<String>();
	void add(String word){
		words.add(word);
	}
}
