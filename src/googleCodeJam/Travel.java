package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Travel {
    City[] cities;
    Road[] roads;
    int[][] DS;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Travel t = new Travel();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			String[] NMK = in.readLine().split(" ");
			int N = Integer.parseInt(NMK[0]);
			int M = Integer.parseInt(NMK[1]);
			int K = Integer.parseInt(NMK[2]);
			cities = new City[N+1];
			roads = new Road[M];
			for(int n=1;n<N+1;n++){
				cities[n] = new City();
			}
			for(int m=0;m<M;m=m+1){
				String[] xy = in.readLine().split(" ");
				int x = Integer.parseInt(xy[0]);
				int y = Integer.parseInt(xy[1]);
				cities[x].cRoads.add(m);
				cities[y].cRoads.add(m);
				cities[x].cCities.add(y);
				cities[y].cCities.add(x);
				int[] cost = new int[24];
				String[] cs = in.readLine().split(" ");
			    for(int i=0;i<24;i++){
			    	cost[i] = Integer.parseInt(cs[i]);
			    }
			    roads[m] = new Road(m,x,y,cost);
			}
			//printMap();
			DS = new int[K][2];
			for(int k=0;k<K;k++){
				String[] dss = in.readLine().split(" ");
				DS[k][0] = Integer.parseInt(dss[0]);
				DS[k][1] = Integer.parseInt(dss[1]);
			}

			String output = "Case #" + caseN + ":";
			for(int k=0;k<K;k++){
				for(int n=1;n<cities.length;n++){
					cities[n].refresh();
				}
				int D = DS[k][0];
				int s = DS[k][1];
				findShortestTime(1,D,s);
			    int time = cities[D].rT;
			    if(time == -1){
			    	output = output + " -1";
			    }else{
			    	output = output + " " + (time - s);
			    }
			}
			System.out.println(output);
			out.println(output);
		}
		
		in.close();
		out.close();
	}
	void findShortestTime(int n1,int n2, int s){
		City c1 = cities[n1];
		if(c1.rT == -1){//starting point
			c1.rT = s;
		}
		int sc = c1.rT;
		c1.out = true;
		if(n1 == n2)
			return;
		//System.out.println("n1="+n1+",c1.cRoads.size="+c1.cRoads.size());
		for(int i=0;i<c1.cRoads.size();i++){
			int m = c1.cRoads.get(i);//
			int tc1c = roads[m].cost[sc%24];//time required
			City c = cities[c1.cCities.get(i)];//connected to c1
			if(c.out)
				continue;//no need to consider out campus
			if(c.rT < 0){
				c.rT = sc + tc1c;
				c.previous.add(m);//road
			}else if(c.rT > sc + tc1c){
				c.rT = sc + tc1c;
				c.previous.clear();
				c.previous.add(m);//add new previous
			}else if(c.rT == sc + tc1c){
				c.previous.add(m);//two roads leads to c 
			}
		}
		//printStatus();
		int n = getShortestUnvisited();
		//System.out.println("n="+n);
		if(n == -1){
			cities[n2].rT = -1;
			return;
		}
		findShortestTime(n,n2,s);
	}
	int getShortestUnvisited(){
		int nn = -1;
		int time = -1;
		for(int n=1;n<cities.length;n++){
			if(cities[n].out || cities[n].rT == -1)
				continue;
			if(time == -1){
				nn = n;
				time = cities[n].rT;
			}else if(time > cities[n].rT){
				time = cities[n].rT;
				nn = n;
			}
		}
		return nn;
	}
	public void printStatus(){
		String output = "";
		for(int i=1;i<cities.length;i++){
			output = "cities " + i + ","+ cities[i].out + "," + cities[i].rT;
			System.out.println(output);
		}
		
	}
	public void printMap(){
		String output = "";
		for(int i=1;i<cities.length;i++){
			output = "cities "+i +":\n";
			City ri = cities[i];
			for(int m:ri.cRoads){
				output += m + ",";
			}
			output = output + "\n";
			for(int n:ri.cCities){
				output += n + ",";
			}
			System.out.println(output+",cRoads.size="+ri.cRoads.size());
		}
	}

}
class Road{
	int m;//roads number
	int n1;
	int n2;
	int[] cost = new int[24];
	Road(){
	}
	Road(int m,int n1,int n2,int[] cost){
		this.m = m;
		this.n1 = n1;
		this.n2 = n2;
		this.cost = cost;
	}
}
class City{
	int rT = -1;//time to starting point
	LinkedList<Integer> cRoads = new LinkedList<Integer>();
	LinkedList<Integer> cCities = new LinkedList<Integer>();
	LinkedList<Integer> previous = new LinkedList<Integer>();
	boolean out = false;
	City(){
	}
	void refresh(){
		rT = -1;
		previous.clear();
		out = false;
	}
}