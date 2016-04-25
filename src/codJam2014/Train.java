package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Template;
import tools.TT;

public class Train {
	long mod = 1000000007;
	LinkedList<Car> cars = new LinkedList<Car>();
	LinkedList<Car> cars2 = new LinkedList<Car>();
	int N;
	public static void main(String[] args) throws IOException {
		Train t = new Train();
		String s = "aaabbbbccccdefff";
		TT.println(Car.deRepetition(s));
		long x = t.mod*t.mod;
		TT.println(x);
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			cars.clear();
			cars2.clear();
			N = Integer.parseInt(in.readLine());
			String[] line = in.readLine().split(" ");
			N = line.length;
			boolean is0 = false;
			for(int n=0;n<N;n++){
				String s = line[n];
				Car acar = new Car(s);
				if(!isValid(acar.s)){
					is0 = true;
					break;
				}
				cars.add(acar);
			}
			printCars();
			String s = "";
			if(is0){
			   s = "0";
			}else{
				s = getNumber() + "";
			}
			
			
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	long getNumber(){
		//combine cars with the same characters
		for(int i=0;i<cars.size();i++){
			Car ci = cars.get(i);
			if(ci.s.length()>1)
				continue;
			else{
			   char c = ci.s.charAt(0);
			   int count = 1;
			   for(int j=i+1;j<cars.size();j++){
				   Car cj = cars.get(j);
				   if(cj.s.length()==1 && cj.s.charAt(0)==c){
					   count++;
					   cars.remove(j);
					   j--;
				   }
			   }
			   ci.number = TT.perm(count, mod);
			   cars2.add(ci);
			   cars.remove(ci);
			   i--;
			}
		}
		//Tools.println("break1");
		//connect cars 
		
		for(int i=0;i<cars.size();i++){
			Car ci = cars.get(i);
			for(int j=0;j<cars2.size();j++){
				Car cj = cars2.get(j);
				if(!isValid(ci.s,cj.s))
					return 0;
				if(ci.combine(cj)){
					cars2.remove(j);
					break;
				}
			}
		}
		//Tools.println("break2");
		for(int i=0;i<cars2.size();i++){
			Car ci = cars2.get(i);
			for(int j=0;j<cars.size();j++){
				Car cj = cars.get(j);
				if(!isValid(ci.s,cj.s))
					return 0;
				if(ci.combine(cj)){
					cars.remove(j);
					break;
				}
			}
		}
		cars.addAll(cars2);
		cars2.clear();
		for(int i=0;i<cars.size();i++){
			Car ci = cars.get(i);
			for(int j=0;j<cars.size();j++){
				if(i == j)
					continue;
				Car cj = cars.get(j);
				if(!isValid(ci.s,cj.s))
					return 0;
				if(ci.combine(cj)){
					//if(!isValid(ci.s))
						//return 0;
					cars.remove(j);
					if(j<i)
						i--;
					j=-1;
				}
			}
		}
		printCars();
		int n = cars.size();
		long re = TT.perm(n, mod);
		for(int i=0;i<n;i++){
			long num = cars.get(i).number;
			re *= num;
			re = re % mod;
		}
		return re;
	}
	boolean isValid(String s){
		int L = s.length();
		for(int i=0;i<L;i++){
			char ci = s.charAt(i);
			for(int j=i+1;j<L;j++){
				if(ci==s.charAt(j))
					return false;
			}
		}
		return true;
	}
	boolean isValid(String s1,String s2){
		int L1 = s1.length();
		int L2 = s2.length();
		if((L1>1||L2>1) && s1.charAt(0)==s2.charAt(L2-1)&&s1.charAt(L1-1)==s2.charAt(0))
			return false;
		for(int i1=0;i1<L1;i1++){
			char c1 = s1.charAt(i1);
			int a1 = 0;
			int a2 = L2;
			if(i1==0)
				a2 = L2-1;
			if(i1==L1-1)
				a1 = 1;
			for(int i2=a1;i2<a2;i2++){
				if(c1 == s2.charAt(i2))
					return false;
			}
		}
		return true;
	}
	void printCars(){
		for(Car acar:cars){
			TT.print(acar.s+" ");
		}
		TT.println();
	}
}
class Car implements Comparable<Car>{
	final static long mod = 1000000007;
	String s;
	long number = 1;
	Car(String s){
		this.s = deRepetition(s);
	}
	boolean combine(Car c){
		int L1 = s.length();
		char l = s.charAt(L1-1);
		char f = c.s.charAt(0);
		if(l==f){
			s = s + c.s.substring(1);
			number = number*c.number % mod;
			return true;
		}
		return false;
	}
	public static String deRepetition(String s1){
		char[] cs1 = s1.toCharArray();
		int L = cs1.length;
		char pre = cs1[0];
		for(int i=1;i<L;i++){
			if(cs1[i] == pre)
				cs1[i] = '_';
			else
				pre = cs1[i];
		}
		String s2 = new String(cs1);
		s2 = s2.replaceAll("_","");
		return s2;
	}
	public int compareTo(Car o) {
		return s.compareTo(o.s);
	}
}
