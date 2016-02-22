package solutionForFun;

import java.util.LinkedList;
public class Player {
	String name = "";
	LinkedList<Card> cards = new LinkedList<Card>();
   // LinkedList<LinkedList<CardsPlayCard>> legalMovements = 
    //		new LinkedList<LinkedList<CardsPlayCard>>();
	Player(LinkedList<Card> cards){
		this.cards = cards;
	}
	LinkedList<Move> nextLegalMoves(Move competitorLastMove){
		Move m = competitorLastMove;
		LinkedList<Move> allMoves = new LinkedList<Move>();
		int type=m.type;
		if(type==0){
		//********************need to think about it	
		}else if(type==1){
			for(Card aCard:cards){
				LinkedList<Card> l = new LinkedList<Card>();
				l.add(aCard);
				Move aMove = new Move(l,1);
				allMoves.add(aMove);
			}
			LinkedList<Card> l = new LinkedList<Card>();
			Move aMove = new Move(l,0);//pass
		}else if(type==2){
		}
    	return allMoves;
    }
    Player nextMove(Move S){
    	//return a virtual player if this player shoots aShoot
    	LinkedList<Card> cardsCopy = new LinkedList<Card>();
    	LinkedList<Card> aShoot = S.move;
    	for(Card aCard:cards){
    		cardsCopy.add(aCard.copy());
    	}//clone cardsCopy
    	for(Card aCard: aShoot){//remove cards in aShoot from cards
    		for(Card anotherCard:cardsCopy){
    			if(anotherCard.isSame(aCard)){
    				cardsCopy.remove(anotherCard);
    				break;
    			}
    		}
    	}
    	Player vPlayer = new Player(cardsCopy);
    	return vPlayer;
    }
    boolean isLegal(LinkedList<Card> aShoot){//detemine if a Shoot is leagal
    	//鍗曞彧锛岄『瀛�
    	//涓�瀵癸紝杩炲
    	//3涓紝3甯�1锛�3甯�1瀵癸紝椋炴満
    	//鐐稿脊锛岀帇鐐革紝4甯�2
    	int n = aShoot.size();
    	if(n==1){
    		
    	}else if(n==2){
    		
    	}else if(n==3){
    		
    	}else if(n==4){
    		
    	}else if(n==5){
    		
    	}
    	return false;
    }
}
