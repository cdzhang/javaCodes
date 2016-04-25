package codJam2014;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import tools.TT;

public class Charging {
	long[] power2 = new long[42];
	int N,L;
	long[] outlets,devices;
	Charging(){
		setPower2();
	}
	void setPower2(){
		long l = 1;
		int i = 0;
		while(i<power2.length){
			power2[i] = l;
			l *= 2;
			i++;
		}
	}
	public static void main(String[] args) throws IOException {

		Charging t = new Charging();
		t.solve();

	}
	void run(){
		/*Random rnd = new Random();
		for(long i=0;i<=100;i++){
			long l1 = rnd.nextLong();
			long l2 = rnd.nextLong();
			String s1 = Long.toBinaryString(l1);
			String s2 = Long.toBinaryString(l2);
			Tools.println(s1+"\n"+s2+"\n"+compare(l1,l2));
		}*/
		String s1 = "1111111011011001011110001000001010001010100011100111101100000001";
		String s2 = "1010001111110101001110011000111010101011111000000100000111000000";
		long l1 = TT.convert(s1);
		long l2 = TT.convert(s2);
		TT.println(l1+","+l2+":"+compare(l1,l2));
	    
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		long startTime = System.currentTimeMillis();
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			int[] nl = TT.intArray(in.readLine(), " ");
			N = nl[0];
			L = nl[1];
			String[] outletsS = in.readLine().split(" ");
			String[] devicesS = in.readLine().split(" ");
			outlets = new long[N];
			devices = new long[N];
			for(int n=0;n<N;n++){
				outlets[n] = TT.convert(outletsS[n]);
				devices[n] = TT.convert(devicesS[n]);
			}
			sortDevices();
			//Tools.print(outlets);
			//Tools.print(devices);
			if(caseN==2){
			    for(int i=0;i<N;i++){
			    	TT.println(Long.toBinaryString(devices[i]));
			    }
			}
			//int re = switches(0);
			int re = minF();
			String s = re>=0?re+"":"NOT POSSIBLE";
			String output = "Case #"+caseN + ": "+s;
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
		long endTime = System.currentTimeMillis();
		TT.println(endTime-startTime);
	}
	int switches(int i){
		if(i>=L)
			return 0;
		int time0 = -1;
		int time1 = -1;
		if(i==0){
			int dc0 = 0;
			int dc1 = 0;
			int oc0 = 0;
			int oc1 = 0;
			for(int n=0;n<N;n++){
				if((devices[n] & 1)==0)
					dc0++;
				else
					dc1++;
				if((outlets[n] & 1)==0)
					oc0++;
				else
					oc1++;
			}

			if(dc0==oc0 && dc1==oc1){
				time0 = switches(i+1);
			}
			if(dc0==oc1 && dc1==oc0){
				switchOutlet(0);
				int s2 = switches(i+1);
				if(s2>=0)
					time1 = 1 + s2;
				switchOutlet(0);
			}
			if(time0 < 0 && time1 < 0)
				return -1;
			else if(time0 < 0 && time1 > 0){
				return time1;
			}else if(time0>=0 && time1<0){
				return time0;
			}else{
				return Math.min(time0, time1);
			}
		}else{
			LinkedList<long[]> di = new LinkedList<long[]>();
			long ni_1 = devices[0]%power2[i];
			long[] count = {0,0,ni_1};
			for(int n=0;n<N;n++){
				long ni1 = devices[n]%power2[i];
				int biti = devices[n]%power2[i+1]==ni1?0:1;
				if(ni1!=ni_1){
					di.add(count);
					ni_1 = ni1;
					count = new long[]{0,0,ni_1};
				}
				count[biti] = count[biti]+1;
				if(n==N-1){
					di.add(count);
				}
			}
		/*	for(long[] a:di){
				Tools.println("["+a[0]+" "+a[1]+" "+a[2]+"]");
			}*/
			Map<Long,Long> mi0 = new HashMap<Long,Long>();
			Map<Long,Long> mi1 = new HashMap<Long,Long>();
			for(int n=0;n<N;n++){
				long ni1 = outlets[n]%power2[i];
				Map<Long,Long> mi = outlets[n]%power2[i+1]==ni1?mi0:mi1;
				Long ci = mi.get(ni1);
				if(ci==null){
					mi.put(ni1,1L);
				}else{
					mi.put(ni1, ci+1);
				}
			}
			
			boolean fit = true;
			boolean switchFit = true;
			for(long[] dcount:di){
				long de0 = dcount[0];
				long de1 = dcount[1];
				long ni1 = dcount[2];
				Long ou0 = mi0.get(ni1);
				if(ou0==null) ou0 = 0L;
				Long ou1 = mi1.get(ni1);
				if(ou1==null) ou1 = 0L;
				if(!(de0==ou0 && de1==ou1))
					fit = false;
				if(!(de0==ou1 && de1==ou0))
					switchFit = false;
			}
			if(fit){
				time0 = switches(i+1);
			}
			if(switchFit){
				switchOutlet(i);
				int s2 = switches(i+1);
				switchOutlet(i);
				if(s2>=0)
					time1=1+s2;
			}
		}
		if(time0<0 && time1<0)
			return -1;
		else if(time0>=0 && time1<0)
			return time0;
		else if(time0<0 && time1>0)
			return time1;
		else
			return Math.min(time0, time1);
	}
	String switches1(){
		int time = 0;
		//for i=0;
		int dc0 = 0;
		int dc1 = 0;
		int oc0 = 0;
		int oc1 = 0;
		for(int n=0;n<N;n++){
			if((devices[n] & 1)==0)
				dc0++;
			else
				dc1++;
			if((outlets[n] & 1)==0)
				oc0++;
			else
				oc1++;
		}
		if(dc0==oc0 && dc1==oc1){
		}else if(dc0==oc1 && dc1==oc0){
			time++;
			switchOutlet(0);
		}else{
			return "NOT POSSIBLE";
		}
		for(int i=1;i<L;i++){
			LinkedList<long[]> di = new LinkedList<long[]>();
			long ni_1 = devices[0]%power2[i];
			long[] count = {0,0,ni_1};
			for(int n=0;n<N;n++){
				long ni1 = devices[n]%power2[i];
				int biti = devices[n]%power2[i+1]==ni1?0:1;
				if(ni1!=ni_1){
					di.add(count);
					ni_1 = ni1;
					count = new long[]{0,0,ni_1};
				}
				count[biti] = count[biti]+1;
				if(n==N-1){
					di.add(count);
				}
			}
		/*	for(long[] a:di){
				Tools.println("["+a[0]+" "+a[1]+" "+a[2]+"]");
			}*/
			Map<Long,Long> mi0 = new HashMap<Long,Long>();
			Map<Long,Long> mi1 = new HashMap<Long,Long>();
			for(int n=0;n<N;n++){
				long ni1 = outlets[n]%power2[i];
				Map<Long,Long> mi = outlets[n]%power2[i+1]==ni1?mi0:mi1;
				Long ci = mi.get(ni1);
				if(ci==null){
					mi.put(ni1,1L);
				}else{
					mi.put(ni1, ci+1);
				}
			}
			
			boolean fit = true;
			boolean switchFit = true;
			for(long[] dcount:di){
				long de0 = dcount[0];
				long de1 = dcount[1];
				long ni1 = dcount[2];
				Long ou0 = mi0.get(ni1);
				if(ou0==null) ou0 = 0L;
				Long ou1 = mi1.get(ni1);
				if(ou1==null) ou1 = 0L;
				if(!(de0==ou0 && de1==ou1))
					fit = false;
				if(!(de0==ou1 && de1==ou0))
					switchFit = false;
			}
			if(fit)
				continue;
			else if(switchFit){
				time++;
				switchOutlet(i);
			}else{
				return "NOT POSSIBLE";
			}
		}
		return time + "";
	}
	void switchOutlet(int i){
		long li = power2[i];
		for(int n=0;n<N;n++){
			outlets[n] = outlets[n]^li;
		}
	}
	void sortDevices(){
		for(int i=0;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				if(compare(devices[i],devices[j])>0){
					long l = devices[i];
					devices[i] = devices[j];
					devices[j] = l;
				}
			}
		}
	}
	int compare(long l1, long l2){//l1>=l2
		if(l1==l2)
			return 0;
		long l10 = l1 & 1;
		long l20 = l2 & 1;
		if(l10 > l20)
			return 1;
		else if(l20 > l10)
			return -1;
		else{
			l10 = l1 / 2;
			l20 = l2 / 2;
			return compare(l10,l20);
		}
	}
	int minF(){
		long d=devices[0];
		int count = -1;
		for(int i=0;i<N;i++){
			long oi = outlets[i];
			long f = oi^d;
	    	boolean isAllIn = true;
			boolean[] choosen = new boolean[N];
		    for(int j=0;j<N;j++){
		    	long oj = outlets[j];
		    	long b = oj^f;
		    	boolean isIn = false;
		    	for(int k=0;k<N;k++){
		    		if(choosen[k])
		    			continue;
		    		if(b==devices[k]){
		    			isIn = true;
		    			break;
		    		}
		    	}
		    	if(isIn==false){
		    		isAllIn = false;
		    		break;
		    	}
		    }
		    if(isAllIn){
		    	int c = 0;
		    	while(f>0){
		    		if(f%2==1)
		    			c++;
		    		f /= 2;
		    	}
		    	if(count<0 || count > c)
		    		count = c;
		    }
		}
		return count;
	}
	long flip(long a,long f){
		return a^f;
	}
	long getFlip(long a, long b){
		return a^b;
	}

}
