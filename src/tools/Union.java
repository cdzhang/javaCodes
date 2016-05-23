package tools;

public class Union {
	private Union p = this;
	private int rank = 1;
	public static Union find(Union x){
		if(x.p!=x.p.p)
			x.p = find(x.p);
		return x.p;
	}
	public static void unite(Union x,Union y){
		x = find(x);
		y = find(y);
		if(x==y) return;
		if(x.rank < y.rank){
			x.p = y;
		}else{
			y.p = x;
			if(x.rank == y.rank)
				x.rank++;
		}
	}
	public static boolean same(Union x, Union y){
		return find(x)==find(y);
	}
	public void unite(Union y){
		unite(this,y);
	}
	public boolean same(Union y){
		return same(this,y);
	}
}
