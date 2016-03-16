package codeJame2015;

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
}
