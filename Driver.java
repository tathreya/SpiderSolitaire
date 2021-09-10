public class Driver
/**
 * Tanay Athreya, 12/11/19, Period 4
 * Overall, this lab was fun and took me 1.5 hours to complete. Converting a deck to a string and converting 
 * a string to deck were pretty straightforward because we have done similar labs (Squeeze and Unsqueeze) in the past.
 * The format I chose to use to recreate the game is numStacks in the first line and then each of the stacks following it.
 * I represented decks like this: symbol value faceup/down and since I had a set format I could easily use the scanner class
 * to find each token because all the tokens appeared in a specific order. I used a while loop that ran until there were no more tokens
 * in the file and the used nextInt to find the number of Stacks and used nextLine to find the next stack. Then I called the methods that I
 * created earlier (deckToString and string to deck). After doing this I could save the game to a file. Loading the game was the exact 
 * same logic except now I needed to create the Stacks arraylist and the draw pile and add the cards to them. For the next iteration 
 * of this lab, I would like to do more complicated things with the FileWriter class and Scanner.
 */
{
    public static void main(String[] args) {
        new SpiderSolitaire().play();
        
    }
}
