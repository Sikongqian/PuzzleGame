package PuzzleGame.Spirit;

import PuzzleGame.Util.*;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/** Piece block frame in a map in this Puzzle Game.
 * Piece block frame in a map in this Puzzle Game.
 * @function This class is the parent class for all the pieces appearing in the game, players can move them to the right places in map using mouse, rotate them to some angle. There are three kinds of pieces in total.
 * @author Group 10- Heather Xiong - 20722114 24/5/2022
 */

public abstract class Piece extends Parent {
    private boolean flag = false;

    //location
    final Position pos = new Position();
    private double x;
    private double y;
    private int row = -1;
    private int column = -1;

    private Coordinate coordinate = new Coordinate();

    /** Because different pieces have different exact coordinate
        we construct pi which symbolizes the coordinate for this piece
        at current map block*/
    private PiecesOfA pi = new PiecesOfA();

    private boolean isTouched;
    private int pieceType;//1,2,3
    private int angle;// 0,45,90,135,180,225,270,315
    private int rotateCount;
    private String NameURL;

    private ImageView imageView;;

    /* record the location before mouse dragging */
    private double startDragX;
    private double startDragY;
    private Point2D dragAnchor;

    /* mouse dragging distance */
    private double transX;
    private double transY;

    /**
     *  Piece constructor include five listeners: 1.click 2.drag 3.up 4.stay 5.leave
     * @param image Image
     * @param x double
     * @param y double
     * @param pieceType int
     */
    public Piece(Image image, double x, double y, int pieceType) {
        this.x = x;
        this.y = y;


        //initialization
        coordinate.setX(x);
        coordinate.setY(y);
        imageView = new ImageView(image);
        imageView.setFitHeight(125);
        imageView.setFitWidth(125);
        this.pieceType = pieceType;
        setNameURL(pieceType, angle);
        setPi();
        imageView.setFitWidth(pi.getPiecewidth());
        imageView.setFitHeight(pi.getPieceHeight());

        this.getChildren().add(imageView);
        this.setLayoutX(x);
        this.setLayoutY(y);

        /**
         * 1.Click Mouse every time the piece is clicked, programme will record the removed location and initialize location information in map
         */
        this.setOnMousePressed(e -> {
            toFront();
            //record x abd y
            pos.x1 = e.getX();
            pos.y1 = e.getY();
            startDragX = this.getTranslateX();
            startDragY = this.getTranslateY();
            dragAnchor = new Point2D(e.getSceneX(), e.getSceneY());
            if(row != -1){
                Map.map.mapInfo[column][row][0] = -1;
                Map.map.mapInfo[column][row][1] = -1;
            }
        });

        /**
         * 2.Drag Mouse
         * nwX and newY record the current place of piece
         * minX and minY are to avoid accidentally mouse touch
         * maxX and maxY are the limitation of dragging a piece, ensuring the piece is still at the GamePane
         * */
        this.setOnMouseDragged(e -> {
            //1.Calculates the position of the new movement
            double newX = startDragX + e.getSceneX() - dragAnchor.getX() ;
            double newY = startDragY + e.getSceneY() - dragAnchor.getY();
            //2.The minimum position to move
            double minX = Constant.PIECEDRAG_MINX;//PIECEDRAG_MINX
            double miny = Constant.PIECEDRAG_MINY - this.getLayoutY();//PIECEDRAG_MINY
            //3.The maximum position to move
            double maxX = Constant.PIECEDRAG_MAXX;//PIECEDRAG_MAXX
            double maxY = Constant.PIECEDRAG_MAXY  + this.getLayoutY();//PIECEDRAG_MAXY
            if ((newX > minX) && newX < maxX && newY > miny && newY < maxY){
                this.setTranslateX(newX);
                this.setTranslateY(newY);
            }

            transX = this.getTranslateX() + this.getLayoutX();
            transY = this.getTranslateY() + this.getLayoutY();
        });


        /**
         *3.Up Mouse
         * Here we provide a method in map to absorb the piece in the particular map block
         */
        this.setOnMouseReleased(e -> {
            row = -1;
            column = -1;
            Map.map.resetPiece(this, transX, transY);
        });

        /**4.Mouse Stay*/
        this.setOnMouseEntered(e -> {

            isTouched = true;
        });

        /**5.Mouse move away*/
        this.setOnMouseExited(e -> {
            isTouched = false;
        });

    }

