/*
Hangman Game
Created for TeamTreeHouse Beginning Java course
https://teamtreehouse.com/tracks/beginning-java
*/

public class Hangman {

  public static void main(String[] args) {
    // Check if user input a string to use as the answer
    if (args.length == 0) {
      System.out.println("Usage: java Hangman <answer>");
      System.err.println("Answer is required");
      System.exit(1);
    }
    
    // Create new instance of the game and prompter
    Game game = new Game(args[0]);
    Prompter prompter = new Prompter(game);
    
    // Loop until run out of tries or game is won
    while (game.getRemainingTries() > 0 && !game.isWon()) {
      prompter.displayProgress();
      prompter.promptForGuess();
    }
    
    prompter.displayOutcome();
  }
}
