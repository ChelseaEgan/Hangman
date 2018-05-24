/*
This file holds the prompter logic for Hangman
Built based on beginning Java course on TeamTreeHouse
https://teamtreehouse.com/tracks/beginning-java
*/

import java.util.Scanner;

public class Prompter {
  // Private variables
  private Game game;
  
  // Scanner object for input
  Scanner scanner = new Scanner(System.in);
  
  // Constructor
  public Prompter(Game game) {
    this.game = game;
    this.game.setPlayerName(promptForPlayerName());
  }
  
  // Get player name and make sure something was entered
  public String promptForPlayerName() {
    boolean isAcceptable = false;
    String name = "";
    
    do {
      System.out.print("Enter your first name: ");
      name = scanner.nextLine();
      if(name.length() == 0) {
        System.out.printf("No name found. Please try again.%n");
      } else {
        System.out.printf("Thanks for playing, %s!%n", name);
        isAcceptable = true;
      }
    } while(!isAcceptable);
    
    return name;
  }
  
  // Get a letter from the player
  // Verify that it's an acceptable letter
  // Check if it's a correct guess
  public boolean promptForGuess() {
    boolean isHit = false;
    boolean isAcceptable = false;
    
    do {
      System.out.print("Enter a letter:  ");
      String guessInput = scanner.nextLine();
      
      try {
        isHit = game.applyGuess(guessInput);
        System.out.printf("Good guess, %s!%n", 
                          game.getPlayerName());
        isAcceptable = true;
      } catch (IllegalArgumentException iae) {
        System.out.printf("%s. Please try again. %n",
                          iae.getMessage());
      }
    } while(! isAcceptable);
    return isHit;
  }
  
  // Display number of tries left and progress against answer
  public void displayProgress() {
    System.out.printf("You have %d tries left to solve:  %s%n", 
                      game.getRemainingTries(), 
                      game.getCurrentProgress());
  }
  
  // Display whether or not the player has won
  // If lost, display correct answer
  public void displayOutcome() {
    if (game.isWon()) {
      System.out.printf("Congratulations, %s! You won with %d tries remaining.%n", 
                        game.getPlayerName(), 
                        game.getRemainingTries());
    } else {
      System.out.printf("Bummer, %s. The correct answer was: %s%n", 
                        game.getPlayerName(), 
                        game.getAnswer());
    }
  }
}
