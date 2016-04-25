package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Monkey {
	int K,L,S;
	char[] kb,word;
	int[] freq;
	boolean is0;
	public static void main(String[] args) throws IOException {
		Monkey t = new Monkey();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] line = TT.intArray(in.readLine(), " ");
			K = line[0];
			L = line[1];
			S = line[2];
			is0 = false;
			freq = new int[L];
			kb = in.readLine().toCharArray();
			word = in.readLine().toCharArray();
			for(int i=0;i<L;i++){
				int fre = 0;
				char c = word[i];
				for(int j=0;j<K;j++){
					if(c==kb[j])
						fre++;
				}
				if(fre==0){
					is0 = true;
					break;
				}
				freq[i] = fre;
			}
		//s	Tools.print(freq);
			String output = "Case #"+caseN + ": "+expectation();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	
	double expectation(){
		int tb = bananas();
		if(tb==0)
			return 0;
		double pl = 1.0;
		for(int fre:freq){
			pl=pl*fre/K;
		}

		//Tools.println("tb="+tb+",pl="+pl);
		return tb-(S-L+1)*pl;
	}
	int bananas(){
		int tr = tailRepeat(word);
		if(L>S || is0){
			return 0;
		}
		return 1 + (S-L)/(L-tr);
	}
	
	int tailRepeat(char[] aword){
		if(aword.length==1) return 0;
		for(int i=1;i<aword.length;i++){
			for(int j=0;j<aword.length-i;j++){
				if(aword[j]!=aword[i+j]){
					break;
				}
				if(i+j==aword.length-1)
					return aword.length-i;
			}
		}
		return 0;
	}
}
