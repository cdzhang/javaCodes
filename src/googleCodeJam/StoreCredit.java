package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tools.TT;

public class StoreCredit {

	public static void main(String[] args) throws IOException {
		StoreCredit s = new StoreCredit();
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		s.calculateCredits(inputFile,outputFile);
	}
	public void calculateCredits(String inputFile, String outputFile) throws IOException{
    	BufferedReader in = new BufferedReader(new FileReader(inputFile));
    	PrintWriter out = new PrintWriter(outputFile);
    	int totalCasesNumber = Integer.parseInt(in.readLine());//first line
    	for(int index=1;index<=totalCasesNumber;index++){
    		String l1 = in.readLine();//credit
    		String l2 = in.readLine();//total items
    		String l3 = in.readLine();//items
    		StoreCreditCase c = new StoreCreditCase(index,l1,l2,l3);
    		System.out.println(c);
    		out.println(c);
    	}
    	in.close();
    	out.close();
	}
}
