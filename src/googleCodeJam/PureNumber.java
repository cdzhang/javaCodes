package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class PureNumber {
    int[] caseNs;
    long[][] f;
    int[][] C;//to store C(n,k), C(n,k)=c[n-1][k-1],c[n][k]=C(n+1,k+1)
    public static void main(String[] arg) throws IOException{
    	PureNumber p = new PureNumber();
    	p.solve();
    	//p.run();
    }
    public void run(){
    	int maxN = 50;
    	getC(maxN);
    	System.out.println("C_100003(10,9)="+C_100003(10,9)+",C2(10,9)="+C2(20,9));
    	System.out.println("C_100003(5,3)="+C_100003(5,3)+",C2(5,3)="+C2(5,3));
    	System.out.println("C_100003(15,9)="+C_100003(15,9)+",C2(15,9)="+C2(15,9));
    	System.out.println("C_100003(17,9)="+C_100003(17,9)+",C2(17,9)="+C2(17,9));
    	System.out.println("C_100003(18,9)="+C_100003(18,9)+",C2(18,9)="+C2(18,9));
    	System.out.println("C_100003(20,9)="+C_100003(20,9)+",C2(20,9)="+C2(20,9));
    	System.out.println("C_100003(20,9)="+C_100003(20,9)+",C2(20,9)="+C2(20,9));
    	System.out.println("C_100003(25,10)="+C_100003(25,10)+",C2(25,10)="+C2(25,10));
    	for(int n=1;n<=50;n++){
    		for(int k=0;k<=n;k++){
    			if(C_100003(n,k) != C2(n,k)){
    				System.out.println("n="+n+",k="+k);
    				System.out.println("C_100003(18,9)="+C_100003(18,9)+",C2(18,9)="+C2(18,9));
    				return;
    			}
    		}
    	}
    	/*for(int n=0;n<maxN;n++){
    		String line = "";
    		for(int k=0;k<maxN;k++){
    			if(k==0) line = line + C[n][k];
    			else line = line+","+ C[n][k];
    		}
    		System.out.println("n="+n+":"+line);
    	}*/
    	//int a = 100003 * 100003;
    	//System.out.println(a);
    }
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int totalCasesNumber = Integer.parseInt(in.readLine());//first line
		caseNs = new int[totalCasesNumber];
		for(int i=0;i<totalCasesNumber;i++){
			caseNs[i] = Integer.parseInt(in.readLine());
		}//store all the n in array caseNumbers
		int maxN = caseNs[0];
		for(int i=1;i<totalCasesNumber;i++){
			if(caseNs[i] > maxN)
				maxN = caseNs[i];
		}
		f = new long[maxN][maxN];
		System.out.println("maxN="+maxN);
		getC(maxN);
		for(int i=0;i<maxN;i++){
			for(int j=0;j<maxN;j++){
				f[i][j] = 0;
			}
		}//i<j
		System.out.println(maxN);
		long maxnn = 1;
		//f(k,n)=f[k-1][n-1]
		for(int j=0;j<maxN;j++){
			for(int i=0;i<maxN;i++){
				if(i>=j)
					continue;
				if(i==0){
					f[i][j] = 1;
				}else if(i==j-1){
					f[i][j] = 1;
				}else{
				    long fij = 0;//fij -> f[i][j] -> f(i+1,j+1)
					int lower_limit = Math.max(1,2*(i+1)-j-1);
					for(int ii=lower_limit;ii<=i;ii++){
						//fij += f[ii-1][i] * C_100003(j-i-1,i-ii);
						if(maxnn < j-i-1) maxnn = j-i-1;
						fij += f[ii-1][i] * C2(j-i-1,i-ii);
						if(fij > 100003) fij = fij % 100003;
					}
					f[i][j] = fij;
				}
			}
		}//i<j
		for(int c=1;c<=totalCasesNumber;c++){
			int sum = 0;
			int n = caseNs[c-1];
			for(int k=1;k<n;k++){
				sum += f[k-1][n-1];
			}
			String output = "Case #"+c+": " + sum % 100003;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		System.out.println("maxnn="+maxnn);
	}
	private int C3(int n,int k){
		return 0;
	}
	private int C2(int n,int k){
		if(n<k) return 0;
		if(k==0 || n==k) return 1;
		if(2*k > n) return C2(n,n-k);
		if(n>33){
			//System.out.println("C("+(n-1)+","+(k-1)+")");
			return ((C2(n-1,k-1)%100003) + (C2(n-1,k)%100003));
		}
		int n1 = 1;
		int r1 = 1;
		for(int i=1;i<=k;i++){
			r1 *= i;
			n1 *= (n-i+1);
		}
	    //System.out.println("n1="+n1+",r1="+r1);
		return (n1/r1);
				//% 100003;
	}
	private int C_100003(int n, int k){
		if (k==0) return 1;
		return C[n-1][k-1];
	}
	void getC(int maxN){
		C = new int[maxN][maxN];
		//C[n][k] = C(n+1,k+1)
		//C(n+1,k+1) = C(n,k) + C(n,k+1) -> C[n][k] = C[n-1][k-1]+C[n-1][k]
		//C(n+1,1) = n+1 -> C[n][0] = n+1
		//C(n+1,k+1) = C(n+1,n-k) -> C[n][k] = C[n][n-k-1]
		//C[n][n] = 1
		for(int n=0;n<maxN;n++){
			for(int k=0;k<maxN;k++){
				if(k>n){
					C[n][k] = 0;//not used
				}else if(k==0){
					C[n][k] = n+1;
				}else if(n==k){
					C[n][k] = 1;
				}else if(2*(k+1) > (n+1)){
					C[n][k] = C[n][n-k-1];
				}else{
					C[n][k] = C[n-1][k-1] + C[n-1][k];
					C[n][k] = C[n][k] % 100003;
				}
			}
		}
	}
}
