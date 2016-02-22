package googleCodeJam;

import java.io.IOException;

public class Numbers {
	public static void main(String[] args){
		for(int i=1;i<=30;i++){
			double xn = Math.pow((3+Math.sqrt(5)),i);
			double yn = xn%100;
			System.out.println("n="+i+":"+yn);
		}	
	}
	public void milkShakesProcess(String inputFile, String outputFile) throws IOException{
	
	}
	public String outputCase(int n, int caseIndex){
		double xn = n*Math.log10(3+Math.sqrt(5));
		return "";
	}
}
