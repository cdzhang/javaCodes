package practice;

import java.util.LinkedList;
import java.util.PriorityQueue;

import tools.Int;
import tools.TT;
public class Run {
	String s = "abcdaabdeabcddcabbccddab";
	int N = s.length();
	long[][] count1 = new long[N+1][N+1];
	long[][][] count2 = new long[N+1][N+1][N+1];
	public static void main(String[] arg){
		Run r = new Run();
		r.test();
		TT.println(3600*24*2e-11*1e-4);
	}
	void test(){
		long a = 1232;
		long b = 573;
		long z = 1;
		long[] xy = TT.exgcd(a,b,z);
		if(xy!=null)
			TT.print(xy);
	}
	void densityDependent(){
		double dm = 0.001;
		for(double m = 0;m<1;m+=dm){
			TT.println(m+"\t"+Math.pow(m/(1-m),4));
		}
	}
	double time(double G){
		double mu = 0.08*G/(0.0072+G);
		return 1/mu;
	}
    public static int Puzzle(int lowerBound, int upperBound) {
        int m = 1;
        for(int i=lowerBound;i<=upperBound;i++)
            m *= i;
        return m;
    }
	void priority(){
		PriorityQueue<Int> pq = new PriorityQueue<Int>();
		pq.add(new Int(1));
		pq.add(new Int(2));
		pq.add(new Int(3));
		pq.add(new Int(4));
		while(!pq.isEmpty()){
			Int pqt = pq.poll();
			TT.print(" " +pqt);
		}
		TT.println();
	}
	Int[] lk(Int[] a,int k){
		int N = a.length;
		Int[] b = new Int[N-k+1];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		Int max = a[0];
		int index = 0;
		for(int i=1;i<k;i++){
			if(a[i].compareTo(max)>=0){
				max = a[i];
				index = i;
			}
		}
		queue.add(index);
		for(int i=k;i<N;i++){
			b[i-k] = a[queue.getFirst()];
			while(!queue.isEmpty() && queue.getFirst() <= i-k)
				queue.removeFirst();
			while(!queue.isEmpty() && a[queue.getLast()].compareTo(a[i]) <= 0)
				queue.removeLast();
			queue.addLast(i);
		}
		b[N-k] = a[queue.getFirst()];
		return b;
	}
	void caucluateCount(){
		for(int i=1;i<=N;i++){
			char ci = s.charAt(i-1);
			for(int n=1;n<=N;n++){
				if(ci=='a'){
					if(n==1){
						count1[i][n] = count1[i-1][n] + 1;
					}
					else{
						count1[i][n] = count1[i-1][n-1] + count1[i-1][n];
						//count[i][n] = count[i-1][n-1];
					}
				}else{
					count1[i][n] = count1[i-1][n];
				}
			}
		}
	}
	long divide(int n,int m,long mod){
		long[][] dp = new long[m+1][n+1];
		for(int j=1;j<=n;j++){
			dp[1][j] = 1;
		}
		for(int i=1;i<=m;i++)
			dp[i][1] = 1;
		for(int i=2;i<=m;i++){
			for(int j=2;j<=n;j++){
				if(j>i)
					dp[i][j] = dp[i][j-i] + dp[i-1][j];
				else if(j==i){
					dp[i][j] = 1 + dp[i-1][j];
				}else{
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[m][n];
	}
	long getCount(int n,int m,int[] a,long mod){
		long[][] dp = new long[n+1][m+1];
		for(int i=0;i<=n;i++){
			for(int j=0;j<=m;j++){
				if(j==0)
					dp[i][j] = 1;
				else if(i==0){
					dp[i][j] = 0;
				}else{
					int gi = Math.min(j, a[i-1]);
					long dpi = 0;
					for(int k=0;k<=gi;k++){
						dpi += dp[i-1][j-k];
						dpi %= mod;
					}
					dp[i][j] = dpi;
				}
			}
		}
		return dp[n][m];
	}
}
