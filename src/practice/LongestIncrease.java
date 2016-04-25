package practice;

import tools.TT;

public class LongestIncrease {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = {4,2,3,1,5};
		TT.println(new LongestIncrease().longest(a));

	}
	int longest(int[] a){
		int res = 0;
		int N = a.length;
		int[] dp = new int[N];
		for(int i=0;i<N;i++){
			dp[i] = 1;
			for(int j=0;j<i;j++){
				if(a[j] < a[i]){
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			res = Math.max(dp[i], res);
		}
		return res;
	}

}
