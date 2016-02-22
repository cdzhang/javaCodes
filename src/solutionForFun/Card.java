package solutionForFun;

public class Card{
	int number;//1,2,....,13,14, 14 means 
	int suit; //1:spades, 2:hearts, 3:diamonds, 4:clubs 
	String name;
	Card(int n, int su){
		number = n;
		suit = su;
		switch (number){
			case 11: name = "J"; break;
			case 12: name = "Q"; break;
			case 13: name = "K"; break;
			case 14: name = "master"; break;
			case 15: name = "master"; break;
			default: name = number+""; break;
		}
	}
	public Card copy(){
		return new Card(number,suit);
	}
	public boolean isSame(Card aCard){
		if(number == aCard.number && suit == aCard.suit){
			return true;
		}
		return false;
	}
}
