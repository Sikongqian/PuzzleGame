package PuzzleGame.Pane;

import PuzzleGame.Util.RecordTimeText;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class RecordPane extends AnchorPane {

    private Text text;
    private Label time;
    private Label price;
    /** private RecordTimeTe recordTimeText;*/
    private RecordTimeText recordTimeText;

    private Text piecePrice;

    public RecordPane() {
        this.setPrefHeight(500);
        this.setPrefWidth(200);
        this.setStyle("-fx-border-color:orange;"+"-fx-background-color:#e2e1e4");

        recordTimeText = new RecordTimeText();

        piecePrice = new Text("0");
        piecePrice.setLayoutX(88);
        piecePrice.setLayoutY(420);
        piecePrice.setFont(new Font("Arial", 20));
        text = new Text();
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        text.setEffect(ds);
        text.setCache(true);
        text.setX(30);
        text.setY(100);
        text.setFill(Color.RED);
        text.setText("Integral plate:");
        text.setFont(Font.font(null, FontWeight.BOLD, 20));
        this.getChildren().add(text);

        time = new Label("Time:");
        price = new Label("Price:");
        time.setLayoutX(30);
        time.setLayoutY(250);
        time.setStyle("-fx-background-color:POWDERBLUE");
        time.setFont(new Font("Arial", 20));
        time.setTextFill(Color.web("#f0a1a8"));
        time.setPrefWidth(150);
        time.setWrapText(true);
        price.setLayoutY(400);
        price.setLayoutX(30);
        price.setStyle("-fx-background-color:POWDERBLUE");
        price.setFont(new Font("Arial", 20));
        price.setTextFill(Color.web("#f0a1a8"));
        price.setPrefWidth(150);
        price.setWrapText(true);
        this.getChildren().add(time);
        this.getChildren().add(price);
        this.getChildren().add(recordTimeText.getText_time());
        this.getChildren().add(piecePrice);
    }

    public void setRecordTimeText(RecordTimeText recordTimeText) {
        this.recordTimeText = recordTimeText;
    }

    public RecordTimeText getRecordTimeText() {
        return recordTimeText;
    }

    public Text getPiecePrice() {
        return piecePrice;
    }

    public void init(){
        piecePrice.setText("0");
        this.getChildren().remove(recordTimeText.getText_time());
        recordTimeText = new RecordTimeText();
        this.getChildren().add(recordTimeText.getText_time());
    }
}
