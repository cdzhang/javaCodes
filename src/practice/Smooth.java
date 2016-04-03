package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;


public class Smooth {
	
	public static void main(String[] args) throws IOException {
		Smooth t = new Smooth();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] nk = Tools.intArray(in.readLine(), " ");
			int N = nk[0];
			int K = nk[1];
			int[] sum = Tools.intArray(in.readLine()," ");
			String output = "Case #"+caseN + ": "+getD(K,N,sum);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int getD(int K,int N, int[] sum){
		int[][] span = new int[K][2];
		for(int i=0;i<K;i++){
			int mini = 0;
			int maxi = 0;
			int xmki = 0;
			for(int m=1;m*K+i<N;m++){
				xmki = xmki + sum[(m-1)*K+i+1] - sum[(m-1)*K+i];
				if(mini > xmki)
					mini = xmki;
				if(maxi < xmki)
					maxi = xmki;
			}
			span[i] = new int[]{mini,maxi};
		}
		int s0 = sum[0];
		int min = span[0][0];;
		for(int i=1;i<K;i++){
			int mini = span[i][0];
			if(mini<min)
				mini=min;
		}
		//make span[i][0] all the same
		for(int i=0;i<K;i++){
			int mini = span[i][0];
			int dx = mini - min;
			span[i][0] = min;
			span[i][1] = span[i][1] - dx;
			s0 = s0 + dx;
			//s0 = (s0%K+K)%K;
		}
		s0 = (s0%K+K)%K;
		for(int i=0;i<K-1;i++){
			for(int j=i+1;j<K;j++){
				if(span[i][1] > span[j][1]){
					int tmp = span[i][1];
					span[i][1] = span[j][1];
					span[j][1] = tmp;
				}
			}
		}
		int max = span[K-1][1];
		for(int i=0;i<K;i++){
			int maxi = span[i][1];
			int dx = max - maxi;
			s0 -= dx;
			if(s0 <= 0)
				break;
		}
		if(s0 > 0)
			max++;
		return max - min;
	}

}
