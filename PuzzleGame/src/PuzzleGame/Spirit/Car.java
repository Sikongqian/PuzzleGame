package PuzzleGame.Spirit;

import PuzzleGame.Framework;
import PuzzleGame.Views.FinishView;
import PuzzleGame.Views.GameView;
import PuzzleGame.Util.*;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;

import static PuzzleGame.Framework.app;

/**
 * This class record the basic information of the car, and the method of generating the car path.
 * @function Draw the path at the end of placing the puzzle, include judging  whether the path is draw successfully and the running of the car.
 */
public class Car {
    ImageView carImage;
    Path carPath;
    PathTransition carPathTransition;

    /** Create statically instantiated objects.*/
    public static Car car = new Car();

    /**
     *  This method is the constructor of the class and contains initialization of the car image, path, starting point, and animation.
     */
    public Car(){
        carImage = new ImageView(new Image("images/car.png"));
        carPath = new Path();
        carPath.getElements().add(new MoveTo(Constant.CONTROL_A_POINTX + 250,Constant.CONTROL_A_POINTY));
        carPathTransition = new PathTransition();
    }

    /**
     *  This method is an initialization method that initializes the picture, path, and animation of the car.
     */
    public void init(){
        carImage = new ImageView(new Image("images/car.png"));
        carPath = new Path();
        carPathTransition = new PathTransition();
    }

    /**
     *  This method is used to get a imageview instance of a car.
     * @return carImage
     */
    public ImageView getCarImage() {
        return carImage;
    }

    /**
     *  This method is used to get the value of the exit point placed after the puzzle is rotated.
     * @param inPoint The entry point of the puzzle.
     * @param type The type of puzzle, there are three types of puzzle pieces: hyperbolic, cross straight and arc.
     * @param angle Angle of rotation of puzzle pieces before placement.
     * @return nowOutPoint
     */
    public int getOutpoint(int inPoint, int type, int angle){
        int outPoint = -1;
        int nowOutPoint = 0;
        int [][] allPair = new int[4][2];
        /* Use two-dimensional arrays to store possible entry and exit points for all puzzle types.*/
        switch (type){
            case 1 : {
                allPair = new int[][]{{1,7}, {7,1}, {3,5}, {5,3}};
                break;
            }
            case 2 : {
                allPair = new int[][]{{0,4}, {4,0}, {2,6}, {6,2}};
                break;
            }
            case 3 : {
                allPair = new int[][]{{0,3}, {3,0},{-1,-1},{-1,-1}};
            }
        }

        /* The puzzle entry point after rotated.*/
        inPoint = (inPoint + (360 - angle)/45) % 8;
        /* The standard exit point is determined from the puzzle entry point.*/
        for (int i=0; i<4; i++){
            if (inPoint == allPair[i][0]){
                outPoint = allPair[i][1];
                break;
            }else{
                outPoint = -1;
            }
        }

        if(outPoint == -1) return outPoint;
        /* Calculate the puzzle points after rotation.*/
        nowOutPoint =  (outPoint+angle/45)%8;

        return nowOutPoint;
    }

    /**
     *  This method calculates the entry point to return the next puzzle piece.
     * @param outPoint The exit point value of the last puzzle piece.
     * @return The value of next entry point.
     */
    public int getInPoint (int outPoint){
        return (outPoint + 4) % 8;
    }

    /**
     *  The method is to calculate the map x-coordinates for the next piece of the puzzle.
     * @param X The x-coordinate of the previous map.
     * @param outPoint The exit point value of the last puzzle piece.
     * @return The x-coordinate of the next map.
     */
    public static int getNextPieceX(int X, int outPoint){
        int i = 0;
        if(outPoint==0||outPoint==4){
            i = X;
        }
        if(outPoint==1||outPoint==2||outPoint==3){
            i= X+1;

        }
        if(outPoint==5||outPoint==6||outPoint==7){
            i = X-1;
        }
        return i;
    }

    /**
     *  The method is to calculate the map y-coordinates for the next piece of the puzzle.
     * @param Y The y-coordinate of the previous map.
     * @param outPoint The exit point value of the last puzzle piece.
     * @return The y-coordinate of the next map.
     */
    public static int getNextPieceY(int Y, int outPoint){
        int i = 0;
        if(outPoint==2||outPoint==6){
            i = Y;
        }
        if(outPoint==0||outPoint==1||outPoint==7){
            i = Y-1;
        }
        if(outPoint==3||outPoint==4||outPoint==5){

            i = Y+1;
        }
        return i;
    }

