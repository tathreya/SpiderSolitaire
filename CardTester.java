public class CardTester
{
    public static void main(String[] args) {
        
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        
        // creating cards
        Card card6 = new Card("6", 6);
        Card card10 = new Card("10", 10);
        Card c = new Card ("6", 6);
        
        System.out.println(card6.symbolEquals(card10.getSymbol()));
        System.out.println(card6.symbolEquals(c.getSymbol()));
        
        // testing getter for value and symbol and isFaceUp
        System.out.println(card6.getValue());
        System.out.println(card6.getSymbol());
        System.out.println(card6.isFaceUp());
        
        // testing the setter method for face up
        card6.setFaceUp(true);
        System.out.println(card6.isFaceUp());
        
        // testing equals 
        System.out.println(card6.equals(card10));
        System.out.println(card10.equals(card6));
        
        // testing compareTo
        System.out.println(card6.compareTo(card10));
        System.out.println(card10.compareTo(card6));
        
        // testing toString
        System.out.println(card10.toString());
        System.out.println(card6.toString());

    }
}
