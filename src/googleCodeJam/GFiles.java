package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class GFiles {
	int N;
	long[] span;
	public static void main(String[] args) throws IOException {
		GFiles t = new GFiles();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			N = Integer.parseInt(in.readLine());
			span = new long[]{0,Long.MAX_VALUE};
			int Pi;
			long Ki;
			boolean exist = true;
			for(int n=1;n<=N;n++){
				String[] line = in.readLine().split(" ");
				Pi = Integer.parseInt(line[0]);
				Ki = Long.parseLong(line[1]);
				if(!updateSpan(Pi,Ki)){
					exist = false;
					break;
				}
				
			}
			String result = "";
			if(exist==false){
				result = "-1";
			}else{
				long L = span[0];
				long H = span[1];
				if(L==H)
					result = "" + H;
				else
					result = "-1";
			}
			String output = "Case #"+caseN + ": " +result;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	boolean updateSpan(int Pi,long Ki){
		long L = span[0];
		long H = span[1];
		if(Pi==0)
			return true;
		if(Pi==100 && Ki > 0){
			span = new long[]{Ki,Ki};
			return true;
		}
		if(Pi>0&&Ki==0)
			return false;
		long H1 = (100L*Ki)/Pi;
		long L1 = (100L*Ki)/(Pi+1)+1;
		L1 = Math.max(L,L1);
		H1 = Math.min(H1,H);
		if(L1>H1){
			System.out.println("wrong");
			return false;
		}
		span[0] = L1;
		span[1] = H1;
		return true;
	}
}
