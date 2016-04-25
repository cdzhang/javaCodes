package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class Consonants {
	public static void main(String[] args) throws IOException {
		Consonants t = new Consonants();
		String s = "boqaiiunlhwageyoeifailecyqpeulijmlrgumerakiiagoexvupairoueuaeopwiezeanhiwvilakhoazhbbawcwlkduuu";
		TT.println(s.length());
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String[] line = in.readLine().split(" ");
			String name = line[0];
			int n = Integer.parseInt(line[1]);
			String output = "Case #"+caseN + ": "+getNValue(name,n);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long getNValue(String name, int n){
		int left = 0;
		int L = name.length();
		LinkedList<int[]> cSpan = new LinkedList<int[]>();
		while(left < L&&isVowel(name.charAt(left))){
			left++;
		}
		int right = left;
		if(right==L-1 && right - left+1>=n){
			cSpan.add(new int[]{left,right});
		}
		right++;
		while(right < L){
			char c = name.charAt(right);
			if(isVowel(c)){
				if(right - left >=n){
					cSpan.add(new int[]{left,right-1});
				}
				left = right+1;
				while(left < L && isVowel(name.charAt(left))){
					left++;
				}
				right = left+1;
			}else{
				if(right==L-1 && right - left+1>=n){
					cSpan.add(new int[]{left,right});
				}
				right++;
			}
		}
		int sz = cSpan.size();
		long count = 0L;
		int lastEnd = 0;
		for(int i=0;i<sz;i++){
			int[] span = cSpan.get(i);
			int lJ = span[0];
			int rJ = span[1];
			count += (long)(lJ - lastEnd+1)* (long)(L-lJ-n+1);
			for(int k=lJ+1;k<=rJ-n+1;k++){
				count += L-k-n+1;
			}
			lastEnd = rJ-n+2;
		}
		return count;
	}
	boolean isVowel(char c){
		if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u')
			return true;
		return false;
	}
}
