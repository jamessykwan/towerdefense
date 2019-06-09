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

public class Tack extends GameEffect {

    public Tack(Coordinate pos, Coordinate targetPos) {
        setEffect(new Sprite("../resources/dart1.png", pos.getX(), pos.getY()));
        setX(pos.getX());
        setY(pos.getY());
        setVelX((targetPos.getX() - pos.getX()));
        setVelY((targetPos.getY() - pos.getY()));
        setAge(0);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // example resizing image on call to paint
        BufferedImage resizedImg = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g3 = resizedImg.createGraphics();

        g3.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g3.drawImage(getEffect().img, 0, 0, 5, 5, null);
        g3.dispose();

        // example rotating image on call to paint
        //getEffect().tx.rotate(1, 50, 50);
        g2.drawImage(resizedImg, getEffect().tx, null);

    }

    public void move(ArrayList<Balloon> enemies) {
        age += .09;
        effect.tx.translate(velX / 50, velY / 50);
        effect.setX(effect.tx.getTranslateX());
        effect.setY(effect.tx.getTranslateY());

        for (Balloon enemy : enemies) {
            double distanceX = enemy.getX() + 10 - this.getEffect().tx.getTranslateX();
            double distanceY = enemy.getY() + 10 - this.getEffect().tx.getTranslateY();
            double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
            if (Double.compare(distance, 40) < 0) {
                enemy.takeDamage(1);
                isDone = true;
            }
        }
    }
}
