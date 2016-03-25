package practice;

public class Run {

	int[] w = {2,1,3,2};
	int[] v = {3,2,4,2};
	int n = 4;
	boolean[] used = new boolean[4];
	public static void main(String[] arg){
		Run r = new Run();
		Tools.println(r.maxValue(5));
	}
	int maxValue(int W){
		int max = 0;
		if(W<=0)
			return 0;
		for(int i=0;i<n;i++){
			int maxi = 0;
			if(!used[i] && w[i]<=W){
				used[i] = true;
				maxi = v[i] + maxValue(W-w[i]);
				if(maxi>max)
					max = maxi;
				used[i] = false;
			}
		}
		return max;
	}
}
