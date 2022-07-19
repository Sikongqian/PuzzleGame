package PuzzleGame.Spirit;

import javafx.scene.image.Image;

/**
 * Piece 1.
 */
public class Piece1 extends Piece{

    public Piece1(double x, double y) {
        super(new Image("images/pieceType1.png"), x, y, 1);
        super.setAngle(0);
    }
}
