package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

import tools.TT;

public class Nile {
	int[][] river;
	long[][] building;
	long[][] dist;//dist[i][j] means the distance between ith and jth building
	int W, H,B;
	Stack<Integer> x = new Stack<Integer>();
	Stack<Integer> y = new Stack<Integer>();
	public static void main(String[] args) throws IOException {
		Nile t = new Nile();
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
			int[] line = TT.intArray(in.readLine()," ");
		    W = line[0];
		    H = line[1];
		    B = line[2];
			river = new int[H][W];
			building = new long[B][4];
			dist = new long[B+1][B+1];
			for(int b=0;b<B;b++)
				building[b] = TT.longArray(in.readLine(), " ");
			for(int i=0;i<B+1;i++){
				for(int j=0;j<B+1;j++){
					if(i==j)
						dist[i][j] = 0;
					else if(i>j)
						dist[i][j] = dist[j][i];
					else if(j==B){
						dist[i][j] = W-1-building[i][2];
					}else{
						long[] bi = building[i];
						long[] bj = building[j];
						dist[i][j] = distance(bi,bj);
					}
				}
			}
			for(long[] ab:building){
				long X0 = ab[0];
				long Y0 = ab[1];
				long X1 = ab[2];
				long Y1 = ab[3];
				for(int i=(int) Y0;i<=Y1;i++){
					for(int j=(int) X0;j<=X1;j++){
						river[i][j] = -1;
					}
				}
			}
			//printRiver();
			//int l = flowAmount();
				//printRiver();
			String output = "Case #"+caseN + ": "+flowAmount();
		    if(caseN==26)
			printRiver();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int flowAmount(){
		for(int j=0;j<W;j++){
			if(river[0][j]==0){
				int flow = j + 1;
				x.clear();
				y.clear();
				riverFlow(flow,0,j);
			}
		}
		int[] last = river[H-1];
		int count = 0;
		for(int c:last)
			if(c>0)
				count++;
		return count;
	}
	void riverFlow(int flow, int i, int j){
		//left, up, right, down
		river[i][j] = flow;
		if(i==H-1)
			return;
        int[] di,dj;
		int pi = -1;
		int pj = j;
		if(!x.empty()){
			pi = x.peek();
			pj = y.peek();
		}
		if(pi+1==i || (pi==i&&pj==j)){
			di = new int[]{0,1,0};
			dj = new int[]{-1,0,1};
		}else if(pi-1==i){
			di = new int[]{0,-1,0};
			dj = new int[]{1,0,-1};
		}else if(pj+1==j){
			di = new int[]{1,0,-1};
			dj = new int[]{0,1,0};
		}else{
			di = new int[]{-1,0,1};
			dj = new int[]{0,-1,0};
		}
		int nextI = -1;
		int nextJ = -1;
		for(int m=0;m<3;m++){
			int newI = i+di[m];
			int newJ = j+dj[m];
			if(newI>=0&&newI<H&&newJ>=0&&newJ<W&&river[newI][newJ]==0){
				nextI = newI;
				nextJ = newJ;
				break;
			}
		}
		if(nextI >= 0){
			x.push(i);
			y.push(j);
			riverFlow(flow,nextI,nextJ);
		}else{
			river[i][j] = -2;
			//find last
			if(!x.empty()){
				int lastI = x.pop();
				int lastJ = y.pop();
				riverFlow(flow,lastI,lastJ);
			}
		}
	}
	void riverFlow1(){
		
	}
	void printRiver(){
		for(int i=H-1;i>=0;i--){
			for(int j=0;j<W;j++){
				System.out.printf("%2d ",river[i][j]);
			}
			TT.println();
		}
		TT.println("---------------------");
	}
	//minum distance from this side to that side = the amount of flow
	//the amount of flow <= minimum blocks that need to block the 
	long minCut(){
		long[] bd = new long[B+1]; //bd[B] is the distance of the other side
		boolean[] visited = new boolean[B+1];
		for(int i=0;i<B;i++){
			bd[i] = building[i][0];
		}
		bd[B] = W;
		int index = shortestIndex(bd,visited);
		long d = bd[index];
		while(index < B){
			visited[index] = true;
			for(int i=0;i<=B;i++){
				if(visited[i])
					continue;
				long newd = d + dist[index][i];
				if(newd < bd[i]){
					bd[i] = newd;
				}
			}
			index = shortestIndex(bd,visited);
			d = bd[index];
		}
		return bd[B];
	}
	int shortestIndex(long[] bd,boolean[] visited){
		int L = bd.length;
		long min = -1;
		int index = -1;
		for(int i=0;i<L;i++){
			if(visited[i])
				continue;
			if(min < 0 || bd[i] < min){
				min = bd[i];
				index = i;
			}
		}
		return index;
	}
	long distance(long[] b1,long[] b2){
		long x01 = b1[0];
		long y01 = b1[1];
		long x1 = b1[2];
		long y1 = b1[3];
		long x02 = b2[0];
		long y02 = b2[1];
		long x2 = b2[2];
		long y2 = b2[3];
		long dx = distance(x01,x1,x02,x2);
		long dy = distance(y01,y1,y02,y2);
		return Math.max(dx, dy);
	}
	long distance(long x01,long x1,long x02,long x2){
		if(x01>x02)
			return distance(x02,x2,x01,x1);
		if(x1<x02)
			return x02 - x1-1;
		else
			return 0;
	}
}
