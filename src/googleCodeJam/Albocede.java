package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import tools.TT;

public class Albocede {
	char[] S;
	long mod = 1000000007;
	Map<Long,Long> values = new HashMap<Long,Long>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Albocede a = new Albocede();
		a.solve();
		//a.test();
		//System.out.println(Integer.MAX_VALUE);
	}
	public void test(){
		for(int i=0;i<=50;i++){
			TT.println(i+":"+C(50,i));
		}
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String s = in.readLine();
			int N = s.length();
			S = new char[N];
			for(int i=0;i<N;i++){
				S[i] = s.charAt(i);
			}
			values.clear();
			String output = "Case #"+caseN + ": "+countN(0);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
    public long countN(int st){//substraing of S(st,end)
    	if(st >= S.length) return 0;
    	Long l = values.get(st);
    	if(l != null) return l;
        long count = 0;
    	LinkedList<Integer> aI = findA(st);
    	int naM = aMax(st);
    	//System.out.println("naM="+naM);
        int Na = aI.size();
        if(Na==0)
        	return 0;
        fora:for(int aLastI=0;aLastI<Na;aLastI++){
        	int naM1 = Math.min(aLastI+1, naM);
        	forna:for(int na=1;na<=naM1;na++){
        		LinkedList<Integer> bI = findB(na,aI.get(aLastI)+1);
        		int Nb = bI.size();
        		if(Nb==0) break forna;
        		int nbM = bMax(na,aI.get(aLastI)+1);
        		//System.out.println("nbM="+nbM);
        		for(int bLastI=0;bLastI<Nb;bLastI++){
        			int nbM1 = Math.min(bLastI+1,nbM);
        			for(int nb=1;nb<=nbM1;nb++){
        				//System.out.println("bLastI="+bLastI+",nb="+nb);
        				LinkedList<Integer> cI = findC(na,nb,bI.get(bLastI)+1);
        				int Nc = cI.size();
        				if(Nc<na) break;
        				for(int cLastI=na-1;cLastI<cI.size();cLastI++){
        					LinkedList<Integer> dI = findD(nb,cI.get(cLastI)+1);
        					int Nd = dI.size();
        					if(Nd<nb) break;
        					for(int dLastI=nb-1;dLastI<Nd;dLastI++){
            					long NA = C(aLastI,na-1);
            					long NB = C(bLastI,nb-1);
            					long NC = C(cLastI,na-1);
            					long ND = C(dLastI,nb-1);
            					long NN = NA*NB%mod;
            					NN = NN*NC%mod;
            					NN = NN*ND%mod;
            					long left = countN(dI.get(dLastI)+1)%mod;
            					NN = (NN + NN*left%mod)%mod;
            				//	Tools.println(aI.get(aLastI)+","+bI.get(bLastI)+","+cI.get(cLastI)+","
            					//		+dI.get(dLastI)+":"+NN);
            					
            					count = (count + NN) % mod;
        					}
        				}
        			}
        		}
        	}
        }
        values.put((long)st,count);
    	return count;
    }
    long C(int n,int k){
    	if(k==0) return 1;
    	if(2*k > n)
    		return C(n,n-k);
    	long up = 1;
    	long down = 1;
    	for(int i=1;i<=k;i++){
    		up *= (n-i+1);
    		down *= i;
    		if(up > 1e6){
    			long t = gcd(up,down);
    			up /= t;
    			down /= t;
    		}
    	}
    	return (up / down) % mod;
    }
    LinkedList<Integer> findA(int st){
    	LinkedList<Integer> aI = new LinkedList<Integer>();
    	if(st >= S.length) return aI;
    	for(int ia=st;ia<S.length;ia++){
    		if(S[ia]=='a')
    			aI.add(ia);
    	}
    	return aI;
    }
    int aMax(int st){
    	int na = 0;
    	int nc = 0;
    	int ia = st;
    	int ic = S.length - 1;
		if(S[ia] == 'a')
			na++;
		if(S[ic] == 'c')
			nc++;
    	while(ia < ic){
    		if(na <= nc){
    			ia++;
        		if(S[ia] == 'a')
        			na++;
    		}else{
    			ic--;
    			if(S[ic] == 'c')
    				nc++;
    		}
    	}
    	return nc;
    }
    int bMax(int na,int st){
    	int nc = 0;
    	int J = S.length - 1;
    	while(J > st){
    		if(S[J] == 'c' )
    			nc++;
    		if(nc == na)
    			break;
    		J--;
    	}
    	int ib = st;
    	int id = S.length - 1;
    	int nb = 0;
    	int nd = 0;
		if(S[ib] == 'b')
			nb++;
		if(S[id] == 'd')
			nd++;
    	while(ib < id && ib < J){
    		if(nb <= nd){
    			ib++;
        		if(S[ib] == 'b')
        			nb++;
    		}else{
    			id--;
    			if(S[id] == 'd')
    				nd++;
    		}
    	}
    	return nd;
    }
    LinkedList<Integer> findB(int na,int st){
    	LinkedList<Integer> bI = new LinkedList<Integer>();
    	if(st >= S.length) return bI;
    	int J = S.length - 1;
    	int nc = 0;
    	while(J > st){
    		if(S[J] == 'c' )
    			nc++;
    		if(nc == na){
    			break;
    		}
    		J--;
    	}
    	int Ib = st;
    	while(Ib<J){
			if(S[Ib] == 'b'){
				bI.add(Ib);
			}
			Ib++;
    	}
    	return bI;
    }
    LinkedList<Integer> findC(int na,int nb,int st){
    	LinkedList<Integer> cI = new LinkedList<Integer>();
    	if(st >= S.length) return cI;
    	int J = S.length - 1;
    	int nd = 0;//count d
    	while(J > st){
    		if(S[J] == 'd' )
    			nd++;
    		if(nd == nb){
    			break;
    		}
    		J--;
    	}
    	if(J <= st+na-1){
    		cI.clear();
    		return cI;
    	}
    	int nc = 0;
    	for(int ic=st;ic<J;ic++){
    		if(S[ic] == 'c'){
    			cI.add(ic);
    			nc ++;
    		}
    	}
    	if(nc >= na){
    		return cI;
    	}else{
    		cI.clear();
    		return cI;
    	}
    }

    LinkedList<Integer> findD(int nb,int st){
    	LinkedList<Integer> dI = new LinkedList<Integer>();
    	if(st >= S.length) return dI;
    	for(int id=st;id<S.length;id++){
    		if(S[id]=='d')
    			dI.add(id);
    	}
    	if(dI.size()<nb)
    		dI.clear();
    	return dI;
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
