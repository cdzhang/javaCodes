package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class SquareTiles {
	char[][] b;
	int R,C;
	public static void main(String[] args) throws IOException {
		new SquareTiles().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] rc = TT.intArray(in.readLine(), " ");
			R = rc[0];
			C = rc[1];
			b = new char[R][C];
			for(int r=0;r<R;r++)
				b[r] = in.readLine().toCharArray();
			String output = "Case #"+caseN + ":"+replace();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String replace(){
		String board = "";
		for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
				if(b[r][c]=='#'){
					b[r][c] = '/';
					if(r+1>=R||b[r+1][c]!='#')
						return "\nImpossible";
					b[r+1][c] = '\\';
					if(c+1>=C||b[r][c+1]!='#')
						return "\nImpossible";
					b[r][c+1] = '\\';
					if(b[r+1][c+1]!='#')
						return "\nImpossible";
					b[r+1][c+1] = '/';
				}
			}
		}
		for(int r=0;r<R;r++){
			board+="\n";
			for(int c=0;c<C;c++){
				board += b[r][c];
			}
		}
		return board;
	}
}
