package codejam2016;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Match2 {
	char[] CO,JO;
	long LC,LJ;
	public static void main(String[] args) throws IOException {
		new Match().solve();
		char a = '3';
		char b = (char) (a-1);
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
	void compare(char[] C,char[] J,int cl, int i){
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
			char[] C1 = C.clone(),J1=J.clone();
			if(C[i]=='?'&&J[i]=='?'){
				refresh(C1,J1,C,J);
				C1[i] = 0;
				J1[i] = 0;
				compare(C1,J1,0,i+1);
				refresh(C1,J1,C,J);
				C1[i] = 0;
				J1[i] = 1;
				compare(C1,J1,-1,i+1);
				refresh(C1,J1,C,J);
				C1[i] = 1;
				J1[i] = 0;
				compare(C1,J1,1,i+1);
			}else if(C[i]=='?'&&J[i]!='?'){
				refresh(C1,J1,C,J);
				C1[i] = J1[i];
				compare(C1,J1,0,i+1);
				if(J[i] != '9'){
					refresh(C1,J1,C,J);
					C1[i] = (char)(J1[i]+1);
					compare(C1,J1,1,i+1);	
				}
				if(J[i]!='0'){
					refresh(C1,J1,C,J);
					C1[i] = (char)(J1[i]-1);
					compare(C1,J1,-1,i+1);	
				}
			}
			else if(C[i]!='?'&&J[i]=='?'){
				refresh(C1,J1,C,J);
				J1[i] = C1[i];
				compare(C1,J1,0,i+1);
				if(J[i] != '9'){
					refresh(C1,J1,C,J);
					J1[i] = (char)(C1[i]+1);
					compare(C1,J1,-1,i+1);	
				}
				if(C[i]!='0'){
					refresh(C1,J1,C,J);
					J1[i] = (char)(C1[i]-1);
					compare(C1,J1,1,i+1);	
				}
			}else{
				if(C[i]-J[i]>0)
					compare(C,J,1,i+1);
				if(C[i]-J[i]<0)
					compare(C,J,-1,i+1);
				if(C[i]==J[i])
					compare(C,J,0,i+1);
			}
		}else if(cl < 0){
			char[] C1 = C.clone();
			char[] J1 = J.clone();
			for(int j=i;j<N;j++){
				if(C1[j]=='?')
					C1[j] = '9';
				if(J1[j]=='?')
					J1[j] = '0';
			}
			compare(C1,J1,cl,N);
		}else{
			char[] C1 = C.clone();
			char[] J1 = J.clone();
			for(int j=i;j<N;j++){
				if(C1[j]=='?')
					C1[j] = '0';
				if(J1[j]=='?')
					J1[j] = '9';
			}
			compare(C1,J1,cl,N);
		}
		
	}
	void refresh(char[] C1,char[] J1,char[] C,char[] J){
		C1 = C.clone();
		J1 = J.clone();
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
