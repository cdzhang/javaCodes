package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import tools.Tools;

public class ReverseWords{
	public static void main(String[] args) throws IOException{
		ReverseWords rw = new ReverseWords();
        String input = Tools.chooseFile();
        String output = Tools.getOutputName(input);
        rw.reverseWords(input,output);
	}
    public void reverseWords(String inputFile, String outputFile) throws IOException{
    	BufferedReader in = new BufferedReader(new FileReader(inputFile));
    	PrintWriter out = new PrintWriter(outputFile);
    	//int N = Integer.parseInt(in.readLine());//read first line
    	in.readLine();//read first line
    	int i = 1;
    	while(in.ready()){
    		String aLine = in.readLine();
    		//System.out.print(aLine + "\n");
    		String toPrint = "Case #" + i + ": " + reverseWordsLine(aLine) +"\n";
    	    System.out.print(toPrint);
    	    out.print(toPrint);
    	    i ++;
    	}
    	in.close();
    	out.close();
    }
    private String reverseWordsLine(String line){
        List<String> s = new ArrayList<String>();
        int L = line.length();
        int wordStart = 0;
        int wordEnd = 0;
        for(int i=0;i<L;i++){//store all the words in s
        	char c = line.charAt(i);
        	if(c == ' '){
        		//wordEnd = i-1;
        		wordEnd = i;
        		s.add(line.substring(wordStart, wordEnd));
        		wordStart = i + 1;
        		i++;
        	}
        }
        if(wordEnd < L - 1){
        	s.add(line.substring(wordStart));
        }
        String rs = "";
        int Ls = s.size();
        for(int j = Ls - 1;j > 0;j--){
        	rs = rs + s.get(j) + " ";
        }
        rs = rs + s.get(0);
    	return rs;
    }
}
