public class DeckTester
{
    public static void main(String[] args) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
        Deck deckOfCards = new Deck(52);

        int numDecks = 4;
        String [] symbols = {"A" , "2" , "3", "4", "5", "6", "7" , "8", "9" , "T", "J", "Q", "K"};
        int [] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        for (int i = 0 ; i < numDecks ; i++) { 
            for (int j = 0 ; j < 13 ; j++) {
                Card card = new Card (symbols[j], values[j]);
                
                    card.setFaceUp(true);
                
               
                deckOfCards.addCard(card);
            }

        }
        deckOfCards.shuffle();
        
        System.out.println(deckOfCards.toString());
        
        deckOfCards.sort();
        
        System.out.println(deckOfCards.toString());
        
        
        

    }
    
}
