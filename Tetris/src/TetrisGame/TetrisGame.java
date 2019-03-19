package TetrisGame;

import Shapes.PiecesOnBoard;
import Shapes.IShape;
import Shapes.JShape;
import Shapes.LShape;
import Shapes.OShape;
import Shapes.Piece;
import Shapes.SShape;
import Shapes.TShape;
import Shapes.ZShape;
import UserInterface.Updatable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

public class TetrisGame extends Timer implements ActionListener {

    private final int width;
    private final int height;
    private final int blockSize;

    private Updatable updatable;

    private final PiecesOnBoard piecesOnBoard;
    private IShape iShape;
    private JShape jShape;
    private LShape lShape;
    private OShape oShape;
    private SShape sShape;
    private ZShape zShape;
    private TShape tShape;

    private int score;
    private int currentShape;
    //private int moveShape;

    private boolean gameOver;
    private boolean isPaused;
    private boolean fallingPieceOnBoard;

    private ArrayList<Piece> ListOfPiecesOnBoard;
    private ArrayList<Integer> nextShapeChooser;
    private ArrayList<Piece> toBeDeleted = new ArrayList<>();
    private ArrayList<Integer> toBeMovedDown = new ArrayList<>();

    public TetrisGame(int width, int height, int blockSize) {
        super(1000, null); // Set back to 1000

        addActionListener(this);
        setInitialDelay(500); //Set back to 2000

        this.width = width;
        this.height = height;
        this.blockSize = blockSize;

        this.gameOver = false;
        this.fallingPieceOnBoard = false;

        this.piecesOnBoard = new PiecesOnBoard();
        this.ListOfPiecesOnBoard = new ArrayList<Piece>();
        this.ListOfPiecesOnBoard = this.piecesOnBoard.getActiveShapes();

        this.nextShapeChooser = new ArrayList<Integer>();

        this.score = 0;
        // this.moveShape = 0;
        this.isPaused = false;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        isTheGameOver();

        if (gameOver == true) {
            gameOver();
        }

        if (this.nextShapeChooser.isEmpty()) {
            createNewSetOfShapes();
        }

        if (this.fallingPieceOnBoard == false) {
            pickANewShape();
            createNewShape();
        }

        moveCurrentShapeDown();

        this.score++;

        deleteAndMove();

        this.updatable.update();

        super.setDelay(1000); // change back to 1000
    }

    public void moveCurrentShapeDown() {
        if (ifCurrentShapeIsI()) {
            iShape.moveDown();
        } else if (IfCurrentShapeIsJ()) {
            jShape.moveDown();
        } else if (IfCurrentShapeIsL()) {
            lShape.moveDown();
        } else if (IfCurrentShapeIsO()) {
            oShape.moveDown();
        } else if (IfCurrentShapeIsS()) {
            sShape.moveDown();
        } else if (IfCurrentShapeIsZ()) {
            zShape.moveDown();
        } else if (IfCurrentShapeIsT()) {
            tShape.moveDown();
        }

        this.updatable.update();
        //  moveShape = 0;
    }

    public void moveCurrentShapeLeft() {
        if (ifCurrentShapeIsI()) {
            iShape.moveLeft();
        } else if (IfCurrentShapeIsJ()) {
            jShape.moveLeft();
        } else if (IfCurrentShapeIsL()) {
            lShape.moveLeft();
        } else if (IfCurrentShapeIsO()) {
            oShape.moveLeft();
        } else if (IfCurrentShapeIsS()) {
            sShape.moveLeft();
        } else if (IfCurrentShapeIsZ()) {
            zShape.moveLeft();
        } else if (IfCurrentShapeIsT()) {
            tShape.moveLeft();
        }

        this.updatable.update();
    }

    public void moveCurrentShapeRight() {
        if (ifCurrentShapeIsI()) {
            iShape.moveRight();
        } else if (IfCurrentShapeIsJ()) {
            jShape.moveRight();
        } else if (IfCurrentShapeIsL()) {
            lShape.moveRight();
        } else if (IfCurrentShapeIsO()) {
            oShape.moveRight();
        } else if (IfCurrentShapeIsS()) {
            sShape.moveRight();
        } else if (IfCurrentShapeIsZ()) {
            zShape.moveRight();
        } else if (IfCurrentShapeIsT()) {
            tShape.moveRight();
        }

        this.updatable.update();
    }

    public void rotateClockwise() {
        if (ifCurrentShapeIsI()) {
            iShape.rotateShapeClockwise();
        } else if (IfCurrentShapeIsJ()) {
            jShape.rotateShapeClockwise();
        } else if (IfCurrentShapeIsL()) {
            lShape.rotateShapeClockwise();
        } else if (IfCurrentShapeIsO()) {
            oShape.rotateShapeClockwise();
        } else if (IfCurrentShapeIsS()) {
            sShape.rotateShapeClockwise();
        } else if (IfCurrentShapeIsZ()) {
            zShape.rotateShapeClockwise();
        } else if (IfCurrentShapeIsT()) {
            tShape.rotateShapeClockwise();
        }

        this.updatable.update();
    }

    public void rotateAntiClockwise() {
        //Maybe add this in the future
    }

    public void createNewShape() {
        if (ifCurrentShapeIsI()) {
            createIShape();
        } else if (IfCurrentShapeIsJ()) {
            createJShape();
        } else if (IfCurrentShapeIsL()) {
            createLShape();
        } else if (IfCurrentShapeIsO()) {
            createOShape();
        } else if (IfCurrentShapeIsS()) {
            createSShape();
        } else if (IfCurrentShapeIsZ()) {
            createZShape();
        } else if (IfCurrentShapeIsT()) {
            createTShape();
        }
    }

