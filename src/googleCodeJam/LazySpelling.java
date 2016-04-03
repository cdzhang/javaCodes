package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class LazySpelling {
	long mod = 1000000007 ;
	public static void main(String[] args) throws IOException {
		LazySpelling t = new LazySpelling();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String s = in.readLine();
			String output = "Case #"+caseN + ": " + getNumber(s);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long getNumber(String s){
		int L = s.length();
		if(L==1) 
			return 1;
		long n = 1;
		n *= count(s.charAt(0),s.charAt(1));
		for(int i=1;i<L-1;i++){
			n *= count(s.charAt(i-1),s.charAt(i),s.charAt(i+1));
			n = n % mod;
		}
		n *= count(s.charAt(L-2),s.charAt(L-1));
		n = n % mod;
		return n;
	}
	int count(char c1,char c2){
		if(c1==c2)
			return 1;
		return 2;
	}
	int count(char c1,char c2,char c3){
		if(c1==c2 || c1==c3)
			return count(c2,c3);
		else 
			return 1 + count(c2,c3);
	}

}
