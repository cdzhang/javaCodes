package codJam2014;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Tools {
	public static String chooseFile(){
		String s = "";
		String currentDir = System.getProperty("user.dir");
		JFileChooser fileChooser = new JFileChooser(currentDir+"/inputs");
		if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
		  File file = fileChooser.getSelectedFile();
		  s = file + "";
		  s = s.replace("\\", "/");
	    }
		return s;
	}
	public static String getOutputName(String inputName){
		File inputFile = new File(inputName);
		String fileName = inputFile.getName();
		String dirName = inputFile.getParent().replace("\\", "/");
		return dirName + "/output_" + fileName;
	}
	public static int[] intArray(String s,String delimit){
		String[] ss = s.split(delimit);
		int N = ss.length;
		int[] a = new int[N];
		for(int n=0;n<N;n++)
			a[n] = Integer.parseInt(ss[n]);
		return a;
	}
	public static long[] longArray(String s,String delimit){
		String[] ss = s.split(delimit);
		int N = ss.length;
		long[] a = new long[N];
		for(int n=0;n<N;n++)
			a[n] = Long.parseLong(ss[n]);
		return a;
	}
	public static void print(Object o){
		System.out.print(o.toString());
	}
	public static void println(Object o){
		System.out.println(o.toString());
	}
	public static void println(){
		System.out.println();
	}
	public static void print(Object[][] oA){
		for(Object[] ob:oA){
			for(Object o:ob)
				print(o+" ");
			println();
		}
	}
	public static void print(int[][] N){
		for(int[] n:N){
			for(int i:n)
				print(i+" ");
			println();
		}
	}
	public static void print(int[] N){
		print("[");
		for(int n:N){
			print(n+",");
		}
		println("]");
	}
	
	public static void print(long[] N){
		print("[");
		for(long n:N){
			print(n+",");
		}
		println("]");
	}
	public static void print(double[] d){
		print("[");
		for(double di:d){
			print(di+",");
		}
		println("]");
	}
	public static long gcd(long a, long b){
		long c = a % b;
		if(c==0)
			return b;
		else
			return gcd(b,c);
	}
	public static int gcd(int a, int b){
		int c = a % b;
		if(c==0)
			return b;
		else
			return gcd(b,c);
	}
	public static long mul(long a,long b){
		long c = gcd(a,b);
		return a * b / c;
	}
	public static int mul(int a,int b){
		int c = gcd(a,b);
		return a * b / c;
	}
	public static long convert(String binary){
		int L = binary.length();
		long power = 1;
		long r = 0;
		for(int i=0;i<L;i++){
			char c = binary.charAt(L-1-i);
			if(c=='1'){
				r += power;
			}
			power = power * 2;
		}
		return r;
	}
	public static long[] power2(int N){
		long[] p = new long[N+1];
		long value = 1;
		for(int i=0;i<=N;i++){
			p[i] = value;
			value *= 2;
		}
		return p;
	}

}
