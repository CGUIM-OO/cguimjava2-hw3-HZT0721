import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
//import java.util.Scanner;



/**
 * @author TODO: B0544238¶À¤l·Ê
 * Card class:
 * 1.change instance field from int suit to Suit suit
 * 2.change constructor from (int s, int r) to (Suit s, int r)
 * 3.change printCard method : print(suit,int rank)
 * 4.change getsuit method :return suit;
 * 
 * Deck class:
 * 1.Add new Filed:ArrayList<Card> usedCard & public int nUsed
 * 2.add shuffle() method: replace all the card in usedCard back to decks  
 * make a random number to pick a random card to move to the first card,repeat it again and again
 * after all reset nUsed  
 * 3.add getOneCard() method: when decks is empty, call shuffle method
 * return the first card and delete the first card .In the same time, put that card into usedCard and nUsed+1
 * 
 * HW3 class:
 * 1.get nDeck by keyboard(scanner) key in
 * 2.create a deck
 * 3.get 2 cards and print them
 * 4.shuffle it
 * 
 * little problem :the test"isShuffleWorking" can only test correctly when nDeck is 1 because it test nUsed has been reset or not after get 52 cards
 *  But when nDeck >1 ,we don't need to reset(shuffle) after get 52 cards (decks is not empty) 
 * 
 */
public class HW3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("input N(deck of cards):");
		String testn= sc.nextLine(); 
		int nDeck=Integer.parseInt(testn);
		
		Deck deck=new Deck(nDeck);
				
		Card newCard=deck.getOneCard();
		newCard.printCard();
		Card newCard2=deck.getOneCard();
		newCard2.printCard();
		deck.shuffle();
		
		if(isAllCardsCorrect(deck.getAllCards(),nDeck)){
			if(!isShuffleWorking(deck,newCard,newCard2)){
				System.out.println("All Card: Well done! But shufller is not working");
			}else{
				System.out.println("Well done!");
			}
			
		}else{
			System.out.println("All Card: Error, please check your sourse code");
		}

	}
		
	/**
	 * This method is used for checking your result, not a part of your HW3
	 */
	private static boolean isShuffleWorking(Deck deck,Card newCard,Card newCard2){
		deck.shuffle();
		boolean isCorrect=true;
		if(newCard.getSuit().equals(newCard2.getSuit()) &&
				newCard.getRank()==newCard2.getRank()){
					isCorrect=false;
					return isCorrect;
		}
		for(int i=0;i<53;i++){
			deck.getOneCard();
		}
		if(deck.nUsed!=1){
			isCorrect=false;
		}
		return isCorrect;
	}
	
	private static boolean isAllCardsCorrect(ArrayList<Card> allCards,int nDeck){
		//check the output 
		boolean isCorrect=true;;
		HashMap <String,Integer> checkHash=new HashMap<String,Integer>();
		for(Card card:allCards){
			Card.Suit suit= card.getSuit();
			int rank = card.getRank();
			if(rank>13||rank<1){
				isCorrect=false;
				break;
			}
			
			if(checkHash.containsKey(suit+","+rank)){
				
				checkHash.put(suit+","+rank, 
						checkHash.get(suit+","+rank)+1);
			}else{
				checkHash.put(suit+","+rank, 1);
				
			}

		}
		if(checkHash.keySet().size()==52){
			for(int value:checkHash.values()){
				if(value!=nDeck){
					
					isCorrect=false;
					break;
				}
			}
		}else{
			
			isCorrect=false;
		}
		return isCorrect;
	}

}
