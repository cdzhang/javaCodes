package googleCodeJam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;

import tools.Tools;

public class StandingOvation {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		StandingOvation so = new StandingOvation();
		so.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			String[] s = in.readLine().split(" ");
			int Smax = Integer.parseInt(s[0]);
			String audience = s[1];
			int standUp = 0;
			int added = 0;
			for(int i=0;i<audience.length();i++){
				int Si = Integer.parseInt(audience.charAt(i)+"");//number of audience that have shyness level i
                if(standUp < i){//should add i - standUp audience
                	added += i - standUp;
                	standUp = i + Si;
                }else{
                	standUp += Si;
                }
			}
			String output = "Case #" + caseN + ": " + added;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
}
