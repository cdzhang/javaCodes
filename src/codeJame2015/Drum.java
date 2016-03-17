package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Drum {
	long mod = 1000000007;
	long[][] ti;
	long x3,xs2,xs3,x23,x26;
	public static void main(String[] args) throws IOException {
		Drum t = new Drum();
		t.solve();
	}
	void setx(int C){
		if(C%6==0){
			xs2 = 2;
			x26 = 1;
			x23 = 1;
		}else if(C%3==0){
			x26 = 0;
			x23 = 1;
			xs2 = 1;
		}else{
			xs2 = 0;
			x23 = 0;
			x26 = 0;
		}
		if(C%4==0){
			x3 = 4;
			xs3 = 1;
		}
		else{
			x3 = 0;
			xs3 = 0;
		}
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] rc = Tools.intArray(in.readLine(), " ");
			long count = timesStart(rc[0],rc[1]);
			String output = "Case #"+caseN + ": "+count;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long timesStart(int R, int C){
		ti = new long[101][13];
		for(int i=0;i<101;i++){
			for(int ro=0;ro<13;ro++){
				ti[i][ro] = -1;
			}
		}
		setx(C);
		long r;
		if(R==1)
			r = 1;
		else if(R==2){
			r = xs2+1;
		}else if(R==3){
			r = 2+xs3;
		}else if(R==4){
			r = 1 + 2*xs2;
		}else if(R==5){
			r = 1+xs3+xs2+xs2+xs3;
		}else{
			r = times(R-2,1)+times(R-3,1)%mod+x23*times(R-4,3)+x26*times(R-4,6)+xs3*times(R-5,4)%mod;
		}
		return r % mod;
	}
	long times(int R,int period){
		if(ti[R][period]!=-1)
			return ti[R][period];
		long r = 0;
		long xx23,xx26, xx3;
		if(period > 1){
			xx23 = x23*Math.min(period, 3);
			xx26 = x26*Math.min(period, 6);
			xx3 = xs3*Math.min(period, 4);
		}else{
			xx23 = x23;
			xx26 = x26;
			xx3 = xs3;
		}
		long X2 = xx23 + xx26;
		if(R==1)
			r = 1;
		else if(R==2){
			r = X2;
		}else if(R==3){
			r = 1 + xx3;
		}else if(R==4){
			r = 1 + X2;
		}else if(R==5){
			r = X2 + X2 + xx3;
		}else{
			r = times(R-3,period)%mod + xx23*times(R-4,mul(period,3))%mod +xx26*times(R-4,mul(period,6))%mod 
					+ xx3*times(R-5,mul(period,4))%mod;
		}
		r = r % mod;
		ti[R][period] = r;
		return r;
	}
	int mul(int a, int b){
		return Tools.mul(a, b);
	}
}
