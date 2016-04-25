package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import tools.TT;

public class Trie {
	long mod = 1000000007;
	public static void main(String[] args) throws IOException {
		Trie t = new Trie();
		t.test();
	}
	void test(){
		String[] s = {"AAA","AAB","AB","B"};
		TT.println(countNodes(s));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			
			
			
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	void sort(String[] s){
		int L = s.length;
		for(int i=0;i<L-1;i++){
			for(int j=i;j<L;j++){
				String si = s[i];
				String sj = s[j];
				if(TT.largerThan(si, sj)>0){
					String s1 = si;
					s[i] = s[j];
					s[j] = s1;
				}
			}
		}
	}
	long countNodes(String[] s){
		int L = s.length;
		Set<Character> chars = new HashSet<Character>();
		int index = 0;
		int count = 1;//root
		while(true){
			chars.clear();
			for(int i=0;i<L;i++){
				String si = s[i];
				if(index<si.length()){
					chars.add(si.charAt(index));
				}
			}
			int c = chars.size();
			if(c==0)
				break;
			else{
				count += c;
			}
			index++;
		}
		return count;
	}

}
