package practice;

import tools.Tools;

public class AddK {
	int[] a = {1,2,3,5,10,100,20};
	int K = 88;
	int n = a.length;
	public static void main(String[] arg){
		AddK ad = new AddK();
		Tools.print(ad.canFind(0, 0));
	}
	boolean canFind(int i, int sum){
		if(i==n-1) return sum==K;
		if(canFind(i+1,sum)) return true;
		if(canFind(i+1,sum+a[i])) return true;
		return false;
	}
}
