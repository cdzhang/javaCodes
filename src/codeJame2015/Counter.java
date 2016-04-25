package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import tools.TT;

public class Counter {
	Set<Long> passed = new HashSet<Long>();
	Set<Long> current = new HashSet<Long>();
	Set<Long> next = new HashSet<Long>();
	long N;
	public static void main(String[] args) throws IOException {
		Counter t = new Counter();
		//t.solve();
		t.test();
	}
	void test(){

		N = 1000;
		TT.println(minCount1());
		clear();
		N = 1001;
		TT.println(minCount1());
		clear();
		N = 1007;
		TT.println(minCount1());
		clear();
		N = 1010;
		TT.println(minCount1());
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			N = Long.parseLong(in.readLine());
			passed.clear();
			current.clear();
			next.clear();
			String output = "Case #"+caseN + ": " + minCount1();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void clear(){
		passed.clear();
		current.clear();
		next.clear();
	}
	public long reverse(long n){
		String s = n + "";
		long r = 0;
		long power = 1;
		for(int i=0;i<s.length();i++){
			r += Integer.parseInt(s.charAt(i)+"")*power;
			power = power * 10;
		}
		return r;
	}
	long minCount(long n){
		if(n<=20)
			return n;
		TT.print(n+",");
		long nr = reverse(n);
		long n1 = n - 1;
		if(nr >= n)
			return minCount(n1)+1;
		else
			return Math.min(minCount(n1), minCount(nr))+1;
	}
	long minCount1(){
		long nf = N;
		if(nf<=11)
			return nf;
		for(long i=1;i<=11;i++)
			passed.add(i);
		long count = 11;
		current.add(12L);
		while(true){
			count++;
			for(long n:current){
				long nr = reverse(n);
				long n1 = n + 1;
				if(nr == nf)
					return count+1;
				if(n1 == nf)
					return count+1;
				passed.add(n);
				if(!passed.contains(nr)){
					next.add(nr);
				}
				if(!passed.contains(n1)){
					next.add(n1);
				}
			}
			current = next;
			next = new HashSet<Long>();
		}
	}
}
