/*
 * [PalindromeSolver.java]
 * Oct 18 and 24 Array File Methods Assignment - Palindrome Solver
 * @authors: Daniel Liu
 * @version: October 23 2022
 * @IPO table: https://docs.google.com/document/d/1udfRLSj27_CvNhCJzQYOb741Vf55JOTCILKuykDsvhc/edit?usp=sharing
 */

// import classes
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.io.File;
import java.io.PrintWriter;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Hello World");
    // initialize objects
    File puzzle = new File("PalindromePuzzle.txt");
    Scanner input2 = new Scanner(puzzle);
    // File updatedPuzzle = new File("updatedPuzzle.txt");
    // PrintWriter output = new PrintWriter(updatedPuzzle);

    // store how many lines there are in the puzzle and how long the lines are
    int lineCount = 0;
    int lineLength = 0;
    String noSpaceStr = "";
    while (input2.hasNext()) {
      lineCount = lineCount + 1;
      noSpaceStr = input2.nextLine().replaceAll(" ", "");
      lineLength = noSpaceStr.length();
    }

    // restart the scanner stream
    input2.close();
    Scanner input = new Scanner(puzzle);

    // the leftmost [] is the greatest array and going rightwards the other []
    // describe the "nested" arrays, with the rightmost [] being the array that has
    // only regular elements
    // creates the 2d array of the puzzle board that the program will work with
    char[][] board = new char[lineCount][lineLength];
    noSpaceStr = createWorkingArray(input, noSpaceStr, lineLength, board);

    // creates the 2d array of the solved puzzle board that the program will output
    // and preemptively fills the array with blank spaces
    char[][] solBoard = new char[lineCount][lineLength];
    for (int row = 0; row < lineCount; row++) {
      for (int column = 0; column < lineLength; column++) {
        Arrays.fill(solBoard[row], column, column + 1, ' ');
      }
    }

    // searches row by row for horizontal palindromes
    searchH(lineCount, lineLength, board, solBoard);

    // searches column by column for vertical palindromes
    searchV(lineCount, lineLength, board, solBoard);

    // searches diagonally for diagonal palindromes
    searchD(lineCount, lineLength, board, solBoard);
    
    // prints the board for debugging purposes
    for (int ar = 0; ar < lineCount; ar++) {
      System.out.println(Arrays.toString(board[ar]));
    }

    // makes a filler line
    System.out.println("");
    // prints the solution board for debugging purposes
    for (int sar = 0; sar < lineCount; sar++) {
      System.out.println(Arrays.toString(solBoard[sar]));
    }
    // int ar[] = new int[]{2, 2, 1, 8, 3, 2, 2, 4, 2};
    // // Fill from index 1 to index 4.
    // Arrays.fill(ar, 1, 5, 10);
    // System.out.println(Arrays.toString(ar));

    // close the puzzle reader stream
    input.close();
  }

  public static String createWorkingArray(Scanner input, String str, int elements, char[][] ar) {
    /*
     * function: creates the 2d array that the search methods will be working with
     * parameters: Scanner input, String str, int elements, char[][] ar
     * returns: String str
     */
    int lineNumber = 0;
    // while the file has a line to be read, takes the line, removes spaces, and stores it in the 2d array according to the line number
    while (input.hasNext()) {
      String inputStr = input.nextLine();
      str = inputStr.replaceAll(" ", "");
      for (int element = 0; element < elements; element++) {
        Arrays.fill(ar[lineNumber], element, element + 1, str.charAt(element));
      }
      lineNumber = lineNumber + 1;
    }
    return str;
  }

  public static boolean palindrome(String str) {
    /*
     * function: checks if the string of characters found is a palindrome or not
     * parameters: String str
     * returns: boolean mirrored
     */
    int mirroredness = 0;
    boolean mirrored = false;
    // the string needs to be at least 3 characters long for it to be considered a palindrome
    if (str.length() >= 3) {
      // matches the first letter with the last letter, second letter with the penultimate letter, and so on
      for (int letter = 0; letter < str.length(); letter++) {
        if (str.charAt(letter) == str.charAt(str.length() - letter - 1)) {
          mirroredness = mirroredness + 1;
        }
      }
    }
    // the string length and the # of times the letters correctly match up should be the same
    if (mirroredness == str.length()) {
      mirrored = true;
    }
    return mirrored;
  }

  public static void searchH(int rows, int columns, char[][] ar, char[][] solAr) {
    /*
     * function: searches every row of the puzzle for horizontal palindromes; also
     * detects palindromes that wrap around
     * parameters: int rows, int columns, char[][] ar, char[][] solAr
     * returns: void (the method *references* the 2d array)
     */
    for (int row = 0; row < rows; row++) {
      for (int startingLetter = 0; startingLetter < columns; startingLetter++) {
        String word = "";
        for (int endingLetter = 1; endingLetter < columns; endingLetter++) {
          word = "";
          // adds on every letter in the desired search area to a string
          for (int letter = startingLetter; letter < startingLetter + endingLetter + 1; letter++) {
            // if the current position is out of bounds, then wraps it around to be in bounds
            if (startingLetter + endingLetter >= columns && letter >= columns) {
              word = word + ar[row][letter - columns];
            // otherwise, it simply takes the current position's character
            } else {
              word = word + ar[row][letter];
            }
            // checks if the string is a palindrome
            if (palindrome(word) == true) {
              System.out.println("PALINDROME FOUND! word: " + word);
              for (int charInWord = 0; charInWord < word.length(); charInWord++) {
                // adds on the palindrome to the solution board
                if (startingLetter + charInWord >= columns) {
                  Arrays.fill(solAr[row], startingLetter + charInWord - 7, startingLetter + charInWord + 1 - 7,
                      word.charAt(charInWord));
                } else {
                  Arrays.fill(solAr[row], startingLetter + charInWord, startingLetter + charInWord + 1,
                      word.charAt(charInWord));
                }
              }
            }
          }
        }
      }
      System.out.println("new row");
    }
  }

  public static void searchV(int rows, int columns, char[][] ar, char[][] solAr) {
    /*
     * function: searches every column of the puzzle for vertical palindromes; also
     * detects palindromes that wrap around
     * parameters: int rows, int columns, char[][] ar, char[][] solAr
     * returns: void (the method *references* the 2d array)
     */
    for (int column = 0; column < columns; column++) {
      for (int startingLetter = 0; startingLetter < rows; startingLetter++) {
        String word = "";
        for (int endingLetter = 1; endingLetter < rows; endingLetter++) {
          word = "";
          // adds on every letter in the desired search area to a string
          for (int letter = startingLetter; letter < startingLetter + endingLetter + 1; letter++) {
            // if the current position is out of bounds, then wraps it around to be in bounds
            if (startingLetter + endingLetter >= rows && letter >= rows) {
              word = word + ar[letter - rows][column];
            // otherwise, it simply takes the current position's character
            } else {
              word = word + ar[letter][column];
            }
            // checks if the string is a palindrome
            if (palindrome(word) == true) {
              System.out.println("PALINDROME FOUND! word: " + word);
              for (int charInWord = 0; charInWord < word.length(); charInWord++) {
                // adds on the palindrome to the solution board
                if (startingLetter + charInWord >= rows) {
                  Arrays.fill(solAr[startingLetter + charInWord - rows], column, column + 1, word.charAt(charInWord));
                } else {
                  Arrays.fill(solAr[startingLetter + charInWord], column, column + 1, word.charAt(charInWord));
                }
              }
            }
          }
        }
      }
      System.out.println("new column");
    }
  }

  public static void searchD(int rows, int columns, char[][] ar, char[][] solAr) {
    /*
     * function: searches every element in the 2d puzzle for "positive slope" and
     * "negative slope" diagonal palindromes
     * parameters: int rows, int columns, char[][] ar, char[][] solAr
     * returns: void (the method *references* the 2d array)
     */
    String word = "";
    int diagSizeMax;
    boolean notItselfPS = true; // boolean to keep checking in the "positive slope" direction
    boolean notItselfNS = true; // boolean to keep checking in the "negative slope" direction
    int movePos = 0;
    // records if there are less columns than rows
    if (rows >= columns) {
      diagSizeMax = columns;
      // records if there are less rows than columns
    } else {
      diagSizeMax = rows;
    }
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        // creates a string to hold the characters 
        word = "";
        // searches for "negative slope" diagonals
        notItselfNS = true;
        movePos = 0;
        for (int letter = 0; letter < diagSizeMax+1; letter++) {
          if (notItselfNS) {
            // if the current position is out of bounds, then it wraps it around to be in bounds
            if (row + letter + movePos >= rows) {
              movePos = 0 - (column+letter);
            } else if (column + letter + movePos >= columns) {
              movePos = 0 - (row+letter);
            }
            // if the current position is also the starting position, then it stops the loop
            if ((row + letter + movePos == row) && (column + letter + movePos == column) && (movePos != 0)) {
              notItselfNS = false;
            }
            // adds the current position's character to the string if it's not the starting position
            if (notItselfNS == true) {
              if (row + letter >= rows || column + letter >= columns) {
                word = word + ar[row + letter + movePos][column + letter + movePos];
              } else {
                word = word + ar[row + letter][column + letter];
              }
            }
            // checks if the updated string is a palindrome
            if (palindrome(word) == true) {
              System.out.println("PALINDROME FOUND! word: " + word);
              // adds the palindrome to the solution board
              for (int charInWord = 0; charInWord < word.length(); charInWord++) {
                // if the palindrome went out of bounds
                if (row + charInWord >= rows || column + charInWord >= columns) {
                  Arrays.fill(solAr[row+charInWord+movePos],column+charInWord+movePos,column+charInWord+movePos+1,word.charAt(charInWord));
                } else {
                  Arrays.fill(solAr[row+charInWord],column+charInWord,column+charInWord+1,word.charAt(charInWord));
                }
              }
            }
          }
        }
      }
    }
  }
}
