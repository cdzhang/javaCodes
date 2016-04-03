package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

import tools.Tools;

public class Travelling {
	Set<City> visited = new HashSet<City>();
	Set<City> dead = new HashSet<City>();
	LinkedList<City> route = new LinkedList<City>();
	//LinkedList<City> next = new LinkedList<City>();
	LinkedList<City> next = new LinkedList<City>();
	Stack<City> stack = new Stack<City>();
	City[] cities;
	int N, M, count;
	public static void main(String[] args) throws IOException {
		Travelling t = new Travelling();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			visited.clear();
			next.clear();
			stack.clear();
			dead.clear();
			route.clear();
			count = 0;
			int[] nm = Tools.intArray(in.readLine(), " ");
			N = nm[0];
			M = nm[1];
			cities = new City[N+1];
			for(int n=1;n<=N;n++){
				int zip = Integer.parseInt(in.readLine());
				cities[n] = new City(n,zip);
			}
			for(int m=1;m<=M;m++){
				int[] rd = Tools.intArray(in.readLine()," ");
				int r1 = rd[0];
				int r2 = rd[1];
				City c1 = cities[r1];
				City c2 = cities[r2];
				c1.connected.add(c2);
				c2.connected.add(c1);
			}
			for(int n=1;n<=N;n++){
				Collections.sort(cities[n].connected);
			}
			/*if(caseN!=70)
				continue;*/
			
			String output = "Case #"+caseN + ": "+getNumber1();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String getNumber1(){
		String number = "";
		int index = 1;
		for(int n=2;n<=N;n++){
			if(cities[n].zip < cities[index].zip)
				index = n;
		}
		count++;
		City start = cities[index];
		route.add(start);
		next.addAll(start.connected);
		Collections.sort(next);
		number += start.zip;
		while(count < N){
			LinkedList<City> tmp_route = new LinkedList<City>();
			Set<City> tmp_visited = new HashSet<City>();
			City nextCity = next.get(0);
			int size = next.size();
			for(int i=0;i<size;i++){
				City tentative = next.get(i);
				tmp_route.clear();
				tmp_route.addAll(route);
				tmp_visited.clear();
				tmp_visited.addAll(visited);
				City last = tmp_route.getLast();
				while(!last.isConnected(tentative)){
					tmp_visited.add(last);
					tmp_route.removeLast();
					last = tmp_route.getLast();
				}
				tmp_route.add(tentative);
				if(isConnected(tmp_visited,tmp_route)){
					nextCity = tentative;
					break;
				}
			}
			route = tmp_route;
			visited = tmp_visited;
			count++;
			number += nextCity.zip;
			if(count == N){
				return number;
			}
			next.clear();
			for(City cr:route){
				for(City cn:cr.connected){
					if(!visited.contains(cn) && !next.contains(cn) && !route.contains(cn)){
						next.add(cn);
					}
				}
			}
			Collections.sort(next);
		}
		return number;
	}
	boolean isConnected(Set<City> tmp_dead,LinkedList<City> tmp_route){
		for(City c:tmp_route){
			setState(c,tmp_dead);
		}
		boolean result = true;
		for(int n=1;n<=N;n++){
			City c = cities[n];
			if(!tmp_dead.contains(c) && c.state==0){
				result = false;
				break;
			}
		}
		for(int n=1;n<=N;n++){
			City c = cities[n];
			c.state = 0;
		}
		return result;
	}
	void setState(City c,Set<City> tmp_dead){
		c.state = 1;
		for(City ct:c.connected){
			if(!tmp_dead.contains(ct) && ct.state == 0){
				setState(ct,tmp_dead);
			}
		}
	}
}
class City implements Comparable<City>{
	int id;
	int zip;
	int state = 0;
	City(int id,int zip){
		this.id = id;
		this.zip = zip;
	}
	LinkedList<City> connected = new LinkedList<City>();
	boolean isConnected(City c){
		for(City ci:connected){
			if(ci==c)
				return true;
		}
		return false;
	}
	public int compareTo(City c) {
		if(zip > c.zip)
			return 1;
		else if(zip < c.zip)
			return -1;
		else
		return 0;
	}
}
