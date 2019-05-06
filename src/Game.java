import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Game implements Runnable {

    Background bg;
    ArrayList<DartTower> towers = new ArrayList<>();
    ArrayList<Sprite> balloons = new ArrayList<Sprite>();
    private Sprite player;
    private GamePanel gamePanel;
    private Point mouseCoordinate;
    private GameState gameState;
    private JFrame frame;


    public Game() {
        gameState = GameState.SETUP;
        run();
    }

    public static void main(String[] args) {
        new Game();
    }

    public void init() {
        DartTower tower = new DartTower();
        //towers.add(tower);
        gamePanel = new GamePanel(this);
        gamePanel.setPreferredSize((gamePanel.getPreferredSize()));
        frame = new JFrame();
        frame.setTitle("tower defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setContentPane(gamePanel);
        frame.pack();
        frame.setVisible(true);
        gameState = GameState.DRAW;

    }

    public void update() {
        mouseCoordinate = gamePanel.getMouseCoordinate();

    }

    public void placeTower() {

    }

    void draw(Graphics g) {

        for (DartTower tower : towers) {
            tower.draw(g);
        }

    }

    public void run() {
        while (true) {
            {
                if (gameState == GameState.SETUP) {
                    System.out.println("setup");
                    init();
                } else if (gameState == GameState.UPDATE) {
                    System.out.println("updating");
                    update();
                } else if (gameState == GameState.DRAW) {
                    System.out.println("drawing");
                    gamePanel.repaint();
                } else {
                    break;
                }
            }

        }
    }

    enum GameState {SETUP, UPDATE, DRAW, WAIT, END}
}
