package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Template;
import tools.TT;

public class Equal {
	long[] num,sum;
	boolean[] choosen;
	LinkedList<Long> sumall = new LinkedList<Long>();
	int N;
	public static void main(String[] args) throws IOException {
		new Equal().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] s = TT.longArray(in.readLine(), " ");
			N = (int)s[0];
			num = new long[N];
			sum = new long[N];
			sumall.clear();
			sumall.add(0L);
			choosen = new boolean[N];
			for(int i=0;i<N;i++){
				num[i] = s[i+1];
			}
			TT.sort(num);
			for(int i=0;i<N;i++){
				if(i==0)
					sum[i] = num[i];
				else
					sum[i] = sum[i-1]+num[i];
			}
			int lI = -1;
			long LS = -1;
			for(int i=0;i<N;i++){
				long li = num[i];
				for(int j=0;j<sumall.size();j++){
					long sj = sumall.get(j);
					long ls = sj + li;
					if(sumall.contains(ls)){
						lI = i;
						LS = ls;
						break;
					}else{
						sumall.add(sj);
					}
				}
			}
			String ss = "";
			if(lI==-1){
				ss = "Impossible";
			}else{
				
			}
			String output = "Case #"+caseN + ": "+ss;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	void getI(boolean[] c,long LS,int i,int lI){
		
	}
	long sum(boolean[] c,int I){
		long s = 0;
		for(int i=0;i<=I;i++){
			if(c[i])
				s+=num[i];
		}
		return s;
	}
}
