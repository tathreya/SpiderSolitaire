import java.util.Scanner;
import java.util.ArrayList; 
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Tanay Athreya, 12/7/19, Period 4
 * Overall, this lab was fun and took me 3 hours to complete. The logic for setting
 * up the board was simple, however, I made minor mistakes that made it hard to debug once I 
 * tested it. My biggest mistake was that when I was removing cards from the drawpile, I started
 * the for loop from the start and I forgot that whenever you remove an element, the size of the array
 * changes. This made it so that I wasn't removing exactly what I wanted and it messed up the entire drawpile.
 * This was an easy fix but it took me a long time to realize my mistake. For Activity 5, all the 
 * methods were pretty simple except for the makeMove method. This took the majority of the 3 hours as it had a lot 
 * of moving parts and was overall complicated. I first started by finding the index of the symbol starting from the 
 * end of the stack and saved this index. To actually make a move, I had three booleans which had to be true 
 * for the user to make a move. These booleans were, isRun , allFaceUp, and destLongRun. isRun essentially checked whether or not
 * the source stack had a run starting from the specified symbol going to the end of the stack. allFaceUp checked if the cards in the run
 * were all face up. destLongRun checked if the run from the source stack would create a longer run in the destination stack
 * if it were to be added. If all these were true, then the user could make a move. If any of these conditions were not met, 
 * the appropriate error message was displayed to the user. Debugging was pretty difficult to do but I decided to use a series 
 * of printline statements to track the code and I eventually realized my error. For then next iteration of this lab, I would like
 * to fully debug my program and make sure it works with all types of test cases. 

 */

public class Board {   
    /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
    // Attributes
    private ArrayList <Deck> Stacks; 
    private Deck drawPile;
    private int numStacks;
    private int numDecks; 

    /**
     * 
     *  Sets up the Board and fills the stacks and draw pile from a Deck
     *  consisting of numDecks Decks.  The number of Cards in a Deck
     *  depends on the number of suits. Here are examples:
     *  
     *  # suits     # numDecks      #cards in overall Deck
     *      1            1          13 (all same suit)
     *      1            2          26 (all same suit)
     *      2            1          26 (one of each suit)
     *      2            2          52 (two of each suit)
     *      4            2          104 (two of each suit)
     *      
     *  Once the overall Deck is built, it is shuffled and half the cards
     *  are placed as evenly as possible into the stacks.  The other half
     *  of the cards remain in the draw pile.  If you'd like to specify
     *  more than one suit, feel free to add to the parameter list.
     */    

    public Board(int numStacks, int numDecks) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        this.numStacks = numStacks;
        this.numDecks = numDecks;

        String [] symbols = {"A" , "2" , "3", "4", "5", "6", "7" , "8", "9" , "T", "J", "Q", "K"};
        int [] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        drawPile = new Deck(numDecks * 13);
        Stacks = new ArrayList <Deck> (numStacks);

        // initializes each deck in Stack
        for (int i = 0; i < numStacks ; i ++ ) {
            Stacks.add(new Deck());

        }

        // makes drawPile
        for (int i = 0 ; i < numDecks ; i++) { 
            for (int j = 0 ; j < 13 ; j++) {
                Card card = new Card (symbols[j], values[j]);
                drawPile.addCard(card);
            }

        }

        // shuffles drawPile 
        drawPile.shuffle();

        // adds drawPile cards to Stack 

        for(int i = 0, j = 0 ; i < drawPile.getNumCards()/2 ; i++, j++) {
            Card c = drawPile.getCard(i);
            Stacks.get(j).addCard(c);
            if (j == numStacks - 1) {
                //CHANGING J FROM J=-1 TO J=0
                j = -1;
                continue;
            }

        }

        // physically remove the cards that were added to stacks from drawpile
        for (int i = drawPile.getNumCards()/2 - 1  ; i >= 0 ; i--) {
            drawPile.removeCard(i);

        }