    public void checkForRowsToDelete() {
        for (int j = 46; j <= 711; j += blockSize) {
            int rowCompleteCheck = 0;
            for (int i = 46; i <= 361; i += blockSize) {
                for (Piece p : this.ListOfPiecesOnBoard) {
                    if (p.getX() == i && p.getY() == j) {
                        rowCompleteCheck++;
                        System.out.println(rowCompleteCheck + "  x " + i + "  y " + j);
                    }
                }
            }
            if (rowCompleteCheck >= 10) {
                for (int i = 46; i <= 361; i += blockSize) {
                    for (Piece p : this.ListOfPiecesOnBoard) {
                        if (p.getX() == i && p.getY() == j) {
                            this.toBeDeleted.add(p);
                        }
                    }
                }
                this.toBeMovedDown.add(j);
            }
        }
    }

    public void deleteAndMove() {
        if (this.toBeDeleted.isEmpty()) {
            return;
        }

        int howManyDeleted = 0;
        for (Piece p : this.toBeDeleted) {
            for (Piece i : this.ListOfPiecesOnBoard) {
                if (i.getX() == p.getX() && i.getY() < p.getY()) {
                    i.setY(i.getY() + blockSize);
                }
            }
            this.ListOfPiecesOnBoard.remove(p);
            howManyDeleted++;
        }

        checkHowManyPointsEarned(howManyDeleted);

        this.toBeDeleted.clear();
        this.toBeMovedDown.clear();
    }

    public void checkHowManyPointsEarned(int howManyDeleted) {
        if (howManyDeleted == 40) {
            this.score += 800;
        } else if (howManyDeleted == 30) {
            this.score += 500;
        } else if (howManyDeleted == 20) {
            this.score += 300;
        } else if (howManyDeleted == 10) {
            this.score += 100;
        }
    }

    public void speedUpGame() {
        //if score tracker reaches a certain number, speed game up (Maybe ever 5000 points)
    }

    public void addShapeToBoard(Piece p) {
        this.piecesOnBoard.addShape(p);
    }

    public ArrayList<Piece> getArrayOfShapesOnBoard() {
        return ListOfPiecesOnBoard;
    }

    public ArrayList<Piece> shapesToBeDrawn() {
        return ListOfPiecesOnBoard;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void createNewSetOfShapes() {
        this.nextShapeChooser.add(1);
        this.nextShapeChooser.add(2);
        this.nextShapeChooser.add(3);
        this.nextShapeChooser.add(4);
        this.nextShapeChooser.add(5);
        this.nextShapeChooser.add(6);
        this.nextShapeChooser.add(7);
    }

    public void pickANewShape() {
        Collections.shuffle(nextShapeChooser);

        this.currentShape = this.nextShapeChooser.get(0);
        this.nextShapeChooser.remove(0);
        this.fallingPieceOnBoard = true;
    }

    public void deactivateFallingShape() {
        this.fallingPieceOnBoard = false;
        this.updatable.update();
    }

    public int getCurrentShape() {
        return currentShape;
    }

    public void increaseScore() {
        this.score++;
    }

    public int getScore() {
        return this.score;
    }

    public void updateGame() {
        this.updatable.update();
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public void pauseGame() {
        System.out.println("Pausing game");
        this.isPaused = true;
        this.updatable.update();
        super.stop();
    }

    public boolean isGamePaused() {
        if (this.isPaused) {
            return true;
        }

        return false;
    }

    public void continueGame() {
        System.out.println("Continue game");
        this.isPaused = false;
        this.updatable.update();
        super.start();
    }

    public boolean checkGameOver() {
        return this.gameOver;
    }

    public void isTheGameOver() {
        int counter = 0;
        for (Piece p : this.ListOfPiecesOnBoard) {
            if (p.getX() == 186 && p.getY() == 81) {
                counter++;
            }
        }

        if (counter >= 2) {
            gameOver = true;
        } else {
            counter = 0;
        }
    }

    public void gameOver() {
        System.out.println("GAME OVER");
        this.stop();
    }

    public boolean ifCurrentShapeIsI() {
        return this.currentShape == 1;
    }

    public boolean IfCurrentShapeIsJ() {
        return this.currentShape == 2;
    }

    public boolean IfCurrentShapeIsL() {
        return this.currentShape == 3;
    }

    public boolean IfCurrentShapeIsO() {
        return this.currentShape == 4;
    }

    public boolean IfCurrentShapeIsS() {
        return this.currentShape == 5;
    }

    public boolean IfCurrentShapeIsZ() {
        return this.currentShape == 6;
    }

    public boolean IfCurrentShapeIsT() {
        return this.currentShape == 7;
    }

    public void createIShape() {
        iShape = new IShape(this);
        this.iShape.createShape();
        for (Piece p : this.iShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }

    public void createJShape() {
        jShape = new JShape(this);
        this.jShape.createShape();
        for (Piece p : this.jShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }

    public void createLShape() {
        lShape = new LShape(this);
        this.lShape.createShape();
        for (Piece p : this.lShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }

    public void createOShape() {
        oShape = new OShape(this);
        this.oShape.createShape();
        for (Piece p : this.oShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }

    public void createSShape() {
        sShape = new SShape(this);
        this.sShape.createShape();
        for (Piece p : this.sShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }

    public void createZShape() {
        zShape = new ZShape(this);
        this.zShape.createShape();
        for (Piece p : this.zShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }

    public void createTShape() {
        tShape = new TShape(this);
        this.tShape.createShape();
        for (Piece p : this.tShape.getShape()) {
            this.ListOfPiecesOnBoard.add(p);
        }
    }
}
