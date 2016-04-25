package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class Fairland2 {
	int N, D;
	Em[] ems;
	Em ceo;
	//LinkedList<Em> sorted = new LinkedList<Em>();
	public static void main(String[] args) throws IOException {
		Fairland2 t = new Fairland2();
		t.solve();
		TT.println(Integer.MAX_VALUE);
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());
		for(int caseN=1; caseN<=T;caseN++){
			int[] nd = TT.intArray(in.readLine(), " ");
			N = nd[0];
			D = nd[1];
			Em.D = D;
			long[] line1 = TT.longArray(in.readLine(), " ");
			long S0 = line1[0];
			long As = line1[1];
			long Cs = line1[2];
			long Rs = line1[3];
			long[] line2 = TT.longArray(in.readLine(), " ");
			long M0 = line2[0];
			long Am = line2[1];
			long Cm = line2[2];
			long Rm = line2[3];
			ems = new Em[N];
			ceo = new Em(0,S0);
			ems[0] = ceo;
			long Si = S0;
			long Mi = M0;
			for(int i=1;i<N;i++){
				Si = (Si*As+Cs)%Rs;
				Mi = (Mi*Am+Cm)%Rm;
				int mID = (int)(Mi%i);
				Em aE = new Em(i,Si,ems[mID]);
				ems[i] = aE;
			}
			getSpan(ceo);
			String output = "Case #"+caseN + ": ";//+maxRemaining();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void getSpan(Em E){
		E.getSpan();
		for(Em Es:E.sbor){
			Es.getSpan();
		}
	}
	int maxRemaining(){
		long S0 = ceo.salary;
		int count = 0;
		for(long x=Math.max(0,S0-D);x<=S0;x++){
			int countx = getCount(ceo,x);
			if(countx>count)
				count = countx;
		}
		return count;
	}
	int getCount(Em E,long X){
		if(E.span==null)
			return 0;
		else if(E.span[0]<=X && E.span[1]>=X){
			int count = 1;
			for(Em Es:E.sbor){
				count += getCount(Es,X);
			}
			return count;
		}else{
			return 0;
		}
	}
}
class Em implements Comparable<Em>{
	static int D;
	int id;
	long salary;
	Em manager = null;
	long[] span;
	LinkedList<Em> sbor = new LinkedList<Em>();
	Em(){}
	Em(int id, long salary){
		this.id = id;
		this.salary = salary;
	}
	Em(int id, long salary,Em manager){
		this.id = id;
		this.salary = salary;
		this.manager = manager;
		manager.sbor.add(this);
	}
	void getSpan(){
		if(id==0){
			span = new long[]{salary-D,salary};
			if(span[0]<0){
				span[0]=0;
			}
			return;
		}
		long[] mspan = manager.span;
		if(mspan==null){
			span = null;
			return;
		}
		long[] myspan=new long[]{salary-D,salary};
		if(mspan[0]>myspan[1]||myspan[0]>mspan[1]){
			span = null;
			return;
		}else{
			span = new long[]{Math.max(mspan[0], myspan[0]),Math.min(mspan[1], myspan[1])};
			if(span[0]<0){
				span[0]=0;
			}
		}
	}
	public int compareTo(Em E) {
		if(salary>E.salary)
			return 1;
		else if(salary < E.salary)
			return -1;
		return 0;
	}
}