        // sets the last element of each deck in the stack to face up
        for (int i = 0 ; i < numStacks; i++) {

            Deck d = Stacks.get(i);

            d.getCard((d.getNumCards()- 1)).setFaceUp(true);

        }

    }   

    /**
     *  Moves a run of cards from src to dest (if possible) and flips the
     *  next card if one is available.  Change the parameter list to match
     *  your implementation of Card if you need to.
     *  
     */ 
    public void makeMove(String symbol, int src, int dest) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */

        if (src >= numStacks || src < 0 || dest >= numStacks || dest < 0) {
            System.out.println("Invalid stack entered");
            return;
        }

        int indexOfFirstFaceUp = 0;
        for (int i = 0; i < Stacks.get(src).getNumCards(); i++) {
            if (Stacks.get(src).getCard(i).isFaceUp()) {
                indexOfFirstFaceUp = i;
                break;
            }
            else {
                continue;

            }

        }

        boolean containsSymbol = true;

        for (int i = indexOfFirstFaceUp;  i < Stacks.get(src).getNumCards(); i++) {
            if (Stacks.get(src).getCard(i).symbolEquals(symbol)) {
                containsSymbol = true;
                break;

            }
            else {
                continue;

            }

        }

        // error checking
        if (!containsSymbol) {
            System.out.println("Symbol entered is not in src stack");
            return;
        }

        boolean canMove = true;
        boolean allFaceUp = true;
        boolean isRun = true; 

        // finds the index of the symbol in the stack
        int indexFound = 0;
        int numCards = Stacks.get(src).getNumCards();

        for (int i = numCards - 1; i >= 0 ; i-- ) {

            if (Stacks.get(src).getCard(i).symbolEquals(symbol)) {
                indexFound = i;
                break;

            }
            else {
                continue;
            }

        }

        // finds if it is a run in the src stack

        if ((numCards - 1) == indexFound ) {
            isRun = true;

        }
        else {
            for (int i = indexFound ; i < numCards - 1 ; i++) { 

                Card c1 = Stacks.get(src).getCard(i);
                Card c2 = Stacks.get(src).getCard(i+1);

                if (c1.compareTo(c2) == 1) {

                    isRun = true;
                    continue;

                }

                else {
                    isRun = false;
                    break;
                }

            }
        }

        // error checks run
        if (!isRun) {
            System.out.println("Invalid move. Src stack does not have valid run after specified symbol.");
            return;
        }

        // checks if all cards in run are face up
        for (int i = indexFound ; i < numCards ; i++) { 
            Card c = Stacks.get(src).getCard(i);
            if (c.isFaceUp()) {
                allFaceUp = true;
                continue;

            }
            else {
                allFaceUp = false;
                break;

            }

        }

        // error checks all face up
        if (!allFaceUp) {
            System.out.println("Invalid move. Symbol card is not found in the stack");
            return;
        }

        //check if the run can be added to dest stack 

        Card srcFirst = Stacks.get(src).getCard(indexFound);
        int destNumCards = Stacks.get(dest).getNumCards()-1;
        Card destLast; 
        boolean destLongRun = false; ; 
        if(Stacks.get(dest).getNumCards()!=0){
            destLast = Stacks.get(dest).getCard(destNumCards); 
            if(srcFirst.compareTo(destLast) == -1){
                destLongRun= true; 
            }
        }
        else{
            destLast = new Card("K", 20); 
            if(srcFirst.compareTo(destLast) < 0){
                destLongRun = true; 
            }

        }

        // error checks if it creates a longer run
        if (!destLongRun) {
            System.out.println("Invalid move. Does not continue a valid run on dest stack");
            return;
        }

        canMove = isRun && allFaceUp && destLongRun; 

        // moves stack if legal
        if (canMove) {

            // move the stack to destination from source
            for (int i = indexFound ; i < numCards ; i ++ ) {
                Card c = Stacks.get(src).getCard(i);
                Stacks.get(dest).addCard(c);

            }

            // remove cards that were added from source
            for (int i = numCards - 1; i >= indexFound ; i -- ) {
                Stacks.get(src).removeCard(i);

            }

            // check if you need to face up the card before indexFound on the src stack
            if (indexFound != 0 && !(Stacks.get(src).getCard(indexFound - 1).isFaceUp())) {
                Stacks.get(src).getCard(indexFound - 1).setFaceUp(true);

            }
        }

    }   

    /** 
     *  Moves one card onto each stack, or as many as are available
     */
    public void drawCards() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean isStacksFull = true;

        // checks if all stacks are empty or not
        for (int i = 0 ; i < numStacks; i ++) {
            if (Stacks.get(i).getNumCards() > 0) {
                isStacksFull = true;
                continue;

            }
            else {
                isStacksFull = false;
                break;
            }

        }

        if(isStacksFull) {

            if (drawPile.getNumCards() < numStacks) {
                for (int i = 0 ; i < drawPile.getNumCards(); i++) {

                    Card c = drawPile.getCard(i);
                    c.setFaceUp(true);
                    Stacks.get(i).addCard(c);

                }
                for (int i = drawPile.getNumCards() - 1 ; i >= 0; i--) {
                    drawPile.removeCard(i);

                }

            }
            else {

                for (int i = 0;  i < numStacks ; i ++) {
                    Card c = drawPile.getCard(i);
                    c.setFaceUp(true);
                    Stacks.get(i).addCard(c);

                }
                for (int i = numStacks - 1; i >= 0 ; i --) {
                    drawPile.removeCard(i);

                }

            }
        }
        else {
            System.out.println("Cannot draw cards if any stacks are empty!");
            return;

        }
    }

    /**
     *  Returns true if all stacks and the draw pile are all empty
     */ 
    public boolean isEmpty() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean isStackEmpty = true;
        for (int i = 0; i < numStacks; i++) {
            if (Stacks.get(i).getNumCards() == 0) {
                isStackEmpty = true;
                continue;
            }
            else {
                isStackEmpty = false;
                break;
            }

        }
        if (drawPile.getNumCards() == 0 && isStackEmpty) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *  If there is a run of A through K starting at the end of sourceStack
     *  then the run is removed from the game or placed into a completed
     *  stacks area.
     *  
     *  If there is not a run of A through K starting at the end of sourceStack
     *  then an invalid move message is displayed and the Board is not changed.
     */
    public void clear(int sourceStack) {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 5 *** */
        boolean isRun = true;

        if (sourceStack >= numStacks || sourceStack < 0) {
            System.out.println("Out of bounds error, the sourceStack inputted is not a valid stack number");
            return;
        }

        int lastCard = Stacks.get(sourceStack).getNumCards()-1;
        for (int i = lastCard; i > lastCard - 12 ; i--) {
            Card c1 = Stacks.get(sourceStack).getCard(i);
            Card c2 = Stacks.get(sourceStack ).getCard(i-1);
            if (c1.compareTo(c2) == -1) {
                isRun = true;
                continue;
            }
            else {
                isRun = false;
                break;
            }

        }

        if (isRun) {
            for (int i = lastCard; i >= lastCard - 12 ; i--) {
                Stacks.get(sourceStack).removeCard(i);  

            }
        }

        else {
            System.out.println("Invalid move");
        }
    }

    /**
     * Prints the board to the terminal window by displaying the stacks, draw
     * pile, and done stacks (if you chose to have them)
     */
    public void printBoard() {
        /* *** TO BE IMPLEMENTED IN ACTIVITY 4 *** */
        System.out.println("Stacks:");

        for (int i = 0 ; i < numStacks; i++) {
            System.out.println((i+1) + ": " + Stacks.get(i).toString());

        }
        System.out.println();
        System.out.println();

        System.out.println("Draw Pile: ");
        System.out.println(drawPile.toString());

    }

    public void saveGame() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        // Create a JFileChooser that points to the current directory
                        JFileChooser chooser = new JFileChooser(".");

                        // Tell the JFileChooser to show a Save dialog window
                        chooser.showSaveDialog(null);

                        // Ask the JFileChooser for the File the user typed in or selected
                        File input = chooser.getSelectedFile();

                        // handles cancel case
                        if (input == null) {
                            return;
                        }

                        // Create a FileWriter that can write to the selected File

                        try {

                            FileWriter out = new FileWriter(input);
                            out.write(numStacks + " ");
                            out.write("\n");
                            for (int i = 0 ; i < numStacks; i++) {
                                out.write(Stacks.get(i).deckToString());
                                out.write("\n");

                            }
                            out.write(drawPile.deckToString());
                            out.close();

                        }
                        catch (IOException i) {
                            System.out.println("Error: " + i.getMessage());

                        }

                    }
                });
        }
        catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void loadGame() {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        // Create a JFileChooser that points to the current directory
                        JFileChooser chooser = new JFileChooser(".");
                        
                        // Tell the JFileChooser to show a Save dialog window
                        chooser.showOpenDialog(null);
                        
                        // Ask the JFileChooser for the File the user typed in or selected
                        File input = chooser.getSelectedFile();

                        // handles cancel case
                        if (input == null) {
                            return;
                        }

                        // Create a FileWriter that can write to the selected File

                        try {
                            ArrayList <Deck> Stacks = new ArrayList<Deck>(); 
                            Deck drawPile = new Deck();
                            Scanner keyboard = new Scanner(input);
                            
                            while (keyboard.hasNext()) {
                                int numStacks = keyboard.nextInt();
                                keyboard.nextLine();
                                for (int i = 0 ; i < numStacks; i++) {
                                    String stack = keyboard.nextLine();
                                    Deck d = new Deck(stack);
                                    Stacks.add(d);
                                                    
                                }
                                String pile = keyboard.nextLine();
                                Deck d = new Deck(pile);
                            }
                            
                        }
                        catch (IOException i) {
                            System.out.println("Error: " + i.getMessage());

                        }

                    }
                });
        }
        catch (InterruptedException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InvocationTargetException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}