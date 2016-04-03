package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tools.Tools;

public class Rabbit {
	int G,W,H,B,X;
	long D;
	int day = 24*3600;
	LinkedList<Integer> record = new LinkedList<Integer>();
	LinkedList<Long> recordDay = new LinkedList<Long>();
	LinkedList<Long> recordCount = new LinkedList<Long>();
	public static void main(String[] args) throws IOException {
		Rabbit t = new Rabbit();
		t.solve();
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			record.clear();
			recordDay.clear();
			recordCount.clear();
			G = getTime(in.readLine());
			W = getTime(in.readLine());
			H = getTime(in.readLine());
			B = getTime(in.readLine());
			X = getTime(in.readLine());
			D = Long.parseLong(in.readLine());
			String output = "Case #"+caseN + ": "+press();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	long press(){
		if(D==0) return 0;
		if(H - W + 1 >X){
			return -1;
		}
		if(G+day-B+1 > X){
			if(D==1)
				return pressOne();
			return -1;
		}
		int time = findNext(0);
		if(time < 0){
			Tools.println("here");
			return -1;
		}
		long psDay = 0;
		long count = 1;
		boolean cFound = false;
		while(psDay < D){
			int[] g = findTime(time);
			int n = g[0];
			time = g[1];
			//time = findNext(time);
			//Tools.println(time);
			if(time < 0)
				return -1;
			if(time >= day){
				time = time % day;
				psDay++;
			}
			count+=n;
			if(psDay == D-1 && time + X >=day)
				return count;
			if(cFound == false && psDay <= D-2){
				int c = contains(time);
				if(c==-1){
				  record.add(time);
				  recordDay.add(psDay);
				  recordCount.add(count);
				}
				else{
					cFound = true;
					long period = count - recordCount.get(c);
					long periodDay = psDay - recordDay.get(c);
					long pN = (D-2-psDay)/periodDay;
					count = count + pN*period;
					psDay = psDay + pN*periodDay;
					record.clear();
					recordDay.clear();
				}
			}
		}
		return count;
	}
	long pressOne(){
		//if(D!=1) return -2;
		int time = findNext(0);
		if(time < G){
			Tools.println("D=1 start cannot");
			return -1;
		}
		long count = 1;
		while(true){
			int[] g = findTime(time);
			int n = g[0];
			time = g[1];
			count+=n;
			if(time + X >= day){
				return count;
			}else if(time == B-1){
				return -1;
			}
		}
	}
	int contains(int i){
		int L = record.size();
		int index = -1;
		for(int j=0;j<L;j++){
			if(record.get(j)==i){
				index = j;
				break;
			}
		}
		return index;
	}
	int getTime(String s){
		int[] a = Tools.intArray(s,":");
		return (3600*a[0] + 60*a[1] + a[2]);
	}
	int findNext(int ptime){//find next press time
		if(ptime == 0 && X < G){
			Tools.println("here");
			return -1;//Day 0
		}
		int tentative = ptime + X;
		int t = tentative % day;
		if((t>=G && t<W)||(t>=H && t<B))
			return tentative;
		else if((t>=W && t<H)){
			return W - 1 + (tentative / day)*day;
		}else{
			return B - 1;
		}
	}
	/*int[] findTime(int ptime){
		int pt = ptime;
		int[] g = {0,-1};
		boolean a = false;
		if(pt>=G && pt < W){
			int n = (W-1-pt)/X;
			int r = (W-1-pt)%X;
			if(n>=1){
				if(r==0){
					g[0] = n;
				}else{
					g[0] = n+1;
				}
				g[1] = W-1;
			}else{
				a=true;
			}
		}else if(pt >=H && pt < B){
			int n = (B-1-pt)/X;
			int r = (B-1-pt)%X;
			if(n>=1){
				if(r==0){
					g[0] = n;
				}else{
					g[0] = n+1;
				}
				g[1] = B-1;
			}else{
				a = true;
			}
		}
		if(a){
			g[0] = 1;
			int tentative = ptime + X;
			int t = tentative % day;
			if((t>=G && t<W)||(t>=H && t<B)){
				g[1] = tentative;
				Tools.print("h3");
			}
			else if((t>=W && t<H)){
				Tools.print("h2");
				g[1] = W - 1 + (tentative / day)*day;
			}else{
				Tools.print("h1");
				g[1] = B - 1;
		     }
	    }
		if(g[1]==-1)
			Tools.println("hehe");
		return g;
   }*/
	int[] findTime(int ptime){//cannot be day0 0am
		/*if(ptime==0 && ptime!=G){
			Tools.println("cannot be 0 here");
		}*/
		int pt = ptime;
		int[] g = {0,-1};
		boolean a = false;
		if(pt>=G && pt < W){
			int n = (W-1-pt)/X;
			if(n>=1){
				g[0] = n;
				g[1] = pt + n*X;
				return g;
			}else{
				a=true;
			}
		}else if(pt >=H && pt < B){
			int n = (B-1-pt)/X;
			if(n>=1){
				g[0] = n;
				g[1] = pt + n*X;
			}else{
				a = true;
			}
		}
		if(a){
			g[0] = 1;
			g[1] = findNext(ptime);
	    }
		return g;
   }
}
class Time{
	int time;
	Time(){}
	Time(String s){
		int[] a = Tools.intArray(s,":");
		time = 3600*a[0] + 60*a[1] + a[2];
	}
  }
