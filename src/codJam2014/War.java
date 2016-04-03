package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.Template;
import tools.Tools;

public class War {
	
	public static void main(String[] args) throws IOException {
		War t = new War();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int N = Integer.parseInt(in.readLine());
			double[] naod = Tools.doubleArray(in.readLine()," ");
			double[] kend = Tools.doubleArray(in.readLine()," ");
			LinkedList<Double> nao1 = new LinkedList<Double>();
			LinkedList<Double> nao2 = new LinkedList<Double>();
			LinkedList<Double> ken1 = new LinkedList<Double>();
			LinkedList<Double> ken2 = new LinkedList<Double>();
			for(int i=0;i<N;i++){
				nao1.add(naod[i]);
				nao2.add(naod[i]);
				ken1.add(kend[i]);
				ken2.add(kend[i]);
			}
			int y = deceitWarScore(nao1,ken1);
			int z = warScore(nao2,ken2);
			String output = "Case #"+caseN + ": "+y+" " + z;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		Tools.println("total execution time is "+timeUsed);
	}
	int warScore(LinkedList<Double> nao, LinkedList<Double> ken){
		Collections.sort(nao);
		Collections.sort(ken);
		int score = 0;
		while(nao.size()>0){
			double nw = nao.getLast();
			nao.removeLast();
			if(ken.getLast() < nw){
				ken.removeFirst();
				score++;
			}else{
				int i = 0;
				while(ken.get(i) < nw)
					i++;
				ken.remove(i);
			}
		}
		return score;
	}
	int deceitWarScore(LinkedList<Double> nao, LinkedList<Double> ken){
		Collections.sort(nao);
		Collections.sort(ken);
		int score = 0;
		while(ken.size()>0){
			double kw = ken.getFirst();
			ken.removeFirst();
			if(kw > nao.getLast()){
				return score;
			}else{
				int i = 0;
				while(nao.get(i)<kw)
					i++;
				nao.remove(i);
				score++;
			}
		}
		return score;
	}

}
