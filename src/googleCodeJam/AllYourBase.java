package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Tools;

public class AllYourBase {
	public static void main(String[] args) throws IOException {
		AllYourBase ayb = new AllYourBase();
		ayb.solve();
		//ayb.test();

	}
	public void test(){
		//System.out.println(crack("11001001"));
		//System.out.println(crack("cats"));
		//System.out.println(crack("zig"));
		System.out.println(crack("abcdefghijklmno"));
		//System.out.println((long)Math.pow(2,61));
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			String aline = in.readLine();
			long l = crack(aline);
			String output = "Case #" + caseN + ": " + l;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	private long crack(String s){
	   long l = 0l;
	   int L = s.length();
	   int[] number = new int[L];
	   int base = 0;
	   String ch = "";
	   for(int i=0;i<L;i++){
		   char c = s.charAt(i);
		   if(ch.indexOf(c) >= 0){
			   continue;
		   }else{
			   ch += c;
			   base++;
		   }
	   }
	   if(base < 2)
		   base = 2;
	   //System.out.println("base="+base);
	   char[] dic = new char[base];
	   for(int i=0;i<dic.length;i++)
		   dic[i] = '-';
	   int newIndex = 0;//cannot be 1
	   for(int i=0;i<L;i++){
		  // printArray(dic);
		   char c = s.charAt(i);
		   if(i==0){//the first char in s represents 1
			   dic[1] = c;
			   number[i] = 1;
		   }else{
			   //find if c is already in dic
			   int cIndex = -1;
			   for(int j=0;j<base;j++){
				   if(dic[j] == c){
					   cIndex = j;
					   break;
				   }
			   }
			   //System.out.println("c="+c+",cIndex="+cIndex);
			   if(cIndex >= 0){//c already in dic
				   number[i] = cIndex;
			   }else{//c not in dic
				  // System.out.println("newIndex="+newIndex);
				   dic[newIndex] = c;
				   number[i] = newIndex;
				   newIndex++;
				   if(newIndex == 1 && newIndex < base)
					   newIndex++;
			   }
		   }
	   }
	 //  for(int i=0;i<L;i++){
	//	   l = l +  (long)((long)number[i] * (long) Math.pow(base, L-1-i));
	  // }
	   l = 0L;
	   long power = 1;
	   for(int i=L-1;i>=0;i--){
		   l = l + number[i] * power;
		   power *= base;
	   }
	  // printArray(number);
	   //System.out.println(base);
	   return l;
	}
	void printArray(char[] dic){
		for(Object o:dic){
			System.out.print(o+",");
		}
		System.out.println();
	}
	void printArray(int[] dic){
		for(Object o:dic){
			System.out.print(o+",");
		}
		System.out.println();
	}
}
