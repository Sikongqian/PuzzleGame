package PuzzleGame.Pane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * This class records information about the Finish Scene.
 *@function Generate success and failure scene, determine whether to win or lose and jump to the correct scene.
 */
public class FinishPane extends AnchorPane {
    private Button returnButton;
    private Button retry;
    private Label result;
    private Font wordFormat;
    private Font backFormat;
    private ImageView finishBack;
    private Image backGround;

    public FinishPane(){
        retry = new Button("Retry");
        returnButton = new Button("Return");
        wordFormat = Font.font("Arial", FontWeight.LIGHT, FontPosture.REGULAR, 15);
        retry.setFont(wordFormat);
        returnButton.setFont(wordFormat);
        retry.setMinSize(150,50);
        returnButton.setMinSize(150,50);
        retry.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        returnButton.setStyle("-fx-background-color: yellow;-fx-border-color: black");
        retry.setLayoutX(150);
        retry.setLayoutY(400);
        returnButton.setLayoutX(400);
        returnButton.setLayoutY(400);

        result = new Label();
        backFormat = Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR,55);
        result.setFont(backFormat);
        finishBack = new ImageView();
        result.setPrefSize(400,100);
        result.setLayoutX(100);
        result.setLayoutY(15);

    }

    public Button getReturnButton() {
        return returnButton;
    }

    public Button getRetry() {
        return retry;
    }

    public Label getResult() {
        return result;
    }

    public ImageView getFinishBack() {
        return finishBack;
    }

    public Image getBackGround() {
        return backGround;
    }

    public void setResult(Label result) {
        this.result = result;
    }

    public void setFinishBack(ImageView finishBack) {
        this.finishBack = finishBack;
    }

    public void setBackGround(Image backGround) {
        this.backGround = backGround;
    }

    /**
     *  judging image according to isFail, add button event.
     * @param isFail
     */
    public void init(boolean isFail){
        this.getChildren().clear();
        if (!isFail){
            result.setText("YOU WIN!");
            this.setBackGround(new Image("Images/WIN.jpg"));
            finishBack.setImage(this.getBackGround());
            finishBack.setOpacity(0.9);
        } else {
            result.setText("YOU LOSE!");
            this.setBackGround(new Image("Images/LOSE.jpg"));
            finishBack.setImage(this.getBackGround());
            finishBack.setOpacity(0.9);
        }
        this.getChildren().add(result);
        this.getChildren().add(finishBack);
        this.getChildren().add(retry);
        this.getChildren().add(returnButton);
    }

}
