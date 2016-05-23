package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import tools.TT;
import tools.Template;

public class Aerobics {
	int N;
	long W,L;
	long[] r;
	public static void main(String[] args) throws IOException {
		new Aerobics().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long[] nwl = TT.longArray(in.readLine(), " ");
			N = (int) nwl[0];
			W = nwl[1];
			L = nwl[0];
			r = TT.longArray(in.readLine(), " ");
			String output = "Case #"+caseN + ":" + getResult1();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	String getResult1(){
		double[] x = new double[N];
		double[] y = new double[N];
		Random rnd = new Random();
		for(int i=0;i<N;i++){
			x[i] = rnd.nextDouble()*W;
			y[i] = rnd.nextDouble()*L;
		}
		x[0] = 0;
		y[0] = 0;
		while(true){
			long count = 0;
			for(int i=0;i<N;i++){
				for(int j=i+1;j<N;j++){

					double xij = x[i]-x[j];
					double yij = y[i]-y[j];
					double dij = Math.sqrt(xij*xij+yij*yij);
					double dd = r[i] + r[j] - dij;
					if(dd > 0){
						double nxj=0;
						double nyj=0;
						if(dij<=1e-7){
							nxj = x[j] + dd;
							nyj = y[j] + dd;
						}else{
							nxj = x[j] + 1.1*(x[j]-x[i])/dij*dd+rnd.nextDouble()*0.001*W;
							nyj = y[j] + 1.1*(y[j]-y[i])/dij*dd+rnd.nextDouble()*0.001*L;
						}
						nxj = lx(nxj);
						nyj = ly(nyj);
						x[j] = nxj;
						y[j] = nyj;
						TT.print(i+",ri="+r[i]+","+x[i]+","+y[i]+","+j+",rj="+r[j]+","+x[j]+","
								+y[j]+"|");
						count++;
					}
				}
			}
			TT.println();
			if(count == 0)
				break;
		}
		String s = "";
		for(int i=0;i<N;i++)
				s += " "+x[i]+" " + y[i];
		return s;
	}
	String getResult(){
		double[] x = new double[N];
		double[] y = new double[N];
		Random rnd = new Random();
		for(int i=0;i<N;i++){
			x[i] = rnd.nextDouble()*W;
			y[i] = rnd.nextDouble()*L;
		}
		double[][] d = distance(x,y);
		while(true){
			long count = 0;
			for(int i=0;i<N;i++){
				for(int j=i+1;j<N;j++){
					double dij = d[i][j];
					double dd = r[i] + r[j] - dij;
					if(dd > 0){
						double nxj=0;
						double nyj=0;
						if(dij<=1e-7){
							nxj = x[j] + dd;
							nyj = y[j] + dd;
						}else{
							nxj = x[j] + (x[j]-x[i])/dij*dd;
							nyj = y[j] + (y[j]-y[i])/dij*dd;
						}
						nxj = lx(nxj);
						nyj = ly(nyj);
						x[j] = nxj;
						y[j] = nyj;
						for(int k=0;k<j;k++){
							double xkj = x[j]-x[k];
							double ykj = y[j]-y[k];
							d[k][j] = Math.sqrt(xkj*xkj+ykj*ykj);
						}
						for(int k=j+1;k<N;k++){
							double xkj = x[j]-x[k];
							double ykj = y[j]-y[k];
							d[j][k] = Math.sqrt(xkj*xkj+ykj*ykj);
						}
						count++;
					}
				}
			}
			if(count == 0)
				break;
		}
		String s = "";
		for(int i=0;i<N;i++)
				s += " "+x[i]+" " + y[i];
		return s;
	}
	double[][] distance(double[] x, double[] y){
		double[][] d = new double[N][N];
		for(int i=0;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				double xij = x[i]-x[j];
				double yij = y[i]-y[j];
				d[i][j] = Math.sqrt(xij*xij+yij*yij);
			}
		}
		return d;
	}
	double lx(double x){
		if(x<0)
			return W + x % W;
		else if(x>W)
			return x % W;
		else
			return x;
	}
	double ly(double y){
		if(y<0)
			return L + y % L;
		else if(y>L)
			return y % L;
		else
			return y;
	}
}
