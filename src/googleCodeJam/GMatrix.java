package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class GMatrix {
	LinkedList<E> matrix = new LinkedList<E>();
	int N,K,X,C;
	public static void main(String[] args) throws IOException {
		GMatrix t = new GMatrix();
		t.solve();
		//System.out.println(Integer.MAX_VALUE);
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			matrix.clear();
			int[] NKCX = Tools.intArray(in.readLine()," ");
			N = NKCX[0];
			K = NKCX[1];
			C = NKCX[2];
			X = NKCX[3];
			int[] A = Tools.intArray(in.readLine(), " ");
			int[] B = Tools.intArray(in.readLine(), " ");
			for(int i=1;i<=N;i++){
				for(int j=1;j<=N;j++){
					long x = (A[i-1]*i%X+B[j-1]*j%X+C)%X;
					matrix.add(new E(x,i,j));
				}
			}
			Collections.sort(matrix);
			String output = "Case #"+caseN + ": " + getSum();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long getSum(){
		int NN = N*N;
		long sum = 0;
		long countM = 0;
		int totalM = (N-K+1)*(N-K+1);
		for(int n=NN-1;n>=0;n--){
			E e1 = matrix.get(n);
			int imin = Math.max(1, e1.i-K+1);
			int imax = Math.min(e1.i,N-K+1);
			int jmin = Math.max(1,e1.j-K+1);
			int jmax = Math.min(e1.j, N-K+1);
			for(int i=imin;i<=imax;i++){
				for(int j=jmin;j<=jmax;j++){
					if(!hasLarger(i,j,n)){
						sum += e1.x;
						countM++;
						if(countM == totalM)
							return sum;
					}
				}
			}
		}
		return 0;
	}
	boolean hasLarger(int mi,int mj,int n){
		int NN = N*N;
		if(n==NN-1) return false;
		for(int nn=NN-1;nn>n;nn--){
			E Enn = matrix.get(nn);
			int in = Enn.i;
			int jn = Enn.j;
			if(in>=mi && in <=mi+K-1 && jn>=mj && jn<=mj+K-1){
				return true;
			}
		}
		return false;
	}
}
class E implements Comparable<E>{//element in matrix
	long x;
	int i;
	int j;
	E(long x1, int i1, int j1){
		x = x1;
		i = i1;
		j = j1;
	}
	public int compareTo(E e) {
		if(this.x > e.x)
			return 1;
		if(this.x < e.x)
			return -1;
		return 0;
	}
}