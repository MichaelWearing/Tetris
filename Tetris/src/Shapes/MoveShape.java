package Shapes;

import TetrisGame.TetrisGame;
import java.util.ArrayList;

public class MoveShape {

    private final int blockSize;
    private final int width;
    private final int height;

    private final TetrisGame game;

    public MoveShape(TetrisGame game) {
        this.game = game;
        this.blockSize = game.getBlockSize();
        this.width = game.getWidth();
        this.height = game.getHeight();
    }

    public void moveDown(ArrayList<Piece> shape) { //WE CANT MOVE THIS AS WE NEED THE BOOLEANS FOR MOVEMENT AND ROTATION TO BE CHANGED WHEN NEEDED
        for (Piece p : shape) {
            if (checkForContactDown(p, shape)) {
                return;
            }
        }

        for (Piece p : shape) {
            p.setY(p.getY() + blockSize);
        }
    }

    public boolean checkForContactDown(Piece p, ArrayList<Piece> shape) {
        for (Piece arrayP : game.shapesToBeDrawn()) {

            if (shape.contains(arrayP)) {
                break;
            }

            if (p.getY() == arrayP.getY() - blockSize) {
                if (p.getX() == arrayP.getX()) {
                    System.out.println("Hit another Piece");
                    game.deactivateFallingShape();
                    game.checkForRowsToDelete();
                    return true;
                }

            }

        }

        if (p.getY() >= height - blockSize - blockSize) {
            System.out.println("Hit the bottom wall");
            game.deactivateFallingShape();
            game.checkForRowsToDelete();
            return true;
        }

        return false;
    }

    public void moveLeft(ArrayList<Piece> shape) {

        for (Piece p : shape) {
            if (p.getX() - blockSize == 11) {
                System.out.println("Hitting a wall to the Left");
                return;
            }

            if (checkForContactLeft(p, shape)) {
                return;
            }
        }

        for (Piece p : shape) {
            p.setX(p.getX() - blockSize);
        }

        // moveDown();
    }

    public boolean checkForContactLeft(Piece p, ArrayList<Piece> shape) {
        for (Piece arrayP : game.shapesToBeDrawn()) {

            if (shape.contains(arrayP)) {
                break;
            }

            if (p.getX() - blockSize == arrayP.getX()) {
                if (p.getY() == arrayP.getY()) {
                    System.out.println("Hit a Piece left");
                    return true;
                }
            }
        }

        return false;
    }

    public void moveRight(ArrayList<Piece> shape) {

        for (Piece p : shape) {
            if (p.getX() + 35 == width - blockSize + 1) {
                System.out.println("Hitting a wall to the Right");
                return;
            }

            if (checkForContactRight(p, shape)) {
                return;
            }
        }

        for (Piece p : shape) {
            p.setX(p.getX() + blockSize);
        }

        // moveDown();
    }

    public boolean checkForContactRight(Piece p, ArrayList<Piece> shape) {
        for (Piece arrayP : game.shapesToBeDrawn()) {

            if (shape.contains(arrayP)) {
                break;
            }

            if (p.getX() + blockSize == arrayP.getX()) {
                if (p.getY() == arrayP.getY()) {
                    System.out.println("Hit a Piece right");
                    return true;
                }
            }

        }

        return false;
    }
}
