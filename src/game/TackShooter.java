package game;

import java.util.ArrayList;

public class TackShooter extends Tower{
	public TackShooter(int x, int y) {
		super(x, y, "../resources/tackShooter.png");
		setAttackRadius(200);
	}
	public void findTarget(ArrayList<Balloon> balloons) {
		if (getTarget() != null) {
			return;

		}
		double closestDist = 0.0;
		Sprite closestTarget = null;
		for (Balloon t : balloons) {
			System.out.println("detecting");

			double distanceX = t.x+ 10 - this.getX();
			double distanceY = t.y+10 - this.getY();

			double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) + 110;
			System.out.println(distance);
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
		setTarget((Balloon) closestTarget);
	}
}