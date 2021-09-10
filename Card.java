/**
 * Tanay Athreya, 11/30/19, Period 4
 * 
 * Overall, this lab was fun and took me an hour and a half to complete. Coding the Card class was relatively
 * simple because all the methods we needed were already given to us. I was slightly confused with the isFaceUp method
 * but I eventually figured it out. The equals and compare method were also relatively simple because I have already 
 * used the compareTo method with strings. For the Deck class, I started by brainstorming all the things that a deck of cards
 * can do. I eventually realized that I needed a method to shuffle the deck, a method to add cards to the deck, and a method to deal cards
 * from a deck. To accomplish this, I concluded that using an array list would be the easiest because of the swap method in the collections class
 * and the add method for arraylists. For the add card method, I used the add method for arraylists and added the card passed in to the 
 * deck of cards. For the shuffle method, I generated a random number from the current index to the last index of the deck of cards (inclusive) and then swapped the
 * current index with the randomIndex. For the next iteration of this lab, I would like to start making the actual spider solitaire
 * game. 
 * 
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        
        this.symbol = symbol;
        this.value = value;
        this.isFaceUp = false;
        
    }

    /**
     * Getter method to access this <code>Card</code>'s symbol.
     * 
     * @return this <code>Card</code>'s symbol.
     */
    public String getSymbol() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        
        return this.symbol;
        
    }
    
    /**
     * Getter method to access this <code>Card</code>'s value
     * 
     * @return this <code>Card<\code>'s value
     */
    public int getValue() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
        
        return this.value;
        
    }
    /**
     * Getter method to access this <code>Card</code>'s orientation (face up or down)
     * 
     * @return this <code>Card<\code>'s orientation (face up or down)
     */
    public boolean isFaceUp() {
        return this.isFaceUp;
    }
    /**
     * Setter method to set this <code>Card</code>'s orientation (face up or down)
     * 
     * @param state The current orientation (face up or down)
     *  
     */
    public void setFaceUp(boolean state) {
        this.isFaceUp = state;
    }
    /**
     * This method returns the difference in values between this card and the card passed in.
     * Returns negative number if this card is lower in value than the card passed in
     * Return positive number if this card is higher in value than the card passed in 
     * Returns 0 if the two card's values are equal
     * 
     * @return the difference in values between this card and the card passed in
     * @param other The card that this card will be compared with
     * 
     */
    public int compareTo(Card other) {
        return this.getValue() - other.getValue();
        
    }
    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     * @return whether or not this Card is equal to other
     * @param other The card that this card will be compared with
     */
    public boolean equals(Card other) {
        
        if (this.getSymbol() == other.getSymbol()) {
            return true;
        }
        else {
            return false;
        }
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
    }
    
    
    public boolean symbolEquals(String symbol) {
        
        if (this.getSymbol().equals(symbol)) {
            return true;
        }
        else {
            return false;
        }
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
    }
    /**
     * Returns this card as a String.  If the card is face down, "X"
     * is returned.  Otherwise the symbol of the card is returned.
     *
     * @return a <code>String</code> containing the symbol and point
     *         value of the card.
     */
    @Override
    public String toString() {
        if (!this.isFaceUp()) {
            return "X";
        }
        else {
            return this.getSymbol();
        }
        /* *** TO BE IMPLEMENTED IN ACTIVITY 2 *** */
    }
}
