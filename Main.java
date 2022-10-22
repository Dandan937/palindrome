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
    // File updatedRecords = new File("updatedRecords.txt");
    // PrintWriter output = new PrintWriter(updatedRecords);

    // variables to store how many lines there are in the puzzle and how long the lines are
    int lineCount = 0;
    int lineLength = 0;
    
    // if string contains 1 element, string[5] is an array and contains 5 elements, then string[5][10] should be an array that contains 10 five element arrays, right?... wrong. this thinking perceives the rightmost [] as how many of the type of array on the left there are, but this is wrong. it is actually just the leftmost [] is the greatest array and going rightwards the other [] describe the "nested" arrays, with the rightmost [] being the array that has only regular elements
    char[][] board;
    while (input.hasNext()) {
      String inputStr = input.nextLine();
      lineLength = inputStr.length();
      String str1 = inputStr.replaceAll(" ", "");
      for (int element = 0; element < str1.length(); element++) {
        
      }
    }

    // System.out.println(Arrays.toString(board[]));

    // int ar[] = {2, 2, 1, 8, 3, 2, 2, 4, 2};
  
    // // Fill from index 1 to index 4.
    // Arrays.fill(ar, 1, 5, 10);
 
    // System.out.println(Arrays.toString(ar));
    input.close();
	}
}
