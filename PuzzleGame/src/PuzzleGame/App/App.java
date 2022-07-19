package PuzzleGame.App;

import PuzzleGame.Input.KeyInput;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import PuzzleGame.Framework;

import java.util.HashMap;

/**
 * This is an overall App
 * @function This class has five main functions:
 * 1. arrange stages, scenes, panes
 * 2. arrange scenes, switch views
 * 3. arrange engine's start and stop
 * 4. provide three interfaces for user interaction: onLaunch(), onFinish(), onExit()
 * 5. provide two life period function for internal use: launch(), finish()
 * @author Group 10 - Heather Xiong - 20722114
 * 2022/5/12
 */
public class App {
    //GUI
    private final Stage stage;
    private final Scene scene;
    private final Pane root;

    //name mapping for each view
    private final HashMap<String, View> viewMap;
    //current view
    private final ObjectProperty<View> currentView;

    private final Engine engine;

    private final KeyInput keyInput;

    OnLaunch onLaunch;
    OnFinish onFinish;
    OnExit onExit;

    /**
     *  App construction which add the stage, scene, engine, key or mouse input.
     * Also allocate memory for the viewMap, currentView and the initialization for Framework, app, engine.
     * @param stage Stage
     */
    public App(Stage stage){
        this.stage = stage;
        root = new StackPane();//use stack we can implement page coverage
        scene =  new Scene(root);//remember this cannot be null
        stage.setScene(scene);
        engine = new Engine();
        keyInput = new KeyInput();

        viewMap = new HashMap<>();
        currentView = new SimpleObjectProperty<>();

        initFramework();
        initApp();
        initEngine();
    }

    /**
     *  initialize the engine.
     */
    private void initEngine() {
        engine.onStart = () -> {
            for (View view: viewMap.values())
            {
                view.onStart();
            }
            keyInput.install(stage);
        };
        engine.onUpdate = (time -> {
            View view = getCurrentView();
            if(view != null) {
                view.onUpdate(time);
            }
            keyInput.refresh();
        });
        engine.onStop = () -> {
            for (View view: viewMap.values())
            {
                view.onStop();
            }
            keyInput.uninstall(stage);
        };
        //this is for the Game running in the background once Game stage's focus change
        stage.focusedProperty().addListener((o, ov, nv)->{
            if (nv) {
                engine.start();
            } else {
                engine.stop();
            }
        });
    }

    /**
     *  initialize Framework by making its app, engine and keyInput to the overall one in App.
     */
    private final void initFramework(){
        Framework.app = this;
        Framework.engine = engine;
        Framework.keyInput = keyInput;
    }

    /**
     *  initialize the App, and replace the original stage closed event with a new one.
     */
    private final void initApp() {
        scene.setFill(Color.WHITE);//background
        root.setBackground(Background.EMPTY);//root's background == null
        stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {//lambda
            if(onExit != null && !onExit.handle()) {//destroy the original closed event
                event.consume();
            }
        });
        //a view change listener where ov is the original view, nv is the new view.
        currentView.addListener((o, ov, nv)->{
            //whether having an old view
            if(ov != null) {
                ov.onLeave();
                root.getChildren().remove(ov.getPane());//remove all the widgets exit in the stage
            }
            //whether accessing a new view
            if(nv != null) {
                root.getChildren().add(nv.getPane());
                nv.onEnter();
            }
        });
    }

    public Stage getStage(){
        return stage;
    }
    public Scene getScene(){
        return scene;
    }
    public Pane getPane() {
        return root;
    }
    public String getTitle() {
        return stage.getTitle();
    }
    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public StringProperty titleProperty() {
        return stage.titleProperty();
    }

    //The window changes with the root node size, but not vice versa.
    public double getWidth() {
        return root.getMinWidth();
    }
    public void setWidth(double width){
        root.setMinWidth(width);
    }

    public DoubleProperty widthProperty() {
        return root.minWidthProperty();
    }

    public double getHeight() {
        return root.getMinHeight();
    }
    public void setHeight(double height){
        root.setMinHeight(height);
    }

    public DoubleProperty heightProperty() {
        return root.minHeightProperty();
    }

    /**
     *  This function is used to add a new view into the game.
     * @param name String the name of this new view which can be accessed in different view through button.
     * @param view View the new view be added into.
     */
    public void regView(String name, View view) {
        viewMap.put(name, view);
    }

    /**
     *  delete a view from the game.
     * @param name String name of the target view.
     */
    public void unregView(String name){
        View view = viewMap.remove(name);
        //This sentence is to avoid a situation where current view is exactly the one we are going to delete.
        if(view != null && view == getCurrentView()) {
            currentView.setValue(null);
        }
    }

    public View getView(String name){
        return viewMap.get(name);
    }

    //accessing view's property
    public View getCurrentView() {
        return currentView.get();
    }

    /*in order to protect our views in the game, we can choose only return readOnly one.*/
    public ReadOnlyObjectProperty<View> currentViewProperty() {
        return currentView;
    }

    /**
     *  An effective way of switching to another view.
     * @param name String
     */
    public void gotoView(String name) {
        View view = viewMap.get(name);
        if(view != null) {
            currentView.set(view);
        }
    }

    /* These three methods are for the interaction with users */
    //Method changed to package, to ensure that the internal framework will not be modified during secondary development
    void launch(){
        if(onLaunch != null){
            onLaunch.handle();
        }
        //launch each view
        for (View view : viewMap.values()) {
            view.onLaunch();
        }

        stage.requestFocus();
        stage.show();
    }
    void finish(){
        for (View view : viewMap.values()){
            view.onFinish();
        }
    }
    public void exit() {
        Platform.exit();
    }

    //Three interfaces. Don't need the outside world to know, change them be in the package.

    /**
     * Actions taken when launch the game
     */
    static interface OnLaunch {
        void handle();
    }

    /**
     * Actions taken when Finish the game
     */
    static interface OnFinish {
        void handle();
    }

    /**
     * Actions taken when Exit the game
     */
    static interface OnExit {
        boolean handle();
    }
}