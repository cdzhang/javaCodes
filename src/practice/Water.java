package practice;

import java.util.Scanner;

import tools.TT;

public class Water {
	public static void main(String[] arg){
		Water w = new Water();
		Scanner user_input = new Scanner( System.in );
		int i = 0;
		TT.println("input "+i+":");
		String x = user_input.next();
		String[] s = new String[10];
		s[i] = x;
		while(i<9){
			i++;
			//Tools.println("input "+i+":");
			x = user_input.next();
			s[i] = x;
			//System.out.println(x.length());
		}
		user_input.close();
		int R = s.length;
		int C = s[0].length();
		TT.println("\n"+R+","+C);
		char[][] field = new char[R][C];
		for(int m=0;m<R;m++){
			for(int n=0;n<C;n++){
				field[m][n] = s[m].charAt(n);
			}
		}
		for(String as:s){
			System.out.println(as);
		}
		TT.println(w.countPool(field));
	}
	public int countPool(char[][] field){
		int R = field.length;
		int C = field[0].length;
		int count = 0;
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				if(field[i][j]=='W'){
					count++;
					spread(field,i,j);
				}
			}
		}
		return count;
	}
	public void spread(char[][] field,int i,int j){
		int R = field.length;
		int C = field[0].length;
		field[i][j] = '.';
		for(int m=i-1;m<=i+1;m++){
			for(int n=j-1;n<=j+1;n++){
				if(m==i&&n==j)
					continue;
				if(m>=0&&m<R&&n>=0&&n<C&&field[m][n]=='W')
					spread(field,m,n);
			}
		}
	}
}
