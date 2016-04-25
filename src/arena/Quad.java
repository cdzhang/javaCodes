package arena;

import tools.TT;

public class Quad {
	String l1 = " -";
	String l2 = "/ \\";
	String l3 = "\\ /";
	public static void main(String[] args){
		new Quad().test();
	}
	void test(){
		TT.println(l1);
		TT.println(l2);
		TT.println(l3);
		TT.println(l1);
	}
}
