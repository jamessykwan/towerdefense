package game;

import java.util.ArrayList;

import game.GameEffects.Dart;
import game.GameEffects.Tack;

public class SniperMonkey extends Tower{
	public SniperMonkey(int x, int y, String type) {
        super(x, y, type);
        // TODO Auto-generated constructor stub
        super.setCost(150);
    }

    public SniperMonkey(int i, int j) {
        // TODO Auto-generated constructor stub
        super(i, j, "../resources/Sniper_Monkey.png");
        setAttackRadius(5000);
        super.setCost(150);
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
    public void findTarget(ArrayList<Balloon> sprites) {
        /*if (getTarget() != null) {
            return;
        }

         */
        double closestDist = 0.0;
        Balloon closestTarget = null;
        for (Balloon t : sprites) {
            double distanceX = t.getX() + 10 - this.getX();
            double distanceY = t.getY() + 10 - this.getY();
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
        this.update(closestTarget);
        if (closestTarget != null) {
            //Dart dart = new Dart(new Coordinate(getX(), getY()), new Coordinate((int) closestTarget.getX(), (int) closestTarget.getY()));
            //Driver.gameEffects.add(dart);
            closestTarget.takeDamage(10);
            //setTarget(null);
        }
    }
}
