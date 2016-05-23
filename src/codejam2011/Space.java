package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Space {
	long t;
	int L,N,C;
	long[] a;
	public static void main(String[] args) throws IOException {
		new Space().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] li = TT.longArray(in.readLine(), " ");
			L = (int)li[0];
			t = li[1];
			N = (int)li[2];
			C = (int)li[3];
			a = new long[C];
			for(int i=4;i<4+C;i++)
				a[i-4] = li[i];
			
			
			String output = "Case #"+caseN + ": "+getTime();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getTime(){
		//double[][] dp = new double[N+1][L+1];
		double[][] dp1 = new double[2][N+1];
		for(int n=1;n<N;n++){
			dp1[0][n] = dp1[0][n-1]+a[(n-1)%C]*2;
			dp1[1][n] = dp1[0][n];
		}
		if(L==0) return (long) dp1[0][N] + "";
		for(int l=1;l<=L;l++){
			int i = (l-1)%2;
			double[] d1 = dp1[i];
			double[] d2 = dp1[1-i];
			for(int n=1;n<=N;n++){
				long ai = a[(n-1)%C];
				double t1 = d2[n-1]+ai*2;
				double t2 = d1[n-1];
				if(t2+ai*2<t){
					t2 = t2 + ai*2;
				}else if(t2<t){
					t2 = (ai-(t-t2)*0.5)+t;
				}else{
					t2 = t2 + ai;
				}
				d2[n] = Math.min(t1, t2);
			}
			if(l==L)
				return (long) d2[N]+"";
		}
		/*for(int n=1;n<=N;n++){
			for(int l=0;l<=L;l++){
				long ai = a[(n-1)%C];
				if(l==0)
					dp[n][l] = dp[n-1][l]+ai*2;
				else{
					double t1 = dp[n-1][l]+ai*2;
					double t2 = dp[n-1][l-1];
					if(t2+ai*2<t){
						t2 = t2 + ai*2;
					}else if(t2<t){
						t2 = (ai-(t-t2)*0.5)+t;
					}else{
						t2 = t2 + ai;
					}
					dp[n][l] = Math.min(t1,t2);
				}
			}
		}*/
		return null;
	}
	
}
