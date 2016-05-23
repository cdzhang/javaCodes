package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Match {
	char[] CO,JO;
	long LC,LJ;
	public static void main(String[] args) throws IOException {
		new Match().solve();
		char a = '3';
		char b = ++a;
		TT.println(b);
		//TT.println(Long.parseLong("000"));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String[] s = in.readLine().split(" ");
			String s1 =  s[0];
			String s2 =  s[1];
			char[] C = s1.toCharArray();
			char[] J = s2.toCharArray();
			int N = C.length;
			CO = new char[N];
			JO = new char[N];
			for(int n=0;n<N;n++){
				CO[n] = C[n];
				JO[n] = J[n];
			}
			LC = -1;
			LJ = -1;
			compare(C,J,0,0);
			String sc = "" + LC;
			String sj = "" + LJ;
			while(sc.length()<N)
				sc = "0" + sc;
			while(sj.length()<N)
				sj = "0" + sj;
			String output = "Case #"+caseN + ": "+sc +" "+sj;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	void compare(char[] C,char[] J,int cl,int i){
		int N = C.length;
		if(i>=N){
			long lc = getL(C);
			long lj = getL(J);
			if(LC<0 && LJ<0){
				LC = lc;
				LJ = lj;
			}else{
				if(Math.abs(LC-LJ)>Math.abs(lc-lj)){
					LC = lc;
					LJ = lj;
				}else if(Math.abs(LC-LJ)==Math.abs(lc-lj)){
					if(lc<LC){
						LC = lc;
						LJ = lj;
					}else if(LC==lc && lj < LJ){
						LC = lc;
						LJ = lj;
					}
				}
			}
			return;
		}
		if(cl==0){
			char cn = C[i];
			char jn = J[i];
			if(cn=='?'&&jn=='?'){
				C[i] = '0';J[i] = '0';
				compare(C,J,0,i+1);
				for(int j=i;j<N;j++){
					C[j] = CO[j];
					J[j] = JO[j];
				}
				C[i] = '0';J[i] = '1';
				compare(C,J,-1,i+1);
				for(int j=i;j<N;j++){
					C[j] = CO[j];
					J[j] = JO[j];
				}
				C[i] = '1';J[i] = '0';
				compare(C,J,1,i+1);
				for(int j=i;j<N;j++){
					C[j] = CO[j];
					J[j] = JO[j];
				}
			}else if(cn=='?' && jn!='?'){
				if(jn!='0'){
					C[i] = --jn;
					compare(C,J,-1,i+1);
					jn++;
					for(int j=i;j<N;j++){
						C[j] = CO[j];
						J[j] = JO[j];
					}
				}
				C[i] = jn;
				compare(C,J,0,i+1);
				for(int j=i;j<N;j++){
					C[j] = CO[j];
					J[j] = JO[j];
				}
				if(jn!='9'){
					C[i] = ++jn;
					compare(C,J,1,i+1);
					jn--;
					for(int j=i;j<N;j++){
						C[j] = CO[j];
						J[j] = JO[j];
					}
				}
				
			}else if(cn!='?' && jn=='?'){
				if(cn!='0'){
					J[i] = --cn;
					compare(C,J,1,i+1);
					cn++;
					for(int j=i;j<N;j++){
						C[j] = CO[j];
						J[j] = JO[j];
					}
				}
				J[i] = cn;
				compare(C,J,0,i+1);
				for(int j=i;j<N;j++){
					C[j] = CO[j];
					J[j] = JO[j];
				}
				if(cn!='9'){
					J[i] = ++cn;
					compare(C,J,-1,i+1);
					cn--;
					for(int j=i;j<N;j++){
						C[j] = CO[j];
						J[j] = JO[j];
					}
				}
			}else{
				if(cn - jn > 0){
					compare(C,J,1,i+1);
				}
				else if(cn - jn < 0){
					 compare(C,J,-1,i+1);
				}
				else{
					compare(C,J,0,i+1);
				}
				
			}
		}else if(cl < 0){
			for(int j=i;j<N;j++){
				if(C[j]=='?')
					C[j] = '9';
				if(J[j]=='?')
					J[j] = '0';
			}
			compare(C,J,cl,N);
		}else{
			for(int j=i;j<N;j++){
				if(C[j]=='?')
					C[j] = '0';
				if(J[j]=='?')
					J[j] = '9';
			}
			compare(C,J,cl,N);
		}
	}
	long getL(char[] L){
		String s = "";
		for(int l=0;l<L.length;l++)
			s += L[l];
		long l = 0;
		try{
			l = Long.parseLong(s);
			return l;
		}catch(NumberFormatException ex){
			TT.println(s);
			return 0;
		}
	}
	
}
