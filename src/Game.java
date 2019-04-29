import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game implements Runnable {

    Sprite player;
    Background bg;
    GamePanel gamePanel;
    ArrayList<Sprite> towers = new ArrayList<Sprite>();
    ArrayList<Sprite> balloons = new ArrayList<Sprite>();
    Point mouseCoordinate;
    GameState gameState;
    JFrame frame;


    public Game() {
        gameState = GameState.SETUP;
        run();
    }

    public static void main(String[] args) {
        new Game();
    }

    public void init() {
        gamePanel = new GamePanel(this);
        frame = new JFrame();
        frame.setTitle("tower defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(gamePanel);
        frame.pack();
        frame.setVisible(true);
        gameState = GameState.UPDATE;
    }

    public void update() {
        mouseCoordinate = gamePanel.getMouseCoordinate();

    }

    public void placeTower() {

    }

    public void draw(Graphics g) {

    }

    public void run() {
        while (true) {
            {
                if (gameState == GameState.SETUP) {
                    init();
                } else if (gameState == GameState.UPDATE) {
                    update();
                } else if (gameState == GameState.DRAW) {
                    gamePanel.repaint();
                } else {
                    break;
                }
            }

        }
    }

    enum GameState {SETUP, UPDATE, DRAW, WAIT, END}
}
