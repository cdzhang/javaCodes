package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class MagicTrick {
	
	public static void main(String[] args) throws IOException {
		MagicTrick t = new MagicTrick();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int a1 = Integer.parseInt(in.readLine())-1;
			int[][] ar1 = new int[4][4];
			for(int i=0;i<4;i++){
				ar1[i] = Tools.intArray(in.readLine(), " ");
			}
			int a2 = Integer.parseInt(in.readLine())-1;
			int[][] ar2 = new int[4][4];
			for(int i=0;i<4;i++){
				ar2[i] = Tools.intArray(in.readLine(), " ");
			}
			int[] cards1 = ar1[a1];
			int[] cards2 = ar2[a2];
			int count = 0;
			int number = 0;
			for(int i=0;i<4;i++){
				int c1i = cards1[i];
				for(int j=0;j<4;j++){
					if(c1i==cards2[j]){
						number = c1i;
						count++;
					}
				}
			}
			String s = "";
			if(count == 0)
				s = "Volunteer cheated!";
			else if(count ==1){
				s += number;
			}else
				s = "Bad magician!";
			
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		Tools.println("total execution time is "+timeUsed);
	}

}
