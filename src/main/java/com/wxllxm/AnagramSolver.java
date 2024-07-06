package com.wxllxm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class AnagramSolver {
  public static void main(String[] args) {

    String wordsFilePath = "words_alpha.txt";

    System.out.println("Please enter your anagram letters...");
    // get anagram from input, convert to character array and sort alphabetically
    String anagramLetters = getUserInput();
    char[] anagramArray = anagramLetters.toCharArray();
    Arrays.sort(anagramArray);

    // try open text file
    try (BufferedReader wordsFile = openTextFile(wordsFilePath)) {
      String line;
      // ArrayList to save found anagrams
      ArrayList<String> foundWords = new ArrayList<>();
      // loop through text file
      while ((line = wordsFile.readLine()) != null) {
        // convert word to character array and sorted alphabetically
        char[] lineArray = line.toCharArray();
        Arrays.sort(lineArray);

        // if sorted characters match, anagram is stored
        if (Arrays.compare(anagramArray, lineArray) == 0) {
          foundWords.add(line);
        } else {
          continue;
        }
      }

      // print results
      if (foundWords.isEmpty()) {
        System.out.println("No anagrams found.");
      } else {
        System.out.println("\nAnagrams found:");
        foundWords.forEach(word -> {
          System.out.println(word);
        });
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  // get user input for anagram characters
  private static String getUserInput() {
    Scanner scanner = new Scanner(System.in);
    try {
      return scanner.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      scanner.close();
    }
  }

  // attempt to open text file from path
  private static BufferedReader openTextFile(String filePath) {
    try {
      return new BufferedReader(new FileReader(filePath));
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
