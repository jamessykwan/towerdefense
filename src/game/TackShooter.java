package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import game.GameEffects.Dart;
import game.GameEffects.Tack;

public class TackShooter extends Tower {
    public TackShooter(int x, int y) {
        super(x, y, "../resources/Tack_Shooter.png");
        setAttackRadius(300);
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(getTower().img, (int) getX(), (int) getY(), 100, 100, null);
        g2.dispose();
    }


    public void findTarget(ArrayList<Balloon> sprites) {
        if (getTarget() != null) {
            return;
        }
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
        if (closestTarget != null) {
            for (int i = 0; i < 8; i++) {
                Tack tack = new Tack(new Coordinate(getX() + 50, getY() + 50), new Coordinate((int) (getX() + getAttackRadius() * Math.cos(.785 * i)), (int) (getY() + getAttackRadius() * Math.sin(.785 * i))));
                Driver.gameEffects.add(tack);
                setTarget(null);
            }
        }
    }

}