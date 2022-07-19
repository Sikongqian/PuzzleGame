package PuzzleGame.Pane;

import PuzzleGame.Views.GameView;
import PuzzleGame.Spirit.Car;
import PuzzleGame.Spirit.Piece;
import PuzzleGame.Util.Constant;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import static PuzzleGame.Framework.app;

/**
 * This class generates a control pane for the game which controls the start and pause of the game
 *@function gives four buttons named Start, Pause, Go and Exit which control different reactions.
 */
public class ControlPane extends AnchorPane {

    private Button startButton;
    private Button exitButton;
    private Button pauseButton;
    private Button resetButton;
    public ControlPane () {
        this.setPrefHeight(100);
        this.setPrefWidth(1000);
        this.setStyle("-fx-border-color:orange;"+"-fx-background-color:#e2e1e4");

        startButton = new Button("Start");
        startButton.setLayoutX(50);
        startButton.setLayoutY(30);
        startButton.setStyle("-fx-font: 20 arial; -fx-base: #ee3f00;");
        exitButton = new Button("Exit ");
        exitButton.setLayoutX(900);
        exitButton.setLayoutY(30);
        exitButton.setStyle("-fx-font: 20 arial; -fx-base: #ee2211;");
        pauseButton = new Button("Pause");
        pauseButton.setLayoutX(200);
        pauseButton.setLayoutY(30);
        pauseButton.setStyle("-fx-font: 20 arial; -fx-base: #ee3f00;");
        resetButton = new Button("Go!");
        resetButton.setLayoutX(370);
        resetButton.setLayoutY(30);
        resetButton.setStyle("-fx-font: 20 arial; -fx-base: #ee3f00;");

        this.getChildren().add(startButton);
        this.getChildren().add(exitButton);
        this.getChildren().add(pauseButton);
        this.getChildren().add(resetButton);
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    /**
     *  add button event
     */
    public void init(){
        this.getStartButton().setOnAction(e->{
            GameView.getRecordPane().getRecordTimeText().startClock();
            for(Piece piece: GameView.getGamePane().getPieceList()){
                piece.setActive();
            }
            this.getStartButton().setVisible(false);
        });
        this.getPauseButton().setOnAction(e->{
            GameView.getRecordPane().getRecordTimeText().closeClock();
            for(Piece piece: GameView.getGamePane().getPieceList()){
                piece.setInactive();
            }
            this.getStartButton().setVisible(true);
        });
        this.getResetButton().setOnAction(e->{
            GameView.getRecordPane().getRecordTimeText().closeClock();
            GameView.getGamePane().getChildren().add(Car.car.getCarImage());
            Car.car.pathSegment();
        });
        this.getExitButton().setOnAction(e->{
            app.setTitle("Begin Scene");
            app.setWidth(Constant.OTHERSCENE_WIDTH);
            app.setHeight(Constant.OTHERPANE_HEIGHT);
            app.getStage().setWidth(Constant.OTHERSCENE_WIDTH);
            app.getStage().setHeight(Constant.OTHERPANE_HEIGHT);
            app.gotoView("Begin Scene");
        });
    }
}
