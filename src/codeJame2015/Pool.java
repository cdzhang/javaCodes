package codeJame2015;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

public class Pool {
	double err = 1e-9;
	LinkedList<Tap> taps = new LinkedList<Tap>();
	int N;
	double V,X;
	public static void main(String[] args) throws IOException {
		Pool t = new Pool();
		t.solve();
		double Rc=1e-4;
		double Rh=1e-4;
		double Cc=57;
		double Ch=97;
		Tools.println((Ch+Cc)/2);
		Tools.println((Ch*Rh+Cc*Rc)/(Rc+Rh));
	}
	public void solve() throws IOException{
		String inputFile = Tools.chooseFile();
		String outputFile = Tools.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			taps.clear();
			String[] l = in.readLine().split(" ");
			N = Integer.parseInt(l[0]);
			V = Double.parseDouble(l[1]);
			X = Double.parseDouble(l[2]);
			for(int n=0;n<N;n++){
				String[] l1 = in.readLine().split(" ");
				double Ri = Double.parseDouble(l1[0]);
				double Ci = Double.parseDouble(l1[1]);
				Tap atap = new Tap(Ri,Ci);
				taps.add(atap);
			}
			Collections.sort(taps);
			String output = "Case #"+caseN + ": "+minTime();
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	String minTime(){
		double speed = maxSpeed();
		//Tools.println("speed="+speed);
		if(speed<0)
			return "IMPOSSIBLE";
		return (V/speed)+"";
	}
	double maxSpeed(){
		if(taps.getFirst().C > X || taps.getLast().C < X)
			return -1;
		int cI = 0;
		for(;cI<N;cI++){
			double C = taps.get(cI).C;
			if(C>=X)
				break;
		}
		cI--;
		int hI = N-1;
		for(;hI>0;hI--){
			if(taps.get(hI).C<=X)
				break;
		}
		hI++;
		double speed = 0;
		for(int i=cI+1;i<hI;i++){
			speed += taps.get(i).R;
		}
		double copen = 0;
		double hopen = 0;
		while(cI>=0 && hI<=N-1){
			double Rc = taps.get(cI).R;
			double Cc = taps.get(cI).C;
			double Rh = taps.get(hI).R;
			double Ch = taps.get(hI).C;
			double mx = ((1-copen)*Rc*Cc+(1-hopen)*Rh*Ch)/((1-copen)*Rc+(1-hopen)*Rh);
			Tools.println("Rc="+Rc+",Rh="+Rh+"copen="+copen+",hopen="+hopen+ ",mx="+mx+",Cc="+Cc+",Ch="+Ch);
			if(Math.abs(mx-X)<=err){
				speed += (1-hopen)*Rh + (1-copen)*Rc;
				cI--;hI++; hopen=0;copen=0;
			}else if(mx > X){
				double ahopen = (1-copen)*Rc*(X-Cc)/(Rh*(Ch-X));
				speed += (1-copen)*Rc + ahopen*Rh;
				hopen += ahopen;
				cI--; copen=0;
			}else if(mx < X){
				double acopen = (1-hopen)*Rh*(Ch-X)/(Rc*(X-Cc));
				speed += (1-hopen)*Rh + acopen*Rc;
				copen += acopen;
				hI++; hopen=0;
			}else{
				
			}
		}
		return speed;
	}
}
class Tap implements Comparable<Tap>{
	double R;
	double C;
	Tap(double R, double C){
		this.R = R;
		this.C = C;
	}
	public int compareTo(Tap t) {
		if(C>t.C)
			return 1;
		else if(C<t.C)
			return -1;
		return 0;
	}
	
}