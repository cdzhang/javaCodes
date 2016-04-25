package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tools.TT;

public class MinimumScalarProduct {
	public static void main(String[] args) throws IOException {
		MinimumScalarProduct msp = new MinimumScalarProduct();
		msp.minimumScalar();
		
	}
	private void minimumScalar() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
    	BufferedReader in = new BufferedReader(new FileReader(inputFile));
    	PrintWriter out = new PrintWriter(outputFile);
    	int T = Integer.parseInt(in.readLine());//first line, cases
    	for(int t=1;t<=T;t++){
    		int n = Integer.parseInt(in.readLine());//number of elements
    		List<Long> X = convertToSortedList(in.readLine());
    		List<Long> Y = convertToSortedList(in.readLine());
    		Long product = new Long(0);
    		for(int i=0;i<n;i++){
    			product = product + X.get(i) * Y.get(n-i-1);
    		}
    		//out.println("Case #" + t + ": " + product);
    		out.printf("Case #%d: %d\n", t,product);
    		System.out.printf("Case #%d: %d\n", t,product);
    	}
    	in.close();
    	out.close();
	}
	private List<Long> convertToSortedList(String s){
		List<String> sL = new ArrayList<String>(Arrays.asList(s.split(" ")));
		List<Long> I = new ArrayList<Long>();
		int L = sL.size();
		for(int i=0;i<L;i++){
			I.add(Long.parseLong(sL.get(i)));
		}
		Collections.sort(I);
		return I;
	}

}
