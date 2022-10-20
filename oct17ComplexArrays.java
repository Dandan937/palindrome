/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.lang.Math;
import java.util.Arrays;
import java.util.Scanner;
public class oct17ComplexArrays
{
	public static void main(String[] args) {
		System.out.println("Hello World");
		
		/* question 1
		int[][] sum = {{1,2,3,4,5,6},
		               {1,2,3,4,5,6}};
		String sumMatrix = "sumMatrix:";
		
		for (int die1 = 0; die1 < sum[0].length; die1++) {
		    sumMatrix = sumMatrix + ("\n");
		    for (int die2 = 0; die2 < sum[1].length; die2++) {
		        sumMatrix = sumMatrix + (sum[0][die1] + sum[1][die2]);
		        sumMatrix = sumMatrix + (" ");
		    }
		}
		System.out.print(sumMatrix);
		*/
		
		/* question 2
		int[][] sum = {{1,2,3,4,5,6},
		               {1,2,3,4,5,6},
		               {1,2,3,4,5,6}};
		String sumMatrix = "tripleSumMatrix:\nnl ";
		int numCount = 0;
		
		for (int die1 = 0; die1 < sum[0].length; die1++) {
		    for (int die2 = 0; die2 < sum[1].length; die2++) {
		        for (int die3 = 0; die3 < sum[2].length; die3++) {
    		        sumMatrix = sumMatrix + (sum[0][die1] + sum[1][die2] + sum[1][die3]);
    		        sumMatrix = sumMatrix + (" ");
		        }
		        sumMatrix = sumMatrix + ("; ");
		    }
		    sumMatrix = sumMatrix + "\nnl ";
		}
		System.out.print(sumMatrix);
		for (int num = 3; num < 19; num++) {
		    for (int length = 0; length < sumMatrix.length(); length++) {
		        if (num == sumMatrix.charAt(length)) {
		            numCount = numCount + 1;
		        }
		    }
	    	int[] totalSums = new int[0];
		    int[] filler = new int[totalSums.length + 1];
		    int index;
            for (index = 0; index < totalSums.length; index++) {
                filler[index] = totalSums[index];
            }
		    filler[index] = numCount;
		    int[] totalSums = new int[filler.length];
		    for (index = 0; index < filler.length; index++) {
                totalSums[index] = filler[index];
            }
		}
		for (int value = 3; value < totalSums.length+3; value++) {
		    System.out.println(value + " --> " + totalSums[value-3]);
		}
		*/
		
		// question 3
		Scanner k = new Scanner(System.in);
		System.out.println("enter wanted min elements per row");
		int minElem = k.nextInt();
		System.out.println("enter wanted max elements per row");
		int maxElem = k.nextInt();
		System.out.println("enter wanted min value of elements");
		int minNum = k.nextInt();
		System.out.println("enter wanted max value of elements");
		int maxNum = k.nextInt();
		
		int[][] array = new int[5][];
		for(int i = 0; i < 5; i++) {
		    int randLen = (int)((Math.random()*(maxElem+1 - minElem)/1)+minElem);
		    array[i] = new int[randLen];
		    generateArray(array[i], minNum, maxNum);
		}
		System.out.println(printArray(array));
	}
	
	public static void generateArray(int[] arr, int min, int max) {
	    for(int i = 0; i < arr.length; i++){
	        arr[i] = (int)((Math.random()*(max+1 - min)/1)+min);
	    }
	}
	
	public static String printArray(int[][] arr) {
	    String output = "";
	    for (int i = 0; i < arr.length; i++){
    	    output = output + Arrays.toString(arr[i]) + "\n";
	    }
	    return output;
	}
}