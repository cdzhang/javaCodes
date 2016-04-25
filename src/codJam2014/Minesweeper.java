package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Minesweeper {
	
	public static void main(String[] args) throws IOException {
		Minesweeper t = new Minesweeper();
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
			int[] rcm = TT.intArray(in.readLine(), " ");
			int R = rcm[0];
			int C = rcm[1];
			int M = rcm[2];
			
			String output = "Case #"+caseN +": "+getResult(R,C,M);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getResult(int R,int C, int M){
		String os = "";
		if(R <= C){
			char[][] s = getArrange(R,C,M);
			os = getString(s);
		}else{
			char[][] s = getArrange(C,R,M);
			s = TR(s);
			os = getString(s);
		}
		return os;
	}
	char[][] getArrange(int R, int C, int M){//suppose R < = C
		int l = R*C - M;
		char[][] s = new char[R][C];
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				s[i][j] = '*';
			}
		}
		if(l==1){
			s[0][0] = 'c';
		}else if(R==1){
			s[0][0] = 'c';
			for(int i=1;i<l;i++)
				s[0][i] = '.';
		}else if(R==2){
			if(l<4 || l%2==1)
				return null;
			int K = l/2;
			for(int j=0;j<K;j++){
				s[0][j] = '.';
				s[1][j] = '.';
			}
			for(int j=K;j<C;j++){
				s[0][j] = '*';
				s[1][j] = '*';
			}
			s[0][0] = 'c';
		}else{
			if(l<4||l==5||l==7)
				return null;
			if(l % 2 == 0){
				int fI = l / (2*C);
				for(int i=0;i<fI*2;i=i+2){
					for(int j=0;j<C;j++){
						s[i][j] = '.';
						s[i+1][j] = '.';
					}
				}
				l = l - fI * 2 * C;
				if(R-fI*2 >=2 && l > 3){
					int i = fI*2;
					int cI = l/2;
					for(int j=0;j<cI;j++){
						s[i][j] = '.';
						s[i+1][j] = '.';
					}
				}else{
					int i = fI*2;
					for(int j=0;j<l;j++){
						s[i][j] = '.';
					}
				}
				s[0][0] = 'c';
			}else{
				int fI = (l-3) / (2*C);
				if(fI == 0){
					int jl = (l-3)/2;
					for(int j=0;j<jl;j++){
						s[0][j] = '.';
						s[1][j] = '.';
					}
					for(int j=0;j<3;j++){
						s[2][j] = '.';
					}
					s[0][0] = 'c';
				}else{
					int rL = l / C;
					for(int i=0;i<rL;i++){
						for(int j=0;j<C;j++){
							s[i][j] = '.';
						}
					}
					l = l % C;
					if(l==1){
						s[rL-1][C-1] = '*';
						s[rL][0] = '.';
						s[rL][1] = '.';
					}else{
						for(int j=0;j<l;j++)
							s[rL][j] = '.';
					}
					s[0][0] = 'c';
				}
			}
		}
		return s;
	}
	char[][] TR(char[][] s){
		if(s==null)
			return null;
		int R = s.length;
		int C = s[0].length;
		char[][] ts = new char[C][R];
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				ts[j][i] = s[i][j];
			}
		}
		return ts;
	}
	String getString(char[][] s){
		String os = "\n";
		if(s==null){
			os += "Impossible";
		}else{
			int R = s.length;
			int C = s[0].length;
			for(int i=0;i<R;i++){
				for(int j=0;j<C;j++){
					os += s[i][j];
				}
				if(i!=R-1)
					os += "\n";
			}
		}
		return os;
	}
}
