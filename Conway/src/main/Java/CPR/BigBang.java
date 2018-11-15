package CPR;

/**
 * Created by mblac on 3/1/2018.
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BigBang extends JComponent implements ActionListener, MouseListener, KeyListener {
    World world;
    boolean isRunning = false;
    Forager forage = new Forager();
    Forager2 forage2 = new Forager2();

    private static BigBang instance = null; //needed for singleton pattern
    private BigBang(World world) {
        this.world = world;
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocus();
    }


    public static BigBang getInstance(World world){//needed for singleton pattern
        if (instance == null) {
            instance = new BigBang(world);
        }
        return instance;
    }

    public void start() {
        JFrame a = new JFrame();
        a.add( this, BorderLayout.CENTER );
        JButton button = new JButton("Start");
        button.setPreferredSize(new Dimension(50,40));
        a.add(button, BorderLayout.PAGE_END);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isRunning) isRunning = false;
                else isRunning = true;
                repaint();
            }
        });
        a.setVisible(true);
        a.setSize(900, 900);

    }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mousePressed(MouseEvent e) {
    }
    public void mouseReleased(MouseEvent u) { }
    public void mouseClicked(MouseEvent e) { }

    public void actionPerformed(ActionEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }
    public void keyTyped(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }


    public void paintComponent(Graphics g) {
        this.world.draw(g);
        this.forage.draw(g);
    }
}
