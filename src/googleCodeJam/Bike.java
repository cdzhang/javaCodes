package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import tools.TT;

public class Bike {
    int Np,Ne,Nt;
    long[][] PQ;
    int[] Gp,Ge,Gt;
    int M;
    Set<Pair> GT ;
	public static void main(String[] args) throws IOException {
		Bike b = new Bike();
		b.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String aline = in.readLine();
			while(aline.length() == 0)
				 aline = in.readLine();;
			String[] N3 = aline.split(" ");
		    Np = Integer.parseInt(N3[0]);
		    Ne = Integer.parseInt(N3[1]);
			Nt = Integer.parseInt(N3[2]);
			Gp = new int[Np];
			Ge = new int[Ne];
			Gt = new int[Nt];
			String[] sGp = in.readLine().split(" ");
			for(int i=0;i<Np;i++){
				Gp[i] = Integer.parseInt(sGp[i]);
			}
			String[] sGe = in.readLine().split(" ");
			for(int i=0;i<Ne;i++){
				Ge[i] = Integer.parseInt(sGe[i]);
			}
			String[] sGt = in.readLine().split(" ");
			for(int i=0;i<Nt;i++){
				Gt[i] = Integer.parseInt(sGt[i]);
			}
			//Arrays.sort(Gp);
			//Arrays.sort(Ge);
			//Arrays.sort(Gt);
			M = Integer.parseInt(in.readLine());
			PQ = new long[M][2];
			String output = "Case #" + caseN + ":\n";
			GT = new HashSet<Pair>();
			for(int ie1=Ne-1;ie1>=0;ie1--){
				for(int ie2=0;ie2<Ne;ie2++){
					if(ie1==ie2) continue;
					long ge1 = Ge[ie1];
					long ge2 = Ge[ie2];
					long g = gcd(ge1,ge2);
					//System.out.println("before:ge1="+ge1+",ge2="+ge2);
					ge1 /= g;
					ge2 /= g;

					//System.out.println("after:ge1="+ge1+",ge2="+ge2);
					GT.add(new Pair(ge1,ge2));
				}
			}
			//System.out.println(GT);
			for(int m=0;m<M;m++){
				String[] pqs = in.readLine().split(" ");
				long P = Integer.parseInt(pqs[0]);
				long Q = Integer.parseInt(pqs[1]);
				PQ[m][0] = P;
				PQ[m][1] = Q;
			}
			boolean[] pq = getRatio();
			for(int m=0;m<M;m++){
				if(pq[m]){
					output = output + "Yes";
				}else{
					output = output + "No";
				}
				if(m!=M-1){
					output = output+"\n";
				}
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	public boolean[] getRatio(){
		boolean[] pq = new boolean[M];
		for(int ip=0;ip<Np;ip++){
			long gp = Gp[ip];
			for(int it=0;it<Nt;it++){
				long gt = Gt[it];
				for(int m=0;m<M;m++){
					if(pq[m]) continue;
					long P = PQ[m][0];
					long Q = PQ[m][1];
					long r1 = gp*Q;
					long r2 = gt*P;
					long g = gcd(r1,r2);
					r1 /= g;
					r2 /= g;
					Pair p = new Pair(r2,r1);
					if(GT.contains(p)){
						pq[m] = true;
					    continue;
					}
				}
			}
		}
		return pq;
	}
	long gcd(long a,long b){
		while(b!=0){
			long t = a % b;
			a = b;
			b = t;
		}
		return a;
	}
}

class Pair{
	long i;
	long j;
	Pair(){}
	Pair(long i1,long j1){
		i = i1;
		j = j1;
	}
	public boolean equals(Object p){
		Pair p1 = (Pair) p;
		return (i==p1.i) && (j==p1.j);
	}
	public int hashCode(){
		return (int) i;
	}
	public String toString(){
		return "["+i+"="+j+"]";
	}
}