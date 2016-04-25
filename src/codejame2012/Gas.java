package codejame2012;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Gas {
	double D;
	int N,A;
	double[][] car;
	public static void main(String[] args) throws IOException {
		new Gas().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String[] line = in.readLine().split(" ");
			D = Double.parseDouble(line[0]);
			N = Integer.parseInt(line[1]);
			A = Integer.parseInt(line[2]);
			car = new double[N][2];
			for(int i=0;i<N;i++){
				double[] di = TT.doubleArray(in.readLine(), " ");
				car[i] = di;
			}
			String s = "";
			double[] Ai = TT.doubleArray(in.readLine(), " ");
			for(double a:Ai){
				s = s+"\n"+minTime(a);
			}
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	double minTime(double a){
		double v = 0.0;
		double p = 0.0;
		double pi = car[0][1];
		if(pi >= D){
			return Math.sqrt(2*D/a);
		}
		for(int i=1;i<N;i++){
			boolean isD = false;
			double ti = car[i][0];
			double ti_1 = car[i-1][0];
			pi = car[i][1];
			if(pi>=D){
				ti = (D-car[i-1][1])/vi(i) + ti_1;
				isD = true;
			}
			double dt = ti - ti_1;
			double vm = v + dt*a;
			double pm = v*dt + 0.5*a*dt*dt+p;
			//TT.println(v+","+p+","+vm+","+pm+","+isD);
			if(isD){
				if(pm >= D)
					return ti;
				else{
					return (-v+Math.sqrt(v*v-2*a*(p-D)))/a+ti_1;
				}
			}else{
				if(pm > car[i][1]){
					v = vi(i);
					p = car[i][1];
				}else{
					v = vm;
					p = pm;
				}
			}

		}
		
		return -1.0;
	}
	double vi(int i){
		if(i==0)
			return (car[i+1][1]-car[i][1])/(car[i+1][0]-car[i][0]);
		else
			return (car[i][1]-car[i-1][1])/(car[i][0]-car[i-1][0]);
	}
}
