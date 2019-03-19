package UserInterface;

import TetrisGame.TetrisGame;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;

public class UserInterface implements Runnable {

    private final int width = 600;
    private final int height = 850; //750
    private JFrame frame;
    private DrawingBoard drawingBoard;
    private final TetrisGame game;

    public UserInterface(TetrisGame game) {
        this.game = game;
    }

    @Override
    public void run() {
        frame = new JFrame("Tetris");
        frame.setPreferredSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(350, 100);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container c) {
        drawingBoard = new DrawingBoard(game);
        c.add(drawingBoard);

        KeyboardListener keyboardListener = new KeyboardListener(game);
        frame.addKeyListener(keyboardListener);
    }

    public Updatable getUpdatable() {
        return drawingBoard;
    }
}
