package game;

import game.GameEffects.Dart;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
    private int r;
    private Balloon target;
    private boolean withinRange;
    private double cooldown;
    double targetAngle = 0;
    double currentAngle = 0;

    public Tower(int x, int y) {
        this.setX(x);
        this.setY(y);
        r = 0;
        setTower(new Sprite("../resources/Dart_Monkey.png", x, y));
    }

    public Tower(int x, int y, String type) {
        this.setX(x);
        this.setY(y);
        setTower(new Sprite(type, x, y));
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double d) {
        cooldown = d;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        BufferedImage resizedImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = resizedImg.createGraphics();

        g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g3.drawImage(getTower().img, 0, 0, 200, 200, null);
        g3.dispose();
        AffineTransform tx = new AffineTransform();
        tx.rotate(Math.toRadians(targetAngle + 90), getTower().getX() + 18, getTower().getY() + 24);
        tx.translate(getX() - 20, getY() - 10);
        tx.scale(1.4, 1.4);
        g2.drawImage(getTower().img, tx, null);

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

    public void update(Sprite target) {
        if (target != null) {
            double distanceX = target.getX() + 10 - this.getX();
            double distanceY = target.getY() + 10 - this.getY();
            // angle that the tower needs to be rotated to face the target
            targetAngle = Math.toDegrees(Math.atan2(distanceY, distanceX));
            getTower().repaint();
        }

    }

    //find a target that is within the attack range
    public void findTarget(ArrayList<Balloon> sprites) {
        double closestDist = 0.0;
        Balloon closestTarget = null;
        for (Balloon t : sprites) {
            double distanceX = t.getX() + 10 - this.getX();
            double distanceY = t.getY() + 10 - this.getY();
            double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) + 110;
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
        this.update(closestTarget);
        if (closestTarget != null) {
            Dart dart = new Dart(new Coordinate(getX(), getY()), new Coordinate((int) closestTarget.getX(), (int) closestTarget.getY()));
            Driver.gameEffects.add(dart);
            closestTarget.takeDamage(1);
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
