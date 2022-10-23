/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
// import classes
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.io.File;
import java.io.PrintWriter;

public class Main
{
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
    
    // the leftmost [] is the greatest array and going rightwards the other [] describe the "nested" arrays, with the rightmost [] being the array that has only regular elements
    // creates the 2d array of the puzzle board that the program will work with
    char[][] board = new char[lineCount][lineLength];
    int lineNumber = 0;
    while (input.hasNext()) {
      String inputStr = input.nextLine();
      noSpaceStr = inputStr.replaceAll(" ", "");
      for (int element = 0; element < lineLength; element++) {
        Arrays.fill(board[lineNumber], element, element+1, noSpaceStr.charAt(element));
      }
      lineNumber = lineNumber + 1;
    }

    // creates the 2d array of the solved puzzle board that the program will output
    char[][] solBoard = new char[lineCount][lineLength];

    for (int row = 0; row < lineCount; row++) {
      for (int startingLetter = 0; startingLetter < lineLength; startingLetter++) {
        String word = "";
        for (int endingLetter = startingLetter+1; endingLetter < lineLength+1; endingLetter++) {
          word = "";
          for (int letter = startingLetter; letter < endingLetter; letter++) {
            word = word + board[row][letter];
            if (palindrome(word) == true) {
              System.out.println("words: "+word);
              System.out.println("PALINDROME FOUND!");
              for (int charInWord = 0; charInWord < word.length(); charInWord++) {
                Arrays.fill(solBoard[row],startingLetter+charInWord,startingLetter+charInWord+1,word.charAt(charInWord));
              }
            }
          }
          // System.out.println("new ending letter");
        }
        // System.out.println("new starting letter");
      }
      System.out.println("new row");
    }
    
    // prints the board for debugging purposes
    for (int ar = 0; ar < lineCount; ar++) {
      System.out.println(Arrays.toString(board[ar]));
    }

    System.out.println("");
    // prints the solution board for debugging purposes
    for (int sar = 0; sar < lineCount; sar++) {
      System.out.println(Arrays.toString(solBoard[sar]));
    }
    // int ar[] = new int[]{2, 2, 1, 8, 3, 2, 2, 4, 2};
    // // Fill from index 1 to index 4.
    // Arrays.fill(ar, 1, 5, 10);
    // System.out.println(Arrays.toString(ar));
    input.close();
	}

  public static boolean palindrome(String str) {
    int mirroredness = 0;
    boolean mirrored = false;
    if (str.length() >= 3) {
      for (int letter = 0; letter < str.length(); letter++) {
        if (str.charAt(letter) == str.charAt(str.length()-letter-1)) {
          mirroredness = mirroredness + 1;
        }
      }
    }
    if (mirroredness == str.length()) {
      mirrored = true;
    }
    return mirrored;
  }
}
