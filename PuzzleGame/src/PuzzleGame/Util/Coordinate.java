package PuzzleGame.Util;

/**
 * This class records pieces coordinates on (0,0)
 *@function provides basic coordinates to help pieces find the right location in the next step
 */
public class Coordinate {

    private double x;
    private double y;
    private String StandardName;

    private static Coordinate beginning = new Coordinate(-7+250, -1);
    private static Coordinate piece1_0 = new Coordinate(-2+250, 0);
    private static Coordinate piece1_45 = new Coordinate(-1+250, 0);
    private static Coordinate piece1_90 =new Coordinate(-5+250,-2);
    private static Coordinate piece1_135 = new Coordinate(-1+250, -4);

    private static Coordinate piece2_0 = new Coordinate(-5+250, -3);
    private static Coordinate piece2_90 = new Coordinate(-5+250, -5);

    private static Coordinate piece3_0 = new Coordinate(28+250, 0);
    private static Coordinate piece3_45 = new Coordinate(25+250, 17);
    private static Coordinate piece3_90 =new Coordinate(12+250,31);
    private static Coordinate piece3_135 =new Coordinate(-4+250,32);
    private static Coordinate piece3_180 = new Coordinate(-9+250, 13);
    private static Coordinate piece3_225 = new Coordinate(-6+250, -1);
    private static Coordinate piece3_270 =new Coordinate(8+250,-18);
    private static Coordinate piece3_315 =new Coordinate(25+250,-18);


    private static Coordinate destination = new Coordinate(-10+250, -15);

    /** The array saves each pieces' 8 different out coordinates of the car */
    private static Coordinate [] inOutPoint = {
            new Coordinate(Constant.INOUTPOINT_0_AX,Constant.INOUTPOINT_0_AY),
            new Coordinate(Constant.INOUTPOINT_1_AX,Constant.INOUTPOINT_1_AY),
            new Coordinate(Constant.INOUTPOINT_2_AX,Constant.INOUTPOINT_2_AY),
            new Coordinate(Constant.INOUTPOINT_3_AX,Constant.INOUTPOINT_3_AY),
            new Coordinate(Constant.INOUTPOINT_4_AX,Constant.INOUTPOINT_4_AY),
            new Coordinate(Constant.INOUTPOINT_5_AX,Constant.INOUTPOINT_5_AY),
            new Coordinate(Constant.INOUTPOINT_6_AX,Constant.INOUTPOINT_6_AY),
            new Coordinate(Constant.INOUTPOINT_7_AX,Constant.INOUTPOINT_7_AY)
    };
    public Coordinate(){
    }

    /**
     *  To build the coordinate of the piece
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Coordinate(String standardName) {
        StandardName = standardName;
    }

    public String getStandardName() {
        return StandardName;
    }

    public void setStandardName(String standardName) {
        StandardName = standardName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Return the coordinate of the car move out the piece
     *@param x int The x coordinate of inOutPoint[n]
     *@param y int The y coordinate of inOutPoint[n]
     *@param n int The piece index in the inOutPoint[n]
     *@return inOutPointXYN Coordinate The coordinate of car move out this piece
     */
    public static Coordinate getInOutPoint(int x, int y, int n){
        Coordinate inOutPointXYN = new Coordinate(inOutPoint[n].getX() + 125*x +250,inOutPoint[n].getY()+125*y);
        return inOutPointXYN;
    }

    public static Coordinate getBeginning() {
        return beginning;
    }

    public static Coordinate getDestination() {
        return destination;
    }

    public static Coordinate getPiece1_0() {
        return piece1_0;
    }

    public static Coordinate getPiece1_90() {
        return piece1_90;
    }


    public static Coordinate getPiece3_0() {
        return piece3_0;
    }

    public static Coordinate getPiece3_45() {
        return piece3_45;
    }

    public static Coordinate getPiece3_225() {
        return piece3_225;
    }

    public static Coordinate getPiece3_180() {
        return piece3_180;
    }

    public static Coordinate getPiece3_90() {
        return piece3_90;
    }

    public static Coordinate getPiece3_270() {
        return piece3_270;
    }

    public static Coordinate getPiece3_315() {
        return piece3_315;
    }

    public static Coordinate getPiece3_135() {
        return piece3_135;
    }

    public static Coordinate getPiece2_0() {
        return piece2_0;
    }

    public static Coordinate getPiece1_45() {
        return piece1_45;
    }

    public static Coordinate getPiece1_135() {
        return piece1_135;
    }

    public static Coordinate getPiece2_90() {
        return piece2_90;
    }

    public static double getControlX(int x) {
        return 125 * x + Constant.CONTROL_A_POINTX + 250;
    }

    public static double getControlY(int y) {
        return 125 * y + Constant.CONTROL_A_POINTY;
    }
}

