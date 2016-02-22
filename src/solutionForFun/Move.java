package solutionForFun;

import java.util.LinkedList;

public class Move {
	int type;
	//0: pass
	//1: 单牌
	//****************
	//2: 一对
	//3: 3只
	//4: 3带1
	//5: 3带1对
	//6: 炸弹
	//7: 顺子

	//8: 连对
	//9: 飞机
	
	
	LinkedList<Card> move;
	Move(LinkedList<Card> move, int type){
		this.move = (LinkedList<Card>) move.clone();
		this.type = type;
	}
	Move(LinkedList<Card> move){
		this.move = (LinkedList<Card>) move.clone();
		type = getType(move);
	}
	boolean largerThan(Move m1){
		if(m1.type == 0){
			if(type == 0){
				return false;
			}
			return true;
		}else if(type != m1.type){
			return false;
		}else if(type == 1){
			Card thisC = move.get(0);
			Card c1 = m1.move.get(0);
			if(c1.number == 1 || c1.number ==2){
				if(thisC.number == 14){
					return true;
				}else if(c1.number == 1 && thisC.number == 2){
					return true;
				}
				return false;
			}else if(c1.number >=3 && c1.number <=13 ){
				if(thisC.number >=3 && thisC.number <=13 && thisC.number <= c1.number)
					return false;
				else
					return true;
			}else if(c1.number ==14 && c1.suit == 1){
				if(thisC.number == 14 && thisC.suit ==2)
					return true;
				else
					return false;
			}else if(c1.number == 14 && c1.suit ==2){
				return false;
			}
		}
		return false;
	}
	static int getType(LinkedList<Card> move){
		if(move.size()==0){
			return 0;//pass
		}else if(move.size()==1){
			return 1;//single card
		}else
			return -1;
	}
	
	int getType_Back(LinkedList<Card> move){
		int t = 0;
		if(move.size()==0){
			return 0;//pass
		}else if(move.size()==1){
			return 1;//单牌
		}else if(move.size()==2){
			if(move.get(0).number == move.get(1).number){
				if(move.get(0).number == 14){
					return 6;//王炸
				}
				return 2;//pair
			}
			return -1;//error
		}else if(move.size()==3){
			if(move.get(0).number == move.get(1).number 
					&& move.get(0).number == move.get(2).number){
				return 3;//3 same
			}
			return -1;
		}else if(move.size()==4){//炸弹（6） or 3带1（4） 
			int n0 = move.get(0).number;
			int n1 = move.get(1).number;
			int n2 = move.get(2).number;
			int n3 = move.get(3).number;
			if(n0 == n1 && n1 == n2 && n2==n3){
				return 6;
			}
		}
		return t;
	}
	boolean isType(int t){
		if(t==0){
			
		}else if(t==1){
			
		}else if(t==2){
			
		}else if(t==3){
			
		}else if(t==4){
			
		}else if(t==5){
			
		}else if(t==6){
			
		}else if(t==7){
			
		}else if(t==8){
			
		}
		return false;
	}
}
