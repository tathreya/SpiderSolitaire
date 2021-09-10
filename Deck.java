import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
public class Deck {
    
    /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
    
    private ArrayList<Card> deckOfCards;
    private static int numCards;
    private int cardsUsed;
    /**
     * Creates a new <code>Deck</code> instance.
     *
     * @param numCards the number of cards in the deck
     */
    
    public Deck() {
        this.deckOfCards = new ArrayList<Card>();
        
    }
    
    // str to deck method
    public Deck (String str) {
        this.deckOfCards = new ArrayList<Card>();
       
        Scanner keyboard = new Scanner(str);
        while (keyboard.hasNext()){ 
            String symbol = keyboard.next();
            int value = keyboard.nextInt();
            boolean isFaceUp = false;
            if (keyboard.next().equals("u")) {
                isFaceUp = true;
            }
            else {
                isFaceUp = false;
                
            }
           
            Card c = new Card(symbol, value);
            c.setFaceUp(isFaceUp);
            deckOfCards.add(c);
        }
        
    }
    public Deck (int numCards) {

        this.numCards = numCards;
        this.deckOfCards = new ArrayList<Card>(this.numCards);
        
    }
    
    public void sort() {
    	for (int outer = deckOfCards.size()-1; outer > 0 ; outer --) {
			
			int biggest = deckOfCards.get(outer).getValue();
			int biggestIndex = outer;
					
			for (int inner = 0; inner < outer ; inner ++) {
				if (deckOfCards.get(inner).getValue() > biggest) {
					biggest = deckOfCards.get(inner).getValue();
					biggestIndex = inner;
					
				}
			}
			
			Collections.swap(deckOfCards, outer, biggestIndex);
			//int temp = arr[biggestIndex];
			//arr[biggestIndex] = arr[outer];
			//arr[outer] = temp;
			
			
			
		
		}
    	
    }
    
    
    /**
     * Adds a card to the deck
     * 
     * @param c The card to be added to the deck
     */
    public void addCard(Card c) {
        deckOfCards.add(c);

    }
    
    public void removeCard(int index) {
        deckOfCards.remove(index);
        
    }
    
    /**
     * Shuffles the deck by swapping the curr index with a random index
     */
    public void shuffle() {
        for (int i = 0; i < numCards ; i++) {
            // generates a random index number to swap
            int randomIndex = i + (int)(Math.random() * (numCards-i));
            Collections.swap(deckOfCards, i , randomIndex);

        }
        cardsUsed = 0;

    }

    /**
     * This method deals a card from the deckOfCards and returns that 
     * dealt card 
     * 
     * @return returns the card that has been dealt from the deck of cards 
     */

    public Card dealCard() {
        if (this.cardsLeft() == 0) {
            System.out.println("No more cards left in deck, cannot deal.");

        }
        cardsUsed++;
        return deckOfCards.get(cardsUsed - 1);

    }
    
    /**
     * This method returns the number of cards left in a deck
     * 
     * @return returns the number of cards left in a deck
     */
    public int cardsLeft() {
        return numCards - cardsUsed;
    }
    
    public Card getCard(int index) {
        return deckOfCards.get(index);
        
    }
   
    public int getNumCards() {
        return deckOfCards.size();
    }
    @Override
    /**
     * This method prints the deck of cards using its symbol and value
     * 
     * @return the deck of cards 
     */
    public String toString() {
        return deckOfCards.toString();
    }
    
    public String deckToString() {
        
        String result = "";
        // gets the number of cards in deck and this will be the first part of the string 
        
          
        for (int i = 0 ; i < deckOfCards.size() ; i ++) {
            
            String symbol = deckOfCards.get(i).getSymbol();
            int value = deckOfCards.get(i).getValue();
            String faceUp = "";
            if (deckOfCards.get(i).isFaceUp()) {
                faceUp = "u";
                
            }
            else {
               faceUp = "d"; 
                
            }
                
            if (i == deckOfCards.size() - 1) {
             result =  result + symbol + " " + value + " " + faceUp;
            }
            else {
                 result =  result + symbol + " " + value + " " + faceUp + " ";
                
            }
            
        }
        
        return result;
    }
    
    
    
}
