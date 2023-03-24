
package fi.tuni.prog3.wordle;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public interface Constants {
  public static final String TITLE = "Wordle";
  public static final String NEW_GAME_BUTTON_LABEL = "Start new game";
  public static final String NEW_GAME_BUTTON_ID = "newGameBtn";
  public static final String INFO_LABEL_DEFAULT = "";
  public static final String INFO_LABEL_NON_COMPLETE_WORD = "Give a complete word before pressing Enter!";
  public static final String INFO_LABEL_ID = "infoBox";
  public static final String GAME_LOST_TEXT = "Game over, you lost!";
  public static final String GAME_WON_TEXT = "Congratulations, you won!";
  public static final int PADDING = 10;
  public static final int LABEL_SIZE = 50;
  
  
  public static final String FILENAME = "words.txt";
  
  public static final int MAX_GUESSES = 6;
  
  public static final int SCENE_HEIGHT = LABEL_SIZE * MAX_GUESSES + PADDING * (MAX_GUESSES + 1);
  
  public static final Background DEFAULT_BACKGROUND = new Background(new BackgroundFill(
          Color.WHITE, 
          null, 
          null)
  );

  public static final Background CORRECT_GUESS_BACKGROUND = new Background(new BackgroundFill(
          Color.GREEN, 
          null, 
          null)
  );

  public static final Background WRONG_GUESS_BACKGROUND = new Background(new BackgroundFill(
        Color.GREY, 
        null, 
        null)
        );
  
 public static final Background CORRECT_LETTER_WRONG_PLACE_BACKGROUND = new Background(new BackgroundFill(
        Color.ORANGE, 
        null, 
        null)
        );
  
  public static final Border DEFAULT_BORDER = new Border(new BorderStroke(
          Color.BLACK, 
          BorderStrokeStyle.DASHED,
          CornerRadii.EMPTY,
          BorderWidths.DEFAULT
  ));

  public static final Font DEFAULT_FONT = new Font("Arial", 20);

  public static final ArrayList<KeyCode> ALLOWED_KEYS = new ArrayList<KeyCode>() {
    {
        add(KeyCode.A);
        add(KeyCode.B);
        add(KeyCode.C);
        add(KeyCode.D);
        add(KeyCode.E);
        add(KeyCode.F);
        add(KeyCode.G);
        add(KeyCode.H);
        add(KeyCode.I);
        add(KeyCode.J);
        add(KeyCode.K);
        add(KeyCode.L);
        add(KeyCode.M);
        add(KeyCode.N);
        add(KeyCode.O);
        add(KeyCode.P);
        add(KeyCode.Q);
        add(KeyCode.R);
        add(KeyCode.S);
        add(KeyCode.T);
        add(KeyCode.U);
        add(KeyCode.V);
        add(KeyCode.W);
        add(KeyCode.X);
        add(KeyCode.Y);
        add(KeyCode.Z);
        add(KeyCode.ENTER);
        add(KeyCode.BACK_SPACE);
    }
  };
}
