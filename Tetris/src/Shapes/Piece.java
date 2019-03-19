package Shapes;

import java.awt.Color;


public class Piece {
   private int x;
   private int y;
   private final Color color;
   
   public Piece(int x, int y, Color color) {
       this.x = x;
       this.y = y;
       this.color = color;
   }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public Color getColor() {
        return this.color;
    }
   
   public void makesContact(Piece p) {
       if (this.x == p.x + 1 || this.y == p.y + 1) {
           //either return trun or add to the list
       }
   }
}