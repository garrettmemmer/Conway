package CPR;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Created by csinders on 3/30/18.
 */


public class Forager implements World {
    Random rand = new Random();
    Coord location;
    public Forager() {
        location = new Coord(rand.nextInt(256),rand.nextInt(256));
    }
    public Forager(int x, int y) {
        location = new Coord(x,y);
    }

    public Coord getLocation() {
        return location;
    }
    public int getX() {
        return location.x;
    }
    public int getY() {
        return location.y;
    }
    // Moves to next location randomly and returns a boolean of whether it moved
    public boolean getNextMove(LifeModel lifeModel) {

        boolean[][] board = lifeModel.currentBoard;

        int tempX = location.x;
        int tempY = location.y;
        if (rand.nextBoolean()) {
            if ((tempX + 1) < board.length) { // Don't want to go off board
                tempX++;
            } else {
                if (tempX - 1 > 0) { // Don't want to go off board
                    tempX--;
                }
            }
        } else {
            if (rand.nextBoolean()) {
                if ((tempY + 1) < board[0].length) { // Don't want to go off board
                    tempY++;
                }
            } else {
                if (tempY - 1 > 0) { // don't want to go off board
                    tempY--;
                }
            }
        }
        if (board[tempX][tempY]) {
            //location already has a resource, so can't move here
            return false;
        } else {
            // location is free, so move there
            location.x = tempX;
            location.y = tempY;
            return true;
        }
    }


    // Consumes all resources in a 3x3 square around itself.
    // Returns how many resources were consumed
    public int consumeResources(LifeModel lifeModel) {
        boolean[][] board = lifeModel.currentBoard;
        int consumedAmount = 0;
        // consume already resources in 3x3 square it is in.
        for (int x = location.x-1; x <= location.x + 1; x++) {
            for (int y = location.y - 1; y <= location.y + 1; y++) {
                if (x >= 0 && y >= 0 && x < board.length && y < board.length && board[x][y]) {
                    board[x][y] = false;
                    consumedAmount++;
                }
            }
        }
        return consumedAmount;
    }
    // Draw a red square where the forager is.
    public void draw(Graphics g) {
        int w = 3;
        g.setColor(Color.RED);
        g.drawRect(location.x*w, location.y*w, w, w);
        g.fillRect(location.x*w, location.y*w, w, w);
    }

    // Below methods are required for this to implement world, which is required to be able
    // To draw it on the screen
    public void teh() {
    }

    public void meh(MouseEvent e) {
    }

    public boolean hasEnded() {
        return false;
    }
}