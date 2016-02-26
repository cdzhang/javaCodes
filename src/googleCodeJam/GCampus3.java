
	package googleCodeJam;

	import java.io.BufferedReader;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.LinkedList;
	public class GCampus3 {
		Campus[] rooms;
		int[][] roads;
		boolean[] used;
		public static void main(String[] args) throws IOException{
			GCampus3 gc = new GCampus3();
			gc.solve();
		}
		public void solve() throws IOException{
			String inputFile = Tools.chooseFile();
			String outputFile = Tools.getOutputName(inputFile);
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			PrintWriter out = new PrintWriter(outputFile);
			int T = Integer.parseInt(in.readLine());//first line
			for(int caseN=1; caseN<=T;caseN++){
				String[] NM = in.readLine().split(" ");
				int N = Integer.parseInt(NM[0]);
				int M = Integer.parseInt(NM[1]);
				rooms = new Campus[N];
				for(int n=0;n<N;n++){
					rooms[n] = new Campus(n);
				}//create rooms
				roads = new int[M][3];
				used = new boolean[M];
				for(int i=0;i<M;i++)
					used[i] = false;
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
					rooms[n1].cRoads.add(m);
					rooms[n2].cRoads.add(m);
					rooms[n1].cCampus.add(n2);
					rooms[n2].cCampus.add(n1);
				}
				//track tr = new track(N,M,roads,Nr);
				//printMap();
				String output = "Case #" + caseN + ":" + getInefficient();
				System.out.println(output);
				out.println(output);
			}
			in.close();
			out.close();
		}
		public void findShortestTime(int n1,int n2){//start from n1
			Campus c1 = rooms[n1];
			if(c1.sT == -1L){//starting point
				c1.sT = 0;
			}
			long sc = c1.sT;
			for(int i=0;i<c1.cRoads.size();i++){
				int m = c1.cRoads.get(i);//
				long tc1c = roads[m][2];//time required
				Campus c = rooms[c1.cCampus.get(i)];//connected to c1
				//System.out.println("m="+m +",n="+c.n);
				if(c.out)
					continue;//no need to consider out campus
				if(c.sT < 0){
					c.sT = sc + tc1c;
					c.previous.add(m);//road
				}else if(c.sT > sc + tc1c){
					c.sT = sc + tc1c;
					c.previous = new LinkedList<Integer>();//delete old previous
					c.previous.add(m);//add new previous
				}else if(c.sT == sc + tc1c){
					c.previous.add(m);//two roads leads to c 
				}
			}
			
			c1.out = true;
			for(int n:c1.cCampus){
				if(n==n2 || rooms[n].out) continue;//end point
				findShortestTime(n,n2);
			}
		}
		public String getInefficient(){
			int M = roads.length;
			int N = rooms.length;
			String ineff = "";
			for(int n1=0;n1<N-1;n1++){
				for(int n2=n1+1;n2<N;n2++){
					for(int n=0;n<rooms.length;n++){//refresh
						rooms[n].sT = -1L;//inf
						rooms[n].previous = new LinkedList<Integer>();
						rooms[n].out = false;
					}
					//System.out.println("n1="+n1+",n2="+n2);
					findShortestTime(n1,n2);
					//printMap();
					//printPrevious();
					//
					//printPrevious();
					for(int n=0;n<rooms.length;n++)
						rooms[n].out = false;
					getTrack(n1,n2);
				}
			}
			for(int m=0;m<M;m++){
				if(used[m] == false){
					ineff += "\n" + m;
				}
			}
			return ineff;
		}
		public void getTrack(int n1, int n2){
			if(n1 == n2) return;
			Campus c2 = rooms[n2];
			for(int i=0;i<c2.previous.size();i++){
				int m = c2.previous.get(i);
				used[m] = true;
			}
			c2.out = true;
			for(int i=0;i<c2.previous.size();i++){
				int m = c2.previous.get(i);
				int n = roads[m][0] + roads[m][1] - n2;
				if(rooms[n].out)
					continue;
				getTrack(n1,n);
			}
		}
		
		public void printPrevious(){
			String pre = "";
			for(Campus c:rooms){
				pre = "rooms " + c.n+":";
				for(int i:c.previous)
					pre = pre + i + ",";
				System.out.println(pre);
			}
		}
		public void printMap(){
			String output = "";
			for(int i=0;i<rooms.length;i++){
				output = "rooms "+i +":\n";
				Campus ri = rooms[i];
				for(int m:ri.cRoads){
					output += m + ",";
				}
				output = output + "\n";
				for(int n:ri.cCampus){
					output += n + ",";
				}
				System.out.println(output);
			}
		}
}
class Campus{
	int n;
	LinkedList<Integer> cRoads = new LinkedList<Integer>();//roads that connected to this node
	LinkedList<Integer> cCampus = new LinkedList<Integer>();//nodes that connected to this node
	boolean out = false;//if it is calculated
	LinkedList<Integer> previous = new LinkedList<Integer>();
	long sT = -1;//shortest time from starting room to here
	Campus(int n1){
		n = n1;
	}
}

