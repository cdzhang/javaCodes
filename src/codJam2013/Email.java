package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import tools.TT;

public class Email {
	//public static Set<String> dict = new HashSet<String>();
	int[][] mc;
	static final int M = 5;
	static int wN = 521196;
	public static String[] dict = new String[wN];
	String email;
	int EL;
	int mCount = 0;
	int count = 0;
	Email() throws IOException{
		String currentDir = System.getProperty("user.dir");
		String dictFile = currentDir+"/dict/garbled_email_dictionary.txt";
		BufferedReader dictIn = new BufferedReader(new FileReader(dictFile));
		for(int i=0;i<wN;i++)
			dict[i] = dictIn.readLine();
		dictIn.close();
		TT.println("Load Dict Successful!");
	}
	void test(){
		email = "codejam";
		EL = email.length();
		TT.print(isValid(0,-5,"a"));

	}
	public static void main(String[] args) throws IOException {
		Email t = new Email();
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
			count = 0;
			email = in.readLine();
			EL = email.length();
			mc = new int[EL+1][EL+1];
			mCount = -2;
			for(int i=0;i<EL;i++){
				for(int j=0;j<EL;j++){
					mc[i][j] = -2;
				}
			}
			
			String output = "Case #"+caseN + ": "+getCount1(0,-M);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int getCount1(int sI, int lastC){
		if(sI>=email.length())
			return 0;
		if(lastC>=0){
			int mk = mc[sI][lastC];
			if(mk!=-2)
				return mk;
		}
		int minCount = -1;
		for(String word:dict){
			if(word.length()==0)
				continue;
			int[] a = isValid(sI,lastC,word);
			//Tools.println(word);
			if(a!=null){
				int newSI = a[0];
				int newLC = a[1];
				int count = a[2];
				int iCount = getCount1(newSI,newLC);
				if(iCount >= 0){
					if(minCount < 0 || minCount > iCount + count)
						minCount = iCount + count;
				}
			}
		}
		if(lastC>=0)
			mc[sI][lastC] = minCount;
		return minCount;
	}
	void getCount(int sI,int lastC,int Icount){
		if(sI == email.length()){
			if(mCount < 0 || mCount > Icount)
				mCount = Icount;
		}
		for(String word:dict){
			if(word.length()==0)
				continue;
			int[] a = isValid(sI,lastC,word);
			count++;
			if(count <=200)
				TT.println(word);
			if(a!=null){
				int newSI = a[0];
				int newLC = a[1];
				int count = a[2];
				if(mCount >= 0 && Icount + count >= mCount)
					continue;
				getCount(newSI,newLC,Icount+count);
			}
		}
	}
	int[] isValid(int sI, int lc, String word){
		int L = word.length();
		if(sI + L-1 >= EL)
			return null;
		int lastC = lc;
		int cCount = 0;
		for(int i=0;i<L;i++){
			char si = email.charAt(sI+i);
			char wi = word.charAt(i);
			if(wi!=si){
				if(i+sI - lastC < M)
					return null;
				else{
					cCount++;
					lastC = sI + i;
				}
			}
		}
		int newSI = sI + L;
		int[] re = new int[]{newSI,lastC,cCount};
		return re;
	}
}