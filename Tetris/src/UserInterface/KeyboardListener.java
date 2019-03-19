package UserInterface;

import TetrisGame.TetrisGame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    private final TetrisGame tetrisGame;

    public KeyboardListener(TetrisGame tetrisGame) {
        this.tetrisGame = tetrisGame;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            tetrisGame.moveCurrentShapeLeft();
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            //tetrisGame.setMoveShape(2);
            tetrisGame.moveCurrentShapeRight();
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            tetrisGame.rotateClockwise();
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            tetrisGame.rotateAntiClockwise();
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_SPACE)  {
            tetrisGame.increaseScore();
            tetrisGame.moveCurrentShapeDown();
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_P) {
            if (tetrisGame.isGamePaused()) {
                tetrisGame.continueGame();
            } else if (tetrisGame.isGamePaused() == false) {
                tetrisGame.pauseGame();
            }
        }
        
        if (ke.getKeyCode() == KeyEvent.VK_C) {
            tetrisGame.continueGame();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
