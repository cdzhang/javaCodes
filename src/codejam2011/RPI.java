package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class RPI {
	public static void main(String[] args) throws IOException {
		new RPI().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int N = Integer.parseInt(in.readLine());
			char[][] s = new char[N][N];
			for(int n=0;n<N;n++)
				s[n] = in.readLine().toCharArray();
			
			int[][] record = new int[N][2];
			for(int i=0;i<N;i++){
				int win = 0;
				int lose = 0;
				for(int j=0;j<N;j++){
					if(s[i][j]=='.')
						continue;
					else if(s[i][j]=='0')
						lose++;
					else
						win++;
				}
				record[i][0] = win;
				record[i][1] = lose;
			}
			double[] WP = new double[N];
			double[] OWP = new double[N];
			for(int i=0;i<N;i++){
				int win = record[i][0];
				int lose = record[i][1];
				WP[i] = 1.0*win/(win+lose);
				double owpi = 0;
				int count = 0;
				for(int j=0;j<N;j++){
					if(s[i][j]=='.') continue;
					int winj = record[j][0];
					int losej = record[j][1];
					if(s[i][j]=='1')
						owpi += 1.0*(winj)/(losej+winj-1);
					else
						owpi += 1.0*(winj-1)/(losej+winj-1);
					count++;
				}
				owpi /= count;
				OWP[i] = owpi;
			}
			double[] OOWP = new double[N];
			for(int i=0;i<N;i++){
				double ooi = 0;
				int count = 0;
				for(int j=0;j<N;j++){
					if(s[i][j]=='.')
						continue;
					ooi += OWP[j];
					count++;
				}
				ooi /= count;
				OOWP[i] = ooi;
			}
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
			for(int i=0;i<N;i++){
				double RPI = 0.25*WP[i] + 0.5*OWP[i] + 0.25*OOWP[i];
				System.out.println(RPI);
				out.println(RPI);
			}
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	

}
