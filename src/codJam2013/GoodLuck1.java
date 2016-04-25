package codJam2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import tools.TT;

public class GoodLuck1 {
	int R,N,M,K;
	double P,P2;
	Map<String,Double> gp = new HashMap<String,Double>();
	Map<String,Map<Long,Double>> np = new HashMap<String,Map<Long,Double>>();
	public static void main(String[] args) throws IOException {
		GoodLuck1 t = new GoodLuck1();
		t.solve();
	}
	void test(){
		R = 2;
		N = 3;
		M = 4;
		K = 4;
		P = 1.0/pow(M-1,N);
		P2 = 1.0/(1<<N);
		String s = "244";
		Map<Long,Double> a = getMultiplication(s);
		TT.println(a.size());
		for(Long l:a.keySet()){
			Double d = a.get(l);
			TT.print(l+":"+d+" ");
		}
		TT.println();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] rnmk = TT.intArray(in.readLine(), " ");
			R = rnmk[0];
			N = rnmk[1];
			M = rnmk[2];
			K = rnmk[3];
			P = 1.0/pow(M-1,N);
			P2 = 1.0/(1<<N);
			gp.clear();
			np.clear();
			setGP("",2,N);
			setNP();
			//printGNP();
			String output = "Case #"+caseN + ":";
			System.out.println(output);
			out.println(output);
			for(int r=1;r<=R;r++){
				long[] iR = TT.longArray(in.readLine(), " ");
				String s = getInstance(iR);
				TT.println(s);
				out.println(s);
			}
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	void printGNP(){
		for(String s:gp.keySet()){
			double gps = gp.get(s);
			Map<Long,Double> nps = np.get(s);
			TT.print(s+","+gps+":");
			for(Long l:nps.keySet()){
				double d = nps.get(l);
				TT.print(" " + l+","+d);
			}
			TT.println();
		}
	}
	String getInstance(long[] pk){
		String ms = "";
		double mp = -1.0;
		for(String s:np.keySet()){
			Map<Long,Double> npi = np.get(s);
			double kp = 1.0;
			for(long l:pk){
				Double pl = npi.get(l);
				if(pl==null){
					kp = 0;
					break;
				}
				kp = kp*pl;
			}
			double mp1 = gp.get(s)*kp;
			if(mp < mp1){
				mp = mp1;
				ms = s;
			}
		}
		return ms;
	}
	void setGP(String s1,int m,int n){
		String s = s1;
		if(m==M){
			for(int i=1;i<=n;i++)
				s = s+m;
			gp.put(s, probability(s));
		}else if(n==0){
			gp.put(s, probability(s));
		}else{
			for(int i=0;i<=n;i++){
				String si = s1;
				for(int j=1;j<=i;j++){
					si = si + m;
				}
				setGP(si,m+1,n-i);
			}
		}
	}
	void setNP(){
		for(String s:gp.keySet()){
			np.put(s,getMultiplication(s));
		}
	}
	int[] getCards(String s){
		int[] cards = new int[M+1];
		for(int i=0;i<s.length();i++){
			char ci = s.charAt(i);
			int ii = ci - '0';
			cards[ii] = cards[ii] + 1;
		}
		return cards;
	}
	double probability(String s){
		return probability(getCards(s));
	}
	double probability(int[] cards){//cards[2],cards[3],...M
		long count = 1;
		int n = N;
		for(int m=2;m<=M;m++){
			int mm = cards[m];
			count *= C(n,mm);
			n = n - mm;
		}
		return P*count;
	}
	Map<Long,Double> getMultiplication(String s){
		int[] cards = getCards(s);
		Map<Long,Double> mulmap = new HashMap<Long,Double>();
		int[] select = new int[M+1];
		getMul(cards,select,2,mulmap);
		return mulmap;
	}
	void getMul(int[] cards,int[] select,int m,Map<Long,Double> map){
		if(m==M){
			int cm = cards[m];
			for(int i=0;i<=cm;i++){
				select[m] = i;
				double p = getProb(cards,select);
				long l = getMul(select);
				Double d = map.get(l);
				d = d==null?p:d+p;
				map.put(l,d);
			}
		}else{
			int cm = cards[m];
			for(int i=0;i<=cm;i++){
				select[m] = i;
				getMul(cards,select,m+1,map);
			}
		}
	}
	double getProb(int[] cards,int[] select){
		long c = 1;
		for(int m=2;m<=M;m++){
			c *= C(cards[m],select[m]);
		}
		
		return c*P2;
	}
	long getMul(int[] select){
		long l = 1;
		for(int m=2;m<=M;m++){
			l *= pow(m,select[m]);
		}
		return l;
	}
	long C(int L,int L1){
		if(L1==0)
			return 1;
		long up = 1;
		long down = 1;
		for(int k=1;k<=L1;k++){
			up *= (L-k+1);
			down *= k;
		}
		return up/down;
	}
	long pow(long a, long b){
		long re = 1;
		for(int i=1;i<=b;i++)
			re *= a;
		return re;
	}
	
}
