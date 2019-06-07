package game;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameEffect {

    protected Sprite effect;
    protected int x;
    protected int y;
    protected double velX;
    protected double velY;
    protected double age;
    protected boolean isDone;
    protected double currentAngle;

    public Sprite getEffect() {
        return effect;
    }

    public void setEffect(Sprite effect) {
        this.effect = effect;
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

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void move(ArrayList<Balloon> enemies) {
        age += .09;
        effect.tx.translate(velX / 25, velY / 25);
        effect.setX(effect.tx.getTranslateX());
        effect.setY(effect.tx.getTranslateY());
        // System.out.println("x: " + effect.x + " y: " + effect.y + " velX: " + velX +
        // " velY: " + velY);
        for (Balloon enemy : enemies) {
            double distanceX = enemy.getX() + 10 - x;
            double distanceY = enemy.getY() + 10 - y;
            double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
            // System.out.println(distance);
            if (Double.compare(distance, 60) < 0) {
                enemy.takeDamage(1);
                isDone = true;
            }
        }
    }

    public void paint(Graphics g) {
        effect.paint(g);
    }

    public boolean isDone() {
        return isDone || age >= 1;
    }
}
