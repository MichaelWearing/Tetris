package UserInterface;

import Shapes.Piece;
import TetrisGame.TetrisGame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawingBoard extends JPanel implements Updatable {

    private final int boardWidth;
    private final int boardHeight;
    private final int blockSize;

    private final TetrisGame game;

    public DrawingBoard(TetrisGame game) {
        this.game = game;

        this.boardWidth = 430; // play area = 360
        this.boardHeight = 780; // play area = 710
        this.blockSize = 35;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Create playing area
        g.setColor(Color.BLACK);

        for (int i = 10; i <= boardHeight; i += blockSize) {
            g.drawLine(10, i, boardWidth, i);
        }

        for (int i = 10; i <= boardWidth; i += blockSize) {
            g.drawLine(i, 10, i, boardHeight);
        }

        //Border creating 
        this.colorBoard(g);

        //Draw Shapes on Board
        for (Piece p : this.game.shapesToBeDrawn()) {
            g.setColor(p.getColor());
            g.fillRect(p.getX(), p.getY(), blockSize - 1, blockSize - 1);
        }

        //Game Commands
        gameCommands(g);
    }

    @Override
    public void update() {
        super.repaint();
    }

    public void colorBoard(Graphics g) {

        //Paint Border
        g.setColor(Color.GRAY);
        for (int i = 10; i < boardHeight; i += blockSize) {
            g.fillRect(11, i + 1, 34, 34);
        }
        for (int i = 10; i < boardHeight; i += blockSize) {
            g.fillRect(396, i + 1, 34, 34);
        }
        for (int i = 10; i < boardWidth; i += blockSize) {
            g.fillRect(i + 1, 11, 34, 34);
        }
        for (int i = 10; i < boardWidth; i += blockSize) {
            g.fillRect(i + 1, 746, 34, 34);
        }

        //Fill Middle
        g.setColor(Color.WHITE);
        for (int i = 46; i < boardWidth - 35; i += blockSize) {
            for (int o = 46; o < boardHeight - 35; o += blockSize) {
                g.fillRect(i, o, 34, 34);
            }

        }
    }
    
    public void gameCommands(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial Black", Font.PLAIN, 23));
        g.drawString("Score: ", 470, 80);
        g.setFont(new Font("Arial Black", Font.PLAIN, 40));
        g.setColor(Color.MAGENTA);
        g.drawString(" " + game.getScore(), 450, 130);
       
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial Black", Font.PLAIN, 23));
        g.drawString("Press P", 470, 215);
        g.drawString("to", 500, 245);
        g.drawString("Pause", 475, 275);
        
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        
        g.drawString("Up Arrow to", 465, 385);
        g.drawString("Rotate", 485, 415);
        g.drawString("Clockwise", 470, 445);
        
        g.drawString("Left Arrow to", 465, 495);
        g.drawString("Move Left", 475, 525);
        
        g.drawString("Right Arrow to", 460, 575);
        g.drawString("Move Right", 470, 605);
        
        g.drawString("Space to drop", 463, 655);
        g.drawString("down faster", 470, 685);
        
              

        if (game.isGamePaused() == true) {
            System.out.println("IT IS PAUSED");

            g.setFont(new Font("Arial Black", Font.BOLD, 60));
            g.drawString("PAUSED", 75, 250);
            g.setFont(new Font("Arial Black", Font.PLAIN, 20));
            g.drawString("- P to unpause -", 136, 280);

        }

        if (this.game.checkGameOver()) {
            System.out.println("GAME OVER");
            g.setFont(new Font("Arial Black", Font.BOLD, 60));
            g.drawString("GAME", 116, 250);
            g.drawString("OVER", 116, 300);
        }
    }
}
