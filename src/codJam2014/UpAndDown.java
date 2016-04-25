package codJam2014;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;


public class UpAndDown {
	public static void main(String[] args) throws IOException {
		UpAndDown t = new UpAndDown();
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
			int N = Integer.parseInt(in.readLine());
			long[] A = TT.longArray(in.readLine(), " ");
			A = getRank(A);
			TT.print(A);
			TT.print(getRank(A));
			String output = "Case #"+caseN + ": "+getCount2(A);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long[] getRank(long[] A){
		long[] B = A.clone();
		TT.sort(B);
		long[] C = new long[A.length];
		for(int i=0;i<A.length;i++){
			long Bi = B[i];
			for(int j=0;j<A.length;j++)
				if(Bi == A[j]){
					C[j] = i+1;
					break;
				}
		}
		return C;
	}
	int getCount(long[] A, int i,int m){
		if(i==A.length-1){
			//Tools.print(A);
			return 0;
		}
		if(m<0){
			if(A[i] < A[i+1]){
				return getCount(A,i+1,m);
			}else{
				int count1 = 0;
				int j = i;
				int k = i;
				for(;j>=0;j--){
					if(A[j]>A[j+1]){
						swap(A,j,j+1);
						count1++;
						k = j;
					}else
						break;
				}
				count1 += getCount(A,i+1,m);
				for(;k<=i;k++){
					swap(A,k,k+1);
				}
				int count2 = getCount(A,i+1,i);
				return Math.min(count1, count2);
			}
		}else{
			if(A[i] > A[i+1])
				return getCount(A,i+1,m);
			else{
				int count = 0;
				int j = i;
				int k = i;
				for(;j>m;j--){
					if(A[j]<A[j+1]){
						swap(A,j,j+1);
						count++;
						k=j;
					}else
						break;
				}
				if(k==m+1 && A[m] < A[m+1])
					m = m+1;
				int count1 = count + getCount(A,i+1,m);
				while(k<=i){
					swap(A,k,k+1);
					k++;
				}
				return count1;
			}
		}
	}
	int getCount1(long[] A, int i, int m){
		if(i==A.length-1)
			return 0;
		if(m<0){
			if(A[i] < A[i+1])
				return getCount1(A,i+1,m);
			else{
				swap(A,i,i+1);
				int j = i-1;
				if(j<0)
					j=0;
				int count1 = 1+getCount1(A,j,m);
				swap(A,i,i+1);
				int count2 = getCount1(A,i+1,i);
				return Math.min(count1, count2);
			}
		}else{
			if(A[i] > A[i+1])
				return getCount1(A,i+1,m);
			else{
				swap(A,i,i+1);
				int j = i-1;
				if(j==m){
					if(A[m] < A[m+1])
						m++;
					j=m+1;
				}
				int count = 1 + getCount1(A,j,m);
				swap(A,i,i+1);
				return count;
			}
		}
	}
	int getCount2(long[] A){// 
		int N = A.length;
		int left = 0;
		int right = N-1;
		int count = 0;
		for(int n=1;n<=N;n++){
			int nI = -1;
			for(int j = left;j<=right;j++){
				if(A[j] == n){
					nI = j;
					break;
				}
			}
			int ld = nI - left;
			int rd = right - nI;
			if(ld<=rd){
				while(nI > left){
					swap(A,nI,nI-1);
					nI--;
					count++;
				}
				left++;
			}else{
				while(nI < right){
					swap(A,nI,nI+1);
					nI++;
					count++;
				}
				right--;
			}
		}
		return count;
	}
	void swap(long[] A, int i, int j){
		long Ai = A[i];
		A[i] = A[j];
		A[j] = Ai;
	}
	
}
