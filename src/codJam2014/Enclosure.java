package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Enclosure {
	public static void main(String[] args) throws IOException {
		Enclosure t = new Enclosure();
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
			int[] nmk = TT.intArray(in.readLine(), " ");
			int N = nmk[0];
			int M = nmk[1];
			int K = nmk[2];
			TT.print(nmk);
			String output = "Case #"+caseN + ": "+getNumber(N,M,K);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int getNumber(int N, int M, int K){//assumes N <= M
		if(N > M)
			return getNumber(M,N,K);
		if(N<=2 || K<=4){
			return K;
		}
		int maxn = (N%2==1)?N:N-1;
		int L1 = (maxn*maxn+1)/2;
		int L2 = L1 + (M-maxn)*maxn;
		if(N==maxn+1){
			L1 = L1 + maxn;
			L2 = L1 + (M-maxn)*N;
		}
		if(K<=L1){
			int n = 3;
			while((n*n+1)/2 <= K)
				n +=2;
			n = n-2;
			int l1 = (n*n+1)/2;
			int l2 = l1+(n-1)/2;
			int l3 = l2 + (n+1)/2;
			int l4 = l3 + (n+1)/2;
			if(K==l1)
				return 2*n-2;
			else if(K<=l2)
				return 2*n-1;
			else if(K<=l3)
				return 2*n;
			else if(K<=l4)
				return 2*n+1;
			else
				return 2*n+2;
		}else if(K<=L2){
			int m = 0;
			while(L1+m*N<=K)
				m++;
			m--;
			int l1, l2;
			if(maxn==N){
				l1 = L1+m*N;
				l2 = l1 + (maxn-1)/2;

			}else{
				l1 = L1 +m*N;
				l2 = l1 + (maxn+1)/2;
			}
			if(K==l1)
				return 2*N-2+m*2;
			else if(K<=l2)
				return 2*N-2+m*2+1;
			else
				return 2*N-2 + m*2+2;
		}else{
			int l = (maxn+1)/2;
			int L = L2;
			int E = 2*N - 2 + (M-maxn)*2;
			int remain = K-L;
			while(remain > 0){
				if(remain > 4*(l-1)){
					remain -= 4*(l-1);
					l--;
					E = E+4;
				}else{
					if(remain <= l-1)
						return E + 1;
					else if(remain <= 2*(l-1))
						return E + 2;
					else if(remain <= 3*(l-1))
						return E + 3;
					else
						return E + 4;
				}
			}
		}
		return -1;
	}
}
