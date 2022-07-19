package PuzzleGame.Views;

import PuzzleGame.App.View;
import PuzzleGame.Pane.FinishPane;
import PuzzleGame.Util.Constant;

import static PuzzleGame.Framework.app;

/**
 * @description This class is finish page
 * @function Jump to the finish page after the car is finished,
 */
public class FinishView extends View {
    private FinishPane root;
    /** This flag is used to determine whether to return a successful image or a failed image. It is set in the car class.*/
    private static boolean isFail = true;

    /**
     * @description Initialize button event
     */
    @Override
    public void onLaunch() {
        root = new FinishPane();
        this.getChildren().add(root);
        root.getRetry().setOnAction(e->{
            app.setTitle("Game Scene");
            app.setWidth(Constant.GAMESCENE_WIDTH);
            app.setHeight(Constant.GAMESCENE_HEIGHT);
            app.getStage().setWidth(Constant.GAMESCENE_WIDTH);
            app.getStage().setHeight(Constant.GAMESCENE_HEIGHT);
            app.gotoView("Game Scene");
        });
        root.getReturnButton().setOnAction(e->{
            app.setTitle("Begin Scene");
            app.setWidth(Constant.OTHERSCENE_WIDTH);
            app.setHeight(Constant.OTHERPANE_HEIGHT);
            app.getStage().setWidth(Constant.OTHERSCENE_WIDTH);
            app.getStage().setHeight(Constant.OTHERPANE_HEIGHT);
            app.gotoView("Begin Scene");
        });
    }

    /**
     * @description Initialize the image according to the isfail flag when jumping to the page
     */
    @Override
    public void onEnter() {
        root.init(isFail);
    }

    public static void setIsFail(boolean isFail) {
        FinishView.isFail = isFail;
    }

    public static boolean isIsFail() {
        return isFail;
    }
}
