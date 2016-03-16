package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Neighbors {
	int N,R,C;
	public static void main(String[] args) throws IOException {
		Neighbors t = new Neighbors();
		t.solve();
	}
	void test(){
		R=3;
		C=3;
		int[] v = {4};
		Tools.println(getReduce(v));
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] rcn = Tools.intArray(in.readLine(), " ");
			R = rcn[0];
			C = rcn[1];
			N = rcn[2];
			String output = "Case #"+caseN + ": " +countNoisy();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	int countNoisy(){
		int total = R*C;
		int tl = (total+1)/2;
		int max = 2*R*C-R-C;
		if(N<=tl){
			return 0;
		}
		int Nv = total - N;
		if(Nv==0){
			return max;
		}
		if(R>=3&&C>=3){
			if(((R-2)*(C-2)+1)/2 >= Nv){
				return max - 4*Nv;
			}
		}
		int[] vacancy = new int[Nv];
		for(int i=0;i<Nv;i++){
			vacancy[i] = i;
		}
		int reduce = -1;
		while(vacancy[0] <= tl){
			int re = getReduce(vacancy);
			if(reduce < re)
				reduce = re;	
			vacancy = next(vacancy);
			if(vacancy==null)
				break;
		}
		return max - reduce;
	}
	int getReduce(int[] vacancy){
		boolean[][] cut = new boolean[R][C];
		int reduce = 0;
		for(int i=0;i<vacancy.length;i++){
			int n = vacancy[i];
			int[] axis = toAxis(n);
			int r = axis[0];
			int c = axis[1];
			cut[r][c] = true;
			int minus = 4;
			if(r-1<0||cut[r-1][c]){
				minus--;
			}
			if(r+1>=R||cut[r+1][c]){
				minus--;
			}
			if(c-1<0||cut[r][c-1]){
				minus--;
			}
			if(c+1>=C||cut[r][c+1]){
				minus--;
			}
			reduce += minus;
		}
		return reduce;
	}
	int[] next(int[] vacancy){
		int total = R*C;
		int[] v = vacancy;
	    int Nr = vacancy.length;
	    vacancy[Nr-1] = vacancy[Nr-1]+1;
	    boolean found = true;
	    while(found){
	    	found = false;
	    	for(int i=Nr-1;i>=0;i--){
	    		if(v[i] > total-Nr+i){
	    			if(i==0)
	    				return null;
	    			v[i-1] = v[i-1]+1;
	    			v[i] = 0;
	    			found = true;
	    		}
	    	}
	    }
	    for(int i=1;i<Nr;i++){
	    	if(v[i]==0){
	    		v[i] = v[i-1]+1;
	    	}
	    }
	    return v;
	}
	int[] toAxis(int n){
		int r = n / C;
		int c = n % C;
		return new int[]{r,c};
	}
	int toNumber(int[] rc){
		int r = rc[0];
		int c = rc[1];
		return r*C + c;
	}
}
