package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoadTest {
	public static int count = 0;
	public static void main(String[] args) throws IOException{
		LoadTest L = new LoadTest();
        L.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			String[] s = in.readLine().split(" ");
			int L = Integer.parseInt(s[0]);
			int P = Integer.parseInt(s[1]);
			int C = Integer.parseInt(s[2]);
			String output = "Case #" + caseN + ": " + calculateTest(L,P,C);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	
	public int calculateTest(int L, int P, int C){
	    if((P*1.0/L) <= C){
	    	return 0;
	    }else{
	    	int P1 = (int) Math.ceil(Math.sqrt(P*1.0/L)*L);
	    	return 1 + calculateTest(L,P1,C);
	    }
	}

}
