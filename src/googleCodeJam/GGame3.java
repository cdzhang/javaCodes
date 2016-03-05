package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class GGame3 {
	int N, M;
	int[] Q;
	String st;
	LinkedList<Elf3> elves = new LinkedList<Elf3>();
	public static void main(String[] args) throws IOException {
		GGame3 t = new GGame3();
		t.solve();
	}
	void test(){
		for(int i=1;i<=4;i++){
			System.out.println("i="+i+","+i*pow(2,1));
		}
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String line = in.readLine();
			String[] NM = line.split(" ");
			if(caseN==125){
				System.out.println(line);//test
			}
			N = Integer.parseInt(NM[0]);
			int L = pow(2,N);
			Q = new int[L];
			st = "";
			elves.clear();
			for(int i=1;i<=L;i++){
				Q[i-1] = 0;
				elves.add(new Elf3(i));
			}
			M = Integer.parseInt(NM[1]);
			for(int m=1;m<=M;m++){
				String line1 = in.readLine();
				if(caseN==125){
					System.out.println(line1);//test
				}
				String[] EKB = line1.split(" ");
				int ID = Integer.parseInt(EKB[0]);
				Elf3 e = elves.get(ID-1);
				int K = Integer.parseInt(EKB[1]);
				int B = Integer.parseInt(EKB[2]);
				LinkedList<Elf3> friends = new LinkedList<Elf3>();
				String line2 = in.readLine();
				if(caseN==125){
					System.out.println(line2);//test
				}
				String[] fs = line2.split(" ");
				for(int i=0;i<B;i++){
					int fID = Integer.parseInt(fs[i]);
					Elf3 fr = elves.get(fID-1);
					friends.add(fr);
					fr.beFriends.add(e);
				}
				e.K = K;
				e.friends = friends;
			}
			Collections.sort(elves);
			put(0);
			String output = "Case #"+caseN + ": "+st;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	void put(int i){
		Elf3 e = elves.get(i);
		if(e.pos != null && e.pos.size() == 0){
			if(i==0){
				st = "NO";
				return;
			}
			e.pos = null;
			Elf3 lastE = elves.get(i-1);
			int lq = lastE.q;
			Q[lq] = 0;
			lastE.q = -1;
			lastE.pos.removeFirst();
			put(i-1);
			return;
		}
		if(e.pos == null){
			LinkedList<Integer> pos = getPos(e);
			if(pos.size() == 0){//cannot find a position
				Elf3 lastE = elves.get(i-1);
				int lq = lastE.q;
				Q[lq] = 0;
				lastE.q = -1;
				lastE.pos.removeFirst();
				 put(i-1);
				 return;
			}
			e.pos = pos;
		}
		if(i == pow(2,N)-1){
			st = "YES";
			return;
		}
		int eq = e.pos.getFirst();
		Q[eq] = e.ID;
		e.q = eq;
		put(i+1);
	}
	LinkedList<Integer> getPos(Elf3 e){
		LinkedList<Integer> pos = new LinkedList<Integer>();
		for(int q=0;q<pow(2,N);q++){
			if(Q[q] == 0){
				boolean canAdd = true;
				for(Elf3 bf:e.beFriends){
					int bq = bf.q;
					if(bq==-1)
						continue;
					int bk = bf.K;
					if(level(bq+1,bk)==level(q+1,bk)){
						canAdd = false;
						break;
					}
				}
				if(canAdd){
					if(e.friends!=null){
						for(Elf3 f:e.friends){
							int fq = f.q;
							if(fq==-1)
								continue;
							int fk = f.K;
							if(level(fq+1,fk)==level(q+1,fk)){
								canAdd = false;
								break;
							}
						}
					}
				}
				if(canAdd)
					pos.add(q);
			}
		}
		return pos;
	}
	int level(int pos,int K){
		int p = pos;
		for(int i=1;i<=K;i++){
			p = (p+1) / 2;
		}
		return p;
	}
	static int pow(int x,int n){
		int y = 1;
		for(int i=1;i<=n;i++){
			y = y*x;
		}
		return y;
	}
	int[] perm(int [] N){//
		int n=N.length;
		int sIndex = n-2;
		while(sIndex >= 0){
			if(N[sIndex] < N[sIndex+1]){
				break;
			}
			sIndex--;
		}
		if(sIndex < 0) //[n,n-1,...1]
			return null;
		int iIndex = n-1;
		int II = n+1;
		for(int i=n-1;i>sIndex;i--){
			if(N[i] > N[sIndex] && N[i] < II){
				II = N[i];
				iIndex = i;
			}
		}
		swap(N,iIndex,sIndex);
		for(int i=sIndex+1;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				if(N[i] > N[j])
					swap(N,i,j);
			}
		}
		return N;
	}
	void swap(int[] N,int i,int j){
		int t = N[i];
		N[i] = N[j];
		N[j] = t;
	}
	void print(int[] N){
		System.out.print("[");
		for(int n:N)
			System.out.print(n);
		System.out.println("]");
	}
}
class Elf3 implements Comparable<Elf3>{
	int ID;
	int K = 0;
	int q = -1;
	LinkedList<Elf3> beFriends = new LinkedList<Elf3>();
	LinkedList<Integer> pos = null;
	LinkedList<Elf3> friends = null;;
	Elf3(){}
	Elf3(int id){ID = id;}
	Elf3(int E1,int K1, LinkedList<Elf3> friends1){
		ID = E1;
		K = K1;
		friends = friends1;
	}
	public int compareTo(Elf3 E) {
		if(this.K > E.K)
			return -1;
		else if(this.K < E.K)
			return 1;
		return 0;
	}
}
class Node{
	int K;
	int count = 0;
	Node[] children = null;
	Node parent;
	LinkedList<Elf3> contained;
}