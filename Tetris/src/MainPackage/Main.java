
package MainPackage;

import TetrisGame.TetrisGame;
import UserInterface.UserInterface;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        TetrisGame game = new TetrisGame(430, 780, 35);
        
        UserInterface ui = new UserInterface(game);
        SwingUtilities.invokeLater(ui);
        
        while (ui.getUpdatable() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("The drawing board hasn't been created yet.");
            }
        }
        
        game.setUpdatable(ui.getUpdatable());
        game.start();
    }
    
}
