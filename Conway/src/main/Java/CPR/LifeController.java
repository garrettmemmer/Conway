package CPR;
import java.util.*;
import java.io.File;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Random;

public class LifeController implements Observer {

    LifeView lifeView = new LifeView();
    BigBang bigBang = BigBang.getInstance(lifeView); //called to get instance of a singleton
    LifeModel lifeModel = new LifeModel();
    //Forager forage = new Forager();
    Random rand = new Random();

    public void update(Observable o, Object arg){
        if (o.getClass() == lifeModel.getClass()) {
            lifeView.board = lifeModel.currentBoard;
            //forage.draw(bigBang.getGraphics());
        }
        else if (o.getClass() == lifeView.getClass()) {
            if (bigBang.isRunning) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
                lifeModel.nextGen();
                bigBang.forage.getNextMove(lifeModel);// Move forager to next location
                // Don't need to care if it actually moved or not
                if (rand.nextBoolean()) {
                    bigBang.forage.consumeResources(lifeModel);
                }
                bigBang.repaint();
            }
        }
    }
    
    public static boolean[][] readInput(File file) throws FileNotFoundException {
        boolean[][] initialBoard = new boolean[256][256];
        int rowNum = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] bits = scanner.nextLine().split("\t");
            //System.out.println(Arrays.toString(bits));
            for (int i = 0; i < bits.length; i++) {
                if (bits[i].equals("1")) {
                    initialBoard[rowNum][i] = true;
                }
                else {
                    initialBoard[rowNum][i] = false;
                }
            }
            rowNum++;
        }
        scanner.close();
        return initialBoard;
    }

    public static void main(String[] args) throws FileNotFoundException {
        //initialize board and gui
        LifeController gameOfLife = new LifeController();
        //get file to load
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION)
            gameOfLife.lifeModel.setModel(readInput(chooser.getSelectedFile()));
        gameOfLife.lifeModel.addObserver(gameOfLife);
        gameOfLife.lifeView.addObserver(gameOfLife);
        gameOfLife.bigBang.start();
    }
}
