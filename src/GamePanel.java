import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {

    private int mouseX;
    private int mouseY;
    private boolean mousePressed;
    private Game game;

    public GamePanel(Game game) {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.game = game;
    }

    public Dimension getMinimumSize() {
        return new Dimension(800, 600);
    }

    public Dimension getMaximumSize() {
        return new Dimension(800, 600);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    public Point getMouseCoordinate() {
        return new Point(mouseX, mouseY);
    }

    public void paintComponent(Graphics g) {
        game.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mousePressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mousePressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}