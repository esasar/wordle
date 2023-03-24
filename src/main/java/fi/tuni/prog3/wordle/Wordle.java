package fi.tuni.prog3.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static fi.tuni.prog3.wordle.Constants.*;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * JavaFX App
 */
public class Wordle extends Application {
  private static int currentRow = 0;
  private static Boolean gameWonStatus = false;
  private static String word;
  private static ArrayList<ArrayList<Label>> labels;
  private static int sceneWidth;
  private static GridPane lettersGridPane; 
  private static WordBank wordBank;
  private static Label gameInfoLabel;

    @Override
    public void start(Stage stage) {
      
      stage.setTitle(TITLE);
      
      wordBank = new WordBank(FILENAME);

      GridPane mainGrid = new GridPane();

      gameInfoLabel = new Label(INFO_LABEL_DEFAULT);
      gameInfoLabel.setId(INFO_LABEL_ID);
      Button newGameButton = new Button(NEW_GAME_BUTTON_LABEL);
      newGameButton.setId(NEW_GAME_BUTTON_ID);

      FlowPane fp = new FlowPane();
      fp.getChildren().addAll(newGameButton, gameInfoLabel);

      mainGrid.add(fp, 0, 0);

      lettersGridPane = new GridPane();

      lettersGridPane.setHgap(PADDING);
      lettersGridPane.setVgap(PADDING);
      lettersGridPane.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

      mainGrid.add(lettersGridPane, 0, 1);

      initGame(lettersGridPane);

      Scene scene = new Scene(mainGrid, sceneWidth, SCENE_HEIGHT + 50); // TODO: Fix scene size

      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
          // Only process english alphabet, backspace and enter
          if(!ALLOWED_KEYS.contains(event.getCode())) {
            return;
          }
          // If we have reached the maximum number of guesses or game is won, do nothing
          if(currentRow >= MAX_GUESSES || gameWonStatus) {
            return;
          }

          ArrayList<Label> currentLabels = labels.get(currentRow);

          // Handle backspace, delete last character
          if(event.getCode() == KeyCode.BACK_SPACE) {
            for(int i = currentLabels.size() - 1; i >= 0; --i) {
              Label l = currentLabels.get(i);
              if(!l.getText().equals("")) {
                l.setText("");
                return;
              }
            }
          }

          // Handle enter, go to next row
          if(event.getCode() == KeyCode.ENTER) {
            // Check if each label on the row has a character
            for(Label l : currentLabels) {
              if(l.getText().equals("")) {
                gameInfoLabel.setText(INFO_LABEL_NON_COMPLETE_WORD);
                return;
              }
            }
            gameInfoLabel.setText(INFO_LABEL_DEFAULT);
            // Check for correct place and correct letter
            ArrayList<Integer> usedLabelInds = new ArrayList<>();
            ArrayList<Integer> usedAnswerInds = new ArrayList<>();
            for(int i = 0; i < word.length(); ++i) {
              Label l = currentLabels.get(i);
              String c = String.valueOf(word.charAt(i));
              if(l.getText().equals(c)) {
                l.setBackground(CORRECT_GUESS_BACKGROUND);
                usedLabelInds.add(i);
                usedAnswerInds.add(i);
              } else {
                l.setBackground(WRONG_GUESS_BACKGROUND);
              }
            }
            
            // Check if game is won
            if (usedLabelInds.size() == word.length()) {
              gameInfoLabel.setText(GAME_WON_TEXT);
              gameWonStatus = true;
              return;
            }

            // Check for correct letter but incorrect place
            for(int i = 0; i < word.length(); ++i) {
              if(usedLabelInds.contains(i)) {
                continue;
              }
              Label l = currentLabels.get(i);
              for(int j = 0; j < word.length(); ++j) {
                if(usedAnswerInds.contains(j)) {
                  continue;
                }
                String c = String.valueOf(word.charAt(j));
                if(l.getText().equals(c)) {
                  l.setBackground(CORRECT_LETTER_WRONG_PLACE_BACKGROUND);
                  usedAnswerInds.add(j);
                  break;
                }
              }
            }
            
            ++currentRow;
            if(currentRow == MAX_GUESSES) {
              gameInfoLabel.setText(GAME_LOST_TEXT);
            }
            return;
          }
          
          // Handle other strokes (letters)
          String keyPressed = event.getText().toUpperCase();
          for(Label l : currentLabels) {
            if(l.getText().equals("")) {
              l.setText(keyPressed);
              break;
            }
          }
          
        }
      });

      lettersGridPane.requestFocus(); // Request focus away from the newGameButton
      newGameButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent e) {
          System.out.println("New game button pressed");
          initGame(lettersGridPane);
          stage.setWidth(sceneWidth);
        }
        
      });


      stage.setScene(scene);
      stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void initGame(GridPane lettersGridPane) {
      lettersGridPane.getChildren().clear();
      
      currentRow = 0;
      gameWonStatus = false;
      gameInfoLabel.setText(INFO_LABEL_DEFAULT);
      word = wordBank.getNextWord();
      sceneWidth = word.length() * LABEL_SIZE + (word.length() + 2) * PADDING;
      System.out.println("Word: " + word);
      labels = new ArrayList<>();

      for(int row = 0; row < MAX_GUESSES; ++row) {
        ArrayList<Label> labelRow = new ArrayList<>();
        for(int column = 0; column < word.length(); ++column) {
           Label l = new Label("");
           l.setMinWidth(LABEL_SIZE);
           l.setMinHeight(LABEL_SIZE);
           l.setBackground(DEFAULT_BACKGROUND);
           l.setBorder(DEFAULT_BORDER);
           l.setFont(DEFAULT_FONT);
           l.setAlignment(Pos.CENTER);
           String lId = row + "_" + column;
           l.setId(lId);
           labelRow.add(l);
           lettersGridPane.add(l, column, row, 1, 1);
         }
         labels.add(labelRow);
         lettersGridPane.requestFocus();
       }
    }
    
    
}