    /**
     *  This method is to plot the internal path of each map.
     * @param x The x coordinate of the current map position
     * @param y The y coordinate of the current map position
     * @param type Type of current puzzle.
     * @param outPoint The exit point value of the current puzzle after placement.
     * @param inPoint The enter point value of the current puzzle after placement.
     */
    public void paintPath(int x, int y, int type, int outPoint, int inPoint){
        Coordinate coordinate = new Coordinate();
        switch (type) {
            /* Hyperbolic puzzle*/
            case 1: {
                QuadCurveTo quadCurveTo = new QuadCurveTo( coordinate.getControlX(x), coordinate.getControlY(y),
                        coordinate.getInOutPoint(x,y,outPoint).getX(), coordinate.getInOutPoint(x,y,outPoint).getY());
                carPath.getElements().add(quadCurveTo);
            }
            break;
            /* Cross straight puzzle*/
            case 2: {
                LineTo lineTo = new LineTo(coordinate.getInOutPoint(x,y,outPoint).getX(), coordinate.getInOutPoint(x,y,outPoint).getY());
                carPath.getElements().add(lineTo);
            }
            break;
            /* Arc puzzle*/
            case 3: {
                boolean flag = false;
                if((outPoint - inPoint == 5 || outPoint - inPoint == -3)){
                    flag = true;
                }
                ArcTo arcTo = new ArcTo(125, 125, 0,coordinate.getInOutPoint(x,y,outPoint).getX(), coordinate.getInOutPoint(x,y,outPoint).getY(),
                        false, flag);
                carPath.getElements().add(arcTo);
            }
            break;
        }
    }

    /**
     *  This method is the main function for drawing a path by calling the previous class method.
     */
    public void pathSegment() {
        GameView.getControlPane().getResetButton().setVisible(false);
        GameView.getControlPane().getPauseButton().setVisible(false);
        GameView.getControlPane().getStartButton().setVisible(false);
        GameView.getControlPane().getExitButton().setVisible(false);
        boolean isFail = false;
        /* Initialization */
        int x = getNextPieceX(Map.map.getBeginningX(), Map.map.getStartPoint());
        int y = getNextPieceY(Map.map.getBeginningY(), Map.map.getStartPoint());
        int inPoint = (Map.map.getStartPoint() + 4) % 8;
        System.out.println("x "+x+" y "+y+" start "+inPoint);
        carPath.getElements().add(new MoveTo((Constant.CONTROL_A_POINTX + 250) + Map.map.getBeginningX()*125 ,
                (Constant.CONTROL_A_POINTY) + Map.map.getBeginningY()*125));
        carPath.getElements().add(new LineTo((Constant.CONTROL_A_POINTX + 250) + Map.map.getBeginningX()*125-10 ,
                        (Constant.CONTROL_A_POINTY) + Map.map.getBeginningY()*125));
        int outPoint = 0;
        carPathTransition.setNode(carImage);
        /* The rotation matrix of the car is set to remain perpendicular to the tangent of the path along the geometric path.*/
        carPathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        /* Set animation delay.*/
        carPathTransition.setDuration(Duration.seconds(3));
        while (Map.map.mapInfo[x][y][0] != -1 ){
            System.out.println("进入循环");
            outPoint = getOutpoint(inPoint, Map.map.mapInfo[x][y][0], Map.map.mapInfo[x][y][1]);
            /*1. Puzzle entry not matching, stop drawing path.*/
            if(outPoint == -1){
                isFail = true; break;
            }
            paintPath(x, y, Map.map.mapInfo[x][y][0], outPoint, inPoint);
            /* Find the next map coordinates */
            x = getNextPieceX(x, outPoint);
            y = getNextPieceY(y, outPoint);
            inPoint = getInPoint(outPoint);
            /*2.  Car to the end, stop drawing the path.*/
            if(x == Map.map.getDestinationX() && y == Map.map.getDestinationY()){
                isFail = false;
                break;
            }
            /* 3. Car out of bounds, stop drawing path.*/
            if((x > 3 || x < 0) || (y > 3 || y < 0)){
                isFail = true;
                break;
            }

        }
        /* 4. Stop drawing the path without placing the puzzle in the next piece. */
        if(isFail != true && Map.map.mapInfo[x][y][0] == -1 && (x != Map.map.getDestinationX() || y != Map.map.getDestinationY())){
            isFail = true;
        }
        System.out.println(Map.map.getDestinationX()+" "+Map.map.getDestinationY());

        /* Add the car path in transition */
        carPathTransition.setPath(carPath);
        System.out.println("play");
        carPathTransition.play();
        for(Piece piece: GameView.getGamePane().getPieceList()){
            piece.setInactive();
        }
        FinishView.setIsFail(isFail);

        /* After the car finishes running, switch the page */
        carPathTransition.setOnFinished(e->{
                    if(FinishView.isIsFail()){
                        System.out.println("failed");
                        app.setTitle("Finish Scene");
                        app.setWidth(Constant.OTHERSCENE_WIDTH);
                        app.setHeight(Constant.OTHERPANE_HEIGHT);
                        app.getStage().setWidth(Constant.OTHERSCENE_WIDTH);
                        app.getStage().setHeight(Constant.OTHERPANE_HEIGHT);
                        Framework.app.gotoView("Finish Scene");
                    }else{
                        System.out.println("Success");
                        app.setTitle("Finish Scene");
                        app.setWidth(Constant.OTHERSCENE_WIDTH);
                        app.setHeight(Constant.OTHERPANE_HEIGHT);
                        app.getStage().setWidth(Constant.OTHERSCENE_WIDTH);
                        app.getStage().setHeight(Constant.OTHERPANE_HEIGHT);
                        Framework.app.gotoView("Finish Scene");
                    }
                }
        );
    }
}

