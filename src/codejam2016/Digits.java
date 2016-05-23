package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import tools.TT;

public class Digits {
	Map<Character,Integer> map = new HashMap<Character,Integer>();
	int[] digit;
	public static void main(String[] args) throws IOException {
		new Digits().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			map.clear();
			String s = in.readLine();
			for(int i=0;i<s.length();i++){
				char c = s.charAt(i);
				Integer cI = map.get(c);
				if(cI==null)
					cI = 1;
				else
					cI++;
				map.put(c, cI);
			}
			digit = new int[10];
			String output = "Case #"+caseN + ": "+getNumber();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getNumber(){
		String num = "";
		getN(0,'Z',"ZERO");
		getN(4,'U',"FOUR");
		getN(8,'G',"EIGHT");
		getN(5,'F',"FIVE");
		getN(2,'W',"TWO");
		getN(6,'X',"SIX");
		getN(7,'S',"SEVEN");
		getN(1,'O',"ONE");
		getN(3,'T',"THREE");
		getN(9,'I',"NINE");
		for(int i=0;i<=9;i++){
			for(int j=0;j<digit[i];j++)
				num = num + i;
		}
		return num;
	}
	void getN(int i,char k,String s){
		Integer I = map.get(k);
		if(I!=null && I >0){
			for(int j=0;j<s.length();j++){
				char cj = s.charAt(j);
				Integer J = map.get(cj);
				J = J-I;
				map.put(cj, J);
			}
			digit[i] = I;
		}
	}
	
}
