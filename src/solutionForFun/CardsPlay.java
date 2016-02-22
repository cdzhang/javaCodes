package solutionForFun;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CardsPlay {

	public static void main(String[] args) {
		LinkedList<Double> d = new LinkedList<Double>();
		Double e1 = new Double(1.0);
		Double e2 = new Double(2.0);
		Double e3 = new Double(3.0);
		d.add(e1);
		d.add(e2);
		d.add(e3);
		Double e4 = new Double(4.0);
		d.remove(e2);
		d.add(e2);
		for(Double a:d){
			System.out.print(a+" ");
		}
		System.out.println();
		// TODO Auto-generated method stub

	}
    boolean willWin(Player A,Player B, Move BLastMove){
    	//B's last shoot is BLastShoot
    	LinkedList<Move> nextMoves = A.nextLegalMoves(BLastMove);
    	//all possible moves of A
    	for(Move AMove:nextMoves){
    		Player vPlayer = A.nextMove(AMove);
    		if(vPlayer.cards.size()==0){
    			return true;
    		}else{
    			if(!willWin(B,A,AMove)){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public static void sortCards(LinkedList<Card> cards){
    	LinkedList<Card> newCards = new LinkedList<Card>();
    	while(cards.size() > 0){
	    	Card c1=cards.get(0);
	    	for(Card c:cards){
	    		if(c.number < c1.number){
	    			c1 = c;
	    		}
	    	}
	    	newCards.add(c1);
	    	cards.remove(c1);
    	}
    	for(Card c:newCards){
    		cards.add(c);
    	}
    }
}


