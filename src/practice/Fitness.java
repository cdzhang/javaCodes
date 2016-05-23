package practice;

import tools.TT;

public class Fitness {
	public static void main(String[] args){
		TT.println(new Fitness().fitness(2, 1));
		TT.println(log2(2));
		double[] G = {0.001,0.01,0.1,1};
		double[] x1 = {1.926, 2.5,3.97,15.708};
		double[] x2 = {1.7,15.53,15.27,18.53};
		double[] x3 = {7.67,12.9,9.33,35.76};
		for(int i=0;i<4;i++){
			double y1 = log2(x1[i])/log2(x2[i]);
			double y2 = 1;
			double y3 = log2(x3[i])/log2(x2[i]);
			TT.println(G[i] + " " + y1 + " "+y2 + " " + y3);
		}
	}
	double fitness(int Nt, double t){
		if(t==0)
			return 0;
		else
			return 1 / t * Math.log10(Nt*1.0)/Math.log10(2);
	}
	static double log2(double x){
		return Math.log10(x)/Math.log10(2);
	}
}
