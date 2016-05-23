package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Fashion {
	int J,P,S,K;
	String sl;
	int[][] jp,js,ps;
	boolean[][][] selected;
	public static void main(String[] args) throws IOException {
		new Fashion().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] line = TT.intArray(in.readLine(), " ");
			J = line[0];
			P = line[1];
			S = line[2];
			K = line[3];
			sl = "";
			jp = new int[S+1][S+1];
			js = new int[S+1][S+1];
			ps = new int[S+1][S+1];
			selected = new boolean[S+1][S+1][S+1];
			String output = "Case #"+caseN + ": "+getMax();
			output += sl;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long getMax(){
		long count = 0;
		while(true){
			int[] l = getLowest();
             if(l==null)
            	 return count;
			int j = l[0];
			int p = l[1];
			int s = l[2];
			int max = l[3];
			if(max >=K)
				return count;
			
			count++;
			sl = sl + "\n" + j + " " + p + " " + s;
			jp[j][p]++;
			js[j][s]++;
			ps[p][s]++;
			selected[j][p][s] = true;
		}
	}
	int[] getLowest(){
		int[] jsp = new int[]{100,100,100};
		int[] re = new int[]{0,0,0,0};
		for(int j=1;j<=J;j++){
			for(int p=1;p<=P;p++){
				for(int s=1;s<=S;s++){
					if(selected[j][p][s]) continue;
					int[] a = new int[]{jp[j][p],js[j][s],ps[p][s]};
					if(compare(a,jsp)<0){
						jsp = a;
						re = new int[]{j,p,s,a[2]};
					}
				}
			}
		}
		if(re[0] == 100)
			return null;
		else
		return re;
	}
	int max(int a,int b,int c){
		return Math.max(a, Math.max(c,b));
	}
	int compare(int[] a,int[] b){
		int[] a1 = a.clone();
		int[] b1 = b.clone();
		TT.sort(a1);
		TT.sort(b1);
		for(int i=a1.length-1;i>=0;i--){
			if(a1[i]>b1[i])
				return 1;
			if(a[i]<b1[i])
				return -1;
		}
		return 0;
	}
}
