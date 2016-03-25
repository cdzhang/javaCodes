package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Permutation {
	int[][] permGood;
	int[][] permBad;
	double[] good, bad;
	int N=1000;
	Permutation(){
		int V = 10000;
		good = new double[N];
		bad = new double[N];
		for(int v=1;v<=V;v++){
			int[] g = goodPerm();
			int[] b = badPerm();
			for(int i=0;i<N;i++){
				good[i] = good[i]+g[i]*1.0/V;
				bad[i] = bad[i]+b[i]*1.0/V;
			}
		}
	}
	int[] goodPerm(){
		int[] array = new int[N];
		for(int n=0;n<N;n++)
			array[n] = n;
		for(int n=0;n<N;n++){
			int rd = rand(n,N-1);
			int a = array[n];
			array[n] = array[rd];
			array[rd] = a;
		}
		return array;
	}
	int[] badPerm(){
		int[] array = new int[N];
		for(int n=0;n<N;n++)
			array[n] = n;
		for(int n=0;n<N;n++){
			int rd = rand(0,N-1);
			int a = array[n];
			array[n] = array[rd];
			array[rd] = a;
		}
		return array;
	}
	int rand(int min,int max){
		if(min==max)
			return min;
		Random rnd = new Random();
		int m = max - min;
		int r = rnd.nextInt();
		r = r%m+m;
		r = r%m;
		return min + r;
	}
	public static void main(String[] args) throws IOException {
		Permutation t = new Permutation();
		t.solve();
		//Tools.print(t.good);
		//Tools.print(t.bad);
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			in.readLine();
			int[] perm = Tools.intArray(in.readLine()," ");
			
			String output = "Case #"+caseN + ": "+result(perm);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String result(int[] perm){
		double error1 = 0;
		double error2 = 0;
		for(int i=0;i<N;i++){
		     double l1 = perm[i] - good[i];
		     double l2 = perm[i] - bad[i];
		     error1 += l1*l1;
		     error2 += l2*l2;
		}
		
		return error1 > error2?"BAD":"GOOD";
	}
}
