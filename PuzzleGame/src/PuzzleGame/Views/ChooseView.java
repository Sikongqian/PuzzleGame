package PuzzleGame.Views;

import PuzzleGame.App.View;
import PuzzleGame.Pane.ChoosePane;
import PuzzleGame.Spirit.Map;
import PuzzleGame.Util.Constant;

import static PuzzleGame.Framework.app;

/**
 * @description This class is the choose page
 * @function Choose page can select game level, or back to begin page.
 */
public class ChooseView extends View {
    ChoosePane root;

    /**
     * @description Initialize the button event, note that the initialization parameters for the map are different when different levels are selected.
     */
    @Override
    public void onLaunch() {
        root = new ChoosePane();
        this.getChildren().add(root);
        root.getLevel1().setOnAction(e->{
            app.setTitle("Game Scene");
            app.setWidth(Constant.GAMESCENE_WIDTH);
            app.setHeight(Constant.GAMESCENE_HEIGHT);
            app.getStage().setWidth(Constant.GAMESCENE_WIDTH);
            app.getStage().setHeight(Constant.GAMESCENE_HEIGHT);
            Map.map.init(2,1,0,1,6);
            app.gotoView("Game Scene");
        });
        root.getLevel2().setOnAction(e->{
            app.setTitle("Game Scene");
            app.setWidth(Constant.GAMESCENE_WIDTH);
            app.setHeight(Constant.GAMESCENE_HEIGHT);
            app.getStage().setWidth(Constant.GAMESCENE_WIDTH);
            app.getStage().setHeight(Constant.GAMESCENE_HEIGHT);
            Map.map.init(0,1,2,2,2);
            app.gotoView("Game Scene");
        });
        root.getLevel3().setOnAction(e->{
            app.setTitle("Game Scene");
            app.setWidth(Constant.GAMESCENE_WIDTH);
            app.setHeight(Constant.GAMESCENE_HEIGHT);
            app.getStage().setWidth(Constant.GAMESCENE_WIDTH);
            app.getStage().setHeight(Constant.GAMESCENE_HEIGHT);
            Map.map.init(0,0,0,3,3);
            app.gotoView("Game Scene");
        });
        root.getLevel4().setOnAction(e->{
            app.setTitle("Game Scene");
            app.setWidth(Constant.GAMESCENE_WIDTH);
            app.setHeight(Constant.GAMESCENE_HEIGHT);
            app.getStage().setWidth(Constant.GAMESCENE_WIDTH);
            app.getStage().setHeight(Constant.GAMESCENE_HEIGHT);
            Map.map.init(0,3,2,0,0);
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
}