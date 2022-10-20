/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
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

    // find how many rows are in the puzzle
    int lineCount = 0;
    int lineLength = 0;
    while (input2.hasNext()) {
      lineCount = lineCount + 1;
      lineLength = input2.nextLine().length();
    }
    // restart the scanner stream
    input2.close();
    Scanner input = new Scanner(puzzle);

    // if string contains 1 element, string[5] is an array and contains 5 elements, then string[5][10] should be an array that contains 10 five element arrays, right?... wrong. this thinking perceives the rightmost [] as how many of the type of array on the left there are, but this is wrong. it is actually just the leftmost [] is the greatest array and going rightwards the other [] describe the "nested" arrays, with the rightmost [] being the array that has only regular elements
    String[][] board = new String[lineCount][lineLength];
    for (int line = 0; line < lineLength; line++) {
      System.out.println(line);
    }
    input.close();
	}
}
