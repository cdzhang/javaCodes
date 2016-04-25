package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import tools.TT;

public class AlbocedeLarge {
	String S;
	static final long mod = 1000000007;
							
	Map<ad,Long> oneCount = new HashMap<ad,Long>();
	Map<Integer,Long> all = new HashMap<Integer,Long>();
	public static void main(String[] args) throws IOException {
		AlbocedeLarge a = new AlbocedeLarge();
		a.solve();
	}
	public void test(){
		for(int i=0;i<=50;i++){
			TT.println(i+":"+TT.C(50,i));
		}
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			S = in.readLine();
			oneCount.clear();
			all.clear();
			int N = S.length();
			String output = "Case #"+caseN + ": "+getAllCount(0);
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long get(){
		long[][][][] dp = new long[501][251][251][4];
		long[][][][] dp1 = new long[501][251][251][4];
		long[][] dpa = new long[501][251];
		long[][] dpa1 = new long[501][251];
		//dpa[i][n1][0][0] is the number of ways of finding(validnDNA + n1 'a') from 1 to i
		//ending with last s[i] = 'a';
		//dpa1[i][n1][0], is the number of ways of finding(valid DNA + n1'a') from 1 to i
		//not need to end with last i
		char[] sc = S.toCharArray();
		int N = sc.length;
		for(int i=1;i<=N;i++){
			char ci = sc[i-1];
			if(ci =='a'){
				dpa[i][1] = 1;
				dpa1[i][1] = 1;
				for(int n1 = 1;n1<251;n1++){
					if(ci=='a'){
						dpa[i][1] = 0;
						dpa[i][n1+1] = dpa1[i][n1];
					}
					for(int n2 = 0;n2<251;n2++){
						
					}
				}
			}
		}
		return 0;
	}
	long add(long a,long b){
		return (a%mod + b%mod)%mod;
	}
	long getAllCount(int aI){
		if(aI>=S.length() || aI<0)
			return 0;
		Long count = all.get(aI);
		if(count!=null)
			return count;
		count = 0L;
		int dI = findNext(aI+1,'d');
		long count1 = getOneCount(aI,dI);
		while(count1==0 && dI >= 0){
			dI = findNext(dI+1,'d');
			count1 = getOneCount(aI,dI);
		}
		while(dI >= 0){
			int aI1 = findNext(dI+1,'a');
			long all1 = getAllCount(aI1);
			count += count1 + count1*all1%mod;
			count = count % mod;
			dI = findNext(dI+1,'d');
			if(dI>=0)
				count1 = getOneCount(aI,dI);
			else
				break;
		}
		all.put(aI,count);
		return count;
	}
	/*long getFirstCount(int dI){
		Long count = first.get(dI);
		if(count!=null)
			return count;
		count = 0L;
		LinkedList<Integer> aIs = find('a',0,dI);
		for(int aI:aIs){
			count += getOneCount(aI,dI);
		}
		first.put(dI, count);
		return count;
	}*/
	long getOneCount(int aI, int dI){
		ad ad1 = new ad(aI,dI);
		Long count = oneCount.get(ad1);
		if(count!=null)
			return count;
		count = 0L;
		LinkedList<Integer> aIs = find('a',aI,dI);
		int sa = aIs.size();
		foral:for(int aLastI=0;aLastI < sa;aLastI++){
			LinkedList<Integer> bIs = find('b',aIs.get(aLastI)+1,dI);
			int sb = bIs.size();
			if(sb==0) break foral;
		   forbl: for(int bLastI=0;bLastI<sb;bLastI++){
		    	LinkedList<Integer> cIs = find('c',bIs.get(bLastI)+1,dI);
		    	int sc = cIs.size();
		    	if(sc==0) break forbl;
				forna:for(int na=1;na<=aLastI+1;na++){
					if(na > sc)
						break forna;
					for(int cLastI=na-1;cLastI<sc;cLastI++){
						LinkedList<Integer> dIs = find('d',cIs.get(cLastI)+1,dI);
						int sd = dIs.size();
						if(sd==0) break;
						int nDM = Math.min(bLastI+1, sd);
						for(int nb=1;nb<=nDM;nb++){
							long NA = C(aLastI,na-1);
							long NB = C(bLastI,nb-1);
							long NC = C(cLastI,na-1);
							long ND = C(sd-1,nb-1);
							long NN = (((NA*NB)%mod)*NC%mod)*ND%mod;
							count+=NN;
						}
						
					}
				}
		    }
		}
		oneCount.put(ad1, count);
		return count;
	}
	LinkedList<Integer> find(char c,int left,int right){//left inclusive, right exclusive
		LinkedList<Integer> ls = new LinkedList<Integer>();
		for(int i=left;i<=right;i++){
			if(S.charAt(i)==c)
				ls.add(i);
		}
		return ls;
	}
	int findNext(int st,char c){
		int N = S.length();
		if(st >= N)
			return -1;
		for(int i=st;i<N;i++){
			if(S.charAt(i)==c)
				return i;
		}
		return -1;
	}
	
	public long C(int n,int k){
    	if(k<=0) return 1;
    	if(2*k > n)
    		return C(n,n-k);
    	long up = 1;
    	long down = 1;
    	for(int i=1;i<=k;i++){
    		up *= (n-i+1);
    		down *= i;
    		if(up > 1e6){
    			long t = TT.gcd(up,down);
    			up /= t;
    			down /= t;
    			up = up%mod;
    			down = down %mod;
    		}
    	}
    	return (up / down);
	}
}
class ad{
	int aI;
	int dI;
	ad(int aI, int dI){
		this.aI = aI;
		this.dI = dI;
	}
	public int hashCode(){
		return aI*10 + dI;
	}
	public boolean equals(Object o){
		if(this==o)
			return true;
		ad o1 = (ad) o;
		if(o1.aI==aI && o1.dI==dI)
			return true;
		return false;
	}
}
