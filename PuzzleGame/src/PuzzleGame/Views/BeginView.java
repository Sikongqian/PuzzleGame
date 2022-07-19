package PuzzleGame.Views;

import PuzzleGame.App.View;
import PuzzleGame.Pane.BeginPane;
import PuzzleGame.Spirit.Map;
import PuzzleGame.Util.Constant;

import static PuzzleGame.Framework.app;

/**
 * @description This class is begin page
 * @function Begin page can jump to the choose page, display the leaderboard.
 */
public class BeginView extends View {
    BeginPane root;

    /**
     * @description When an instance of this class is added to the app, the button event will be added.
     */
    @Override
    public void onLaunch() {
         root = new BeginPane();
         this.getChildren().add(root);
         root.getChooseLevel().setOnAction(e->{
             app.setTitle("Choose Scene");
             app.setWidth(Constant.OTHERSCENE_WIDTH);
             app.setHeight(Constant.OTHERPANE_HEIGHT);
             app.getStage().setWidth(Constant.OTHERSCENE_WIDTH);
             app.getStage().setHeight(Constant.OTHERPANE_HEIGHT);
             app.gotoView("Choose Scene");
         });
        root.getNewGame().setOnAction(e->{
            app.setTitle("Game Scene");
            app.setWidth(Constant.GAMESCENE_WIDTH);
            app.setHeight(Constant.GAMESCENE_HEIGHT);
            app.getStage().setWidth(Constant.GAMESCENE_WIDTH);
            app.getStage().setHeight(Constant.GAMESCENE_HEIGHT);
            Map.map.init(2,1,0,1,6);
            app.gotoView("Game Scene");
        });
    }
}
