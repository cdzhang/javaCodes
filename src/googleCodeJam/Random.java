package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class Random {
	long N,X,K,A,B,C;
	double[][][] p;
	double[][][] pn;
	double err = 1e-9;
	public static void main(String[] args) throws IOException {
		Random t = new Random();
		//t.solve();
		int[] a = new int[(int) 1e10];
		Tools.println(0.0/10);
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] line = Tools.longArray(in.readLine(), " ");
			N = line[0];
			X = line[1];
			K = line[2];
			A = line[3];
			B = line[4];
			C = line[5];
			p = new double[2][2][2];
			pn = new double[2][2][2];
			double pa = (1.0*A)/100;
			double pb = (1.0*B)/100;
			double pc = (1.0*C)/100;
			p[0][0][0] = 1;
			p[0][0][1] = 0;
			p[0][1][0] = pa;
			p[0][1][1] = pb+pc;
			p[1][0][0] = pa;
			p[1][0][1] = pb+pc;
			p[1][1][0] = pc;
			p[1][1][1] = pa+pb;
			pn[0][0][1] = 0;
			double a = p[0][1][1] / (1-p[1][1][1]+p[0][1][1]);//should consider p[111] = 1
			if(p[0][1][1]==0){
				a = 0;
			}
			pn[0][1][1] = a + (p[0][1][1]-a)*Math.pow(p[1][1][1]-p[0][1][1],N-1);
			pn[1][0][1] = Math.pow(p[1][0][1], N);
			if(Math.abs(pn[1][0][1])<=err){
				pn[1][0][1] = 0;
			}
			//a = p[0][1][1] / (1 - p[1][1][1] + p[0][1][1]);
			pn[1][1][1] = a + (p[1][1][1]-a)*Math.pow(p[1][1][1]-p[0][1][1], N-1);
			if(caseN==28){
			  Tools.println(a);
			  print();
			}
			String output = "Case #"+caseN + ": "+getMean();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void test(){
		//pn = new double[2][2][2];
		double p111 = 0.9;
		double p010 = 0.7;
		double p011 = 0.3;
		double p110 = 0.1;
		double p101 = 0.3;
		double p100 = 0.7;
		double p001 = 0;
		double p000 = 1;
		
		int i=0;
		double p1i = 0;
		double p0i = 1;
		while(i<10){
			i++;
			double p0 = p0i;
			double p1 = p1i;
			/*p0i = p0*p010 + p1*p110;
			p1i = p0*p011 + p1*p111;*/
			p0i = p0*p010 + p1*p110;
			p1i = p0*p011 + p1*p111;
			Tools.println(i+","+p0i+","+p1i);
		}
	}
	double getMean(){
		double mean = 0;
		String sx = Long.toBinaryString(X);
		String sk = Long.toBinaryString(K);
		int Lx = sx.length();
		int Lk = sk.length();
		if(Lx < Lk){
			for(int i=0;i<Lk-Lx;i++)
				sx = "0"+sx;
		}else if(Lk < Lx){
			for(int i=0;i<Lx-Lk;i++){
				sk = "0" + sk;
			}
		}
		//Tools.println(sx+","+sk);
		int L = sx.length();
		for(int i=0;i<L;i++){
			char cx = sx.charAt(L-i-1);
			char ck = sk.charAt(L-1-i);
			if(cx=='0' && ck=='0'){
			}else if (cx =='0' && ck=='1'){
				mean += Math.pow(2,i)*pn[0][1][1];
			}else if(cx == '1' && ck=='0'){
				mean += Math.pow(2, i)*pn[1][0][1];
			}else{//cx =='1'&& ck=='1'
				mean += Math.pow(2, i)*pn[1][1][1];
			}
		}
		return mean;
	}
	void print(){
		Tools.println(pn[0][0][1]+","+pn[0][1][1]+","+pn[1][0][1]+","+pn[1][1][1]);
	}
}
