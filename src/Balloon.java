import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

public class Balloon extends Sprite {

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
<<<<<<< HEAD
		speed = 2*tier;
		createPath();
	}
	
	public Balloon(int tier, int xpos, int ypos) {
		
		super(updateImage(tier), xpos, ypos);
		this.tier = tier;
		health = tier;
		damage = tier;
		isAlive = true;
		speed = 2*tier;
		createPath();
	}
	
		
	public void createPath() {
		path.add(new Coordinate(156, 405));
		path.add(new Coordinate(156, 140));
		path.add(new Coordinate(410, 140));
		path.add(new Coordinate(410, 673));
		path.add(new Coordinate(70, 673));
		path.add(new Coordinate(70, 850));
		path.add(new Coordinate(875, 850));
		path.add(new Coordinate(875, 562));
		path.add(new Coordinate(610, 563));
		path.add(new Coordinate(610, 310));
		path.add(new Coordinate(880, 310));
		path.add(new Coordinate(880, 20));
		path.add(new Coordinate(517, 20));
		path.add(new Coordinate(517, -100));
=======
		speed = 5;

		createPath();
	}

	public void createPath() {
		path.add(new Coordinate(80, 405));
		path.add(new Coordinate(150, 135));
		path.add(new Coordinate(400, 165));
		path.add(new Coordinate(400, 680));
		path.add(new Coordinate(60, 680));
		path.add(new Coordinate(60, 850));
		path.add(new Coordinate(870, 850));
		path.add(new Coordinate(870, 550));
		path.add(new Coordinate(605, 550));
		path.add(new Coordinate(605, 300));
		path.add(new Coordinate(870, 20));
		path.add(new Coordinate(510, -70));

	}

	public void deletePath() {
		path.clear();
>>>>>>> master
	}

	/*
	 * subtracts from the balloon's health until health is less than zero. Then
	 * calls spawnNew()
	 */
	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			spawnNew();
		}
	}

	/*
	 * Once a balloon "dies" it moves down a tier unless it is already at tier one.
	 * Once it goes down a tier, the traits of the balloon are reset to the new
	 * tier's values.
	 */
	public void spawnNew() {
		if (tier > 1) {
			tier--;
			health = tier;
			damage = tier;
<<<<<<< HEAD
			speed = 2*tier;
			img = getImage(updateImage(tier)); // converted getImage to protected b/c it wasn't accessible by Balloon class (child class)
		}
		else {
			isAlive = false;
=======
			speed = 5;

			img = getImage(updateImage(tier)); // converted getImage to protected b/c it wasn't accessible by Balloon
												// class (child class)
		} else {
			isAlive = false;
			deletePath();
>>>>>>> master
		}
	}

	/*
	 * Switches the image of the balloon based on its tier
	 */
	public static String updateImage(int tier) {
		if (tier == 3) {
			return "greenBalloon.png";
		} else if (tier == 2) {
			return "blueBalloon.png";
		} else if (tier == 1) {
			return "redBalloon.png";
		} else {
			return null;
		}

	}
<<<<<<< HEAD
	
	public boolean isFinished() {
		return (path.size()==0);
	}
	
=======

>>>>>>> master
	/*
	 * moves to the first coordinate in path then deletes the first coordinate from
	 * path and moves to the new first coordinate from path (originally the second
	 * point). It does this until path is empty.
	 */
	public void move() {
		if(path.size() != 0) {
			Coordinate c = path.get(0);
			int x1 = c.x;
			int y1 = c.y;
			
			if(Math.abs(x1 - x) > speed || Math.abs(y1 - y) > speed) {
				
				if(this.x > x1 && Math.abs(x1 - x) > speed ) {
					tx.translate(-speed, 0);
					x = (int) tx.getTranslateX();
				}
			
				else if(this.x < x1 && Math.abs(x1 - x) > speed ) {
					//this.x += speed;
					tx.translate(speed, 0);
					x = (int) tx.getTranslateX();
<<<<<<< HEAD
=======

>>>>>>> master
				}
				
				else if(this.y > y1 && Math.abs(y1 - y) > speed) {
					//vx = 0;
					tx.translate(0, -speed);
					y = (int) tx.getTranslateY();
				}
				
				else if(this.y < y1 && Math.abs(y1 - y) > speed) {
					//vx = 0;
					tx.translate(0, speed);
					y = (int) tx.getTranslateY();
<<<<<<< HEAD
=======

>>>>>>> master
				}
				
			}
			else {
				path.remove(0);
				System.out.println("reached point");
			}
				
		}
	}

}
