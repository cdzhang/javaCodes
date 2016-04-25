package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Tides {
	int H, N, M;
	int[][] ceiling,floor;

	boolean[][] visited;
	public static void main(String[] args) throws IOException {
		new Tides().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] hnm = TT.intArray(in.readLine(), " ");
			H = hnm[0];
			N = hnm[1];
			M = hnm[2];
			ceiling = new int[N][M];
			floor = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0;i<N;i++){
				ceiling[i] = TT.intArray(in.readLine(), " ");
			}
			for(int i=0;i<N;i++){
				floor[i] = TT.intArray(in.readLine(), " ");
			}
			String output = "Case #"+caseN + ": "+minTime();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	double minTime(){
		double[][] time = new double[N][M];
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++)
				time[i][j] = -1;
	    spread(time,0,0);
		while(true){
			int[] nij = getMinTime(time);
			int i = nij[0];
			int j = nij[1];
			//Tools.println(getTime(0,0,0,1,0));
			if(i==N-1&&j==M-1)
				return time[i][j];
			visited[i][j] = true;
			int[] dx = {-1,0,1,0};
			int[] dy = {0,1,0,-1};
			for(int d=0;d<4;d++){
				int ni = i + dx[d];
				int nj = j + dy[d];
				if(ni>=0 && ni<N && nj>=0 && nj<M && !visited[ni][nj]){
					double tij = time[i][j];
					double tn = getTime(i,j,ni,nj,tij);
					if(tn >= 0){
						double ttn = time[ni][nj];
						if(ttn < 0 || ttn > tij+tn){
							time[ni][nj] = tij+tn;;
						}
					}
				}
			}
		}
	}
	int[] getMinTime(double[][] time){
		int nI = -1;
		int nJ = -1;
		double min = -1;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(visited[i][j])
					continue;
				double t = time[i][j];
				if(t<0)
					continue;
				if(min < 0 || min>t){
					min = t;
					nI = i;
					nJ = j;
				}
			}
		}
		return new int[]{nI,nJ};
	}
	void spread(double[][] time,int i,int j){
		time[i][j] = 0;
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		for(int d=0;d<4;d++){
			int ni = i + dx[d];
			int nj = j + dy[d];
			if(ni>=0 && ni<N && nj>=0 && nj<M && time[ni][nj]<0){
				int cc = ceiling[i][j];
				int cf = floor[i][j];
				int nc = ceiling[ni][nj];
				int nf = floor[ni][nj];
				if(cc >= nf+50 && nc>=cf+50 && nc>=nf+50 && nc>=H+50){
					spread(time,ni,nj);
				}
			}
		}
	}
	double getTime(int cI,int cJ,int nI,int nJ,double ct){
		int cc = ceiling[cI][cJ];
		int cf = floor[cI][cJ];
		int nc = ceiling[nI][nJ];
		int nf = floor[nI][nJ];
		double w = Math.max(0.0,H - ct*10.0);
		if(cc < nf+50 || nc < cf+50 || nc < nf+50)
			return -1.0;
		if(w>=cf+20){
			if(nc>=w+50)
				return 1.0;
			else{//w must >= nf
				double t1 = (w-cf-20)/10.0;
				double t2 = (w+50-nc)/10.0;
				if(t1<t2){
					return t2 + 10.0;
				}else{
					return t2 + 1.0;
				}
			}
		}else{
			if(nc>=w+50){
				return 10.0;
			}else{
				return 10 + (w + 50 - nc)/10.0;
			}
		}
	}
}
