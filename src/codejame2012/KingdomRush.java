package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class KingdomRush {
	int N;
	int[][] tower;
	boolean[][] played;
	int myStars;
	public static void main(String[] args) throws IOException {
		KingdomRush t = new KingdomRush();
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
			myStars = 0;
			N = Integer.parseInt(in.readLine());
			tower = new int[N+1][2];
			played = new boolean[N+1][2];
			for(int n=1;n<=N;n++){
				tower[n] = TT.intArray(in.readLine(), " ");
			}
			sortTower();
			printTower();
			String output = "Case #"+caseN + ": "+getMinCount();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
    void printTower(){
    	TT.print(tower);
    }
    void sortTower(){//according to bi
    	for(int n=1;n<N;n++){
    		for(int n2=n+1;n2<=N;n2++){
    			int[] tn = tower[n];
    			int[] tn2 = tower[n2];
    			if(tn[1]<tn2[1]){
    				int tnx = tn[0];
    				int tny = tn[1];
    				tn[0] = tn2[0];
    				tn[1] = tn2[1];
    				tn2[0] = tnx;
    				tn2[1] = tny;
    			}
    		}
    	}
    }
	String getMinCount(){
		int count = 0;
		int level = 0;
		myStars = 0;
		while(level < N){
			int n2 = next2Star();
			if(n2>=1){
				if(played[n2][0])
					myStars++;
				else
					myStars+=2;
				level++;
				count++;
				played[n2][0] = true;
				played[n2][1] = true;
				if(level==N)
					return count+"";
			}else{
				int n1 = next1Star();
				if(n1 <= 0){
					return "Too Bad";
				}
				myStars++;
				played[n1][0] = true;
				count++;
			}
		}
		TT.println("hehhe");
		return count+"";
	}
	int next2Star(){
		for(int n=1;n<=N;n++){
			if(!played[n][1] && tower[n][1] <= myStars){
				return n;
			}
		}
		return -1;
	}
	int next1Star(){
		for(int n=1;n<=N;n++){
			if(!played[n][0] && tower[n][0] <= myStars){
				return n;
			}
		}
		return -1;
	}
}
