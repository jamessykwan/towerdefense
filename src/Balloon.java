import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Balloon extends Sprite{

	int health;
	int damage;
	int rarity;
	int speed;
	boolean isAlive;
	
	public Balloon(String filename) {
		super(filename);
		health = 1;
		damage = 1;
		isAlive = true;
	}
	
	public Balloon(int tier) {
		super("frog.png");
		health = tier;
		damage = tier;
		isAlive = true;
	}
	
	public Balloon(String filename, int h, int d) {
		super(filename);
		health = h;
		damage = d;
		isAlive = true;
		// TODO Auto-generated constructor stub
	}
	
	public Balloon(String filename, int x2, int y2, int h, int d) {
		super(filename, x2, y2);
		health = h;
		damage = d;
		isAlive = true;
		// TODO Auto-generated constructor stub
	}
	
	public void takeDamage(int damage){
		health -= damage;
		if(health<= 0) {
			isAlive = false;
		}
	}
	
	
	
}
