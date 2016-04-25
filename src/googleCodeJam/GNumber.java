package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import tools.TT;

public class GNumber {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GNumber gn = new GNumber();
		gn.solve();
	}
	void test(){
		LinkedList<Long> prime = primeFactors(36300);
		for(long l:prime){
			System.out.print(l+",");
		}
		System.out.println();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			long N = Long.parseLong(in.readLine());
			String output = "Case #" + caseN +": ";
			if(canWin(N)){
				output = output + "Laurence";
			}else
				output = output + "Seymour";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	public boolean canWin(long N){
		//System.out.println("N="+N);
		if(isGNumber(N))
			return false;
		LinkedList<Long> factors = primeFactors(N);

		//System.out.println("length="+fractors.size());
		if(factors.size() == 0){
			return true;//N is a prime number
		}else{
			for(long l:factors){
				long Nl = nextNumber(N,l);
				if(!canWin(Nl)){
					return true;
				}
			}
		}
		return false;
	}
	static long nextNumber(long N,long factor){
		while(N % factor == 0)
			N = N / factor;
		return N;
	}
	private boolean isPrime(long n) {
	    //check if n is a multiple of 2
		if(n==2) return true;
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	private int getDigit(long N){
		int i = (int) (N % 10);
		N = N / 10;
		while(N!=0){
			i += N % 10;
			N /= 10;
		}
		return i;
	}
	private boolean isGNumber(long N){
		int d = getDigit(N);
		if(d==1) return true;
		if(isPrime(d))
			return true;
		return false;
	}
	public static  LinkedList<Long> primeFactors(long numbers) {
		////this code is adapted from
		//"http://www.vogella.com/tutorials/JavaAlgorithmsPrimeFactorization/article.html"
	    long n = numbers;
	    LinkedList<Long> factors = new LinkedList<Long>();
	    if(n%2==0){
	    	factors.add((long) 2);
	    	n = nextNumber(n,2);
	    }
	    for (long i = 3; i * i <= n; i=i+2) {
	      while (n % i == 0) {
	        factors.add((long) i);
	        n = nextNumber(n,i);
	       // System.out.println("nextNumber(n,i)=("+n+","+i+")");
	      }
	    }
	    if (n > 1) {
	      factors.add(n);
	    }
	    return factors;
	  }
	public  long primeFactors2(long N){
		long x = 2;
		long y = 2;
		long d = 1;
		while(d==1){
			x = g(x,N);
			y = g(g(y,N),N);
			d = gcd(Math.abs(x-y),N);
		}
		if(d == N)
			return -1;
		return d;
	}
	LinkedList<Long> primeFactorsQ(long N){
		LinkedList<Long> factors = new LinkedList<Long>();
		long n = N;
		long d = 0;
		while((d=primeFactors2(n))!=-1){
			factors.add(d);
			n = nextNumber(n,d);
		}
		//System.out.println("n="+n);
		LinkedList<Long> factors2 = primeFactors(n);
		factors.addAll(factors2);
		return factors;
	}
    long g(long x, long N){
		return (x*x+1)%N;
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
