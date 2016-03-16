package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class Gballoon2 {
	int N,M,Q;
	int[][] balloons;
	int[] V;
	public static void main(String[] args) throws IOException {
		Gballoon2 t = new Gballoon2();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] NMQ = Tools.intArray(in.readLine(), " ");
			N = NMQ[0];
			M = NMQ[1];
			Q = NMQ[2];
			V = Tools.intArray(in.readLine(), " ");
			balloons = new int[N][4];
			for(int n=0;n<N;n++){
				//Tools.println(n);
				int[] line = Tools.intArray(in.readLine()," ");
				//Tools.println(line[0]);
				balloons[n][0] = line[0];
				balloons[n][1] = line[1];
				balloons[n][2] = 0;
				balloons[n][3] = getTime(n,line[1]);
				
			}
	    	/*if(caseN!=6)
				continue;
			Tools.print(balloons);
			Tools.print(V);*/
			int time = smallestTime();
			String s = "";
			if(time < 0) 
				s = "IMPOSSIBLE";
			else
				s = time + "";
			String output = "Case #"+caseN + ": " + s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	LinkedList<Integer> findMax(){
		int max = balloons[0][3];
		for(int n=0;n<N;n++){
			int time = balloons[n][3];
			if(time < 0){
				max = time;
				break;
			}else if(time > max){
				max = time;
			}
		}
		LinkedList<Integer> maxs = new LinkedList<Integer>();
		for(int n=0;n<N;n++){
			if(balloons[n][3] == max)
				maxs.add(n);
		}
		return maxs;
	}
	int smallestTime(){
		int Q1 = Q;
		boolean smaller = true;
		while(smaller){
			LinkedList<Integer> maxs = findMax();
			for(int m:maxs){
				int usedQ = reduceTime(m,Q1);
				if(usedQ == Q+1){//cannot reduce
					return balloons[m][3];
				}else{
					Q1 = Q1 - usedQ;
				}
			}
		}
		return -1;
	}
	int reduceTime(int bi,int Q1){//Q1 must > 0
		int Hi = balloons[bi][1];
		int dHi = balloons[bi][2];
		int dh = dHi;
		int time = balloons[bi][3];
		int usedQ = 0;
		while(usedQ <= Q1){
			dh = getNext(Hi,dh);
			if(dh==M)
				break;
			else{
				int h = Hi + dh;
				usedQ = Math.abs(dh)-Math.abs(dHi);
				if(usedQ > Q1)
					return Q+1;
				int newTime = getTime(bi,h);
				if(newTime < 0)
					continue;
				if(newTime < time||time < 0){
					balloons[bi][2] = dh;
					balloons[bi][3] = newTime;
					return usedQ;
				}
			}
		}
		return Q+1;
	}
	int getNext(int Hi,int dHi){
		if(dHi >= 0){
			if(Hi-dHi>0){
				return -dHi - 1;
			}else{
				if(Hi+dHi+1 < M)
					return dHi+1;
				else
					return M;
			}
		}else{
			if(Hi-dHi < M){
				return -dHi;
			}else{
				if(Hi+dHi>0)
					return dHi-1;
				else
					return M;
			}
		}
	}
    int getTime(int bj,int h){
    	int Pi = balloons[bj][0];
    	if(Pi == 0) return 0;
    	int vh = V[h];
    	if(vh * Pi > 0) return -1;
    	return (int)Math.ceil(-1.0 * Pi / vh);
    }
}
