package PuzzleGame.Pane;

import PuzzleGame.Spirit.*;
import PuzzleGame.Util.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * This class records information about the Game Scene
 *@function All the game components have to be added to it
 */
public class GamePane extends AnchorPane {

    private List<Piece> pieceList;

    public GamePane() {
        this.setPrefHeight(Constant.GAMEPANE_HEIGHT);
        this.setPrefWidth(Constant.GAMEPANE_WIDTH+300);
        this.setMaxHeight(Constant.GAMEPANE_HEIGHT);
        this.setMaxWidth(Constant.GAMEPANE_WIDTH+300);
        this.setMinHeight(Constant.GAMEPANE_HEIGHT);
        this.setMinWidth(Constant.GAMEPANE_WIDTH);
        this.setStyle("-fx-border-color:orange;"+"-fx-effect: innershadow(three-pass-box,rgba(0,0,0,0.8),15,0.0,0,4)");
    }
    public List<Piece> getPieceList() {
        return pieceList;
    }

    /**
     *  Initialize puzzle pieces
     */
    public void init() {
        pieceList = new ArrayList<Piece>();
        Piece p1 = new Piece1(Constant.PIECE1_INITX, Constant.PIECE1_INITY); p1.setInactive();
        Piece p2 = new Piece2(Constant.PIECE2_INITX, Constant.PIECE2_INITY); p2.setInactive();
        Piece p3 = new Piece3(Constant.PIECE3_INITX, Constant.PIECE3_INITY); p3.setInactive();
        pieceList.add(p1);
        pieceList.add(p2);
        pieceList.add(p3);
        this.getChildren().addAll(pieceList);
        this.getChildren().add(Map.map.getMapImage());
        this.getChildren().add(Map.map.getBeginningImage());
        this.getChildren().add(Map.map.getDestinationImage());
        Map.map.getMapImage().toBack();

        Text price1 = new Text("150");
        Text price2 = new Text("100");
        Text price3 = new Text("50");
        price1.setFont(new Font("Arial", 20));
        price2.setFont(new Font("Arial", 20));
        price3.setFont(new Font("Arial", 20));
        price1.setLayoutX(150);
        price1.setLayoutY(50);
        price2.setLayoutX(150);
        price2.setLayoutY(200);
        price3.setLayoutX(150);
        price3.setLayoutY(350);

        this.getChildren().add(price1);
        this.getChildren().add(price2);
        this.getChildren().add(price3);
    }
}
