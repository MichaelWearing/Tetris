package Shapes;

import TetrisGame.TetrisGame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class ZShape {

    private TetrisGame game;
    private ArrayList<Piece> zShape;
    private Color color;
    private final int width;
    private final int height;
    private final int blockSize;

    private int rotation;
    private boolean allowedToRotate;

    private boolean allowedToMove;
    private MoveShape moveShape;

    public ZShape(TetrisGame tetrisGame) {
        this.zShape = new ArrayList<Piece>();
        this.color = Color.RED;
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

        this.zShape.add(new Piece(151, 46, color)); //3
        this.zShape.add(new Piece(186, 46, color)); //2 
        this.zShape.add(new Piece(186, 81, color)); //1
        this.zShape.add(new Piece(221, 81, color)); //0
        Collections.reverse(zShape);
    }

    public void rotateShapeClockwise() {
        Piece p0 = this.zShape.get(0);
        Piece p1 = this.zShape.get(1);
        Piece p2 = this.zShape.get(2);
        Piece p3 = this.zShape.get(3);

        if (this.allowedToRotate == false) {
            return;
        }

        if (checkForClockwiseRotation() == false) {
            System.out.println("Out of Bounds");
            return;
        }

        if (this.rotation == 1) {
            p0.setX(p0.getX() - blockSize);
            p0.setY(p0.getY() + blockSize);

            p2.setX(p2.getX() + blockSize);
            p2.setY(p2.getY() + blockSize);

            p3.setX(p3.getX() + (blockSize * 2));

        } else if (this.rotation == 2) {
            p0.setX(p0.getX() - blockSize);
            p0.setY(p0.getY() - blockSize);

            p2.setX(p2.getX() - blockSize);
            p2.setY(p2.getY() + blockSize);

            p3.setY(p3.getY() + (blockSize * 2));

        } else if (this.rotation == 3) {
            p0.setX(p0.getX() + blockSize);
            p0.setY(p0.getY() - blockSize);

            p2.setX(p2.getX() - blockSize);
            p2.setY(p2.getY() - blockSize);

            p3.setX(p3.getX() - (blockSize * 2));

        } else if (this.rotation == 4) {
            p0.setX(p0.getX() + blockSize);
            p0.setY(p0.getY() + blockSize);

            p2.setX(p2.getX() + blockSize);
            p2.setY(p2.getY() - blockSize);

            p3.setY(p3.getY() - (blockSize * 2));

        }

        this.rotation++;

        if (this.rotation > 4) {
            this.rotation = 1;
        }

        System.out.println(this.rotation);
    }

    public boolean checkForClockwiseRotation() {
        Piece p0 = this.zShape.get(0);
        Piece p1 = this.zShape.get(1);
        Piece p2 = this.zShape.get(2);
        Piece p3 = this.zShape.get(3);

        if (this.rotation == 1) {
            if (p0.getX() - blockSize > 361
                    || p2.getX() + blockSize > 361
                    || p3.getX() + (blockSize * 2) > 361) {
                System.out.println("p1 first");
                return false;
            }

            if (p0.getY() + blockSize > 746
                    || p2.getY() + blockSize > 746) {
                System.out.println("p2 first");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p0.getX() - blockSize && p.getY() == p0.getY() + blockSize) {
                    System.out.println("p4 first");
                    return false;
                }
            }

        } else if (this.rotation == 2) {
            if (p0.getX() - blockSize > 361
                    || p2.getX() - blockSize > 361) {
                System.out.println("p1 second");
                return false;
            }

            if (p0.getY() - blockSize > 746
                    || p2.getY() + blockSize > 746
                    || p3.getY() + (blockSize * 2) > 746) {
                System.out.println("p2 second");
                return false;
            }

            if (p0.getX() - blockSize < 46) {
                System.out.println("p3 second");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p0.getX() - blockSize && p.getY() == p0.getY() - blockSize) {
                    System.out.println("p5 second");
                    return false;
                }

            }

        } else if (this.rotation == 3) {

            if (p0.getX() + blockSize > 361
                    || p2.getX() - blockSize > 361
                    || p3.getX() - (blockSize * 2) > 361) {
                System.out.println(" p1 third");
                return false;
            }

            if (p0.getY() - blockSize > 746
                    || p2.getY() - blockSize > 746) {
                System.out.println("p2 third");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p0.getX() + blockSize && p.getY() == p0.getY() - blockSize) {
                    System.out.println("p4 third");
                    return false;
                }
            }

        } else if (this.rotation == 4) {
            if (p0.getX() + blockSize > 361
                    || p2.getX() + blockSize > 361) {
                System.out.println("p1 fourth");
                return false;
            }

            if (p0.getY() + blockSize > 746
                    || p2.getY() - blockSize > 746
                    || p3.getY() - (blockSize * 2) > 746) {
                System.out.println("p2 fourth");
                return false;
            }

            for (Piece p : this.game.shapesToBeDrawn()) {
                if (p.getX() == p0.getX() + blockSize && p.getY() == p0.getY() + blockSize) {
                    System.out.println("p4 fourth");
                    return false;
                }
            }
        }

        return true;
    }

    public ArrayList<Piece> getShape() {
        return this.zShape;
    }

    public Color getColor() {
        return color;
    }

    public void moveDown() {

        for (Piece p : this.zShape) {
            if (checkForContactDown(p)) {
                return;
            }
        }

        for (Piece p : this.zShape) {
            p.setY(p.getY() + blockSize);
        }
    }

    public boolean checkForContactDown(Piece p) {

        for (Piece arrayP : this.game.shapesToBeDrawn()) {

            if (this.zShape.contains(arrayP)) {
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

        this.moveShape.moveLeft(zShape);
    }

    public void moveRight() {
        if (this.allowedToMove == false) {
            return;
        }

        this.moveShape.moveRight(zShape);
    }

    public void allowedToMove() {
        this.allowedToMove = false;
    }
}
