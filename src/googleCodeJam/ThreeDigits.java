package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ThreeDigits {
	public static void main(String[] arg)throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		ThreeDigits a = new ThreeDigits();
		a.outputSolution(inputFile,outputFile);
		double b = 3 + Math.sqrt(5);
		
		System.out.println(Math.pow(b, 4));
	}
	public void outputSolution(String input, String output) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(input));
		PrintWriter out = new PrintWriter(output);
		int totalCasesNumber = Integer.parseInt(in.readLine());//first line
		int[] listn = new int[totalCasesNumber];
		for(int caseIndex=1;caseIndex<=totalCasesNumber;caseIndex++){
			int n = Integer.parseInt(in.readLine());
			listn[caseIndex-1] = n;
		}
		int[] solution = getSolution(listn);
		for(int i=0;i<solution.length;i++){
			System.out.println("Case #" + (i+1)+": " + transferInt(solution[i]));
			out.println("Case #" + (i+1)+": " + transferInt(solution[i]));
		}
		//System.out.println("Case #" + caseIndex+": " + transferInt(solution));
		//out.println("Case #" + caseIndex+": " + transferInt(solution));
		out.close();
		in.close();
	}
	public int[] getSolution(int[] listn){
		int L = listn.length;
		int[] solution = new int[L];
		int maxN = listn[0];
		int minN = listn[0];
		int nextIndex = 0;
		for(int i=0;i<listn.length;i++){
			if(listn[i]>maxN)
				maxN = listn[i];
			if(listn[i] < minN){
				minN = listn[i];
				nextIndex = i;
			}
		}
		int nextN = minN;
    	//System.out.println("nextIndex="+nextIndex+",nextN=" + nextN);
		//System.out.println(maxN + "," + minN);

		
		int an_1 = 1;
		int bn_1 = 0;
		int an = 0;
		int bn = 0;
		for(int i=1;i<=maxN;i++){
			an = 3*an_1 + 5*bn_1;
			bn = an_1 + 3*bn_1;
			an = an % 1000;
			bn = bn % 1000;
			an_1 = an;
			bn_1 = bn;
			//System.out.println("a"+i+"="+an);
		    if(i==nextN){
		    	System.out.println("1:nextIndex="+nextIndex+",nextN=" + nextN);
		    	solution[nextIndex] = (an * 2 - 1) % 1000;
		    	int nnextI = nextIndex;
		        nextN = maxN;
		    	for(int j=0;j<L;j++){
		    		if(j != nextIndex && listn[j] >= listn[nextIndex] && listn[j] <= nextN){
		    			nnextI = j;
		    			nextN = listn[j];
		    		}
		    	}
		    	nextIndex = nnextI;
		    	nextN = listn[nextIndex];
		    	System.out.println("2:nextIndex="+nextIndex+",nextN=" + nextN);
		    }
		}
		return solution;
	}
	public String transferInt(int n){
		if(n<=10)
			return "00"+n;
		if(n<=100)
			return "0" +n;
		return ""+n;
	}

}

