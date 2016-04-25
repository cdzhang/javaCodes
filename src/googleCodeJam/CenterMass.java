package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class CenterMass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		CenterMass cm = new CenterMass();
		cm.solve();

	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			int N = Integer.parseInt(in.readLine());
			double meanX = 0;
			double meanY = 0;
			double meanZ = 0;
			double meanVx = 0;
			double meanVy = 0;
			double meanVz = 0;
            for(int n=1;n<=N;n++){
            	String[] s = in.readLine().split(" ");
            	meanX += Double.parseDouble(s[0]);
            	meanY += Double.parseDouble(s[1]);
            	meanZ += Double.parseDouble(s[2]);
            	meanVx += Double.parseDouble(s[3]);
            	meanVy += Double.parseDouble(s[4]);
            	meanVz += Double.parseDouble(s[5]);
            }
            meanX = meanX / N;
            meanY = meanY / N;
            meanZ = meanZ / N;
            meanVx = meanVx / N;
            meanVy = meanVy / N;
            meanVz = meanVz / N;
            double dmin = 0;
            double tmin = 0;
            
            double d2 = meanX*meanX + meanY*meanY + meanZ*meanZ;
            double v2 = meanVx*meanVx + meanVy*meanVy + meanVz*meanVz;
            double d1d2 = meanX*meanVx + meanY*meanVy + meanZ*meanVz;
            if(d2==0){
            	dmin = 0;
            	tmin = 0;
            }else if(v2==0 || d1d2 > 0){
            	dmin = Math.sqrt(d2);
            	tmin = 0;
            }else{
            	double cost2 = d1d2/d2 * d1d2/v2;
            	double sint2 = 1 - cost2;
	            dmin = Math.sqrt(Math.abs(d2*sint2));
	            tmin = Math.abs(d1d2/v2);
            }
            String output = "Case #" + caseN + ": " + dmin + " " + tmin;
            System.out.println(output);
            out.println(output);
		}
		in.close();
		out.close();
	}
}
