package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import tools.Template;
import tools.TT;

public class GoodLuck {
	int R,N,M,K;
	int[][] cards;
	long[][] expPro;

	int expN = 100000000;
	Random random = new Random();
	public static void main(String[] args) throws IOException {
		GoodLuck t = new GoodLuck();
		//Tools.print(t.expPro.length);
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
			int[] rnmk = TT.intArray(in.readLine(), " ");
			R = rnmk[0];
			N = rnmk[1];
			M = rnmk[2];
			K = rnmk[3];
			cards = new int[expN][N];
			expPro = new long[expN][K];
			for(int en=0;en<expN;en++)
				oneCase(en);
			
			String output = "Case #"+caseN+":";
			
			System.out.println(output);
			out.println(output);
			for(int r=0;r<R;r++){
				long[] line = TT.longArray(in.readLine(), " ");
				TT.sort(line);
				output = getCard(line);
				System.out.println(output);
				out.println(output);
			}
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	void oneCase(int n){
		int[] card = new int[M];
		for(int m=0;m<M;m++){
			int rndI = random.nextInt(M-1)+2;
			card[m] = rndI;
		}
		cards[n] = card;
		long[] pro = new long[K];
		for(int i=0;i<K;i++){
			long apro = 1;
			for(int m=0;m<M;m++){
				if(random.nextBoolean())
					apro *= card[m];
			}
			pro[i] = apro;
		}
		TT.sort(pro);
		expPro[n] = pro;
	}
	int score(long[] pro,long[] pro2){
		int count = 0;
		for(int i=0;i<K;i++)
			if(pro[i] == pro2[i])
				count++;
		return count;
	}
	String getCard(long[] pro2){
		LinkedList<Integer> maxList = new LinkedList<Integer>();
		int[] scores = new int[expN];
		int maxScore = 0;
		for(int i=0;i<expN;i++){
			long[] pro = expPro[i];
			int score = score(pro,pro2);
			scores[i] = score;
			if(score>maxScore)
				maxScore = score;
		}
		for(int i=0;i<expN;i++)
			if(scores[i] == maxScore)
				maxList.add(i);
		int L = maxList.size();
		int l = random.nextInt(L);
		int[] card = cards[maxList.get(l)];
		String a="";
		for(int i=0;i<N;i++)
			a += card[i];
		return a;
	}

}