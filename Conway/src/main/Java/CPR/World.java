package CPR;

/**
 * Created by mblac on 3/1/2018.
 */


import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface World {
    public void teh();
    public void draw(Graphics g);
    public void meh(MouseEvent e);
    public boolean hasEnded();
}