package practice;

import tools.TT;

public class Perm {
	int n = 5;
	boolean[] used = new boolean[n];
	int[] perm1 = new int[n];
	public static void main(String[] arg){
		Perm p = new Perm();
		p.perm(0,5);
	}
	//this is a commented line
	void perm(int pos,int n){
		if(pos==n){
			TT.print(perm1);
			return;
		}
		for(int i=0;i<n;i++){
			if(!used[i]){
				used[i]=true;
				perm1[pos]=i;
				perm(pos+1,n);
				used[i]=false;
			}
		}
	}
}
