package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Grid {
	int R,C,N;
	G[][] grid;
	public static void main(String[] args) throws IOException {
		Grid t = new Grid();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] RC = TT.intArray(in.readLine(), " ");
		    R = RC[0];
			C = RC[1];
			grid = new G[R][C];
			for(int r=0;r<R;r++){
				String s = in.readLine();
				for(int c=0;c<C;c++){
					grid[r][c] = new G(s.charAt(c) - '0');
				}
			}
			N = Integer.parseInt(in.readLine());
			String output = "Case #"+caseN + ": ";
			for(int n=1;n<=N;n++){
				String[] s = in.readLine().split(" ");
				if(s[0].equals("M")){
					int x = Integer.parseInt(s[1]);
					int y = Integer.parseInt(s[2]);
					int z = Integer.parseInt(s[3]);
					operationM(x,y,z);
				}else{
					output = output + "\n"+ operationQ();
				}
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void operationM(int x, int y, int z){
		grid[x][y].x = z;
	}
	int operationQ(){
		for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
				grid[r][c].r = 0;
			}
		}
		int label = 1;
		for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
				G g = grid[r][c];
				if(g.r == 0 && g.x == 1){
					g.r = label;
					spread(r,c,label);
					label++;
				}
			}
		}
		return label-1;
	}
	void spread(int i,int j,int level){
		int ii = i-1;
		if(ii>=0 && grid[ii][j].x==1 &&grid[ii][j].r==0){
			grid[ii][j].r = level;
			spread(ii,j,level);
		}
		ii = i+1;
		if(ii<R && grid[ii][j].x==1 && grid[ii][j].r==0){
			grid[ii][j].r = level;
			spread(ii,j,level);
		}
		int jj = j-1;
		if(jj>=0 && grid[i][jj].x==1 && grid[i][jj].r==0){
			grid[i][jj].r = level;
			spread(i,jj,level);
		}
		jj = j+1;
		if(jj<C && grid[i][jj].x==1 && grid[i][jj].r==0){
			grid[i][jj].r = level;
			spread(i,jj,level);
		}
	}
	void printR(){
		for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
				TT.print(grid[r][c].r+" ");
			}
			TT.println();
		}
		TT.println("xxxxxxx");
	}
	void printX(){
		for(int r=0;r<R;r++){
			for(int c=0;c<C;c++){
				TT.print(grid[r][c].x+" ");
			}
			TT.println();
		}
		TT.println("__________");
	}
}
class G{
	int x; //0 or 1
	int r = 0;
	G(){}
	G(int x1){ x=x1;}
}
