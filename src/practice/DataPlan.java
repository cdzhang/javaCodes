package practice;
import tools.TT;
/*
 * Singtel has 3 kinds of value data plan, $7 for 1024mb, $2 for 100mb and $1 for 10mb.
 * every plan has 7 days of expiration date. however, if you buy any kind of value plan from the 3
 * before your data expires, the expiration date of plan can be postponed for 7 days.
 * The question is, you are going to use n weeks of plan, and every week you use x amount of data,
 * What's the best scheme that you will spend least on the data for n weeks.
 */
public class DataPlan {
	public static void main(String[] args){
		DataPlan s = new DataPlan();
		for(int i=1;i<100;i++){
			TT.println(i+":"+s.ts(s.maxValue(150,i)));
		}
	}
	int[] maxValue(double x,int n){
		int[] data = new int[3];
		double tx = n*x;
		double min = -1;
		for(int n1=0;n1<=n;n1++){
			for(int n2=0;n2+n1<=n;n2++){
				int n3 = n-n1-n2;
				if(n1*1024 + n2*100+n3*10<tx)
					continue;
				double price = 7*n1+2*n2+1*n3;
				if(min < 0||min > price){
					min = price;
					data = new int[]{n1,n2,n3};
				}
			}
		}
		return data;
	}
	String ts(int[] a){
		String s = "";
		for(int b:a)
			s+=b+" ";
		return s;
	}
}
