package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class GoogolString {
	public static void main(String[] args) throws IOException {
		GoogolString gs = new GoogolString();
		gs.solve();
	}
	public void test(){
		for(int i=1;i<=15;i++){
			System.out.print(getDigit(i));
		}
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			String aline = in.readLine();
            int digit = getDigit(Long.parseLong(aline));
			String output = "Case #" + caseN + ": " + digit;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int getDigit(long k){
		if(k==1){
			return 0;
		}else if(k==2){
			return 0;
		}else if(k==3){
			return 1;
		}else{
			long power = 1l;
			for(;power*2<=k;power=power*2);
			if(power == k){
				return 0;
			}else{
				return 1 - getDigit(2*power-k);
			}
		}
	}

}
