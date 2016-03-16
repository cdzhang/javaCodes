package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Dijkstra {
	int[][] table = {
			{1,2,3,4},
			{2,-1,4,-3},
			{3,-4,-1,2},
			{4,3,-2,-1}
			};
	long X;
	int L;
	int vL;
	int[] S,Sl,Sr;
	public static void main(String[] args) throws IOException {
		Dijkstra t = new Dijkstra();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] LX = Tools.longArray(in.readLine(), " ");
			L = (int)LX[0];
			X = LX[1];
			S = new int[L];
			Sl = new int[L];
			Sr = new int[L];
			String line = in.readLine();
			for(int n=0;n<L;n++){
				char c = line.charAt(n);
				if(c=='i')
					S[n] = 2;
				else if(c=='j')
					S[n] = 3;
				else
					S[n] = 4;
			}
			//Tools.print(S);
			int l = 1;
			int r = 1;
			for(int n=0;n<L;n++){
				l = multiply(l,S[n]);
				//Tools.println("l=="+l);
				Sl[n] = l;
				r = multiply(S[L-1-n],r);
				Sr[n] = r;
			}
			vL = Sl[L-1];
			//Tools.println("vL="+vL+",X="+X+",pow(vL,X)="+pow(vL,X));
			String output = "Case #"+caseN + ": " + can();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String can(){
		if(pow(vL,X)!=-1)
			return "NO";
		//find i
		long minIndex = Math.min(X, 4L)*L;
		long Iindex = -1;
		for(long i=1;i<=minIndex;i++){
			if(multiL(i) == 2){
				Iindex = i;
				break;
			}
		}
		if(Iindex == -1)
			return "NO";
		long minIndex2 = Math.min(X, 4L)*L;
		minIndex2 = Math.min(minIndex2, L*X - Iindex);
		for(int i=1;i<=minIndex2;i++){
			if(multiR(i)==4){
				return "YES";
			}
		}
		return "NO";
	}
	int multiL(long n){//multiplication of first n characters
		long p = n / L;
		int N =(int) (n % L);
		if(N==0)
			return pow(vL,p);
		else
			return multiply(pow(vL,p),Sl[N-1]);
	}
	int multiR(long n){
		long p = n / L;
		int N = (int)(n % L);
		if(N==0)
			return pow(vL,p);
		else
			return multiply(Sr[N-1],pow(vL,p));
	}
	//1->1, -1->-1, 2->i,-2->-i, 3->j,-3->-j,4->k,-4->-k
	int multiply(int n1,int n2){
		if(n1<0 && n2 > 0)
			return -multiply(-n1,n2);
		else if(n1 < 0 && n2 < 0)
			return multiply(-n1,-n2);
		else if(n1 >0 && n2<0)
			return -multiply(n1,-n2);
		else
			return table[n1-1][n2-1];
	}
	int pow(int base,long power){
		long p = power % 4;
		int v = 1;
		for(int i=1;i<=p;i++){
			v = multiply(v,base);
		}
		return v;
	}

}
