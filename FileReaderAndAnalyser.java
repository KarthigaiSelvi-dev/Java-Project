package com.pak;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReaderAndAnalyser {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Read and Analyze File");
            System.out.println("2. Exit");
            System.out.println("3.Invalid choice");
            System.out.print("Enter your choice: ");
            int choice = s.nextInt();
            s.nextLine();
        // Prompt the user for the file path
        
        switch (choice) {
        case 1:
        System.out.print("Enter the path of the text file: ");
        String filePath = s.nextLine();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) { //A FileReader object that uses a BufferedReader to read from the file
            String line;
            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;
            HashMap<String, Integer> wordFrequency = new HashMap<String, Integer>(); //hashmap is used to store word freequency

            while ((line = reader.readLine()) != null) { //reads each line from the file using 'reader.readline()'until it returns null
               lineCount++;   //increment each line
                
                charCount = charCount + line.length(); //adding the length of the current line
               
                // Split the line into words using space as the delimiter
                String[] words = line.split(" ");
                wordCount += words.length;   //counting the number of words in a current line
              
                // Count word frequency
                for (String word : words) {
                    word = word.toLowerCase().replaceAll("[^a-zA-Z]", ""); // Remove non-alphabetic characters
                    if (!word.isEmpty()) {
                        wordFrequency.put(word, wordFrequency.getOrDefault(word, 0)+1); //increamenting the count of each word
                    }
                }
            }

            System.out.println("Line Count: " + lineCount);
            System.out.println("Word Count: " + wordCount);
            System.out.println("Character Count: " + charCount);
           

            // Display word frequency
            System.out.println("\nWord Frequency:");
            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                System.out.println( entry.getKey()+":"+entry.getValue() + " times");
            }

            // Search for specific words or phrases
           System.out.print("\nEnter a word or phrase to search (or press Enter to skip): ");
           String searchWord = s.nextLine().toLowerCase();
            if (!searchWord.isEmpty()) {
                int occurrences = wordFrequency.getOrDefault(searchWord, 0);
                System.out.println("Occurrences of '" + searchWord + "': " + occurrences); 
                
            }
        } catch (IOException e) {
           System.err.println("Error reading the file: " + e.getMessage());
        }
        case 2:
        	System.out.println("Good bye..Thank you");
        	break;
       
        case 3:
            System.out.println("Invalid choice");
            break; 
        default :
        	System.out.println("Error Reading the file");
        }
      }
    }
}