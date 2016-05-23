package codejam2011;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;

import tools.TT;

public class Pseudominion {
	int N,M;
	PriorityQueue<card> p0 = new PriorityQueue<card> ();
	PriorityQueue<card> p1 = new PriorityQueue<card> ();
	PriorityQueue<card> p2 = new PriorityQueue<card> ();
	LinkedList<card> deck = new LinkedList<card>();
	int score;
	int turn;
	public static void main(String[] args) throws IOException {
		new Pseudominion().solve();
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		long startTime = System.currentTimeMillis();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			p0.clear();
			p1.clear();
			p2.clear();
			deck.clear();
			score = 0;
			turn = 1;
			N = Integer.parseInt(in.readLine());
			for(int n=1;n<=N;n++){
				int[] line = TT.intArray(in.readLine(), " ");
				card c = new card(line[0],line[1],line[2]);
				add(c);
			}
			M = Integer.parseInt(in.readLine());
			for(int m = 1;m<=M;m++){
				int[] line = TT.intArray(in.readLine()," ");
				card c = new card(line[0],line[1],line[2]);
				deck.add(c);
			}
			String output = "Case #"+caseN + ": ";
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long timeUsed = System.currentTimeMillis()-startTime;
		TT.println("total execution time is "+timeUsed);
	}
	int maxScore(){
		int sc = 0;
		sc = sc+countT();
		if(deck.size()==0 || (p2.size()==0&&p1.size()==0)){
			PriorityQueue<card> pp = new PriorityQueue<card> ();
			pp.addAll(p0);
			pp.addAll(p1);
			pp.addAll(p2);
			while(turn > 0){
				card c = pp.poll();
				if(c==null)
					return sc;
				sc += c.s;
				turn --;
			}
		}else{
			sc += countMax2();
			//here need to 
			PriorityQueue<card> p0b = new PriorityQueue<card>();
			PriorityQueue<card> p1b = new PriorityQueue<card>();
			PriorityQueue<card> p2b = new PriorityQueue<card>();
			LinkedList<card> deckb = new LinkedList<card>();
			p1b.addAll(p1);
			p2b.addAll(p2);
			deckb.addAll(deck);
			int sci = 0;
			for(int i2=0;i2<Math.min(turn,p2.size());i2++){
				int sc2 = 0;
				//PriorityQueue<card> p2i = new PriorityQueue<card>();
				for(int i=0;i<i2;i++){
					card c2i = p2.poll();
				}
				
				
				for(int i1=0;i1<Math.min(turn,p1.size());i1++){
					//
					PriorityQueue<card> p1i = new PriorityQueue<card>();
					for(int i=0;i<i1;i++){
						p1i.add(p1.poll());
					}

					p1 = p1i;
					//
					p0 = p0b;
					p1 = p1b;
					p2 = p2b;
					deck = deckb;
					
					
				}
				
			}
			
		}
		return sc;
	}
	int countT(){
		if(turn == 0)
			return 0;
		int sc = 0;
		card tc = getTCard();
		while(tc!=null){
			sc += tc.s;
			pickDeck(tc.c);
			turn = turn - 1 + tc.t;
		}
		return sc;
	}
	int pickDeck(int n){
		n = Math.min(n, deck.size());
		for(int i=0;i<n;i++){
			add(deck.getFirst());
			deck.removeFirst();
		}
		return n;
	}
	int countMax2(){
		card c2;
		int sc = 0;
		while((c2 = getMax2())!=null){
			sc += c2.s;
			p2.poll();
			pickDeck(2);
			sc += countT();
		}
		return sc;
	}
	card getMax2(){
		card c2 = p2.peek();
		if(c2==null)
			return null;
		card c1 = p1.peek();
		if(c1!=null && c1.s > c2.s)
			return null;
		card c0 = p0.peek();
		if(c0!=null && c0.s > c2.s)
			return null;
		return c2;
	}
	card getTCard(){
		card c = p2.peek();
		if(c!=null && c.t > 0){
			p2.poll();
			return c;
		}
		c = p1.peek();
		if(c!=null && c.t > 0){
			p1.poll();
			return c;
		}
		c = p0.peek();
		if(c!=null && c.t > 0){
			p0.poll();
			return c;
		}
		return null;
	}
	void add(card ca){
		if(ca.c==0)
			p0.add(ca);
		else if(ca.c==1)
			p1.add(ca);
		else
			p2.add(ca);
	}
}
class card implements Comparable<card>{
	int c,s,t;
	card(int c1,int t1,int s1){
		c = c1;
		t = t1;
		s = s1;
	}
	public int compareTo(card c) {
		int tc = c.t;
		if(t>tc)
			return -1;
		if(t<tc)
			return 1;
		if(s>c.s)
			return -1;
		if(s<c.s)
			return 1;
		if(this.c>c.c)
			return -1;
		if(this.c<c.c)
			return 1;
		return 0;
	}
	
}