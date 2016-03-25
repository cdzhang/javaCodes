package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Runaway {
	/*double Y;
	double[] P, V;
	int N,caughtN;
	public static void main(String[] args) throws IOException {
		Runaway t = new Runaway();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			
			
			
			caughtN = 0;
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	double shortestTime(double pos,double time,int direction){
		//int N = P.length;
		boolean[] caughtf = caught.clone();
		if(caughtN==N)
			return time;
		if(direction==1){
			double fastest = V[N-1];
			int fI = N-1;
			for(int i=N-2;i>=0 && !caught[i]&&P[i]>0;i--){//can sort first, faster
				if(fastest<V[i]){
					fastest = V[i];
					fI = i;
				}
			}
			double tcf = (P[fI]+time*Y-pos)/(Y-V[fI]);
			double pf = P[fI] + tcf*V[fI];
			caught[fI] = true;
			caughtN++;
			double t1 = shortestTime(pf,tcf,1);
			double t0 = shortestTime(pf,tcf,0);
			double tf = Math.min(t1,t0);
			//caughtN--;
			//caught[fI] = false;
			for(int i=N-1;i>=0 && P[i]>0 && caught[i]==false;i--){
				double pi = P[i] + tcf*V[i];
				if(pi <= pf){
					caught[i]=true;//
					caughtN++;
				}else{
					caught[i] = true;
					caughtN++;
					double tci = (P[i]+time*Y-pos)/(Y-V[i]);
					double pci = P[i] + tci*V[i];
					double ti0 = shortestTime(pci,tci,0);
					double ti1 = shortestTime(pci,tci,1);
					double ti = Math.min(ti0, ti1);
					caught[i] = false;
					caughtN--;
					if(tf > ti){
						tf = ti;
					}
				}
			}
			return tf;
		}else{
			double fastest = V[0];
			int fI = 0;
			for(int i=1;i<N && !caught[i]&&P[i]<0;i++){//can sort first, faster
				if(fastest<V[i]){
					fastest = V[i];
					fI = i;
				}
			}
			double tcf = (P[fI]+time*Y-pos)/(Y-V[fI]);
			double pf = P[fI] + tcf*V[fI];
			caught[fI] = true;
			caughtN++;
			double t1 = shortestTime(pf,tcf,1);
			double t0 = shortestTime(pf,tcf,0);
			double tf = Math.min(t1,t0);
			//caughtN--;
			//caught[fI] = false;
			for(int i=0;i<N && P[i]<0 && caught[i]==false;i++){
				double pi = P[i] + tcf*V[i];
				if(pi >= pf){
					caught[i]=true;
					caughtN++;
				}else{
					caught[i] = true;
					caughtN++;
					double tci = (P[i]+time*Y-pos)/(Y-V[i]);
					double pci = P[i] + tci*V[i];
					double ti0 = shortestTime(pci,tci,0);
					double ti1 = shortestTime(pci,tci,1);
					double ti = Math.min(ti0, ti1);
					//caught[i] = false;
					caughtN--;
					if(tf > ti){
						tf = ti;
					}
					
				}
			}
			return tf;
		}
	}
*/
}
