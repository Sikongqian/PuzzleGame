package PuzzleGame.Spirit;

import PuzzleGame.Views.GameView;
import PuzzleGame.Util.Constant;
import PuzzleGame.Util.Coordinate;
import PuzzleGame.Util.PiecesOfA;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;


/**
 * This class records map information, such as images,coordinates, and current status of the map
 *@function provide a basis for judging functions such as car driving logic and piece adsorption. The map information needs to be reset during initialization. When the puzzle is placed on the map, the map information changes.
 */
public class Map extends Parent {
    /** Unique static map instance */
    public static Map map =new Map();

    private int price = 0;

    /* These are imageViews for gamepane */
    private ImageView mapImage;
    private ImageView beginningImage;
    private ImageView destinationImage;

    /* mapInfo[x][y][0] Is the type of the piece at xy. */
    /* mapInfo[x][y][1] Is the angle of the piece at xy. */
    public int[][][] mapInfo = new int[4][4][2];

    /* These are start info and end info. */
    private int beginningX;
    private int beginningY;
    private int destinationX;
    private int destinationY;
    private int startPoint;

    private int row;
    private int column;

    private Coordinate A = new Coordinate("A");
    private Coordinate B = new Coordinate("B");
    private Coordinate C = new Coordinate("C");
    private Coordinate D = new Coordinate("D");
    private Coordinate E = new Coordinate("E");
    private Coordinate F = new Coordinate("F");
    private Coordinate G = new Coordinate("G");
    private Coordinate H = new Coordinate("H");
    private Coordinate I = new Coordinate("I");
    private Coordinate J = new Coordinate("J");
    private Coordinate K = new Coordinate("K");
    private Coordinate L = new Coordinate("L");
    private Coordinate M = new Coordinate("M");
    private Coordinate N = new Coordinate("N");
    private Coordinate O = new Coordinate("O");
    private Coordinate P = new Coordinate("P");
    private Coordinate Array[] = {A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P};

    private Map() {
    }

