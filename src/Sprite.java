import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Sprite extends JButton{ 
	int x;
	int y;
	//JLabel img;
	Image img;
	int tempy;
	int tempx;
	int sizeX = 40;
	int sizeY = 45;
	int vx = 0, vy = 0, ay = 1;
	
	AffineTransform tx = AffineTransform.getTranslateInstance(x, y);

	// constructs player as affinetransform instead of image
	public Sprite(String filename) {
		tx = AffineTransform.getTranslateInstance(x, y);
		x = 0;
		y = 405;
		img = getImage(filename);

		this.setBounds(x,y,100,100);
		init(x, y);
		
	}


	public Sprite(String f, int x2, int y2) {
		// TODO Auto-generated constructor stub
		x=x2;
		y=y2;
		img = getImage(f);
		init(x, y);
	}


	// move with input from driver
	public void move() {		
		tx.translate(vx, vy);
		//System.out.println(vx + " " + vy);
	}
	
	// use find affinetransform current position
	public int gety() {
		return (int) tx.getTranslateY();
	}

	public int getx() {
		return (int) tx.getTranslateX();
	}

	
	public void rotateCW(){
		tx.rotate(2);
	}
	
	// "rotate" based on direction
	public void rotateleft() {
		img = getImage("Playerleft.png");

	}

	public void rotateright() {
		img = getImage("Playerright.png");

	}

	public void rotatedown() {
		img = getImage("Playerdown.png");

	}

	public void rotateup() {
		img = getImage("Player.png");

	}

	// set size and position and reset player
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(2, 2);
	}

	// draw the affinetransform
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//example resizing image on call to paint
		BufferedImage resizedImg = new BufferedImage(200,200, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g3 = resizedImg.createGraphics();

	    g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g3.drawImage(img, 0, 0, sizeX, sizeY, null);
	    g3.dispose();
	    
	    //example rotating image on call to paint
//	    tx.rotate(1,50,50);
		g2.drawImage(resizedImg, tx, null);
		
		
		
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