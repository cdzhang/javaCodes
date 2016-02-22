package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ChessBoard {
	private int[][] chessBoard;
	private LinkedList<int[]> cutRecord = new LinkedList<int[]>();
	public static void main(String[] arg) throws IOException{
		ChessBoard cb = new ChessBoard();
		cb.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
        String[] MN = in.readLine().split(" ");
        int M = Integer.parseInt(MN[0]);
        int N = Integer.parseInt(MN[1]);
        //next M lines are for chessboard
        chessBoard = new int[M][N];
        for(int m=0;m<M;m++){
           String aline = in.readLine();
           String parsedLine = parseString(aline);
           System.out.println("parsedLine="+parsedLine);
           for(int n=0;n<N;n++){
        	   chessBoard[m][n] = Integer.parseInt(parsedLine.charAt(n)+"");
           }
        }
       // printChessBoard();
        int[][] canCut = getCancut(M,N);
		in.close();
		out.close();
	}
	private String parseString(String s){
		String parsedS = "";
		String as = "";
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			switch (c){
			case '1': as = "0001"; break;
			case '2': as = "0010"; break;
			case '3': as = "0011"; break;
			case '4': as = "0100"; break;
			case '5': as = "0101"; break;
			case '6': as = "0110"; break;
			case '7': as = "0111"; break;
			case '8': as = "1000"; break;
			case '9': as = "1001"; break;
			case 'A': as = "1010"; break;
			case 'B': as = "1011"; break;
			case 'C': as = "1100"; break;
			case 'D': as = "1101"; break;
			case 'E': as = "1110"; break;
			case 'F': as = "1111"; break;
			default: as = ""; 
			}
			parsedS += as;
		}
		return parsedS;
	}
    private int[][] getCancut(int M,int N){
    	for(int m=0;m<M;m++){
    		for(int n=0;n<N;n++){
    			int r = 0;
    			while(true){
    				
    			}
    		}
    	}
    	return null;
    }
	private void printChessBoard(){
		for(int[] a:chessBoard){
			for(int b:a){
				System.out.print(b+",");
			}
			System.out.println();
		}
	}

}
