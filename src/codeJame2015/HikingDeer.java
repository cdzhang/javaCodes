package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import tools.TT;

public class HikingDeer {
	double err = 1e-7;
	int N,totalH;
	//long[][] hikers;
	LinkedList<Hiker> hikers2 = new LinkedList<Hiker>();
	public static void main(String[] args) throws IOException {
		HikingDeer t = new HikingDeer();
		//Tools.println(Long.MAX_VALUE);
		t.solve();
		long[][] a = new long[500000][3];
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			totalH = 0;
			hikers2.clear();
			N = Integer.parseInt(in.readLine());
			//hikers = new long[N][3];
			for(int n=0;n<N;n++){
				long[] g = TT.longArray(in.readLine(), " ");
				//hikers[n][0] = g[0];
				//hikers[n][1] = g[1];
				//hikers[n][2] = g[2];
				totalH += g[1];
				for(int i=0;i<g[1];i++){
					hikers2.add(new Hiker(g[0],g[2]+i));
				}
			}
			Collections.sort(hikers2);
			
			String output = "Case #"+caseN + ": "+minEcounterL();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int minEcounter2(){//the total number of encounters is less than 2
		if(totalH==1)
			return 0;
		//long Mf,Df;
		//long left,right;
		Hiker s = hikers2.get(1);
		Hiker f = hikers2.get(0);
		//Mf = f.M;
		//Df = f.D;
		/*if(N==1){
			Df = hikers[0][0];
			Ds = Df;
			Mf = hikers[0][2];
			Ms = Mf + 1;

		}else{
			Mf = hikers[0][2];
			Df = hikers[0][0];
			Ms = hikers[1][2];
			Ds = hikers[1][0];
			if(Mf == Ms)
				return 0;
			if(Mf > Ms){
				long M = Mf;
				Mf = Ms; Ms = M;
				long D = Df;
				Df = Ds; Ds = D;
			}
		}*/
		//left = s.mt;
		//right = 1L*Mf*(720-Df);
		if(s.encounterMe(f) >= 1)
			return 1;
		else
			return 0;
	}
	long minEcounterS(){//start from slowest
		long maxN = totalH - 1;
		LinkedList<Hiker> encountered = new LinkedList<Hiker>();//
		//slowest
		Hiker slowest = hikers2.getLast();
		long totalEncounter = 0;
		for(int i=0;i<totalH-1;i++){
			Hiker hi = hikers2.get(i);
			long xi = slowest.encounterMe(hi);
			if(xi>=1){
				totalEncounter += xi;
				encountered.add(hi);
			}
		}
		if(maxN > totalEncounter)
			maxN = totalEncounter;//number of encounter following slowest
		
		for(int i=1;i<maxN;i++){
			Hiker hi = hikers2.get(totalH-i-1);//ith slowest, 0th, 1th,..
			totalEncounter = 0;
			for(int j=0;j<encountered.size();j++){
				Hiker h = encountered.get(j);
				if(h==hi){
					encountered.remove(j);
					j--;
				}
				else{
					long ei = hi.encounterMe(h);
					if(ei>=1)
						totalEncounter += ei;
					else{
						encountered.remove(j);
						j--;
					}
				}
			}
			if(maxN > i + totalEncounter){
				maxN = i + totalEncounter;
			}
		}
		return maxN;
	}
	long minEcounterL(){
		if(totalH==1)
			return 0;
		//Set<Hiker> encountered = new HashSet<Hiker>();
		long maxN = totalH - 1;
		for(int i=1;i<totalH-1;i++){
			Hiker hi = hikers2.get(i);
			long encountered = 0;
			for(int j=0;j<i;j++){
				Hiker hj = hikers2.get(j);
				long xji = hi.encounterMe(hj);
				encountered+=xji;
			}
			if(encountered >= maxN)
				return maxN;
			if(totalH-i-1+encountered < maxN)
				maxN = totalH-i-1+encountered;
		}
		return maxN;
	}
}
class Hiker implements Comparable<Hiker>{
    long D;
    long M;
    //double time;//time first reach 360
    long mt;
    Hiker(long D1,long M1){
    	D = D1;
    	M = M1;
    	//time = (360-D)*M/360.0;
        mt = (360-D)*M;
    }
    long encounterMe(Hiker h){//times h can encounter me
    	long up = mt - h.mt;
    	if(up <=0)
    		return 0;
    	else
    		return (up/(360*h.M));
    }
	public int compareTo(Hiker h) {
		if(mt > h.mt)
			return 1;
		if(mt < h.mt)
			return -1;
		return 0;
	}
}
