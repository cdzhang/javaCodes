package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.TT;

public class AlienLanguage {
	int L = 0;
	int D = 0;
	int N = 0;
	LinkedList<String> dict = new LinkedList<String>();
	public static void main(String[] arg) throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		AlienLanguage al = new AlienLanguage();
		al.solve(inputFile, outputFile);
	}
	
	public void solve(String inputFile,String outputFile) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		String LDN = in.readLine();
		String[] ldn = LDN.split(" ");
		L = Integer.parseInt(ldn[0]);
		D = Integer.parseInt(ldn[1]);
		N = Integer.parseInt(ldn[2]);
		//read dictionary
		for(int i=1;i<=D;i++){
			String s = in.readLine();
			dict.add(s);
		}
		System.out.println("there are "+ dict.size() + " words");
		//process words
		for(int i=1;i<=N;i++){
			String s = in.readLine();//a line to be processed
			int count = countPossibles(s);
			System.out.println("Case #"+i +": "+count);
			out.println("Case #"+i +": "+count);
		}
		in.close();
		out.close();
	}
	
	public LinkedList<String> processInput(String s){
		LinkedList<String> possibles = new LinkedList<String>();
	      boolean inPa = false;
	    int L = s.length();
	    String as = "";
	    for(int i=0;i<L;i++){
	    	char c = s.charAt(i);
	    	if(c == '('){//if this is the start of a 
	    		inPa = true;
	    		continue;
	    	}else if (inPa){
	    		if(c == ')'){
	    			possibles.add(as);
	    			as = "";
	    			inPa = false;
	    		}else{
	    			as = as + c;
	    		}
	    	}else{
	    		possibles.add(c+"");
	    	}
	    }
	    return possibles;
	}
	
	public int countPossibles(String s){
		int[] wordIndex = new int[D];
		for(int i=0;i<D;i++){
			wordIndex[i] = 1;
		}
		int count = 0;
		LinkedList<String> ls = processInput(s);
		for(int i=0;i<L;i++){
			String si = ls.get(i);//possible chars at the ith position
			for(int j=0;j<D;j++){
				if(wordIndex[j] == 0) continue;
				char cj = dict.get(j).charAt(i);//ith character of word j
				if(!isIn(cj,si)){
					wordIndex[j] = 0;
				}
			}
		}
		for(int j=0;j<D;j++){
			count+=wordIndex[j];
		}

	    return count;
	}
	public boolean isWord(String s){
		for(String word:dict){
			if (s.equals(word)){
				return true;
			}
		}
		return false;
	}
	public boolean isIn(char c, String s){
		int N = s.length();
		for(int i=0;i<N;i++){
			if(c==s.charAt(i))
				return true;
		}
		return false;
	}
	private String getWordUpdateIndex(LinkedList<String> possibles,int[] index){
		int L = index.length;
		if(index[0] >= possibles.get(0).length())
			return null;
		String s = "";
		for(int i=0;i<L;i++){
			s = s + possibles.get(i).charAt(index[i]);
		}
		index[L-1] = index[L-1] + 1;
		for(int i=L-1; i>0; i--){
			if(index[i] >= possibles.get(i).length()){
				if(i==0)
					return null;//this is the last word
				index[i] = 0;
				index[i-1] = index[i-1] + 1;
			}
		}
	    return s;
	}
}
