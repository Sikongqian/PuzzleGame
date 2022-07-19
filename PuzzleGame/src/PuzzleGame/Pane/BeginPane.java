package PuzzleGame.Pane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This class records information about the Beginning Scene
 *@function sets Game scene and gives the scene three buttons and different reactions when clicked.
 */
public class BeginPane extends AnchorPane {

    private Button newGame;
    private Button chooseLevel;
    private Label backWord;
    private ImageView beginBack;
    private Image backGround;
    private Font wordFormat;
    private Font backFormat;

    /**
     * Construct the Begin scene. Initialize all components
     */
    public BeginPane() {
        this.newGame = new Button("New Game");
        this.chooseLevel = new Button("Choose Level");
        this.wordFormat = Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 15.0D);
        this.newGame.setFont(this.wordFormat);
        this.chooseLevel.setFont(this.wordFormat);
        this.newGame.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.chooseLevel.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.newGame.setMinSize(100.0D, 45.0D);
        this.chooseLevel.setMinSize(100.0D, 45.0D);
        this.newGame.setLayoutX(250.0D);
        this.newGame.setLayoutY(300.0D);
        this.chooseLevel.setLayoutX(450.0D);
        this.chooseLevel.setLayoutY(300.0D);
        this.backWord = new Label();
        this.backWord.setPrefSize(700.0D, 50.0D);
        this.backWord.setLayoutX(220.0D);
        this.backWord.setLayoutY(150.0D);
        this.backWord.setText("A Small Road Builder");
        this.backFormat = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 40.0D);
        this.backWord.setFont(this.backFormat);
        this.beginBack = new ImageView();
        this.backGround = new Image("Images/background.png");
        this.beginBack.setImage(this.backGround);
        this.beginBack.setOpacity(0.75D);
        this.getChildren().add(this.beginBack);
        this.getChildren().add(this.newGame);
        this.getChildren().add(this.chooseLevel);
        this.getChildren().add(this.backWord);
    }
    public Button getNewGame() {
        return newGame;
    }

    public Button getChooseLevel() {
        return chooseLevel;
    }
}
