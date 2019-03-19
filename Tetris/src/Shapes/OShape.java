package Shapes;

import TetrisGame.TetrisGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class OShape {

    private final TetrisGame game;
    private ArrayList<Piece> OShape;
    private final Color color;
    private final int width;
    private final int height;
    private final int blockSize;

    private int rotation;
    private boolean allowedToRotate;

    private boolean allowedToMove;
    private MoveShape moveShape;
    
    public OShape(TetrisGame tetrisGame) {
        this.OShape = new ArrayList<Piece>();
        this.color = Color.YELLOW;
        this.game = tetrisGame;
        this.blockSize = tetrisGame.getBlockSize();
        this.width = tetrisGame.getWidth();
        this.height = tetrisGame.getHeight();
        this.moveShape = new MoveShape(game);
    }

    public void createShape() {
        this.rotation = 1;

        this.allowedToRotate = true;
        this.allowedToMove = true;

        this.OShape.add(new Piece(186, 46, color));
        this.OShape.add(new Piece(186, 81, color));
        this.OShape.add(new Piece(221, 46, color));
        this.OShape.add(new Piece(221, 81, color));
        Collections.reverse(OShape);
    }

    public void rotateShapeClockwise() {
        return;
    }

    public boolean checkForClockwiseRotation() {
        return true;
    }

    public ArrayList<Piece> getShape() {
        return this.OShape;
    }

    public Color getColor() {
        return color;
    }

    public void moveDown() {

        for (Piece p : this.OShape) {
            if (checkForContactDown(p)) {
                return;
            }
        }

        for (Piece p : this.OShape) {
                p.setY(p.getY() + blockSize);
        }
    }

    public boolean checkForContactDown(Piece p) {

        for (Piece arrayP : this.game.shapesToBeDrawn()) {

            if (this.OShape.contains(arrayP)) {
                break;
            }

            if (p.getY() == arrayP.getY() - blockSize) {
                if (p.getX() == arrayP.getX()) {
                    System.out.println("Hit another Piece");
                    this.game.deactivateFallingShape();
                   this.game.checkForRowsToDelete();
                    this.game.updateGame();
                    this.allowedToRotate = false;
            this.allowedToMove = false;
                    return true;
                }
            }
        }

        if (p.getY() >= height - blockSize - blockSize) {
            System.out.println("Hit the bottom wall");
            this.game.deactivateFallingShape();
                   this.game.checkForRowsToDelete();
            this.allowedToRotate = false;
            this.allowedToMove = false;
            return true;
        }

        return false;
    }

    public void moveLeft() {
        if (this.allowedToMove == false) {
            return;
        }
        
        this.moveShape.moveLeft(OShape);
    }

    public void moveRight() {
        if (this.allowedToMove == false) {
            return;
        }
        
        this.moveShape.moveRight(OShape);
    }

    public void allowedToMove() {
        this.allowedToMove = false;
    }
}
