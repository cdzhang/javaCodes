package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.Tools;

public class Milkshakes {
	public static void main(String[] arg)throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		Milkshakes a = new Milkshakes();
		a.milkShakesProcess(inputFile, outputFile);
	}
	
	public void milkShakesProcess(String inputFile, String outputFile) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int totalCasesNumber = Integer.parseInt(in.readLine());//first line
		//System.out.println("totalCasesNumber="+totalCasesNumber);
		for(int caseIndex=1;caseIndex<=totalCasesNumber;caseIndex++){
			int N = Integer.parseInt(in.readLine());//N milkshakes
			//System.out.println("N="+N);
			int customerCount = Integer.parseInt(in.readLine());//customer count
			MilkshakesCase aCase = new MilkshakesCase(N, customerCount);
			//System.out.println("customerCount=" + customerCount);
			for(int j=1;j<=customerCount;j++){
				String line = in.readLine();
				//System.out.println(line);
				MilkshakesCustomer aCustomer = new MilkshakesCustomer(line);
				//System.out.println(line);
				aCase.addCustomer(aCustomer);
			}
			//System.out.println("customerCount=" + customerCount);
			String output = "Case #" + caseIndex +":" + aCase.getOutput();
			out.println(output);
			System.out.println(output);
		}
		in.close();
		out.close();
	}
}
