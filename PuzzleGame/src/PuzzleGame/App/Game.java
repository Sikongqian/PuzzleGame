package PuzzleGame.App;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Game class
 * @function This is the parent of all the game level created in this game.
 * @author Group 10 - Heather Xiong - 20722114
 */

public class Game extends Application {

    private App app;
    //Three lifecycle approaches
    public void onLaunch(){
        //do sth in subClass
    }
    //For example: Print -> Confirm exit?
    public void onFinish(){
        //do sth in subClass
    }
    //confirm end
    public boolean onExit(){
        return true;
    }
    //With final, avoid overridden of the start framework by subclasses.
    @Override
    public final void start(Stage primaryStage) throws Exception {
        app = new App(primaryStage);
        //user interaction with Game in application which will be transferred to Game.
        app.onLaunch = this::onLaunch;//lambda; app.onLaunch = new App.OnLaunch() {...}
        app.onFinish = this::onFinish;
        app.onExit = this::onExit;
        app.launch();
    }

    @Override
    public final void stop() throws Exception {
        app.finish();
    }
}
