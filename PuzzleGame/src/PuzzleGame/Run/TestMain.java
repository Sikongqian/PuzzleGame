package PuzzleGame.Run;

import PuzzleGame.App.Game;
import PuzzleGame.Util.SoundEffect;
import PuzzleGame.Views.*;
import static PuzzleGame.Framework.app;

public class TestMain extends Game {
    BeginView beginView = new BeginView();
    ChooseView chooseView = new ChooseView();
    FinishView finishView = new FinishView();

    @Override
    public void onLaunch() {
        app.setTitle("Puzzle Game Pane");
        app.setWidth(700);
        app.setHeight(500);
        app.regView("Begin Scene", beginView);
        app.regView("Choose Scene", chooseView);
        app.regView("Finish Scene", finishView);
        app.regView("Game Scene", GameView.getGameScene());
        app.gotoView("Begin Scene");
        app.getStage().setResizable(false);
        app.getStage().show();
        //SoundEffect.play("Sounds/车行驶.WAV");
    }
}
