/*
 * [LicensePlateRecordUpdater.java]
 * Oct 7 Methods, Strings, and File I/O Assignment - License Plate Record Updater
 * @authors: Galton Ma (Name, Test Cases), Daniel Liu (License Plate, Output/Input to File)
 * @version: October 15 2022
 * @IPO table: https://docs.google.com/document/d/1reiPIOkDPhCdFhTJdWAbcekOafc85MhKm-CZGG1fhZA/edit
 */

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class LicensePlateRecordUpdater {
  public static void main(String[] args) throws Exception {
    // initialize objects
    File records = new File("record.txt");
    Scanner input = new Scanner(records);
    File updatedRecords = new File("updatedRecords.txt");
    PrintWriter output = new PrintWriter(updatedRecords);

    // declare variables to be used later
    boolean run = true;
    boolean firstLine = true;
    String updatedStr1 = "";
    int colonIndex = 0;
    String updatedStr2 = "";
    int letters = 0;
    int numbers = 0;
    String upperCaseStr = "";
    boolean valid = true;
    String updatedStr3 = "";

    while (input.hasNext()) {
      // puts the current line of the text file in a string
      String inputStr = input.nextLine();

      // checks if there is nothing on the current line
      if (inputStr.equals("")) {
        run = false;
      }

      // if there is something on the current line, attempts to format the text
      if (run) {
        // checks to see if it should write a new line
        firstLine = writeNewLine(firstLine, output);
        
        // removes whitespace
        updatedStr1 = removeWhiteSpace(inputStr);

        // finds the index of the colon
        colonIndex = findColon(updatedStr1);

        // makes only the first letter of each word capitalized
        updatedStr2 = capitalizeName(updatedStr1);

        // finds the number of letters in the license plate
        letters = countLetters(updatedStr2, colonIndex);

        // finds the number of numbers in the license plate
        numbers = countNumbers(updatedStr2, colonIndex);

        // finds the letters of the license and uppercases them
        upperCaseStr = licenseLettersToUpperCase(updatedStr2, colonIndex);

        // checks the validity of the license plate
        valid = checkValidity(upperCaseStr, letters, numbers);

        // reformats the license plates to the correct format
        updatedStr3 = organizeLicensePlate(updatedStr2, colonIndex, letters, numbers, upperCaseStr, valid);

        // prints the updated record
        writeUpdatedRecord(output, updatedStr3);
      }
      run = true;
    }

    // closes the scanner and printerwriter to release the stream
    input.close();
    output.close();
    System.out.println("done!");
  }

  public static boolean writeNewLine(boolean isFirstLine, PrintWriter writer) {
    /*
     * determines if it should write a new line to the file or not
     * parameters: boolean isFirstLine, PrintWriter writer
     * returns: boolean isFirstLine
     */
    if (isFirstLine == true) {
      isFirstLine = false;
    } else {
      writer.print("\n");
    }
    return isFirstLine;
  }

  public static String removeWhiteSpace(String str) {
    /*
     * removes white space from a string
     * parameters: String str
     * returns: String newStr
     */
    String newStr = "";
    char lastChar = ' ';
    for (int i = 0; i < str.length(); i++) {
      if (lastChar != str.charAt(i) || lastChar != ' ') {
        lastChar = str.charAt(i);
        newStr = newStr + str.charAt(i);
      }
    }
    return newStr;
  }

  public static int findColon(String str) {
    /*
     * finds the index of the colon in the string
     * parameters: String Str
     * returns: int coloni
     */
    boolean findColon = true;
    int coloni = 0;
    do {
      if (str.charAt(coloni) != ':') {
        coloni = coloni + 1;
      } else {
        findColon = false;
      }
    } while (findColon);
    return coloni;
  }

  public static String capitalizeName(String str) {
    /*
     * finds the first letter of the words in the owner's name to capitalize them
     * parameters: String str
     * returns: String newStr
     */
    String lowerStr = str.toLowerCase();
    String newStr = "";
    String firstLetter = "";
    char lastChar = ' ';
    for (int j = 0; j < str.length(); j++) {
      if (j != 0 && lastChar != ' ' && lastChar != '(' && lastChar != '.' && lastChar != '-') {
        newStr = newStr + lowerStr.charAt(j);
      } else if (j == 0 || lastChar == ' ' || lastChar == '(' || lastChar == '.' || lastChar == '-') {
        if (97 <= (int) lowerStr.charAt(j) && (int) lowerStr.charAt(j) <= 122) {
          // this if statement checks if the character is a letter or not
          firstLetter = lowerStr.substring(j, j + 1).toUpperCase();
          newStr = newStr + firstLetter;
        } else {
          newStr = newStr + lowerStr.charAt(j);
        }
      }
      lastChar = lowerStr.charAt(j);
    }
    return newStr;
  }

  public static int countLetters(String str, int coloni) {
    /*
     * counts how many letters there are in the license plate
     * parameters: String str, int coloni
     * returns: int letterCount
    */
    int letterCount = 0;
    for (int i = coloni + 2; i < str.length(); i++) {
      if (97 <= (int) str.charAt(i) && (int) str.charAt(i) <= 122) {
        letterCount = letterCount + 1;
      } else if (65 <= (int) str.charAt(i) && (int) str.charAt(i) <= 90) {
        letterCount = letterCount + 1;
      }
    }
    return letterCount;
  }

  public static int countNumbers(String str, int coloni) {
    /*
     * counts how many numbers there are in the license plate
     * parameters: String str, int coloni
     * returns: int numberCount
    */
    int numberCount = 0;
    for (int i = coloni + 2; i < str.length(); i++) {
      if (48 <= (int) str.charAt(i) && (int) str.charAt(i) <= 57) {
        numberCount = numberCount + 1;
      }
    }
    return numberCount;
  }

  public static String licenseLettersToUpperCase(String str, int coloni) {
    /*
     * finds the letters of the license and uppercases them
     * parameters: String str, int coloni
     * returns: String tempLetterStr
    */
    // first finds letters of the license
    String tempLetterStr = "";
    for (int i = coloni + 2; i < str.length(); i++) {
      if ((97 <= (int) str.charAt(i) && (int) str.charAt(i) <= 122) || (65 <= (int) str.charAt(i) && (int) str.charAt(i) <= 90)) {
        tempLetterStr = tempLetterStr + str.charAt(i);
      }
    }
    // converts letters to uppercase
    tempLetterStr = tempLetterStr.toUpperCase();
    return tempLetterStr;
  }

  public static boolean checkValidity(String upperStr, int letterCount, int numberCount) {
    /*
     * checks the license against several different validity checks
     * parameters: String upperStr, int letterCount, int numberCount
     * returns: boolean valid
    */
    boolean validity = true;
    // wrong number of numbers?
    if (numberCount != 3) {
      validity = false;
    }
    // invalid letters in the license?
    if (letterCount == 3) {
      for (int i = 0; i < upperStr.length(); i++) {
        if (upperStr.charAt(i) == 'G' ||
            upperStr.charAt(i) == 'I' ||
            upperStr.charAt(i) == 'Q' ||
            upperStr.charAt(i) == 'U') {
          validity = false;
        }
      }
    } else if (letterCount == 4) {
      if (upperStr.charAt(0) != 'A' &&
          upperStr.charAt(0) != 'B' &&
          upperStr.charAt(0) != 'C') {
        validity = false;
      }
    }
    return validity;
  }

  public static String organizeLicensePlate(String str, int coloni, int letterCount, int numberCount, String upperCasedStr, boolean validity) {
    /*
     * reformats/reorganizes the license plate to the proper format (includes specifying invalidity)
     * parameters: String str, int coloni, int letterCount, int numberCount, String upperCasedStr, boolean validity
     * returns: String newStr
    */
    String newStr = str.substring(0, coloni + 2);
    String addToStr = "";

    // reformats the license based on the number of letters (3 letters)
    if (letterCount == 3) {

      // if the license is 6 digits long and the first digit is a number
      if (48 <= (int) str.charAt(coloni + 2) && (int) str.charAt(coloni + 2) <= 57) {
        // finds the numbers first
        for (int i = coloni + 2; i < str.length(); i++) {
          if (48 <= (int) str.charAt(i) && (int) str.charAt(i) <= 57) {
            addToStr = addToStr + str.charAt(i);
          }
        }
        // adds a hyphen
        addToStr = addToStr + "-";
        // adds the uppercased letters
        addToStr = addToStr + upperCasedStr;
        // checks if the license is invalid
        if (!validity) {
          addToStr = addToStr + " invalid";
        }
        // finally adds the license plate to the owner's name
        newStr = newStr + addToStr;

        
      // if the license is 6 digits long and the first digit is a capital letter
      } else if (65 <= (int) str.charAt(coloni + 2) && (int) str.charAt(coloni + 2) <= 90) {
        // adds the uppercased letters
        addToStr = addToStr + upperCasedStr;
        // adds a hyphen
        addToStr = addToStr + "-";
        // finds the numbers
        for (int i = coloni + 2; i < str.length(); i++) {
          if (48 <= (int) str.charAt(i) && (int) str.charAt(i) <= 57) {
            addToStr = addToStr + str.charAt(i);
          }
        }
        // checks if the license is invalid
        if (!validity) {
          addToStr = addToStr + " invalid";
        }
        // finally adds the license plate to the owner's name
        newStr = newStr + addToStr;


      // if for some reason the code above breaks (it shouldn't)
      } else {
        System.out.println("The char at " + str.charAt(coloni + 2) + " is invalid...");
      }

    // reformats the license based on the number of letters (4 letters)
    } else if (letterCount == 4) {
      // adds the uppercased letters
      addToStr = addToStr + upperCasedStr;
      // adds a hyphen
      addToStr = addToStr + "-";
      // finds the numbers
      for (int i = coloni + 2; i < str.length(); i++) {
        if (48 <= (int) str.charAt(i) && (int) str.charAt(i) <= 57) {
          addToStr = addToStr + str.charAt(i);
        }
      }
      // checks if the license is invalid
      if (!validity) {
        addToStr = addToStr + " invalid";
      }
      // finally adds the license plate to the owner's name
      newStr = newStr + addToStr;

    // if the license plate has an invalid # of letters
    } else if (letterCount != 4 || letterCount != 3) {
      System.out.println("No format found for this owner's type of license plate: "+str);
    }

    return newStr;
  }

  public static void writeUpdatedRecord(PrintWriter writer, String str) {
    /*
     * writes the updated version of the current line to the new file
     * parameters: PrintWriter writer, String str
     * returns: void
    */
    writer.print(str);
  }
}