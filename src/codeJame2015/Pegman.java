package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Pegman {
	char[][] sheet;
	int R,C;
	public static void main(String[] args) throws IOException {
		Pegman t = new Pegman();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] rc = TT.intArray(in.readLine(), " ");
			R = rc[0];
			C = rc[1];
			sheet = new char[R][C];
			for(int r=0;r<R;r++){
				String line = in.readLine();
				for(int c=0;c<C;c++){
					sheet[r][c] = line.charAt(c);
				}
			}
			String output = "Case #"+caseN + ": "+minAdjust();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String minAdjust(){
		int adj = 0;
		for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
				char ch = sheet[r][c];
				if(ch=='.')
					continue;
				else if(ch=='^'){
					if(!upE(r,c)){
						adj++;
						if(!rightE(r,c)&&!downE(r,c)&&!leftE(r,c))
							return "IMPOSSIBLE";
					}
				}else if(ch=='>'){
					if(!rightE(r,c)){
						adj++;
						if(!upE(r,c)&&!downE(r,c)&&!leftE(r,c))
							return "IMPOSSIBLE";
					}
				}else if(ch=='v'){
					if(!downE(r,c)){
						adj++;
						if(!rightE(r,c)&&!upE(r,c)&&!leftE(r,c))
							return "IMPOSSIBLE";
					}
				}else if(ch=='<'){
					if(!leftE(r,c)){
						adj++;
						if(!rightE(r,c)&&!downE(r,c)&&!upE(r,c))
							return "IMPOSSIBLE";
					}
				}
			}
		}
		return adj+"";
	}
	boolean upE(int r,int c){
		for(int r1=r-1;r1>=0;r1--){
			if(sheet[r1][c]!='.'){
				return true;
			}
		}
		return false;
	}
	boolean rightE(int r,int c){
		for(int c1=c+1;c1<C;c1++){
			if(sheet[r][c1]!='.')
				return true;
		}
		return false;
	}
	boolean downE(int r, int c){
		for(int r1=r+1;r1<R;r1++){
			if(sheet[r1][c]!='.')
				return true;
		}
		return false;
	}
	boolean leftE(int r, int c){
		for(int c1=c-1;c1>=0;c1--)
			if(sheet[r][c1]!='.')
				return true;
		return false;
	}
	
}
