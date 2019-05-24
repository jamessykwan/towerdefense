import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class Balloon extends Sprite{
	
	int health;
	int damage;
	int rarity;
	int speed;
	int tier;
	boolean isAlive;
	ArrayList<Coordinate> path = new ArrayList<Coordinate>();
	
	
	public Balloon(String filename) {
		super(filename);
		health = 1;
		damage = 1;
		isAlive = true;
	}
	
	public Balloon(int tier) {
		
		super(updateImage(tier));
		this.tier = tier;
		health = tier;
		damage = tier;
		isAlive = true;
		speed = 5;
		createPath();
	}
		
	public void createPath() {
		path.add(new Coordinate(85, 405));
		path.add(new Coordinate(80, 265));
		path.add(new Coordinate(205, 265));
		path.add(new Coordinate(205, 545));
		path.add(new Coordinate(30, 545));
		path.add(new Coordinate(30, 635));
		path.add(new Coordinate(440, 635));
		path.add(new Coordinate(435, 480));
		path.add(new Coordinate(305, 480));
		path.add(new Coordinate(305, 355));
		path.add(new Coordinate(440, 355));
		path.add(new Coordinate(440, 215));
		path.add(new Coordinate(255, 215));
		path.add(new Coordinate(255, 0));
	}
	
	public void deletePath() {
		path.clear();
	}
	
	/*
	 * subtracts from the balloon's health until
	 * health is less than zero. Then calls spawnNew()
	 */
	public void takeDamage(int damage){
		health -= damage;
		if(health<= 0) {
			spawnNew();
		}
	}
	
	/*
	 * Once a balloon "dies" it moves down a tier
	 * unless it is already at tier one. Once it goes
	 * down a tier, the traits of the balloon are reset
	 * to the new tier's values. 
	 */
	public void spawnNew() {
		if (tier> 1) {
			tier--;
			health = tier;
			damage = tier;
			speed = 5;
			img = getImage(updateImage(tier)); // converted getImage to protected b/c it wasn't accessible by Balloon class (child class)
		}
		else {
			isAlive = false;
			deletePath();			
		}
	}
	
	/*
	 * Switches the image of the balloon based on its tier
	 */
	public static String updateImage(int tier) {
		if(tier == 3) {
			return "greenBalloon.png"; 
		}
		else if(tier == 2) {
			return "blueBalloon.png"; 
		}
		else if(tier == 1) {
			return "redBalloon.png"; 
		}
		else {
			return null;
		}
		
	}
	
	/*
	 * moves to the first coordinate in path
	 * then deletes the first coordinate from path and 
	 * moves to the new first coordinate from path (originally
	 * the second point). It does this until path is empty.
	 */
	public void move() {
		if(path.size() != 0) {
			Coordinate c = path.get(0);
			int x1 = c.x;
			int y1 = c.y;
				
			if(Math.abs(x1 - x) > speed || Math.abs(y1 - y) > speed) {
				if(this.x > x1) {
					vy = 0;
					tx.translate(-speed, vy);
					this.x -= speed;
				}
			
				else if(this.x < x1) {
					//this.x += speed;
					vy = 0;
					tx.translate(speed, vy);
					this.x += speed;
				}
				
				else if(this.y > y1) {
					//vx = 0;
					tx.translate(0, -speed);
					this.y -= speed;
				}
				
				else if(this.y < y1) {
					//vx = 0;
					tx.translate(0, speed);
					this.y += speed;
				}
			}
			
			else {
				path.remove(0);
			}
				
		}
	}
}
