/*
 * [PalindromeSolver.java]
 * Oct 18 and 24 Array File Methods Assignment - Palindrome Solver
 * @authors: Daniel Liu
 * @version: October 28 2022
 * @IPO table: https://docs.google.com/document/d/1udfRLSj27_CvNhCJzQYOb741Vf55JOTCILKuykDsvhc/edit?usp=sharing
 */

// import classes
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.PrintWriter;

public class Main {
  // throws exception because we want to write to a file soon
  public static void main(String[] args) throws Exception {
    // makes a scanner object for keyboard inputs
    Scanner k = new Scanner(System.in);
    // asks user for the input file name
    System.out.print("What is the text file's name of your puzzle? (DO NOT add the .txt at the end of your file name)\n");
    String fileName = k.nextLine() + ".txt";
    k.close();
    // initialize file readers and writers
    File puzzle = new File(fileName);
    Scanner input2 = new Scanner(puzzle);
    File updatedPuzzle = new File("PalindromePuzzleSolution.txt");
    PrintWriter output = new PrintWriter(updatedPuzzle);

    // stores how many lines there are in the puzzle and how long the lines are
    int lineCount = 0;
    int lineLength = 0;
    int currentLineLength = 0;
    boolean valid = true;
    String inputStr = "";
    while (input2.hasNext()) {
      lineCount = lineCount + 1;
      inputStr = input2.nextLine().replaceAll(" ", "");
      currentLineLength = inputStr.length();
      // checks if the puzzle is missing letters; if yes, then the puzzle cannot be solved
      if (lineLength != currentLineLength && lineCount != 1) {
        valid = false;
      }
      lineLength = currentLineLength;
    }

    // restart the scanner stream
    input2.close();
    Scanner input = new Scanner(puzzle);

    // creates the 2d array of the puzzle board that the program will work with
    char[][] board = new char[lineCount][lineLength];
    // gets the puzzle from the input file and fills the 2d array accordingly
    // also checks if there are invalid characters; if yes, the puzzle can't be solved
    if (valid) {
      valid = (createPuzzleArray(input, lineLength, board));
    }

    if (valid) {
      // creates the 2d array of the solved puzzle board that the program will output
      char[][] solBoard = new char[lineCount][lineLength];
      // preemptively fills the entire solution board with spaces
      makeSolBoard(lineCount, lineLength, solBoard);
  
      // searches row by row for horizontal palindromes
      searchH(lineCount, lineLength, board, solBoard);
  
      // searches column by column for vertical palindromes
      searchV(lineCount, lineLength, board, solBoard);
  
      // searches diagonally for diagonal palindromes
      searchD(lineCount, lineLength, board, solBoard);
  
      // writes the solution to the puzzle to a new text file
      writePuzzleSolution(lineCount, solBoard, output);

      // communicates to the user that the program has finished running by printing to the console and telling them if there was a problem with the input puzzle
      System.out.println("Finished!\nThe solution is stored in \"PalindromePuzzleSolution.txt\"");
    } else {
      System.out.println("The inputted puzzle has an error in it and is unable to be solved.");
      output.print("The inputted puzzle has an error in it and is unable to be solved.");
    }
    
    // close the streams
    input.close();
    output.close();
  }

  public static boolean createPuzzleArray(Scanner input, int elements, char[][] ar) {
    /*
     * function: creates the 2d array that the search methods will be working with, and records if the puzzle is valid or invalid
     * parameters: Scanner input, int elements, char[][] ar
     * returns: boolean validness
     */
    int lineNumber = 0;
    String letter = "";
    boolean validness = true;
    // while the file has a line to be read, takes the line, removes spaces, and stores it in the 2d array according to the line number
    while (input.hasNext()) {
      String inputStr = input.nextLine();
      for (int element = 0; element < elements*2 - 1; element = element + 2) {
        letter = "";
        // checks if it's a lowercase letter
        if (97 <= (int) inputStr.charAt(element) && (int) inputStr.charAt(element) <= 122) {
          letter = inputStr.substring(element, element + 1).toUpperCase();
          Arrays.fill(ar[lineNumber], element/2, element/2 + 1, letter.charAt(0));
        // checks if it's an uppercase letter
        } else if (65 <= (int) inputStr.charAt(element) && (int) inputStr.charAt(element) <= 90) {
          Arrays.fill(ar[lineNumber], element/2, element/2 + 1, inputStr.charAt(element));
        } else {
          validness = false;
        }
      }
      lineNumber = lineNumber + 1;
    }
    return validness;
  }

