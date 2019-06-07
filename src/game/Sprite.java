package game;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Sprite extends JButton {
    private double x;
    private double y;
    //JLabel img;
    public Image img;
    private int vx = 0, vy = 0, ay = 1;

    public AffineTransform tx = AffineTransform.getTranslateInstance(getX(), getY());

    // constructs player as affinetransform instead of image
    public Sprite(String filename) {
        tx = AffineTransform.getTranslateInstance(getX(), getY());
        setX(0);
        setY(405);
        img = getImage(filename);

        this.setBounds((int) getX(), (int) getY(), 100, 100);
        init(getX(), getY());
    }


    public Sprite(String f, int x2, int y2) {
        setX(x2);
        setY(y2);
        img = getImage(f);
        init(getX(), getY());
    }


    // move with input from driver
    public void move() {
        tx.translate(vx, vy);
        //System.out.println(vx + " " + vy);
    }
    
    public void moveTo(int x1, int y1) {
    	tx.translate(x1, y1);
    }

    public void setImg(String filename) {
    	img = getImage(filename);
    }
    
    // use find affinetransform current position
    public int gety() {
        return (int) tx.getTranslateY();
    }

    public void sety(int a) {
        y = a;
    }

    public int getx() {
        return (int) tx.getTranslateX();
    }

    public void setx(int a) {
        x = a;
    }

    public void rotateCW() {
        tx.rotate(2);
    }


    // set size and position and reset player
    public void init(double a, double b) {
        tx.setToTranslation(a, b);
        tx.scale(2, 2);
    }

    // draw the affinetransform
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(img, (int) getX(), (int) getY(), 100, 100, null);
        g2.dispose();
    }

    // converts image to make it drawable in paint
    protected Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = Sprite.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

	public void takeDamage(int i) {
		// TODO Auto-generated method stub
		//does nothing
	}

    public int getX() {
        return (int) x;
    }


    public void setX(double x) {
        this.x = x;
    }


    public int getY() {
        return (int) y;
    }


    public void setY(double y) {
        this.y = y;
    }

}