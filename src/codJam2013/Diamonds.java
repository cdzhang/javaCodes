package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

import tools.TT;

public class Diamonds {
	int[] f;
	int maxN = 1000;
	int N;
	Diamonds(){
		f = getNumber(maxN);
	}
	public static void main(String[] args) throws IOException {
		Diamonds t = new Diamonds();
		
		t.solve();
	}
	void test(){
		for(int i=1;i<=10;i++){
			TT.println(i+":"+C(10,i));
		}
		for(int i=1;i<=20;i++){
			N=i;
			TT.print(i+":");
			TT.print(findN());
		}
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] line = TT.intArray(in.readLine()," ");
			N = line[0];
			int X = line[1];
			int Y = line[2];
			X = Math.abs(X);
			//Tools.print(line);
			String output = "Case #"+caseN + ": " + getP(X,Y);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int[] getNumber(int n){
		int[] f = new int[n+1];
		f[0] = 1;
		for(int i=1;i<=n;i++){
			f[i] = 2 + 2*(2*i-1) + 1 + f[i-1];
		}
		return f;
	}
	double getP(int X, int Y){
		int[] a = findN();
		int L = a[0];
		int r = a[1];
		if(X+Y <= 2*L)
			return 1.0;
		else if(X+Y == 2*(L+1)){
			int min = Y + 1;
			if(r < min || X==0)
				return 0;
			else if(r>=min+2*L+2)
				return 1.0;
			else{
				double p = Math.pow(0.5,r);
				double v = 0;
				for(int i=min;i<=r;i++){//this should be r instead of 2L+2
					v += C1(r,i,p);
				}
				if(v<0)
					v=0;
				return v;
			}
		}else{
			return 0;
		}
	}
	int[] findN(){
		int n = 0;
		while(f[n] <= N){
			n++;
		}
		n--;
		return new int[]{n,N-f[n]};
	}
	long C(int r, int min){
		long up = 1;
		long down = 1;
		for(int i=1;i<=min;i++){
			up *= (r-i+1);
			down*=i;
		}
		return up/down;
	}
	double C1(int r, int min,double p){
		BigInteger up = new BigInteger(1+"");
		BigInteger down = new BigInteger(1+"");
		for(int i=1;i<=min;i++){
			int l = r-i+1;
			BigInteger b = new BigInteger(l+"");
			up = up.multiply(b);
			BigInteger bI = new BigInteger(i+"");
			down = down.multiply(bI);
		}
		up = up.divide(down);
		BigDecimal bd = new BigDecimal(up.toString());
		BigDecimal P = new BigDecimal(p+"");
		return bd.multiply(P).doubleValue();
	}
}
