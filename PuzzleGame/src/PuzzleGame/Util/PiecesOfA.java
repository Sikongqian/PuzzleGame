package PuzzleGame.Util;

/**
 * This class records pieces basic information, like coordinate in (1,1), the appearing width and height
 *@function provides basic pieces information
 */
public class PiecesOfA extends Coordinate{
    private Coordinate coordinateOfPieceA=new Coordinate();
    private double Piecewidth;
    private double PieceHeight;
    private static Coordinate coordinate=new Coordinate();

    public static PiecesOfA beginning =new PiecesOfA(coordinate.getBeginning(),131,132);
    public static PiecesOfA piece1_45 =new PiecesOfA(coordinate.getPiece1_135(),123,128);
    public static PiecesOfA piece1_0 =new PiecesOfA(coordinate.getPiece1_0(),126,128);
    public static PiecesOfA piece1_135 =new PiecesOfA(coordinate.getPiece1_45(),125,128);
    public static PiecesOfA piece1_90 =new PiecesOfA(coordinate.getPiece1_90(),126,132);//
    public static PiecesOfA piece3_0 =new PiecesOfA(coordinate.getPiece3_0(),112,114);
    public static PiecesOfA piece3_225 =new PiecesOfA(coordinate.getPiece3_225(),112,114);
    public static PiecesOfA piece3_180 =new PiecesOfA(coordinate.getPiece3_180(),108,114);
    public static PiecesOfA piece3_45 =new PiecesOfA(coordinate.getPiece3_45(),108,114);
    public static PiecesOfA piece3_90 =new PiecesOfA(coordinate.getPiece3_90(),100,117);
    public static PiecesOfA piece3_270 =new PiecesOfA(coordinate.getPiece3_270(),100,116);
    public static PiecesOfA piece3_315 =new PiecesOfA(coordinate.getPiece3_315(),96,116);//
    public static PiecesOfA piece3_135 =new PiecesOfA(coordinate.getPiece3_135(),99,120);//
    public static PiecesOfA destination =new PiecesOfA(coordinate.getDestination(),140,140);
    public static PiecesOfA piece2_0 =new PiecesOfA(coordinate.getPiece2_0(),127,127);
    public static PiecesOfA piece2_90 =new PiecesOfA(coordinate.getPiece2_90(),130,126);

    public PiecesOfA() {
    }

    /**
     * To build the information of a piece
     * @param coordinate The coordinate of the piece
     * @param piecewidth The width of the piece
     * @param pieceHeight The height of the piece
     */
    public PiecesOfA(Coordinate coordinate, double piecewidth, double pieceHeight) {
        this.coordinateOfPieceA=coordinate;
        Piecewidth = piecewidth;
        PieceHeight = pieceHeight;
    }

    public Coordinate getCoordinateOfPieceA() {
        return coordinateOfPieceA;
    }

    public void setCoordinateOfPieceA(Coordinate coordinateOfPieceA) {
        this.coordinateOfPieceA = coordinateOfPieceA;
    }

    public double getPiecewidth() {
        return Piecewidth;
    }

    public void setPieceWidth(double piecewidth) {
        Piecewidth = piecewidth;
    }

    public double getPieceHeight() {
        return PieceHeight;
    }

    public void setPieceHeight(double pieceHeight) {
        PieceHeight = pieceHeight;
    }
}
