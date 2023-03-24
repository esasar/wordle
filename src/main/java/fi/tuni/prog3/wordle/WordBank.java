
package fi.tuni.prog3.wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class WordBank {
  private ArrayList<String> words;
  
  public WordBank(String filename) {
    words = new ArrayList<>();
    readFile(filename);
  }
  
  public String getNextWord() {
    return words.remove(0);
  }
  
  private void readFile(String filename) {
    try(var input = new BufferedReader(new FileReader(filename))) {
      String line;
      while((line = input.readLine()) != null) {
        words.add(line.toUpperCase());
      }
    } catch(Exception e) {
      
    }
  }
}
