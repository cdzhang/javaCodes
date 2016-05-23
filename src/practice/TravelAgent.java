package practice;

import tools.TT;

public class TravelAgent {
	int INF = -1;
	int INF2 = -2;
	int[][] d = {
		{INF,3, INF, 4, INF},
		{INF,INF,5,INF,INF},
		{4,INF,INF,5,INF},
		{INF,INF,INF,INF,3},
		{7,6,INF,INF,INF}
	};
	int N = d.length;
	int[][] dp = new int[1<<N][N];
	int times(int S, int v){
		if(dp[S][v]!=INF)
			return dp[S][v];
		if(S == (1<<N)-1 && v == 0){
			dp[S][v] = 0;
			return 0;
		}
		int res = INF2;
		for(int i=0;i<N;i++){
			if(d[v][i]!=INF && (S & (1<<i)) == 0){
				int Si = (S | (1<<i));
				int ri = times(Si,i);
				if(ri==INF2)
					continue;
				ri = ri + d[v][i];
				if(res==INF2||res > ri){
					res = ri;
				}
			}
		}
		dp[S][v] = res;
		return res;
	}
	void solve(){
		for(int i=0;i<(1<<N);i++)
			for(int j=0;j<N;j++)
				dp[i][j] = INF;
		TT.println(times(0,0));
	}
	public static void main(String[] args){
		new TravelAgent().solve();
	}
}