    /**
     *  Initialize map image and map info
     */
    public void init() {
        price = 0;
        /* import the images */
        mapImage = new ImageView("Images/chessBoard.jpg");
        mapImage.setFitWidth(Constant.MAP_IMAGE_WIDTH);
        mapImage.setFitHeight(Constant.MAP_IMAGE_HEIGHT);
        mapImage.setLayoutY(0);
        mapImage.setLayoutX(250);

        beginningImage = new ImageView("Images/beginning.png");
        beginningImage.setFitWidth(136);
        beginningImage.setFitHeight(136);
        beginningImage.setLayoutX(Constant.CONTROL_A_POINTX + beginningX * 125 + 250 - 71);
        beginningImage.setLayoutY(Constant.CONTROL_A_POINTY + beginningY * 125 - 72);
        beginningImage.setRotate(180 + startPoint * 45);

        destinationImage = new ImageView("Images/destination.png");
        destinationImage.setFitWidth(136);
        destinationImage.setFitHeight(136);
        destinationImage.setLayoutX(Constant.CONTROL_A_POINTX + destinationX * 125 + 250 - 71);
        destinationImage.setLayoutY(Constant.CONTROL_A_POINTY + destinationY * 125 - 72);

        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 4;j++){
                for(int k = 0;k < 2;k++){
                    mapInfo[i][j][k] = -1;
                }
            }
        }
    }

    /**
     *  Initialize the start and end information in the map
     * @param beginningX
     * @param beginningY
     * @param destinationX
     * @param destinationY
     * @param startPoint
     */
    public void init(int beginningX,int beginningY, int destinationX, int destinationY, int startPoint){
        /* Initialize coordinates and start point */
        this.beginningX = beginningX;
        this.beginningY = beginningY;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.startPoint = startPoint;
    }

    /**
     *  This method is called when the mouse drag is over, and it is used to attach the piece to the position corresponding to the map, or return it to its original position. Update price on recordpane at the same time
     * @param piece
     * @param x transX of that piece
     * @param y transY of that piece
     */
    public void resetPiece(Piece piece,double x,double y) {

        getStandardCoordinate(piece.getPi());
        System.out.println(piece.getNameURL());
        Coordinate MoveToLocation = new Coordinate("null");
        double temp;
        double instanceX;
        double instanceY;
        double instance = 99999999;

        /* Find the map xy with the smallest distance from the current piece by traversing. */
        for (int i = 0; i < Array.length; i++) {
            if(x < Constant.PIECEDRAG_VAILDMINX) break;
            instanceX = Array[i].getX() -(x);
            instanceY = Array[i].getY() -(y);
            temp = Math.sqrt(Math.pow(instanceX, 2) + Math.pow(instanceY, 2));
            if (temp < instance) {
                instance = temp;
                MoveToLocation = Array[i];
            }
        }
        setRowAndColumn(MoveToLocation.getStandardName());



        /* If there is a piece in the current position or the piece is not dragged to the map, return to the original position */
        /* else put the piece in that position and generate a new piece at the original position*/
        if (row == -1 || ( mapInfo[column][row][0] != -1 || x < 200)||((row == beginningY && column == beginningX)||(row == destinationY && column == destinationX))) {
            switch (piece.getPieceType()) {
                case 1:
                    Piece p1 = new Piece1(Constant.PIECE1_INITX, Constant.PIECE1_INITY);
                    GameView.getGamePane().getChildren().add(p1);
                    GameView.getGamePane().getPieceList().add(p1);
                    GameView.getGamePane().getChildren().remove(piece);
                    GameView.getGamePane().getPieceList().remove(piece);
                    break;
                case 2:
                    Piece p2 = new Piece2(Constant.PIECE2_INITX, Constant.PIECE2_INITY);
                    GameView.getGamePane().getChildren().add(p2);
                    GameView.getGamePane().getPieceList().add(p2);
                    GameView.getGamePane().getChildren().remove(piece);
                    GameView.getGamePane().getPieceList().remove(piece);
                    break;
                case 3:
                    Piece p3 = new Piece3(Constant.PIECE3_INITX, Constant.PIECE3_INITY);
                    GameView.getGamePane().getChildren().add(p3);
                    GameView.getGamePane().getPieceList().add(p3);
                    GameView.getGamePane().getChildren().remove(piece);
                    GameView.getGamePane().getPieceList().remove(piece);
            }

            piece.setImageViewLocation(new Coordinate(0, 0));

        } else {
            mapInfo[column][row][0] = piece.getPieceType();
            mapInfo[column][row][1] = piece.getAngle();
            piece.setRow(row);
            piece.setColumn(column);
            piece.setImageViewLocation(MoveToLocation);

            switch (piece.getPieceType()) {
                case 1:
                    Piece p1 = new Piece1(Constant.PIECE1_INITX, Constant.PIECE1_INITY);
                    GameView.getGamePane().getChildren().add(p1);
                    GameView.getGamePane().getPieceList().add(p1);
                    price += 150;
                    break;
                case 2:
                    Piece p2 = new Piece2(Constant.PIECE2_INITX, Constant.PIECE2_INITY);
                    GameView.getGamePane().getChildren().add(p2);
                    GameView.getGamePane().getPieceList().add(p2);
                    price += 100;
                    break;
                case 3:
                    Piece p3 = new Piece3(Constant.PIECE3_INITX, Constant.PIECE3_INITY);
                    GameView.getGamePane().getChildren().add(p3);
                    GameView.getGamePane().getPieceList().add(p3);
                    price += 50;
            }
        }

        /* update record pane */
        price = 0;
        for(int i = 0;i < 4;i ++){
            for(int j = 0;j < 4;j++){
                switch (mapInfo[i][j][0]){
                    case 1:
                        price += 150;
                        break;
                    case 2:
                        price += 100;
                        break;
                    case 3:
                        price += 50;
                        break;
                }
            }
        }
        GameView.getRecordPane().getPiecePrice().setText(price + "");

        /* test code: Print out the information of the current map. */
        System.out.println("TYPE");
        for(int i = 0;i < 4;i ++){
            for(int j = 0 ; j < 4; j++){
                System.out.print(mapInfo[j][i][0]);
            }
            System.out.println();
        }
        System.out.println("ANGLE");
        for(int i = 0;i < 4;i ++){
            for(int j = 0 ; j < 4; j++){
                System.out.print(mapInfo[j][i][1]);
            }
            System.out.println();
        }
    }

    /**
     *  get rol and col number of each map block based on its standardName(null, A, B, C... ,P)
     * @param StandardName
     */
    public void setRowAndColumn(String StandardName) {
        switch (StandardName) {
            case "null":
                row = -1;
                column = -1;
                break;
            case "A":
                row = 0;
                column=0;
                break;
            case "B":
                row = 0;
                column=1;
                break;
            case "C":
                row = 0;
                column=2;
                break;
            case "D":
                row = 0;
                column=3;
                break;
            case "E":
                row = 1;
                column=0;
                break;
            case "F":
                row = 1;
                column=1;
                break;
            case "G":
                row = 1;
                column=2;
                break;
            case "H":
                row = 1;
                column=3;
                break;
            case "I":
                row = 2;
                column=0;
                break;
            case "J":
                row = 2;
                column=1;
                break;
            case "K":
                row = 2;
                column=2;
                break;
            case "L":
                row = 2;
                column=3;
                break;
            case "M":
                row = 3;
                column=0;
                break;
            case "N":
                row = 3;
                column=1;
                break;
            case "O":
                row = 3;
                column=2;
                break;
            case "P":
                row = 3;
                column=3;
                break;
        }
    }

    /**
     *  get the coordinates of each block on the map according to piecesofA class
     * @param piecesofa
     */
    public void getStandardCoordinate(PiecesOfA piecesofa) {
        A.setX(piecesofa.getCoordinateOfPieceA().getX());
        A.setY(piecesofa.getCoordinateOfPieceA().getY());
        B.setX(A.getX() + 126);
        B.setY(A.getY());
        C.setX(B.getX() + 126 );
        C.setY(B.getY());
        D.setX(A.getX() + 126 * 3);
        D.setY(A.getY());

        E.setX(A.getX() + 126 * 0);
        E.setY(A.getY() + 126);
        F.setX(A.getX() + 126 * 1);
        F.setY(A.getY() + 126);
        G.setX(A.getX() + 126 * 2);
        G.setY(A.getY() + 126);
        H.setX(A.getX() + 126 * 3);
        H.setY(A.getY() + 126);

        I.setX(A.getX() + 126 * 0);
        I.setY(A.getY() + 126 * 2);
        J.setX(A.getX() + 126 * 1);
        J.setY(A.getY() + 126 * 2);
        K.setX(A.getX() + 126 * 2);
        K.setY(A.getY() + 126 * 2);
        L.setX(A.getX() + 126 * 3);
        L.setY(A.getY() + 126 * 2);

        M.setX(A.getX() + 126 * 0);
        M.setY(A.getY() + 126 * 3);
        N.setX(A.getX() + 126 * 1);
        N.setY(A.getY() + 126 * 3);
        O.setX(A.getX() + 126 * 2);
        O.setY(A.getY() + 126 * 3);
        P.setX(A.getX() + 126 * 3);
        P.setY(A.getY() + 126 * 3);
    }

    public int getBeginningX() {
        return beginningX;
    }

    public int getBeginningY() {
        return beginningY;
    }

    public int getDestinationX() {
        return destinationX;
    }

    public int getDestinationY() {
        return destinationY;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public ImageView getMapImage() {
        return mapImage;
    }

    public ImageView getBeginningImage() {
        return beginningImage;
    }

    public ImageView getDestinationImage() {
        return destinationImage;
    }
}
