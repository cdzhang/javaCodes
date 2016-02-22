package googleCodeJam;

public class Egg {
	public static void main(String[] arg){
		// x% 1 = 0;
		//x % 2 = 1;
		//x % 3 = 0;
		//x % 4 = 1;
		//x % 5 = 4; -> x must be *4 or * 9, combine with 2, x must be *9
		//x % 6 = 3;
		//x % 7 = 5; 
		//x % 8 = 1;
		//x % 9 = 0; 
		for(int x = 9;x<=2520;x+=10){
			if(x%3==0 && x%4==1 && x%6==3 && x%7==5 && x%8 == 1 && x%9==0){
				System.out.println(x);
			}
		}
		for(int n = 0;n<20;n++){
			System.out.println("n="+n+";"+(369+2520*n));
			System.out.println("n="+n+";"+(1629+2520*n));
		}
		Egg e = new Egg();
		e.print(369);
		e.print(1629);
		
	}
	private void print(int x){
		System.out.println("x%1="+(x%1));
		System.out.println("x%2="+(x%2));
		System.out.println("x%3="+(x%3));
		System.out.println("x%4="+(x%4));
		System.out.println("x%5="+(x%5));
		System.out.println("x%6="+(x%6));
		System.out.println("x%7="+(x%7));
		System.out.println("x%8="+(x%8));
		System.out.println("x%9="+(x%9));
	}
}
