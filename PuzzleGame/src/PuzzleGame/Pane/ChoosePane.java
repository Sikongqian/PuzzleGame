package PuzzleGame.Pane;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This class records information about the Choose pane
 *@function sets the choose pane and gives each button a different response
 */
public class ChoosePane extends AnchorPane {

    private Button level1;
    private Button level2;
    private Button level3;
    private Button level4;

    private Button returnButton;
    private Font wordFormat1;
    private Font wordFormat2;
    private ImageView chooseBack;
    private Image backGround;

    /**
     * Construct the choose pane.
     */
    public ChoosePane() {
        this.level1 = new Button("Level 1");
        this.level2 = new Button("Level 2");
        this.level3 = new Button("Level 3");
        this.level4 = new Button("Level 4");
        this.returnButton = new Button("Return");
        this.wordFormat1 = Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 25.0D);
        this.level1.setFont(this.wordFormat1);
        this.level2.setFont(this.wordFormat1);
        this.level3.setFont(this.wordFormat1);
        this.level4.setFont(this.wordFormat1);
        this.wordFormat2 = Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 15.0D);
        this.returnButton.setFont(this.wordFormat2);
        this.level1.setMinSize(225.0D, 100.0D);
        this.level2.setMinSize(225.0D, 100.0D);
        this.level3.setMinSize(225.0D, 100.0D);
        this.level4.setMinSize(225.0D, 100.0D);
        this.returnButton.setMinSize(150.0D, 50.0D);
        this.level1.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.level2.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.level3.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.level4.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.returnButton.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        this.level1.setLayoutX(100.0D);
        this.level1.setLayoutY(100.0D);
        this.level2.setLayoutX(375.0D);
        this.level2.setLayoutY(100.0D);
        this.level3.setLayoutX(100.0D);
        this.level3.setLayoutY(250.0D);
        this.level4.setLayoutX(375.0D);
        this.level4.setLayoutY(250.0D);
        this.returnButton.setLayoutX(500.0D);
        this.returnButton.setLayoutY(425.0D);
        this.chooseBack = new ImageView();
        this.backGround = new Image("Images/chooseBack.jpg");
        this.chooseBack.setImage(this.backGround);
        this.chooseBack.setOpacity(0.75D);
        this.getChildren().add(this.chooseBack);
        this.getChildren().add(this.level1);
        this.getChildren().add(this.level2);
        this.getChildren().add(this.level3);
        this.getChildren().add(this.level4);
        this.getChildren().add(this.returnButton);

    }

    public Button getLevel1() {
        return level1;
    }

    public Button getLevel2() {
        return level2;
    }

    public Button getLevel3() {
        return level3;
    }

    public Button getLevel4() {
        return level4;
    }

    public Button getReturnButton() {
        return returnButton;
    }
}
