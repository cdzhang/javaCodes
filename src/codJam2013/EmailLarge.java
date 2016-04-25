package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

import tools.TT;

public class EmailLarge {
	static final int M = 5;
	static int wN = 521196;
	String email;
	HashSet<String> dict = new HashSet<String>();
	int EL;
	int[][] mc;
	int maxL = 0;
	EmailLarge() throws IOException{
		String currentDir = System.getProperty("user.dir");
		String dictFile = currentDir+"/dict/garbled_email_dictionary.txt";
		BufferedReader dictIn = new BufferedReader(new FileReader(dictFile));
		for(int i=1;i<=wN;i++){
			String word = dictIn.readLine();
			int L = word.length();
			if(maxL < L)
				maxL = L;
			dict.addAll(allSet(word,-M));
		}
		dictIn.close();
		TT.println("Load Dict Successful!");
	}
	HashSet<String> allSet(String word,int lastI){
		HashSet<String> vars = new HashSet<String>();
		vars.add(word);
		int L = word.length();
		int n = (L-1) / (M-1)+1;
		HashSet<String> vari = mute1(word,lastI);
		vars.addAll(vari);
		int i=1;
		while(i<=n){
			HashSet<String> vari1 = new HashSet<String>();
			for(String mute:vari)
				vari1.addAll(mute1(mute,0));
			if(vari1.size()==0)
				break;
			vars.addAll(vari1);
			vari.clear();
			vari = vari1;
		}
		return vars;
	}
	HashSet<String> mute1(String mute,int lastI){
		HashSet<String> vars = new HashSet<String>();
		int L = mute.length();
		int last = lastI;
		for(int i = L-1;i>=0;i--){
			if(mute.charAt(i)=='*'){
				last = i;
				break;
			}
		}
		for(int j=last+M;j<L;j++){
			StringBuilder mys = new StringBuilder(mute);
			mys.setCharAt(j, '*');
			String s = mys.toString();
			vars.add(s);
		}
		return vars;
	}
	public static void main(String[] args) throws IOException {
		EmailLarge t = new EmailLarge();
		t.solve();
	}
	void test(){
		HashSet<String> vars = allSet("coders",-1);
		for(String var:vars)
			TT.println(var);
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			email = in.readLine();
			EL = email.length();
			mc = new int[EL][EL];
			for(int i=0;i<EL;i++)
				for(int j=0;j<EL;j++)
					mc[i][j] = -2;
			String output = "Case #"+caseN + ": "+getCount(0,-M);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int getCount(int index,int k){
		if(index>=EL)
			return 0;
		if(k>=0){
			int mcc = mc[index][k];
			if(mcc!=-2)
				return mcc;
		}
		int count = -1;
		for(int L = 1;L<=maxL;L++){
			int endIndex = Math.min(index+L, EL);
			String s = email.substring(index,endIndex);
			HashSet<String> svars = allSet(s,Math.max(k-index, -M));
			for(String sv:svars){
				if(dict.contains(sv)){
					int sCount = sv.replaceAll("[^*]", "").length();
					int newIndex = index + L;
					int last = k;
					int sL = s.length();
					for(int i=sL-1;i>=0;i--){
						if(sv.charAt(i)=='*'){
							last = index + i;
							break;
						}
					}
					int newCount = getCount(newIndex,last);
					if(newCount >=0){
						if(count < 0 || count > newCount + sCount)
							count = newCount + sCount;
					}
				}
			}
		}
		if(k>=0)
			mc[index][k] = count;
		return count;
	}
}
