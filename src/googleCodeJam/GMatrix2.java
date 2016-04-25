package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import tools.TT;

public class GMatrix2 {
	long[][] matrix;
	long[][] maxi;
	int N,K,C,X;
	public static void main(String[] args) throws IOException{
		new GMatrix2().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] NKCX = TT.intArray(in.readLine()," ");
			N = NKCX[0];
			K = NKCX[1];
			C = NKCX[2];
			X = NKCX[3];
			matrix = new long[N][N];
			maxi = new long[N][N-K+1];
			int[] A = TT.intArray(in.readLine(), " ");
			int[] B = TT.intArray(in.readLine(), " ");
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					long x = (A[i]*(i+1)%X+B[j]*(j+1)%X+C)%X;
					matrix[i][j] = x;
				}
			}
			//TT.print(matrix);
			//TT.println("______________________");
			String output = "Case #"+caseN + ": "+getSum();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long getSum(){
		long res = 0;
		LinkedList<Integer> qi  = new LinkedList<Integer>();
		for(int i=0;i<N;i++){
			qi.clear();
			for(int j=0;j<N;j++){
				long mij = matrix[i][j];
				while(!qi.isEmpty() && qi.getFirst() <= j-K)
					qi.removeFirst();
				while(!qi.isEmpty() && matrix[i][qi.getLast()] <= mij)
					qi.removeLast();
				qi.add(j);
				if(j>=K-1)
					maxi[i][j-K+1] = matrix[i][qi.getFirst()];
			}
		}
		//TT.print(maxi);
		for(int j=0;j<N-K+1;j++){
			qi.clear();
			for(int i=0;i<N;i++){
				long xij = maxi[i][j];
				while(!qi.isEmpty() && qi.getFirst() <= i-K)
					qi.removeFirst();
				while(!qi.isEmpty() && maxi[qi.getLast()][j] <= xij)
					qi.removeLast();
				qi.add(i);
				if(i>=K-1)
					res += maxi[qi.getFirst()][j];
			}
		}
		return res;
	}
}