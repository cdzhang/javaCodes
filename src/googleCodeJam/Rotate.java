package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Tools;

public class Rotate {
    char[][] board;
    char[][] rotatedBoard;
    
	public static void main(String[] args) throws IOException {
		Rotate r = new Rotate();
		r.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1;caseN<=T;caseN++){
	        String[] NK = in.readLine().split(" ");
	        int N = Integer.parseInt(NK[0]);
	        int K = Integer.parseInt(NK[1]);
	        board = new char[N][N];
	        rotatedBoard = new char[N][N];
	        for(int n=0;n<N;n++){
	        	String aline = in.readLine();
	        	for(int i=0;i<N;i++)
	        		board[n][i] = aline.charAt(i);
	        }
	        //rotate board
	        for(int i=0;i<N;i++){
	        	for(int j=0;j<N;j++){
	        		rotatedBoard[j][N-i-1] = board[i][j];
	        	}
	        }//drop
			
	        for(int j=0;j<N;j++){
	        	int bottom = N;//first empty row of jth column
	        	for(int i=N-1;i>=0;i--){
	        		if(rotatedBoard[i][j] == '.'){
	        			if(bottom == N)
	        				bottom = i;
	        		}else if(bottom !=N){
	        			rotatedBoard[bottom][j] = rotatedBoard[i][j];
	        			rotatedBoard[i][j] = '.';
	        			bottom = bottom - 1;//rows N-1 to i in column j are not empty
	        		}
	        	}
	        }
	       printBoard();
	       System.out.println("-------");
	       printRotatedBoard();
	       System.out.println("N="+N+",K="+K);
	        //if red and blue can collect
	        boolean redK = false;
	        boolean blueK = false;
	        for(int j=0;j<N;j++){
	        	if(redK && blueK) break;
	        	for(int i=N-1;i>=0;i--){
	        		if(rotatedBoard[i][j] == '.'){
	        			i=0;
	        			break;//no need for this column
	        		}
	        		boolean redKi = true;
	        		boolean redKj = true;
	        		boolean redKd = true;
	        		boolean redKd2 = true;
	        		boolean blueKi = true;
	        		boolean blueKj = true;
	        		boolean blueKd = true;
	        		boolean blueKd2 = true;
	        		if(rotatedBoard[i][j] == 'R'){
	        			if(redK)
	        				continue;
	        			for(int k=1;k<=K-1;k++){
	        				if(i+k>=N || rotatedBoard[i+k][j] != 'R')
	        					redKi = false;
	        				if(j+k>=N || rotatedBoard[i][j+k] != 'R')
	        					redKj = false;
	        				if(i+k>=N || j+k>=N ||rotatedBoard[i+k][j+k] != 'R')
	        					redKd = false;
	        				if(i+k>=N || j-k <0 ||rotatedBoard[i+k][j-k] != 'R')
	        					redKd2 = false;
	        			}
	        			if(redKi || redKj || redKd || redKd2)
	        				redK = true;
	        		}else{//rotatedBoard[i][j] == 'B'
	        			if(blueK)
	        				continue;
	        			for(int k=1;k<=K-1;k++){
	        				if(i+k>=N || rotatedBoard[i+k][j] != 'B')
	        					blueKi = false;
	        				if(j+k>=N || rotatedBoard[i][j+k] != 'B')
	        					blueKj = false;
	        				if(i+k>=N || j+k>=N ||rotatedBoard[i+k][j+k] != 'B')
	        					blueKd = false;
	        				if(i+k>=N || j-k<0 || rotatedBoard[i+k][j-k] != 'B')
	        					blueKd2 = false;
	        			}
	        			if(blueKi || blueKj || blueKd || blueKd2)
	        				blueK = true;
	        		}
	        	}
	        }
	        String output = "";
	        if(redK && blueK)
	        	output = "Both";
	        else if(redK && !blueK)
	        	output = "Red";
	        else if(!redK && blueK){
	        	output = "Blue";
	        }else{
	        	output = "Neither";
	        }
	        output = "Case #"+caseN+": "+output;
	        
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	private void printBoard(){
		for(char[] line:board){
			for(char c:line){
				System.out.print(c + "");
			}
			System.out.println();
		}
	}
	private void printRotatedBoard(){
		for(char[] line:rotatedBoard){
			for(char c:line){
				System.out.print(c + "");
			}
			System.out.println();
		}
	}
	
}
