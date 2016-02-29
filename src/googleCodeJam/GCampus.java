package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class GCampus {
	public static void main(String[] args) throws IOException{
		GCampus gc = new GCampus();
		gc.solve();
		
	}
	void test(track tr){
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			System.out.println("reading case "+ caseN + "...");
			String[] NM = in.readLine().split(" ");
			int N = Integer.parseInt(NM[0]);
			int M = Integer.parseInt(NM[1]);
			int[][] roads = new int[M][3];
			LinkedList<Integer>[] Nr = new LinkedList[N];
			for(int n=0;n<N;n++){
				Nr[n] = new LinkedList<Integer>();//store roads connected to n
			}
			for(int m=0;m<M;m++){
				String[] road = in.readLine().split(" ");
				int n1 = Integer.parseInt(road[0]);
				int n2 = Integer.parseInt(road[1]);
				int time = Integer.parseInt(road[2]);
				if(n1 > n2){
					int i = n1;
					n1 = n2;
					n2 = i;
				}
				roads[m][0] = n1;
				roads[m][1] = n2;
				roads[m][2] = time;
				Nr[n1].add(m);
				Nr[n2].add(m);
			}

			System.out.println("Solving case "+ caseN + "...");
			track tr = new track(N,M,roads,Nr);
			String output = "Case #" + caseN + ":" + tr.getInefficient();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
}

class track{
	int testCount = 0;
	int N;
	int M;
	int[][] roads;
	boolean[] onTrack ;
	//boolean[] onTrack ;//onTrack[i] is true if it's already on track
	LinkedList<Integer>[] Nr;
	long minTime = -1L;
	track(int N1,int M1,int[][] roads1,LinkedList<Integer>[] Nr1){
		N = N1;
		M = M1;
		roads = roads1;
		Nr = Nr1;
	}
	public String getInefficient(){
		String ie = "";
		onTrack = new boolean[M];
		for(int m=0;m<M;m++){
			onTrack[m] = false;
		}
		for(int n1=0;n1<N-1;n1++){
			for(int n2=n1+1;n2<N;n2++){
				LinkedList<Integer> ontrack0 = new LinkedList<Integer>();
				LinkedList<Integer> visitedN = new LinkedList<Integer>();
				minTime = -1L;
				//System.out.println("calculating for (" +n1 +"," + n2 +")");
				LinkedList<direction> drs = getShortestTrack(n1,n2,ontrack0,visitedN,0L);
				if(n1==69 && n2==72){
					System.out.println(drs.get(0).time);
				}
				if(drs.size()==0) return "";
				for(direction dr:drs){
					for(Integer in:dr.dc){
						onTrack[in] = true;;
					}
				}
			}
		}
		for(int m=0;m<M;m++){
			if(!onTrack[m])
				ie = ie+"\n" +m;
		}
		return ie;
	}
	public LinkedList<direction> getShortestTrack(int n1,int n2,LinkedList<Integer> onTrack1,LinkedList<Integer> visitedN,long usedTime){//
		if(minTime > 0 && usedTime > minTime){
			return null;//don't consider node n1 because it already uses more time
		}
		LinkedList<Integer> dc = new LinkedList<Integer>();
		LinkedList<direction> r = new LinkedList<direction>();
		if(n1 == n2){
			r.add(new direction(dc,usedTime));
			if(minTime < 0){
				minTime = usedTime;
			}
			return r;
		}
		LinkedList<Integer> nRoads = getRoads(n1,onTrack1,visitedN);//roads that connect to n1 and
		/*System.out.print("n1="+n1+",nRoads=[");
		for(int road:nRoads)
			System.out.print(road+",");
		System.out.print("],onTrack1=");
		for(int on:onTrack1){
			System.out.print(on+",");
		}
		System.out.println();*/
		//not on track
		long time = 0L;
		if(nRoads.size() == 0){//this is a dead point
			return null;
		}else if(nRoads.size() == 1 && 
				((roads[nRoads.get(0)][0] == n1) && (roads[nRoads.get(0)][1] == n2)||
				 (roads[nRoads.get(0)][0] == n2) && (roads[nRoads.get(0)][1] == n1))
				){
			//only one road to n2
			dc.add(nRoads.get(0));
			time = usedTime + roads[nRoads.get(0)][2];
			if(minTime > 0 && minTime < time){
				return null;
			}
			r.add(new direction(dc,time));
			return r;
		}else{
			long minTime1 = -1L;
			LinkedList<direction> minR = new LinkedList<direction>();
			for(int i=0;i<nRoads.size();i++){
				int m = nRoads.get(i);
				if(isIn(m,onTrack1))
					continue;
				int ni = roads[m][0] + roads[m][1] - n1;
				if(isIn(ni,visitedN))
					continue;
				long timeM = roads[m][2]*1L;
				if(minTime > 0 && minTime < usedTime + timeM){//no need to consider this road
					continue;
				}
				LinkedList<Integer> onTracki = new LinkedList<Integer>();
				onTracki.addAll(onTrack1);
				onTracki.add(m);
				LinkedList<Integer> visitedNi = new LinkedList<Integer>();
				visitedNi.addAll(visitedN);
			    visitedNi.add(ni);
				LinkedList<direction> ri = getShortestTrack(ni,n2,onTracki,visitedN,usedTime+timeM);
				if(ri == null) continue;//ith direction 
				if(minTime1 == -1){
					minTime1 = ri.get(0).time;
					minR = ri;
					for(int k=0;k<minR.size();k++){
						minR.get(k).dc.addFirst(m);
					}
				}else if(ri.get(0).time < minTime1){
					minTime1 = ri.get(0).time;
					minR = ri;
					for(int k=0;k<minR.size();k++){
						minR.get(k).dc.addFirst(m);
					}
				}else if(ri.get(0).time == minTime1){
					for(int k=0;k<ri.size();k++){
						ri.get(k).dc.addFirst(m);
					}
					minR.addAll(ri);
				}
			}
			if(minTime1 == -1L){
				return null;//this office doesn't lead to n2 or takes too long time
			}else{
				return minR;
			}
		}
	}
	public LinkedList<Integer> getRoads(int n,LinkedList<Integer> ontrack,LinkedList<Integer> visitedN){//get roads 
		//not in ontrack that dicrect connect with n
		LinkedList<Integer> dc = new LinkedList<Integer>();
		for(int i=0;i<Nr[n].size();i++){
			int m = Nr[n].get(i);
			if(isIn(m,ontrack))
				continue;
			if((roads[m][0] == n || roads[m][1] == n)){
				if(!isIn(roads[m][0]+roads[m][1]-n,visitedN))
					dc.add(m); 
			}
		}
		return dc;
	}
	boolean isIn(int a,LinkedList<Integer> b){
		for(int bi:b){
			if(a==bi)
				return true;
		}
		return false;
	}
}
class direction implements Cloneable{
	LinkedList<Integer> dc;
	long time;
	direction(){
	}
	direction(LinkedList<Integer> dc,long time){
		this.dc = dc;
		this.time = time;
	}
}
