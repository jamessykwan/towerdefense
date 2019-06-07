package game.GameEffects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.Balloon;
import game.Coordinate;
import game.GameEffect;
import game.Sprite;

public class Dart extends GameEffect {

    public Dart(Coordinate pos, Coordinate targetPos) {
        setEffect(new Sprite("../resources/dart1.png", pos.getX(), pos.getY()));
        setX(pos.getX());
        setY(pos.getY());
        setVelX((targetPos.getX() - pos.getX()));
        setVelY((targetPos.getY() - pos.getY()));
        setAge(0);
        currentAngle = 0;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // example resizing image on call to paint
        BufferedImage resizedImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = resizedImg.createGraphics();

        g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g3.drawImage(getEffect().img, 0, 0, 10, 10, null);
        g3.dispose();

        // example rotating image on call to paint
        //getEffect().tx.rotate(1, 50, 50);
        g2.drawImage(resizedImg, getEffect().tx, null);

    }

    public void move(ArrayList<Balloon> enemies) {
        setAge(getAge() + .09);
        effect.tx.translate(getVelX() / 25, getVelY() / 25);
        effect.setX(effect.tx.getTranslateX());
        effect.setY(effect.tx.getTranslateY());
        // System.out.println("x: " + effect.x + " y: " + effect.y + " velX: " + velX +
        // " velY: " + velY);
        for (Balloon enemy : enemies) {
            double distanceX = enemy.getX() + 10 - x;
            double distanceY = enemy.getY() + 10 - y;
            double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
            // System.out.println(distance);
            if (Double.compare(distance, 40) < 0) {
                //enemy.takeDamage(1);
                isDone = true;
            }
        }
    }

}
