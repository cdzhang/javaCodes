package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tools.TT;

public class FairAndSquare {
	LinkedList<Long> fs = new LinkedList<Long>();
	public static void main(String[] args) throws IOException {
		FairAndSquare t = new FairAndSquare();
		t.test();
	}
	FairAndSquare(){
		for(long i=1;i<10000000;i++){
			long sq = i*i;
			if(ispalindrome(i) && ispalindrome(sq))
				fs.add(sq);
		}
	}
	void test(){
		BigInteger a = new BigInteger("200000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000002");
		TT.println(a.multiply(a)+"");
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] ab = TT.longArray(in.readLine(), " ");
			long A = ab[0];
			long B = ab[1];
			String output = "Case #"+caseN + ": "+countSmall(A,B);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int countSmall(long A, long B){
		int count = 0;
		for(int i=0;i<fs.size();i++){
			long fi = fs.get(i);
			if(fi>=A){
				if(fi<=B)
					count++;
				else
					break;
			}
		}
		
		return count;
	}
	boolean ispalindrome(long a){
		String s = a+"";
		int N = s.length();
		int left = 0;
		int right = N-1;
		while(left < right){
			if(s.charAt(left)!=s.charAt(right))
				return false;
			left++;
			right--;
		}
		return true;
	}
}
