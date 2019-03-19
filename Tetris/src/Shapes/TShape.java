package Shapes;

import TetrisGame.TetrisGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class TShape {

    private TetrisGame game;
    private ArrayList<Piece> tShape;
    private Color color;
    private final int width;
    private final int height;
    private final int blockSize;

    private int rotation;
    private boolean allowedToRotate;

    private boolean allowedToMove;
    private MoveShape moveShape;

    public TShape(TetrisGame tetrisGame) {
        this.tShape = new ArrayList<Piece>();
        this.color = Color.MAGENTA;
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

        this.tShape.add(new Piece(221, 81, color)); //3
        this.tShape.add(new Piece(186, 46, color)); //2 
        this.tShape.add(new Piece(151, 81, color)); //1
        this.tShape.add(new Piece(186, 81, color)); //0
        Collections.reverse(tShape);
    }

    public void rotateShapeClockwise() {
        Piece p0 = this.tShape.get(0);
        Piece p1 = this.tShape.get(1);
        Piece p2 = this.tShape.get(2);
        Piece p3 = this.tShape.get(3);

        if (this.allowedToRotate == false) {
            return;
        }

        if (checkForClockwiseRotation() == false) {
            System.out.println("Out of Bounds");
            return;
        }

        if (this.rotation == 1) {
            p1.setX(p1.getX() + blockSize);
            p1.setY(p1.getY() - blockSize);

            p2.setX(p2.getX() + blockSize);
            p2.setY(p2.getY() + blockSize);

            p3.setX(p3.getX() - blockSize);
            p3.setY(p3.getY() + blockSize);

        } else if (this.rotation == 2) {
            p1.setX(p1.getX() + blockSize);
            p1.setY(p1.getY() + blockSize);

            p2.setX(p2.getX() - blockSize);
            p2.setY(p2.getY() + blockSize);

            p3.setX(p3.getX() - blockSize);
            p3.setY(p3.getY() - blockSize);

        } else if (this.rotation == 3) {
            p1.setX(p1.getX() - blockSize);
            p1.setY(p1.getY() + blockSize);

            p2.setX(p2.getX() - blockSize);
            p2.setY(p2.getY() - blockSize);

            p3.setX(p3.getX() + blockSize);
            p3.setY(p3.getY() - blockSize);

        } else if (this.rotation == 4) {
            p1.setX(p1.getX() - blockSize);
            p1.setY(p1.getY() - blockSize);

            p2.setX(p2.getX() + blockSize);
            p2.setY(p2.getY() - blockSize);

            p3.setX(p3.getX() + blockSize);
            p3.setY(p3.getY() + blockSize);

        }

        this.rotation++;

        if (this.rotation > 4) {
            this.rotation = 1;
        }

        System.out.println(this.rotation);
    }

    public boolean checkForClockwiseRotation() {
        Piece p0 = this.tShape.get(0);
        Piece p1 = this.tShape.get(1);
        Piece p2 = this.tShape.get(2);
        Piece p3 = this.tShape.get(3);

        if (this.rotation == 1) {
            if (p1.getX() + blockSize > 361
                    || p2.getX() + blockSize > 361
                    || p3.getX() - blockSize > 361) {
                System.out.println("p1 first");
                return false;
            }

            if (p1.getY() - blockSize > 746
                    || p2.getY() + blockSize > 746
                    || p3.getY() + blockSize > 746) {
                System.out.println("p1 first");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p3.getX() - blockSize && p.getY() == p3.getY() + blockSize) {
                    System.out.println("p4 first");
                    return false;
                }
            }

        } else if (this.rotation == 2) {
            if (p1.getX() + blockSize > 361
                    || p2.getX() - blockSize > 361
                    || p3.getX() - blockSize > 361) {
                System.out.println("p1 first");
                return false;
            }

            if (p1.getY() + blockSize > 746
                    || p2.getY() + blockSize > 746
                    || p3.getY() - blockSize > 746) {
                System.out.println("p1 first");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p3.getX() - blockSize && p.getY() == p3.getY() - blockSize) {
                    System.out.println("p5 second");
                    return false;
                }
            }

            if (p3.getX() - blockSize < 46) {
                return false;
            }

        } else if (this.rotation == 3) {

            if (p1.getX() - blockSize > 361
                    || p2.getX() - blockSize > 361
                    || p3.getX() + blockSize > 361) {
                System.out.println("p1 first");
                return false;
            }

            if (p1.getY() + blockSize > 746
                    || p2.getY() - blockSize > 746
                    || p3.getY() - blockSize > 746) {
                System.out.println("p1 first");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p3.getX() + blockSize && p.getY() == p3.getY() - blockSize) {
                    System.out.println("p4 third");
                    return false;
                }
            }

        } else if (this.rotation == 4) {
            if (p1.getX() - blockSize > 361
                    || p2.getX() + blockSize > 361
                    || p3.getX() + blockSize > 361) {
                System.out.println("p1 first");
                return false;
            }

            if (p1.getY() - blockSize > 746
                    || p2.getY() - blockSize > 746
                    || p3.getY() + blockSize > 746) {
                System.out.println("p1 first");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p3.getX() + blockSize && p.getY() == p3.getY() + blockSize) {
                    System.out.println("p4 fourth");
                    return false;
                }
            }
        }

        return true;
    }

    public ArrayList<Piece> getShape() {
        return this.tShape;
    }

    public Color getColor() {
        return color;
    }

    public void moveDown() {

        for (Piece p : this.tShape) {
            if (checkForContactDown(p)) {
                return;
            }
        }

        for (Piece p : this.tShape) {
            p.setY(p.getY() + blockSize);
        }
    }

    public boolean checkForContactDown(Piece p) {

        for (Piece arrayP : this.game.shapesToBeDrawn()) {

            if (this.tShape.contains(arrayP)) {
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

        this.moveShape.moveLeft(tShape);
    }

    public void moveRight() {
        if (this.allowedToMove == false) {
            return;
        }

        this.moveShape.moveRight(tShape);
    }

    public void allowedToMove() {
        this.allowedToMove = false;
    }
}
