package practice;

import tools.Tools;

public class Dict {
	public static void main(String[] args){
		Dict d = new Dict();
		Tools.println(d.getT("ACDBCADDFEBDIEAFDKB"));
	}
	String getT(String S){
		int L = S.length();
		int first = 0;
		int last = L - 1;
		
		String T = "";
		while(first <= last){
			char f = S.charAt(first);
			char l = S.charAt(last);
			boolean left = true;
			if(f > l){
				left = false;
			}else if (f < l){
			}else{//f == l
				int fi = first + 1;
				int li = last - 1;
				while(fi <= li && S.charAt(fi)==S.charAt(li)){
					fi++;
					li--;
				}
				if(fi <=li && S.charAt(fi) > S.charAt(li)){
					left = false;
				}
			}
			if(left){
				T += f;
				first++;
			}else{
				T += l;
				last--;
			}
		}
		
		return T;
	}

}