  public static void makeSolBoard(int rows, int columns, char[][] solAr) {
    /*
     * function: makes the solution 2d array fills it with blank spaces 
     * parameters: int rows, int columns, char[][] solAr
     * returns: none/void (the method only references the 2d array)
     */
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        Arrays.fill(solAr[row], column, column + 1, ' ');
      }
    }
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
     * returns: none/void (the method only references the 2d array)
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
              for (int charInWord = 0; charInWord < word.length(); charInWord++) {
                // adds on the palindrome to the solution board
                if (startingLetter + charInWord >= columns) {
                  Arrays.fill(solAr[row], startingLetter + charInWord - columns, startingLetter + charInWord + 1 - columns,
                      word.charAt(charInWord));
                } else {
                  Arrays.fill(solAr[row], startingLetter + charInWord, startingLetter + charInWord + 1,
                      word.charAt(charInWord));
                }
              }
            }
          }
        } // here
      }
    }
  }

  public static void searchV(int rows, int columns, char[][] ar, char[][] solAr) {
    /*
     * function: searches every column of the puzzle for vertical palindromes; also
     * detects palindromes that wrap around
     * parameters: int rows, int columns, char[][] ar, char[][] solAr
     * returns: none/void (the method only references the 2d array)
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
    }
  }

  public static void searchD(int rows, int columns, char[][] ar, char[][] solAr) {
    /*
     * function: searches every element in the 2d puzzle for "positive slope" and
     * "negative slope" diagonal palindromes
     * parameters: int rows, int columns, char[][] ar, char[][] solAr
     * returns: none/void (the method only references the 2d array)
     */
    int diagSizeMax;
    if (rows >= columns) {
      diagSizeMax = columns;
      // records if there are less rows than columns
    } else {
      diagSizeMax = rows;
    }
    for (int row = 0; row < rows; row++) {
      for (int column = 0; column < columns; column++) {
        // searches for palindromes that have a "negative slope" (down-rightwards or up-leftwards)
        searchNS(diagSizeMax, row, column, rows, columns, ar, solAr);
        // searches for palindromes that have a "positive slope" (up-rightwards or down-leftwards)
        searchPS(diagSizeMax, row, column, rows, columns, ar, solAr);
      }
    }
  }

  public static void searchNS(int maxDiag, int y, int x, int lines, int elements, char[][] array, char [][]solArray) {
    /*
     * function: searches for palindromes in the "negative slope" direction
     * parameters: int maxDiag, int y, int x, int lines, int elements, char[][] array, char[][] solArray
     * returns: none/void (the method only references the 2d array)
     */
    boolean notItself = true; 
    int movePos = 0; // moves the position of the area being searched
    String word = "";
    
    for (int letter = 0; letter < maxDiag+1; letter++) {
      if (notItself) {
        // if the current position is out of bounds, then it wraps it around to be in bounds
        if (y + letter + movePos >= lines) {
          movePos = 0 - (x+letter);
        } else if (x + letter + movePos >= elements) {
          movePos = 0 - (y+letter);
        }
        if (movePos < -maxDiag) {
          movePos = -maxDiag;
        }
        
        // if the current position is also the starting position, then it stops the loop
        if ((y + letter + movePos == y) && (x + letter + movePos == x) && (movePos != 0)) {
          notItself = false;
        }
        
        // adds the current position's character to the string if it's not the starting position
        if (notItself == true) {
          if (y + letter >= lines || x + letter >= elements) {
            word = word + array[y + letter + movePos][x + letter + movePos];
          } else {
            word = word + array[y + letter][x + letter];
          }
        }
        
        // checks if the updated string is a palindrome
        if (palindrome(word) == true) {
          // adds the palindrome to the solution board
          for (int charInWord = 0; charInWord < word.length(); charInWord++) {
            // if the palindrome went out of bounds
            if (y + charInWord >= lines || x + charInWord >= elements) {
              Arrays.fill(
                solArray[y+charInWord+movePos],x+charInWord+movePos,
                x+charInWord+movePos+1,word.charAt(charInWord));
            } else {
              Arrays.fill(
                solArray[y+charInWord],x+charInWord,
                x+charInWord+1,word.charAt(charInWord));
            }
          }
        }
      }
    }
  }

  public static void searchPS(int maxDiag, int y, int x, int lines, int elements, char[][] array, char [][]solArray) {
    /*
     * function: searches for palindromes in the "positive slope" direction
     * parameters: int maxDiag, int y, int x, int lines, int elements, char[][] array, char[][] solArray
     * returns: none/void (the method only references the 2d array)
     */
    boolean notItself = true;
    int movePos = 0; // moves the position of the area being searched 
    String word = "";
    
    for (int letter = 0; letter < maxDiag+1; letter++) {
      if (notItself) {
        // if the current position is out of bounds, then it wraps it around to be in bounds
        if (y - letter + movePos < 0) {
          movePos = x+letter;
        } else if (x + letter - movePos >= elements) {
          movePos = ((lines-1)-y)+letter;
        }
        if (movePos >= maxDiag) {
          movePos = maxDiag;
        }
        
        // if the current position is also the starting position, then it stops the loop
        if ((y - letter + movePos == y) && (x + letter - movePos == x) && (movePos != 0)) {
          notItself = false;
        }

        // adds the current position's character to the string if it's not the starting position
        if (notItself == true) {
          if (y - letter < 0 || x + letter >= elements) {
            word = word + array[y - letter + movePos][x + letter - movePos];
          } else {
            word = word + array[y - letter][x + letter];
          }
        }

        // checks if the updated string is a palindrome
        if (palindrome(word) == true) {
          // adds the palindrome to the solution board
          for (int charInWord = 0; charInWord < word.length(); charInWord++) {
            // if the palindrome went out of bounds
            if (y - charInWord < 0 || x + charInWord >= elements) {
              Arrays.fill(
                solArray[y-charInWord+movePos],x+charInWord-movePos,
                x+charInWord-movePos+1,word.charAt(charInWord));
            } else {
              Arrays.fill(
                solArray[y-charInWord],x+charInWord,
                x+charInWord+1,word.charAt(charInWord));
            }
          }
        }
      }
    }
  }

  public static void writePuzzleSolution(int rows, char[][] solAr, PrintWriter writer) {
    /*
     * function: writes the puzzle solution 2d array to the output text file
     * parameters: int rows, char[][] solAr, PrintWriter writer
     * returns: none/void (the method only references the 2d array)
     */
    for (int line = 0; line < rows; line++) {
      boolean firstChar = true;
      String solLine = "";
      String spacedSolLine = "";
      for (int letter = 0; letter < solAr[line].length; letter++) {
        solLine = solLine + solAr[line][letter];
      }
      // writes each line of the solution to the output file
      for (int character = 0; character < solLine.length(); character++) {
        if (firstChar) {
          // every line is formatted properly
          spacedSolLine = "" + solLine.charAt(character);
          firstChar = false;
        } else {
          spacedSolLine = spacedSolLine + " " + solLine.charAt(character);
        }
      }
      writer.println(spacedSolLine);
    }
  }
}
