package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import tools.TT;

public class TicTack {
	public static void main(String[] args) throws IOException {
		TicTack t = new TicTack();
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
			char[][] s = new char[4][4];
			for(int i=0;i<4;i++){
				String si = in.readLine();
				for(int j=0;j<4;j++){
					s[i][j] = si.charAt(j);
				}
			}
			String aline = in.readLine();
			//printBoard(s);
			String output = "Case #"+caseN + ": "+status(s);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String status(char[][] board){
		if(isChar(board,'X'))
			return "X won";
		else if(isChar(board,'O')){
			return "O won";
		}else if(hasEmpty(board)){
			return "Game has not completed";
		}else{
			return "Draw";
		}
	}
	boolean isChar(char[][] board, char c){
		for(int i=0;i<4;i++){
			boolean ic = true;
			for(int j=0;j<4;j++){
				if(board[i][j]!=c && board[i][j] != 'T'){
					ic = false;
					break;
				}
			}
			if(ic){
				TT.println("line"+i);
				return true;
			}
		}
		for(int j=0;j<4;j++){
			boolean jc = true;
			for(int i=0;i<4;i++){
				if(board[i][j]!=c && board[i][j] != 'T'){
					jc = false;
					break;
				}
			}
			if(jc){
				TT.println("column "+j);
				return true;
			}
		}
		boolean dc1 = true;
		for(int i=0;i<4;i++){
			if(board[i][i] !=c && board[i][i]!='T'){
				dc1 = false;
				break;
			}
		}
		if(dc1){
			TT.println("dc1");
			return true;
		}
		boolean dc2 = true;
		for(int i=0;i<4;i++){
			if(board[3-i][i]!=c && board[3-i][i]!= 'T'){
				dc2 = false;
				break;
			}
		}
		if(dc2){
			TT.println("dc2");
			return true;
		}
		return false;
	}
	boolean hasEmpty(char[][] board){
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++){
				if(board[i][j] == '.')
					return true;
			}
		return false;
	}
	void printBoard(char[][] board){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				TT.print(board[i][j]);
			}
			TT.println();
		}
		
	}

}
