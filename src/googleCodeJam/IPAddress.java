package googleCodeJam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;

import tools.TT;

public class IPAddress {
	int N;
	LinkedList<IP> ips = new LinkedList<IP>();
	public static void main(String[] args) throws IOException {
		IPAddress t = new IPAddress();
		t.solve();
		//t.test();
		
	}
	public void test(){
		TT.println(Integer.toBinaryString(40));
		TT.println(Integer.toBinaryString(32));
		for(int p=0;p<=8;p++){
			TT.println("p="+p+","+getNorm(128,p)+","+getNorm(2,p));
		}
		TT.println(getNorm(32,5)+","+getNorm(40,5));
		int[] ip1 ={10,2,3,4};
		int[] ip2 ={10,128,2,3}; 
		ip1 = getNorm(ip1,9);
		ip2 = getNorm(ip2,9);
		TT.print(ip1);;
		TT.print(ip2);;
		IP i1 = new IP(ip1,9);
		IP i2 = new IP(ip2,9);
		TT.println(i1.contain(i2));
	}
	public void solve() throws IOException{
		String inputFile = TT.chooseFile();
		String outputFile = TT.getOutputName(inputFile);
		BufferedReader in = new BufferedReader(new FileReader(inputFile));
		PrintWriter out = new PrintWriter(outputFile);
		int T = Integer.parseInt(in.readLine());//first line
		for(int caseN=1; caseN<=T;caseN++){
			ips.clear();
			N = Integer.parseInt(in.readLine());
			for(int n=0;n<N;n++){
				String[] s = in.readLine().split("/");
				int[] ip = TT.intArray(s[0], "\\.");
				int prefix = Integer.parseInt(s[1]);
				ip = getNorm(ip,prefix);
				IP aIP = new IP(ip,prefix);
				add(aIP);
			}
			Collections.sort(ips);
			combineIP();
			String output = "Case #"+caseN + ": ";
			for(IP aip:ips){
				output = output + "\n" + aip.toString2();
			}
			System.out.println(output);
			out.println(output);
		}
		in.close();
		out.close();
	}
	public void add(IP aIP){
		for(IP bIP:ips){
			int r = bIP.contain(aIP);
			if(r == 1)
				return;
			if(r == -1){
				bIP = aIP;
				return;
			}
			if(r==2){
				int pre = aIP.prefix - 1;
				int[] ip = bIP.ip;
				ip = getNorm(ip,pre);
				bIP = new IP(ip,pre);
				return;
			}
		}
		ips.add(aIP);
	}
	void combineIP(){
		int n = ips.size();
		if(n==1)
			return;
		int count = 0;
		for(int i=0;i<n-1;i++){
			IP i1 = ips.get(i);
			IP i2 = ips.get(i+1);
			int r = i1.contain(i2);
			if(r==2){
				int pre = i1.prefix - 1;
				int[] ip = i1.ip;
				ip = getNorm(ip,pre);
				IP newIP = new IP(ip,pre);
				ips.remove(i+1);
				ips.remove(i);
				ips.add(i,newIP);
				i--;
				n = ips.size();
				count++;
			}
		}
		if(count > 0){
			combineIP();
		}
	}
	public static int getNorm(int i,int p){
		int x = 1;
		for(int j=1;j<=8-p;j++){
			x = x*2;
		}
		return i / x * x;
	}
	int[] getNorm(int[] ip,int pre){
		int I = pre / 8;
		int p = pre % 8;
		int[] nip = new int[4];
		for(int i=0;i<I;i++){
			nip[i] = ip[i];
		}
		if(I!=4){
			nip[I] = getNorm(ip[I],p);
		}
		//Tools.println("I="+I+",p="+p+",getNorm="+getNorm(ip[I],p)
		//		+"IP[I] ="+ip[I]);
		return nip;
	}
}
class IP implements Comparable<IP>{
	int[] ip;
	int prefix;
	IP(int[] ip1, int prefix1){
		ip = ip1;
		prefix = prefix1;
	}
	int contain(IP i1){//this contains i1, 1
		//i1 contains this, -1
		//neither 0
		//can combine 2
		int pre = Math.min(this.prefix, i1.prefix);
		int I = pre / 8;
		if(pre%8==0)
			I = I-1;
		for(int i=0;i<I;i++){
			if(this.ip[i] != i1.ip[i])
				return 0;
		}
		pre = pre - I*8;
		int n1 = IPAddress.getNorm(this.ip[I],pre);
		int n2 = IPAddress.getNorm(i1.ip[I], pre);
		if(n1==n2){
			if(this.prefix <= i1.prefix)
				return 1;
			else
				return -1;
		}else{
			int n11 = IPAddress.getNorm(this.ip[I],pre-1);
			int n22 = IPAddress.getNorm(i1.ip[I],pre-1);
			if(n11 != n22)
				return 0;
			else{
				if(this.prefix == i1.prefix){
				//Tools.println(toString() +"+"+ i1+"n11="+n11+",n22="+n22+",I="+I);
					return 2;
				}
			}
		}
		return 0;
	}
	public String toString(){
		return ip[0] + "." + ip[1] + "." + ip[2] + "." +
	ip[3] + "/" + prefix;
	}
	public String toString2(){
		return binaryString(ip[0]) +"."+binaryString(ip[1]) +"."+binaryString(ip[2]) +"." +
				binaryString(ip[3]) +"/" + prefix;
	}
	public String binaryString(int i){
		String si = Integer.toBinaryString(i);
		int I = si.length();
		for(int j=0;j<8-I;j++){
			si = "0" + si;
		}
		return si;
	}
	public int compareTo(IP i1) {
		for(int i=0;i<4;i++){
			if(this.ip[i] > i1.ip[i])
				return 1;
			if(this.ip[i] < i1.ip[i])
				return -1;
		}
		return 0;
	}
}