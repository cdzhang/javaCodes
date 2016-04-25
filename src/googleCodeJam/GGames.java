package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class GGames {
	int N, M;
	LinkedList<Elf> snt = new LinkedList<Elf>();
	public static void main(String[] args) throws IOException {
		GGames t = new GGames();
		t.solve();
		//t.test();
		
		int[] a = {1,2,3,4,5};
		/*for(int i=1;i<1000;i++){
			t.print(a);
			a = t.perm(a);
			if(a==null)
				break;
		}*/
	}
	void test(){
		for(int i=1;i<=4;i++){
			System.out.println("i="+i+","+i*pow(2,1));
		}
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String[] NM = in.readLine().split(" ");
			N = Integer.parseInt(NM[0]);
			M = Integer.parseInt(NM[1]);
			snt.clear();
			for(int m=1;m<=M;m++){
				String[] EKB = in.readLine().split(" ");
				int ID = Integer.parseInt(EKB[0]);
				int K = Integer.parseInt(EKB[1]);
				int B = Integer.parseInt(EKB[2]);
				int[] friends = new int[B];
				String[] fs = in.readLine().split(" ");
				for(int i=0;i<B;i++){
					friends[i] = Integer.parseInt(fs[i]);
				}
				snt.add(new Elf(ID,K,friends));
			}
			Collections.sort(snt);
			String output = "Case #"+caseN + ": "+canArrange();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String canArrange(){
		int L = pow(2,N);
		int[] i1 = new int[L];
		for(int i=0;i<L;i++){
			i1[i] = i+1;
		}
		while(i1 != null){
			if(canArrange(i1))
				return "YES";
			i1 = perm(i1);
		}
		return "NO";
	}
	boolean canArrange(int[] arrange){
		for(int i=0;i<arrange.length;i++){
			Elf E = getSensitive(arrange[i]);
			if(E != null){
				int kid = level(i+1,E.K);
				int x = pow(2,E.K);
				int h = kid*x;
				int l = h - x + 1;
				//System.out.println("kid="+kid+",x="+x+",l="+l+",h="+h);
				for(int j=l-1;j<h;j++){
					if(arrange[j] == E.ID)
						continue;
					if(E.isFriend(arrange[j]))
						return false;
				}
			}
		}
		return true;
	}
	Elf getSensitive(int i){//is Elf i sensitive
		for(Elf E:snt){
			int ID = E.ID;
			if(ID == i)
				return E;
			else if(i<ID)
				return null;
		}
		return null;
	}
	int level(int ID,int K){
		int id = ID;
		for(int i=1;i<=K;i++){
			id = (id+1) / 2;
		}
		return id;
	}
	int pow(int x,int n){
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
class Elf implements Comparable<Elf>{
	int ID;
	int level;//0,1,2,3,4
	int K;
	int[] friends;
	Elf(){}
	Elf(int E1,int K1, int[] friends1){
		ID = E1;
		K = K1;
		friends = friends1;
	}
	Elf[] childs;
	/*boolean isSensitive(int Ei,int round){
		if(round != K)
			return false;
		for(int f:friends){
			if(f==Ei)
				return true;
		}
		return false;
	}*/
	boolean isFriend(Elf E){
		return isFriend(E.ID);
	}
	boolean isFriend(int id){
		for(int i=0;i<friends.length;i++)
			if(id == friends[i])
				return true;
		return false;
	}
	public int compareTo(Elf E) {
		if(this.ID > E.ID){
			return 1;
		}else{
			return -1;
		}
	}
}
