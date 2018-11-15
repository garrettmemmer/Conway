package CPR;
import java.util.*;

/**
 * Created by mblac on 3/1/2018.
 */


        import java.awt.*;
        import java.awt.event.*;

public class LifeView extends Observable implements World  {
    boolean[][] board = new boolean[256][256];

    public void teh() {
    }

    public void meh(MouseEvent e) {
    }

    //draws rectangles according to board
    public void draw(Graphics g) {
        int w = 3;
        g.setColor(Color.BLACK);
        for (int x = 0; x < 256; x++) {			// drawing 256 x 256 grid
            for (int y = 0; y < 256; y++) {
                g.drawRect(x*w, y*w, w, w);
                if (board[x][y]==true) {
                    g.fillRect(x*w, y*w, w, w);
                }
            }
        }
        //notify observer we've changed
        setChanged();
        notifyObservers();
    }

    public boolean hasEnded() {
        return false;
    };

    public static void main(String[] args) {

    }
}

