package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import tools.TT;

public class Moist {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Moist m = new Moist();
		m.solve();
		m.test();
	}
	public void test(){
		String s1 = "Annie Bellemare";
		String s2 = "An Ni";
		System.out.println(isLarger(s2,s1));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN = 1;caseN <= T;caseN++){
			int N = Integer.parseInt(in.readLine());
			String[] cards = new String[N];
			for(int n=0;n<N;n++){
				cards[n] = in.readLine();
			}
			int cost = 0;
			int disorder = -1;
			while(disorder != 0){
				disorder = 0;
				for(int i=0;i<N-1;i++){
					String s1 = cards[i];
					String s2 = cards[i+1];
					if(isLarger(s1,s2)){//
						int index = 0;
						disorder++;
						for(index = i;index >= 0;index--){
							if(index == 0 || (isLarger(cards[index],s2) && isLarger(s2,cards[index-1]))){//shift
								String s = s2;
								for(int j=i+1;j>index;j--){
									cards[j] = cards[j-1];
								}
								cards[index] = s;
								cost++;
								break;
							}
						}
					}
				}
			}
			for(String card:cards){
				System.out.println(card);
			}
			String output = "Case #" + caseN + ": " +cost;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
    
	public boolean isLarger(String s1, String s2){
		int n1 = s1.length();
		int n2 = s2.length();
		int n = Math.min(n1, n2);
		for(int i=0;i<n;i++){
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if(c1 == c2)
				continue;
			if(isLarger(c1,c2))
				return true;
			else
				return false;
		}
		return (n1 - n2 > 0);
	}
	public boolean isLarger(char c1, char c2){// c1 != c2
		if(c1 == ' ') return true;
	    if(c2 == ' ') return false;
	    
	    if(c1 >= 'A' && c1 <= 'Z'){
			if(c2 >= 'a' && c2 <= 'z')
				return true;
			else if(c1 - c2 > 0)
				return true;
			else
				return false;
		}else{
			if(c2 >= 'a' && c2 <= 'z' && c1 - c2 > 0)
				return true;
			else
				return false;
		}
	}

}
