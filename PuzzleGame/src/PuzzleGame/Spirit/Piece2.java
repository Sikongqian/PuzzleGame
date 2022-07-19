package PuzzleGame.Spirit;

import javafx.scene.image.Image;

/**
 * Piece 2.
 */
public class Piece2 extends Piece{
    public Piece2(double x, double y) {
        super(new Image("images/pieceType2.png"), x, y, 2);
        super.setAngle(0);
    }
}
