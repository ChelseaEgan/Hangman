/*
This file holds the game logic for Hangman
Built based on beginning Java course on TeamTreeHouse
https://teamtreehouse.com/tracks/beginning-java
*/

public class Game {
  // Number of times the player can guess wrong
  public static final int MAX_MISSES = 7;
  
  // Private variables
  private String playerName;
  private String answer;
  private String hits;
  private String misses;
  
  // Constructor
  public Game(String answer) {
    this.answer = answer.toLowerCase();
    hits = "";
    misses = "";
    playerName = "";
  }
  
  // Public getters
  public String getAnswer() {
    return answer;
  }
  public String getPlayerName() {
    return playerName;
  }

  // Public setters
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
  
  
  // Make sure the guess is a lowercase letter
  // Make sure the letter has not been guessed before
  private char normalizeGuess(char letter) {
    if(! Character.isLetter(letter)) {
      throw new IllegalArgumentException("A letter is required");
    }
   
    letter = Character.toLowerCase(letter);
    
    if(misses.indexOf(letter) != -1 || hits.indexOf(letter) != -1) {
      throw new IllegalArgumentException(letter + " has already been guessed.");
    }
    
    return letter;
  }
  
  // Calls normalizeGuess for verification checks
  // Calls isHit to see if it's a correct guess
  // Stores letter in either misses or hits accordingly
  public boolean applyGuess(char letter) {
    letter = normalizeGuess(letter);
    
    boolean isHit = answer.indexOf(letter) != -1;
    
    if (isHit) {
      hits += letter;
    } else {
      misses += letter;
    }
    
    return isHit;
  }
  
  // Function to process a string
  // Makes sure something was entered then processes as char
  public boolean applyGuess(String letters) {
    if(letters.length() == 0) {
      throw new IllegalArgumentException("No letter found.");
    }
    return applyGuess(letters.charAt(0));
  }
  
  // Return number of tries left out of max
  public int getRemainingTries() {
    return MAX_MISSES - misses.length();
  }
  
  // Returns progress against answer
  // Uses dashes in place of "unguessed" letters
  public String getCurrentProgress() {
    String progress = "";
    for (char letter : answer.toCharArray()) {
      char display = '-';
      if(hits.indexOf(letter) != -1) {
        display = letter;
      }
      progress += display;
    }
    return progress;
  }
  
  // Checks if there are any dashes left to indicate "unguessed" letters
  // If no dashes, player has won
  public boolean isWon() {
    return getCurrentProgress().indexOf('-') == -1;
  }
}
