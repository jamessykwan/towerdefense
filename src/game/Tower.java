package game;

import game.GameEffects.Dart;

import java.awt.*;
import java.util.ArrayList;

abstract class Tower {

    private int damage;
    private int cost;
    private Sprite tower;
    private double timeSinceLastFire;// time since last effect was fired
    private int anchorX; // shifts X coordinate
    private int anchorY; // shifts Y coordinate
    private Point position;
    private double attackRadius;
    private int x;
    private int y;
    private Balloon target;
    private boolean withinRange;

    public Tower(int x, int y) {
        this.setX(x);
        this.setY(y);
        setTower(new Sprite("../resources/weirdPixelMonkey.png", x, y));
    }
    public Tower(int x, int y, String type) {
        this.setX(x);
        this.setY(y);
        setTower(new Sprite(type, x, y));
    }
    public void paint(Graphics g) {
        // g.drawImage(tower.img, (int) position.getX() + anchorX, (int) position.getY()
        // + anchorY, null);
        getTower().paint(g);
    }


    public void setPosition(Point p) {
        position = p;
    }

    public void setTarget(Balloon closestTarget) {
        this.target = closestTarget;
    }

    public boolean hitsTarget() {
        return true;
    }

    public boolean withinRange() {
        return true;
    }

    public void findTarget(ArrayList<Balloon> sprites) {
        if (getTarget() != null) {
            return;
        }
        double closestDist = 0.0;
        Balloon closestTarget = null;
        for (Balloon t : sprites) {
            double distanceX = t.x + 10 - this.getX();
            double distanceY = t.y + 10 - this.getY();
            double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) + 110;
            //System.out.println(distance);
            if (Double.compare(distance, getAttackRadius()) > 0) {
                continue;
            }
            if (closestTarget == null) {
                closestDist = distance;
                closestTarget = t;
            } else if (Double.compare(distance, closestDist) < 0) {
                closestDist = distance;
                closestTarget = t;
            }
        }
        setTarget(closestTarget);
        if (closestTarget != null) {
            Dart dart = new Dart(new Coordinate(getX(), getY()), new Coordinate((int) closestTarget.x, (int) closestTarget.y));
            Driver.gameEffects.add(dart);
            closestTarget.takeDamage(1);
            setTarget(null);
        }
    }

    public void attack(Sprite target) {
    }

    public Sprite getTarget() {
        return target;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Sprite getTower() {
        return tower;
    }

    public void setTower(Sprite tower) {
        this.tower = tower;
    }

    public double getTimeSinceLastFire() {
        return timeSinceLastFire;
    }

    public void setTimeSinceLastFire(double timeSinceLastFire) {
        this.timeSinceLastFire = timeSinceLastFire;
    }

    public int getAnchorX() {
        return anchorX;
    }

    public void setAnchorX(int anchorX) {
        this.anchorX = anchorX;
    }

    public int getAnchorY() {
        return anchorY;
    }

    public void setAnchorY(int anchorY) {
        this.anchorY = anchorY;
    }

    public Point getPosition() {
        return position;
    }

    public double getAttackRadius() {
        return attackRadius;
    }

    public void setAttackRadius(double attackRadius) {
        this.attackRadius = attackRadius;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWithinRange() {
        return withinRange;
    }

    public void setWithinRange(boolean withinRange) {
        this.withinRange = withinRange;
    }
}