    /**
     *  since every piece has several different figures due to angle and type we classified them as followed nameURL.
     * @param type int (the type of piece)
     * @param angle int (the angle the piece has rotated)
     */
    public void setNameURL(int type, int angle) {
        if (type == 1) {
            if (angle == 45 || angle == 225) {
                this.NameURL = "piece1_45";

            } else if (angle == 135 || angle == 315) {
                this.NameURL = "piece1_135";

            } else if (angle == 0 || angle == 180) {
                this.NameURL = "piece1_0";

            } else if (angle == 90 || angle == 270) {
                this.NameURL = "piece1_90";

            }
        } else if (type == 2) {
            if (angle == 0 || angle == 90 || angle == 180 || angle == 270) {
                this.NameURL = "piece2_0";

            } else if (angle == 45 || angle == 135 || angle == 225 || angle == 315) {
                this.NameURL = "piece2_45";

            }
        } else if (type == 3) {
            if (angle == 0) {
                this.NameURL = "piece3_0";

            } else if (angle == 45) {
                this.NameURL = "piece3_45";

            } else if (angle == 90) {
                this.NameURL = "piece3_90";

            } else if (angle == 135) {
                this.NameURL = "piece3_135";

            } else if (angle == 180) {
                this.NameURL = "piece3_180";

            } else if (angle == 225) {
                this.NameURL = "piece3_225";

            } else if (angle == 270) {
                this.NameURL = "piece3_270";

            } else if (angle == 315) {
                this.NameURL = "piece3_315";

            }
        }
    }

    /**
     *  pi is the exact coordinate for different piece under different nameURL
     */
    public void setPi() {
        switch (NameURL) {
            case "start":
                pi = pi.beginning;
                break;
            case "piece1_45":
                pi = pi.piece1_45;
                break;
            case "piece1_0":
                pi = pi.piece1_0;
                break;
            case "piece1_135":
                pi = pi.piece1_135;
                break;
            case "piece1_90":
                pi = pi.piece1_90;
                break;
            case "piece3_0":
                pi = pi.piece3_0;
                break;
            case "piece3_225":
                pi = pi.piece3_225;
                break;
            case "piece3_180":
                pi = pi.piece3_180;
                break;
            case "piece3_45":
                pi = pi.piece3_45;
                break;
            case "piece3_90":
                pi = pi.piece3_90;
                break;
            case "piece3_270":
                pi = pi.piece3_270;
                break;
            case "piece3_315":
                pi = pi.piece3_315;
                break;
            case "piece3_135":
                pi = pi.piece3_135;
                break;
            case "end":
                pi = pi.destination;
                break;
            case "piece2_0":
                pi = pi.piece2_0;
                break;
            case "piece2_45":
                pi = pi.piece2_90;
                break;
        }
    }

    /**
     *  every time we rotate a piece, we will call this function.
     * @param angle int
     * @param imageView ImageView
     */
    public void setRotate(int angle, ImageView imageView) {
        // instantiating the Rotate class.
        Rotate rotate = new Rotate();
        rotate.setAngle(angle);
        rotate.setPivotX(imageView.getFitWidth()/2);
        rotate.setPivotY(imageView.getFitHeight()/2);
        imageView.getTransforms().add(rotate);
    }

    /**
     *  rotate a piece, change the angle and rotate time.
     */
    public void rotate() {
        switch (rotateCount % 8) {
            case (0):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(45);
                break;
            case (1):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(90);
                break;
            case (2):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(135);
                break;
            case (3):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(180);
                break;
            case (4):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(225);
                break;
            case (5):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(270);
                break;
            case (6):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(315);
                break;
            case (7):
                setRotate(45,imageView);
                rotateCount++;
                setAngle(0);
            default:
                break;
        }
        setNameURL(pieceType, angle);
        setPi();
    }

    /**
     *  make the piece removable.
     */
    public void setActive() {
        this.setDisable(false);
        this.setEffect(new DropShadow());
        this.toFront();
    }

    /**
     *  make piece irremovable
     */
    public void setInactive() {
        this.setDisable(true);
        this.setEffect(null);
    }

    private static class Position {
        double x1;
        double y1;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isTouched() {
        return isTouched;
    }

    public void setTouched(boolean touched) {
        isTouched = touched;
    }

    public void setImageView(Image image) {
        imageView = new ImageView(image);
        this.getChildren().add(imageView);
    }

    public void setImageViewLocation(Coordinate coordinate) {
        this.setTranslateX(coordinate.getX() - this.getLayoutX());
        this.setTranslateY(coordinate.getY() - this.getLayoutY());
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getNameURL() {
        return NameURL;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getAngle() {
        return angle;
    }

    public ImageView getImageView() {
        //piece出现大小
        imageView.setFitWidth(pi.getPiecewidth());
        imageView.setFitHeight(pi.getPieceHeight());

        return imageView;
    }

    public PiecesOfA getPi() {
        return pi;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getPieceType() {
        return pieceType;
    }
}

