package CPR;
import java.util.*;
import java.lang.*;


public class LifeModel extends Observable{
    public HashSet<Coord> currentlyAliveCells = new HashSet<Coord>();
    public boolean[][] currentBoard = new boolean[256][256];

    //set up this model according to a given 2d boolean array
    public void setModel(boolean[][] b) {
        currentlyAliveCells = new HashSet<Coord>(); //reset currentlyAliveCells
        //loop through given board, adding alive cells to currentlyAliveCells and
        //setting indeces in currentBoard to values on given board
        for (int i = 0; i<256; i++) {
            for (int j = 0; j<256; j++) {
                currentBoard[i][j] = b[i][j];
                if (b[i][j]) {
                    currentlyAliveCells.add(new Coord(i, j));
                }
            }
        }
        //tell observer we've changed
        setChanged();
        notifyObservers();
    }

    //this method updates currentlyAliveCells and currentBoards to represent
    //the next generation of Conways game of life
    public void nextGen() {
        //initialize collections of cells to make alive and cells to kill at the end of the method
        HashSet<Coord> toMakeAlive = new HashSet<Coord>();
        //obtain a complete set of cells to process by adding alive cells with every alive cell's neighbors
        //go through cellsToProcess and determine what to do to each one
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {
                Coord temp = new Coord(x,y);
                if (shouldBeAlive(temp)) {
                    toMakeAlive.add(temp);
                }
            }
        }
        //make cells alive and dead on board and update currentlyAliveCells
        for (Coord currCoord : toMakeAlive) {
            currentBoard[currCoord.x][currCoord.y] = true;
            currentlyAliveCells.add(currCoord);
        }
        //notify observer we've changed
        setChanged();
        notifyObservers();

    }

    //this method takes a coord and depending on the currentBoard,
    //returns true or false indicating if this coord should be alive
    //in the next generation
    public boolean shouldBeAlive(Coord coord) {
        if (currentBoard[coord.x][coord.y]) {
            return true;
        }
        double growthFactor = 1.5;
        //find number of alive neighbors
        int aliveNeighbors = 0;
        try {if (currentBoard[coord.x-1][coord.y-1] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x-1][coord.y] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x-1][coord.y+1] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x][coord.y-1] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x][coord.y+1] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x+1][coord.y-1] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x+1][coord.y] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        try {if (currentBoard[coord.x+1][coord.y+1] == true) {
            aliveNeighbors++;
        }}catch(Exception e){}
        //determine what to do based on number of alive neighbors

        if (!currentBoard[coord.x][coord.y]) {
            double temp = aliveNeighbors * growthFactor;
            Random rand = new Random();
            double threshHold = rand.nextDouble()*8; // Randomly decides if the number of required
            // alive neighbors is between 0 and 8.
            return temp >= threshHold;
        }
        return true;
    }
    public static void main(String[] args){
   }
}