package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Lottery {
	long[] power2;
	long A, B,K;
	Lottery(){
		power2 = Tools.power2(30);
	}
	public static void main(String[] args) throws IOException {
		Lottery t = new Lottery();
		t.test();
	}
	void test(){
		String a = "01";
		String b = "10";
		long a1 = Tools.convert(a);
		long b1 = Tools.convert(b);
		Tools.println(countZero(a1,b1,2,false,false));
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			
			
			
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	
	long count(long A,long B,long K){
		if(A<=K || B<=K){
			return A*B;
		}else{
			
		}
		
		
		return 0;
	}
	long countK(long A1, long B1, long K1,int b){
		int ba = getBit(A1,b);
		int bb = getBit(B1,b);
		int bk = getBit(K1,b);
		long A2 = A1 & (power2[b]-1);
		long B2 = A2 & (power2[b]-1);
		long K2 = K1 & (power2[b]-1);
		if(bk==1){
			if(ba==0 || bb==0)
				return (A1+1)*(B1+1);
			else{
				return countK(A2,B2,K2,b-1)+(A2+1)*(B1+1)+(A1+1)*(B2+1);
			}
		}else{
			long c00,c01,c10;
			c00 = countK(A2,B2,K1,b-1);
			c10 = 0;
			if(ba == 1){
				c10 = countK(A1,B2,K1,b-1);
			}
			c01=0;
			if(bb == 1){
				c01 = countK(A2,B1,K1,b-1);
			}
			return c00 + c10 + c01;
		}
	}
	long countZero(long x1, long x2, int b, boolean c1, boolean c2){
		if(b==-1)
			return 1;
		int b1 = getBit(x1,b);
		int b2 = getBit(x2,b);
		if(b1==0 && b2==0){
			if(c1 && c2){
				return 3 * countZero(x1,x2,b-1,c1,c2);
			}else if(c1 && !c2){
				return 2 * countZero(x1,x2,b-1,c1,c2);
			}else if(!c1 && c2){
				return 2 * countZero(x1,x2,b-1,c1,c2);
			}else{
				return countZero(x1,x2,b-1,c1,c2);
			}
		}else if(b1==1 && b2==0){
			if(c1 && c2){
				return 3 * countZero(x1,x2,b-1,c1,c2);
			}else if(c1 && !c2){
				return 2 * countZero(x1,x2,b-1,c1,c2);
			}else if(!c1 && c2){
				return 2 * countZero(x1,x2,b-1,true,c2) + countZero(x1,x2,b-1,c1,c2);
			}else{
				return countZero(x1,x2,b-1,true,c2) + countZero(x1,x2,b-1,c1,c2);
			}
			
		}else if(b1==0 && b2==1){
			if(c1 && c2){
				return 3 * countZero(x1,x2,b-1,c1,c2);
			}else if(c1 && !c2){
				return countZero(x1,x2,b-1,c1,c2) + 2*countZero(x1,x2,b-1,c1,true);
			}else if(!c1 && c2){
				return 2*countZero(x1,x2,b-1,c1,c2);
			}else{
				return countZero(x1,x2,b-1,c1,c2) + countZero(x1,x2,b-1,c1,true);
			}
		}else{
			if(c1 && c2){
				return countZero(x1,x2,b-1,c1,c2)*3;
			}else if(c1 && !c2){
				return countZero(x1,x2,b-1,c1,c2) + countZero(x1,x2,b-1,c1,true)*2;
			}else if(!c1 && c2){
				return countZero(x1,x2,b-1,c1,c2) + countZero(x1,x2,b-1,true,c2)*2;
			}else{
				return countZero(x1,x2,b-1,c1,true) + countZero(x1,x2,b-1,true,c2)
						+ countZero(x1,x2,b-1,true,true);
			}
		}
	}
	int getBit(long x1, int b){
		long x = x1 & power2[b];
		if(x==0)
			return 0;
		else
			return 1;
	}
}
