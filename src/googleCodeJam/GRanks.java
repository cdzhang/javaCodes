package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class GRanks {
	LinkedList<Athlete> athletes = new LinkedList<Athlete>();
	int[] S;
	int N;
	int M;
	public static void main(String[] args) throws IOException {
		GRanks t = new GRanks();
		t.solve();
		//System.out.println('T' - 'P');
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			athletes.clear();
			int P = Integer.parseInt(in.readLine());
			S = new int[P];
			String[] ps = in.readLine().split(" ");
			for(int i=0;i<P;i++){
				S[i] = Integer.parseInt(ps[i]);
			}
			N = Integer.parseInt(in.readLine());
			for(int n=1;n<=N;n++){
				String[] line = in.readLine().split(" ");
				int W =  Integer.parseInt(line[0]);
				for(int i=1;i<=P;i++){
					addRecord(line[i],W*S[i-1]);
				}
			}
			M = Integer.parseInt(in.readLine());
			for(Athlete a:athletes){
				a.getFinalScore(M);
				/*System.out.print(a.name+":");
				for(int score:a.scores)
					System.out.print(score+",");
				System.out.println(a.finalScore);*/
			}
			Collections.sort(athletes);
			String output = "Case #"+caseN + ": ";
			int rank = 1;
			long previousScore = athletes.getLast().finalScore;
			output = output + "\n" + rank + ": " + athletes.getLast().name;
			for(int i=athletes.size()-2;i>=0;i--){
				long score = athletes.get(i).finalScore;
				if(score < previousScore){
					rank = athletes.size() - i;
				}
				output = output + "\n" + rank + ": " + athletes.get(i).name;
				previousScore = score;
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void addRecord(String name,int score){
		Athlete a = getAthlete(name);
		if(a==null){
			a = new Athlete(name,score);
			athletes.add(a);
		}else{
			a.addScore(score);
		}
	}
	Athlete getAthlete(String name){
		for(Athlete a:athletes){
			if(a.name.equals(name))
				return a;
		}
		return null;
	}
}
class Athlete implements Comparable<Athlete>{
	String name = "";
	LinkedList<Integer> scores = new LinkedList<Integer>();
	long finalScore;
	Athlete(){}
	Athlete(String name1,int score){
		name = name1;
		scores.add(score);
	}
	void addScore(int score){
		scores.add(score);
	}
	void getFinalScore(int M){
		Collections.sort(scores);
		int sz = scores.size();
		int max = Math.min(M, sz);
		finalScore = 0;
		for(int i=1;i<=max;i++){
			finalScore += scores.get(sz-i);
		}
	}
	public int compareTo(Athlete a) {
		if(this.finalScore > a.finalScore){
			return 1;
		}else if(this.finalScore < a.finalScore)
			return -1;
		else{
			return compareString(a.name,this.name);
		}
	}
	private int compareString(String s1,String s2){
		int L1 = s1.length();
		int L2 = s2.length();
		for(int i=0;i<L1&&i<L2;i++){
			if(s1.charAt(i) > s2.charAt(i))
				return 1;
			if(s1.charAt(i) < s2.charAt(i))
				return -1;
		}
		if(L1 > L2)
			return 1;
		if(L1 < L2)
			return -1;
		return 0;
	}
}