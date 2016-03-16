package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Gballoon {
	int N,M,Q;
	int[][] balloons;
	int[] V;
	public static void main(String[] args) throws IOException {
		Gballoon t = new Gballoon();
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
			balloons = new int[N][2];
			for(int n=0;n<N;n++){
				balloons[n] = Tools.intArray(in.readLine(), " ");
			}

			int time = optimalTime(0,Q,Integer.MAX_VALUE);
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
	int optimalTime(int bj,int Qj,int smallestTime){
		if(Qj < 0) return -1;
		int Hj = balloons[bj][1];
		int hl = Math.max(0, Hj-Qj);
		int hh = Math.min(M-1, Hj+Qj);
		if(bj == N-1){
			int minTime = -1;
			for(int h=hl;h<=hh;h++){
				int time = getTime(bj,h);
				if(time >= 0 && time < smallestTime){
					if(minTime == -1 || time < minTime){
						minTime = time;
					}
				}
			}
			return minTime;
		}
		int minTime = -1;
		int sTime = smallestTime;
		for(int h=hl;h<=hh;h++){
			int bjt = getTime(bj,h);
			if(bjt < 0 || bjt >= smallestTime) continue;
			if(minTime > 0 && minTime < sTime)
				sTime = minTime;
			int time = optimalTime(bj+1,Qj-Math.abs(Hj-h),sTime);
			if(time >= 0){
				time = Math.max(time, bjt);
				if(minTime < 0 || time < minTime)
					minTime = time;
			}
		}
		return minTime;
	}
    int getTime(int bj,int h){
    	int Pi = balloons[bj][0];
    	if(Pi == 0) return 0;
    	int vh = V[h];
    	if(vh * Pi > 0) return -1;
    	return (int)Math.ceil(-1.0 * Pi / vh);
    }
}
