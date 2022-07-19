package PuzzleGame.App;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * View class which holds pane. Pane is the view players see and interact with.
 * @function This is the parent for every view created. View life cycle methods:
 * @function 1. onLaunch(): launch the components in the view, onFinish()
 * @function 2. onEnter(), onLeave()
 * @function 3. onStart():start the game, onUpdate(): update the view, adding some function like key or mouse detection, onStop()
 * @author Group 10 - Heather Xiong - 20722114
 * 2022/5/14
 */
public abstract class View {

    private final StackPane pane;

    public View() {
        pane = new StackPane();//Stack layout, layout overlay
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(Background.EMPTY);//component color get changed
    }

    public Pane getPane() {
        return pane;
    }

    //One of the more common methods, we can use it without .getPane
    public ObservableList<Node> getChildren() {
        return pane.getChildren();
    }

    public abstract void onLaunch();
    public void onEnter() {
        //do sth in subClass
    }
    public void onStart() {
        //do sth in subClass
    }
    public void onUpdate(double time) {
        //do sth in subClass
    }
    public void onStop() {
        //do sth in subClass
    }
    public void onLeave() {
        //do sth in subClass
    }
    public void onFinish() {
        //do sth in subClass
    }

}
