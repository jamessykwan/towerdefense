package game;

import java.util.ArrayList;

public class Balloon extends Sprite {

    private int health;
    private int damage;
    private int speed;
    private int tier;
    private boolean isAlive;
    private boolean isBlimp = false;
    private ArrayList<Coordinate> path = new ArrayList<>();

    public Balloon(String filename) {
        super(filename);
        setHealth(1);
        setDamage(1);
        setAlive(true);
    }
    
    
    // Tier based system for balloons
    public Balloon(int tier) {

        super(updateImage(tier));
        this.setTier(tier);
        setHealth(1);
        setDamage(tier);
        setAlive(true);
        setSpeed((int) 1.2 * tier);
        createPath();
    }

    public Balloon(int tier, int xpos, int ypos) {

        super(updateImage(tier), xpos, ypos);
        this.setTier(tier);
        setHealth(1);
        setDamage(tier);
        setAlive(true);
        setSpeed((int) 1.2 * tier);
        createPath();
    }

    // creates the path for the balloons to take
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

    /*
     * subtracts from the balloon's health until health is less than zero. Then
     * calls spawnNew()
     */
    public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) {
            spawnNew();  
        }
    }

    /*
     * Once a balloon "dies" it moves down a tier unless it is already at tier one.
     * Once it goes down a tier, the traits of the balloon are reset to the new
     * tier's values.
     */
    public void spawnNew() {
        if (getTier() > 1) {
            setTier(getTier() - 1);
            setHealth(1);
            setDamage(getTier());
            setSpeed((int) (1.2 * getTier()));
            img = getImage(updateImage(getTier())); // converted getImage to protected b/c it wasn't accessible by Balloon class (child class)
        } else {
            setAlive(false);
        }
    }

    /*
     * Switches the image of the balloon based on its tier
     */
    public static String updateImage(int tier) {
        if (tier == 3) {
            return "../resources/greenBalloon.png";
        } else if (tier == 2) {
            return "../resources/blueBalloon.png";
        } else if (tier == 1) {
            return "../resources/redBalloon.png";
        } else {
            return null;
        }

    }

    
    //checks if balloon reached the end
    public boolean isFinished() {
        return (getPath().size() == 0 || tx.getTranslateY() < -75);
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

            if (Math.abs(x1 - getX()) > getSpeed() || Math.abs(y1 - getY()) > getSpeed()) { //checks if the balloon is within a certain range away from target

                if (this.getX() > x1 && Math.abs(x1 - getX()) > getSpeed()) {
                    tx.translate(-getSpeed(), 0);
                    setX((int) tx.getTranslateX());
                } else if (this.getX() < x1 && Math.abs(x1 - getX()) > getSpeed()) {
                    //this.x += speed;
                    tx.translate(getSpeed(), 0);
                    setX((int) tx.getTranslateX());
                } else if (this.getY() > y1 && Math.abs(y1 - getY()) > getSpeed()) {
                    //vx = 0;
                    tx.translate(0, -getSpeed());
                    setY((int) tx.getTranslateY());
                } else if (this.getY() < y1 && Math.abs(y1 - getY()) > getSpeed()) {
                    //vx = 0;
                    tx.translate(0, getSpeed());
                    setY((int) tx.getTranslateY());
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
    
    public boolean isBlimp() {
		return isBlimp;
    }
    
    public void setBlimp(boolean b) {
    	isBlimp = b;
    }
}
