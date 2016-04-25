package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class SumSum {
	int N, Q;
	int[] L,R,array1;
	public static void main(String[] args) throws IOException {
		SumSum t = new SumSum();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] NQ = TT.intArray(in.readLine(), " ");
			N = NQ[0];
			Q = NQ[1];
			array1 = TT.intArray(in.readLine()," ");
			L = new int[Q];
			R = new int[Q];
			for(int q=0;q<Q;q++){
				int[] lr = TT.intArray(in.readLine(), " ");
				L[q] = lr[0];
				R[q] = lr[1];
			}
			String output = "Case #"+caseN + ": ";
			long[] la = getOutput();
			for(int q=0;q<Q;q++){
				output = output + "\n" + la[q];
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long[] getOutput(){
		long[] output = new long[Q];
		LinkedList<Integer> array = new LinkedList<Integer>();
		for(int i=0;i<N;i++){
			for(int j=i;j<N;j++){
				int sij = sum(i,j);
				array.add(sij);
			}
		}
		Collections.sort(array);
		int NN = array.size();
		TT.println(NN);
		long[] array2 = new long[NN];
		long sum = 0;
		for(int i=0;i<NN;i++){
			sum += array.get(i);
			array2[i] = sum;
		}
		for(int q=0;q<Q;q++){
			int l = L[q];
			int r = R[q];
			output[q] = array2[r-1]-array2[l-1]+array.get(l-1);
		}
		return output;
	}
	int sum(int i,int j){
		int sum = 0;
		for(int k=i;k<=j;k++){
			sum += array1[k];
		}
		return sum;
	}

}
