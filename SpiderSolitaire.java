import java.util.Scanner;
import java.util.InputMismatchException;

public class SpiderSolitaire
{
    /**
     * Tanay Athreya, 12/8/19, Period 4
     * Overall, this lab was fun and took me 1.5 hrs to complete. I realized that there are 2 
     * possible errors when playing the game. One is the InputMismatchException which occurs when
     * the user inputs something that doesn't match what the keyboard is scanning for. The other is when 
     * the user enters a stack number that is out of bounds. I realized that it would be difficult to cover both of 
     * these scenarios in the same try catch because for example, -1 is a valid integer and would not cause an InputMismatchException
     * for nextInt() however it would cause an outofbounds error if it were called using the move commands because -1 is an invalid stack
     * number. I realized the best way to solve this was to check for the input mismatch exceptions in the SpiderSolitaire class
     * and check for the stack out of bounds in the individual methods in the Board class (clear and makeMove). This
     * made it simple to catch errors and I tested all possible errors I could think of and saw no ways to crash the game.
     * For the next iteration of this lab, I would like to make my own error class to make my code even more clean.
    */
   
    /** Number of stacks on the board **/
    public final int NUM_STACKS = 7;

    /** Number of complete decks used in the game.  
     *  The number of cards in a deck depends on the
     *  type of Card used.  For example, a 1-suit deck
     *  of standard playing cards consists of only 13 cards
     *  whereas a 4-suit deck consists of 52 cards.
     */
    public final int NUM_DECKS = 4;

    /** A Board contains stacks and a draw pile **/
    private Board board;

    /** Used for keyboard input **/
    private Scanner input;

    public SpiderSolitaire()
    {
        // Start a new game with NUM_STACKS stacks and NUM_DECKS of cards
        board = new Board(NUM_STACKS, NUM_DECKS);
        input = new Scanner(System.in);
    }

    /** Main game loop that plays games until user wins or quits **/
    public void play() {

        board.printBoard();
        boolean gameOver = false;

        while(!gameOver) {
            System.out.println("\nCommands:");
            System.out.println("   move [card] [source_stack] [destination_stack]");
            System.out.println("   draw");
            System.out.println("   clear [source_stack]");
            System.out.println("   restart");
            System.out.println("   save");
            System.out.println("   load");
            System.out.println("   quit");
            System.out.print(">");
            String command = input.next();

            if (command.equals("move")) {
                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                String symbol = "";
                int sourceStack = 0;
                int destinationStack = 0;

                try {
                    symbol = input.next();

                    try {
                        sourceStack = input.nextInt();

                        try {
                            destinationStack = input.nextInt();
                            board.makeMove(symbol, sourceStack - 1, destinationStack - 1);

                        }
                        catch(InputMismatchException e) {
                            System.out.println("Invalid input entered for dest stack");
                        }
                    }
                    catch (InputMismatchException e) {
                        System.out.println("Invalid input entered for src stack");
                    } 

                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid symbol entered");

                }
            }

            else if (command.equals("draw")) {
                try {
                    board.drawCards();
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid information entered");
                }

            }
            else if (command.equals("clear")) {

                /* *** TO BE MODIFIED IN ACTIVITY 5 *** */
                try {
                    int sourceStack = input.nextInt();
                    board.clear(sourceStack - 1);
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid information entered");
                }
            }
            else if (command.equals("restart")) {
                try {
                    board = new Board(NUM_STACKS, NUM_DECKS);
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid information entered");
                }
            }
            else if (command.equals("quit")) {
                try {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid information entered");
                }
            }
            else if (command.equals("save")) {
             
                board.saveGame();
           
            }
            else if (command.equals("load")) {
                board.loadGame();
                
            }
            else {

                System.out.println("Invalid command.");

            }

            board.printBoard();
            // If all stacks and the draw pile are clear, you win!
            if (board.isEmpty()) {
                gameOver = true;
            }
        }
        System.out.println("Congratulations!  You win!");
    }
}
