package Shapes;

import TetrisGame.TetrisGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class IShape {

    private TetrisGame game;
    private ArrayList<Piece> iShape;
    private Color color;
    private final int width;
    private final int height;
    private final int blockSize;

    private int rotation;
    private boolean allowedToRotate;

    private boolean allowedToMove;

    private MoveShape moveShape;
    private ArrayList<Piece> toSendToMoveShape;

    public IShape(TetrisGame tetrisGame) {
        this.iShape = new ArrayList<Piece>();
        this.color = Color.CYAN;
        this.game = tetrisGame;
        this.blockSize = tetrisGame.getBlockSize();
        this.width = tetrisGame.getWidth();
        this.height = tetrisGame.getHeight();

        this.toSendToMoveShape = new ArrayList<>();
        this.moveShape = new MoveShape(this.game);
    }

    public void createShape() {
        this.iShape.add(new Piece(186, 46, color));
        this.iShape.add(new Piece(186, 81, color));
        this.iShape.add(new Piece(186, 116, color));
        this.iShape.add(new Piece(186, 151, color));

        Collections.reverse(iShape);

        this.rotation = 1;
        this.allowedToRotate = true;
        this.allowedToMove = true;

        this.toSendToMoveShape = this.getShape();
    }

    public void rotateShapeClockwise() {
        Piece p0 = this.iShape.get(0);
        Piece p1 = this.iShape.get(1);
        Piece p2 = this.iShape.get(2);
        Piece p3 = this.iShape.get(3);

        if (this.allowedToRotate == false) {
            return;
        }

        if (checkForClockwiseRotation() == false) {
            System.out.println("Out of Bounds");
            return;
        }

        if (this.rotation == 1 || this.rotation == 3) {
            p0.setX(p0.getX() - blockSize);
            p0.setY(p0.getY() - blockSize);

            p2.setX(p2.getX() + blockSize);
            p2.setY(p2.getY() + blockSize);

            p3.setX(p3.getX() + (blockSize * 2));
            p3.setY(p3.getY() + (blockSize * 2));

        } else if (this.rotation == 2 || this.rotation == 4) {

            p0.setX(p0.getX() + blockSize);
            p0.setY(p0.getY() + blockSize);

            p2.setX(p2.getX() - blockSize);
            p2.setY(p2.getY() - blockSize);

            p3.setX(p3.getX() - (blockSize * 2));
            p3.setY(p3.getY() - (blockSize * 2));

        }

        this.rotation++;

        System.out.println(this.rotation);

        if (this.rotation > 4 || this.rotation < 0) {
            this.rotation = 0;
        }

        System.out.println(this.rotation);
    }

    public boolean checkForClockwiseRotation() {
        Piece p0 = this.iShape.get(0);
        Piece p1 = this.iShape.get(1);
        Piece p2 = this.iShape.get(2);
        Piece p3 = this.iShape.get(3);

        if (this.rotation == 1 || this.rotation == 3) {
            if (p0.getX() - blockSize > 361
                    || p2.getX() + blockSize > 361
                    || p3.getX() + (blockSize * 2) > 361) {
                return false;
            }

            if (p0.getY() - blockSize > 746
                    || p2.getY() + blockSize > 946
                    || p3.getY() + (blockSize * 2) > 746) {
                return false;
            }

            for (Piece p : this.game.getArrayOfShapesOnBoard()) {
                if (p.getX() == p0.getX() - blockSize && p.getY() == p0.getY() - blockSize) {
                    return false;
                }
                if (p.getX() == p2.getX() + blockSize && p.getY() == p2.getY() + blockSize) {
                    return false;
                }
                if (p.getX() == p3.getX() + (blockSize * 2) && p.getY() == p3.getY() + (blockSize * 2)) {
                    return false;
                }
            }

            if (p0.getX() - blockSize < 46) {
                return false;
            }
        } else if (this.rotation == 2 || this.rotation == 4) {
            if (p0.getX() + blockSize > 361
                    || p2.getX() - blockSize > 361
                    || p3.getX() - (blockSize * 2) > 361) {
                return false;
            }

            if (p0.getY() + blockSize > 746
                    || p2.getY() - blockSize > 746
                    || p3.getY() - (blockSize * 2) > 746) {
                return false;
            }

            for (Piece p : this.game.getArrayOfShapesOnBoard()) {
                if (p.getX() == p0.getX() + blockSize && p.getY() == p0.getY() + blockSize) {
                    return false;
                }
                if (p.getX() == p2.getX() - blockSize && p.getY() == p2.getY() - blockSize) {
                    return false;
                }
                if (p.getX() == p3.getX() - (blockSize * 2) && p.getY() == p3.getY() - (blockSize * 2)) {
                    return false;
                }
            }

        }

        return true;
    }

    public ArrayList<Piece> getShape() {
        return this.iShape;
    }

    public Color getColor() {
        return color;
    }

    public void moveDown() {
      //    this.moveShape.moveDown(iShape);   // MAYBE FIND A WAY FOR CHECKING TO WORK AS WE WANT

        for (Piece p : this.iShape) {
            if (checkForContactDown(p)) {
                return;
            }
        }

        for (Piece p : this.iShape) {
            p.setY(p.getY() + blockSize);
        }
    }

    public boolean checkForContactDown(Piece p) {
        // return this.moveShape.checkForContactDown(p);  // CANT WORK BECUASE THE RETURNED BOOLEAN VALUES

        for (Piece arrayP : this.game.shapesToBeDrawn()) {

            if (this.iShape.contains(arrayP)) {
                break;
            }

            if (p.getY() == arrayP.getY() - blockSize) {
                if (p.getX() == arrayP.getX()) {
                    System.out.println("Hit another Piece");
                    this.game.deactivateFallingShape();
                    this.game.checkForRowsToDelete();
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

         this.moveShape.moveLeft(iShape);
    }

    public void moveRight() {
        if (this.allowedToMove == false) {
            return;
        }

         this.moveShape.moveRight(iShape);
    }

    public int getRotation() {
        return rotation;
    }

    public void allowedToMove() {
        this.allowedToMove = false;
    }
}
