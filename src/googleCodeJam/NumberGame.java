package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class NumberGame {
	//public boolean[][] table;
	//public int[][] AB;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		NumberGame ng = new NumberGame();
		ng.solve();
		//ng.test();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		//AB = new int[T][4];
		for(int caseN=1;caseN<=T;caseN++){
			String[] AB12 = in.readLine().split(" ");
			int A1 = Integer.parseInt(AB12[0]);
			int A2 = Integer.parseInt(AB12[1]);
			int B1 = Integer.parseInt(AB12[2]);
			int B2 = Integer.parseInt(AB12[3]);
			/*AB[caseN-1][0] = A1;
			AB[caseN-1][1] = A2;
			AB[caseN-1][2] = B1;
			AB[caseN-1][3] = B2;*/
			long winningCount = 0l;
			if(B1-B2 < A2-A1){
				int C1 = A1;
				int C2 = A2;
				A1 = B1; A2 = B2;
				B1 = C1; B2 = C2;
			}
			for(int A=A1;A<=A2;A++){
				winningCount += countWinning2(A,B1,B2);
				//System.out.println("winningCount=" + winningCount);
				//if(winningCount < 0) return;
			}
			
		/*	for(int A=A1;A<=A2;A++){
				for(int B=B1;B<=B2;B++){
					if(isWinning(A,B)){
						winningCount++;
					}
				}
			}*/
			String output = "Case #"+caseN+": " + winningCount;
			System.out.println(output);
			out.println(output);
			
		}
	/*	int Amax = 0;
		int Bmax = 0;
		for(int t=0;t<T;t++){
			if(Amax < AB[t][1])
				Amax = AB[t][1];
			if(Bmax < AB[t][3])
				Bmax = AB[t][3];
		}*/

	/*	calculateTable(Amax,Bmax);
		for(int caseN=1;caseN<=T;caseN++){
			int A1 = AB[caseN-1][0];
			int A2 = AB[caseN-1][1];
			int B1 = AB[caseN-1][2];
			int B2 = AB[caseN-1][3];
			int winningCount = 0;
			for(int A=A1;A<=A2;A++){
				for(int B=B1;B<=B2;B++){
					if(table[A-1][B-1]) winningCount++;
				}
			}
			String output = "Case #"+caseN+": " + winningCount;
			System.out.println(output);
			out.println(output);
		}*/
		in.close();
		out.close();
	}
	boolean isWinning(int i,int j){
		if(i==j)
			return false;
		else if(i>j)
			return isWinning(j,i);
		else if(j>=2*i)
			return true;
		else
			return !isWinning(j-i,i);
	}
	int countWinning2(int A,int B1,int B2){
		int count = 0;
		double f = (1+Math.sqrt(5))/2.0;
		int fl = (int)Math.floor(A/f);
		int fh = (int)Math.floor(A*f) + 1;
		if(B1 >= fh || B2 <= fl)
			count = B2 - B1 + 1;
		else if(B1 > fl && B2 < fh)
			count = 0;
		else if(B1 > fl && B2 >= fh)
			count = B2 - fh + 1;
		else if(fl >= B1 && fh > B2)
			count = fl - B1 + 1;
		else if(fl >=B1 && B2 >=fh)
			count = fl - B1 + 1 + B2 - fh + 1;
		return count;
	}
	int countWinning(int A, int B1, int B2){//count the number of winning positions of (A,[B1,B2]), inclusive
		int count = 0;
		if(B1 >= 2*A || B2 <= A/2){
			count = B2 - B1 + 1;
		}else if(B1 >= A/2 && B2 <= 2*A){
			for(int B=B1;B<=B2;B++){
				if(isWinning(A,B))
					count++;
			}
		}else if(B1 >= A/2 && B2 >= A*2){
			count = B2 - A*2 + 1;//include A*2
			for(int B=B1;B<A*2;B++){
				if(isWinning(A,B))
					count++;
			}
		}else if(B1 <= A/2 && B2 <=2*A){
			count = A/2 - B1 + 1;//include A/2
			for(int B=A/2+1;B<=B2;B++){
				if(isWinning(A,B))
					count++;
			}
		}else if(A/2>=B1 && B2>=2*A){
			count = A/2 - B1 + 1 + B2 - 2*A + 1;//include A/2 and 2*A
			for(int B=A/2+1;B<2*A;B++){
				if(isWinning(A,B))
					count++;
			}
		}
		return count;
	}
	/*void calculateTable(int Amax,int Bmax){
		for(int A=0;A<Amax;A++){
			for(int B=0;B<Bmax;B++){
				if(A+1==0){
					table[A][B] = true;
				}else if(A+1==B+1){
					table[A][B] = false;
				}else if((B+1)>=2*(A+1)){
					table[A][B] = true;
				}else if(A+1 > B+1){
					table[A][B] = table[B][A];
				}else{
					table[A][B] = !table[B-A-1][A];
				}
			}
		}
	}*/
	void test(){
	/*	for(int i=1;i<=31;i++){
			int c = countWinning(i,1,31);
			int a = 0;
			for(int j=1;j<=31;j++){
				if(isWinning(i,j))
					a++;
			}
			System.out.println("i="+i+",c="+c+",a="+a);
		}
	}*/
		System.out.println("countWinning(4,1,31)="+countWinning(4,1,31));
	}
}
