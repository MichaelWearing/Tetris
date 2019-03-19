
package Shapes;

import java.util.ArrayList;


public class PiecesOnBoard {
    private ArrayList<Piece> activeShapes;
    
    public PiecesOnBoard() {
        this.activeShapes = new ArrayList<Piece>();
    }
    
    public void addShape(Piece p ) {
        this.activeShapes.add(p);
    }
    
    public ArrayList<Piece> getActiveShapes() {
        return this.activeShapes;
    }
}
