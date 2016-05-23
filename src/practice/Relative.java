package practice;

import tools.TT;
import tools.Union;

public class Relative extends Union{
	int i = 1;
	Relative(int i1){
		i = i1;
	}
	public static void main(String[] args){
		Relative[] ps = new Relative[11];
		for(int n=1;n<=10;n++){
			ps[n] = new Relative(n);
		}
		int[][] rl = {
				{2, 4}, 
				{5,7},
				{1, 3},
				{8, 9},
				{1, 2},
				{5, 6},
				{2, 3}};
		for(int[] r:rl)
			unite(ps[r[0]],ps[r[1]]);
		int[][] ck = {{3,4},{7,10},{8,9}};
		for(int[] c:ck){
			if(same(ps[c[0]],ps[c[1]]))
				TT.println("yes");
			else
				TT.println("no");
		}
	}
}
