package game;

import java.util.ArrayList;

public class Blimp extends Sprite{
	
	private int health;
    private int damage;
    private int speed;
    private int tier;
    private boolean isAlive;
    private ArrayList<Coordinate> path = new ArrayList<>();
	ArrayList<Balloon> balloons;
	
	public Blimp(int tier, int x, int y) {
		super(updateImage(tier), x, y);
		this.setTier(tier);
        if(tier == 1) {
        	setHealth(1);
        	setDamage(4);
            setSpeed(5);
        }
        else {
        	setHealth(20);
        	setDamage(20);
            setSpeed(3);
        }
        setAlive(true);
        createPath();
		// TODO Auto-generated constructor stub
	}
	
	
	public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) {
        	setAlive(false);
        }
    }
	
	
	public static String updateImage(int tier) {
		if(tier == 1) {
			return "../resources/whiteBalloon.png";
		}
		else{
			return "../resources/blimp.png";
		}
	}

       public void createPath() {
        getPath().add(new Coordinate(156, 405));
        getPath().add(new Coordinate(156, 140));
        getPath().add(new Coordinate(410, 140));
        getPath().add(new Coordinate(410, 673));
        getPath().add(new Coordinate(70, 673));
        getPath().add(new Coordinate(70, 850));
        getPath().add(new Coordinate(875, 850));
        getPath().add(new Coordinate(875, 562));
        getPath().add(new Coordinate(610, 563));
        getPath().add(new Coordinate(610, 310));
        getPath().add(new Coordinate(880, 310));
        getPath().add(new Coordinate(880, 20));
        getPath().add(new Coordinate(517, 20));
        getPath().add(new Coordinate(517, -100));
    }


    public void deletePath() {
        getPath().clear();
    }

    public boolean isFinished() {
        return (getPath().size() == 0);
    }


    /*
     * moves to the first coordinate in path then deletes the first coordinate from
     * path and moves to the new first coordinate from path (originally the second
     * point). It does this until path is empty.
     */
    public void move() {
        if (getPath().size() != 0) {
            Coordinate c = getPath().get(0);
            int x1 = c.x;
            int y1 = c.y;

            if (Math.abs(x1 - x) > getSpeed() || Math.abs(y1 - y) > getSpeed()) {

                if (this.x > x1 && Math.abs(x1 - x) > getSpeed()) {
                    tx.translate(-getSpeed(), 0);
                    x = (int) tx.getTranslateX();
//                    tx.rotate(2);
                } else if (this.x < x1 && Math.abs(x1 - x) > getSpeed()) {
                    tx.translate(getSpeed(), 0);
                    x = (int) tx.getTranslateX();

                } else if (this.y > y1 && Math.abs(y1 - y) > getSpeed()) {
                    //vx = 0;
                    tx.translate(0, -getSpeed());
                    y = (int) tx.getTranslateY();
//                    tx.rotate(2);
                } else if (this.y < y1 && Math.abs(y1 - y) > getSpeed()) {
                    //vx = 0;
                    tx.translate(0, getSpeed());
                    y = (int) tx.getTranslateY();
//                    tx.rotate(2);
                }

            } else {
                getPath().remove(0);
                //System.out.println("reached point");
            }
        }  
    }
	
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public ArrayList<Coordinate> getPath() {
        return path;
    }

    public void setPath(ArrayList<Coordinate> path) {
        this.path = path;
    }
	
}
