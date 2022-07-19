package PuzzleGame.Views;

import PuzzleGame.Input.Key;
import PuzzleGame.Spirit.Car;
import PuzzleGame.Spirit.Map;
import PuzzleGame.Spirit.Piece;
import javafx.scene.layout.BorderPane;
import PuzzleGame.App.View;
import PuzzleGame.Pane.*;
import java.util.List;
import static PuzzleGame.Framework.keyInput;

/**
 * @description This class is game page
 * @function All game content is presented here
 */
public class GameView extends View {

    private static GamePane gamePane = new GamePane();
    private static GameView gameView = new GameView();
    private static RecordPane recordPane = new RecordPane();
    private final static ControlPane controlPane = new ControlPane();
    private static BorderPane root = new BorderPane();

    @Override
    public void onLaunch() {
        this.getChildren().add(root);
    }
    @Override
    public void onUpdate(double time) {
        List<Piece> temp = gamePane.getPieceList();

        if (keyInput.isReleased(Key.A)) {
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).isTouched()) {
                    temp.get(i).rotate();
                }
            }
        }
    }

    /**
     * @description Initialize all components when entering
     * the page and add them to the page
     */
    @Override
    public void onEnter() {
        Map.map.init();
        Car.car.init();
        gamePane.init();
        GameView.getControlPane().getResetButton().setVisible(true);
        GameView.getControlPane().getPauseButton().setVisible(true);
        GameView.getControlPane().getStartButton().setVisible(true);
        GameView.getControlPane().getExitButton().setVisible(true);
        recordPane.init();
        controlPane.init();
        root.setCenter(gamePane);
        root.setRight(recordPane);
        root.setBottom(controlPane);
    }

    /**
     * @description Clear all components when leaving this page.
     */
    @Override
    public void onLeave() {
        root.getChildren().clear();
        gamePane.getChildren().clear();
    }

    public static GamePane getGamePane() {
        return gamePane;
    }

    public static GameView getGameScene() {
        return gameView;
    }

    public static RecordPane getRecordPane() {
        return recordPane;
    }

    public static ControlPane getControlPane() {
        return controlPane;
    }

    public static BorderPane getRoot() {
        return root;
    }
}
