package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Template;
import tools.TT;

public class Osmos {
	int[] motes;
	int N;
	public static void main(String[] args) throws IOException {
		Osmos t = new Osmos();
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
			int[] line = TT.intArray(in.readLine(), " ");
			long size = (long)line[0];
			N = line[1];
			motes = TT.intArray(in.readLine(), " ");
			TT.sort(motes);
			//Tools.println(size+":");
			//Tools.print(motes);
			String output = "Case #"+caseN + ": "+operation(size,0);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int operation(long size,int i){
		if(i==N)
			return 0;
		if(size==1)
			return N-i;
		int mi = motes[i];
		if(size > mi){
			long newSize = size+mi;
			return operation(newSize,i+1);
		}else{
			int o1 = N-i;
			long newSize = size;
			int o2 = 0;
			while(newSize <= mi){
				newSize =  2*newSize - 1;
				o2++;
			}
			newSize += mi;
			o2 = o2 + operation(newSize,i+1);
			return Math.min(o1, o2);
		}
	}
}
