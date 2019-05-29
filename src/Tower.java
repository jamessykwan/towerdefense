import java.awt.*;
import java.util.ArrayList;

abstract class Tower {

	public int damage;
	public int cost;
	public Sprite tower;
	protected double timeSinceLastFire;// time since last effect was fired
	private int anchorX; // shifts X coordinate
	private int anchorY; // shifts Y coordinate
	private Point position;
	public double attackRadius;
	public int x;
	public int y;
	private Sprite target;
	private boolean withinRange;

	public Tower(int x, int y) {
		this.x = x;
		this.y = y;
		tower = new Sprite("weirdPixelMonkey.png", x, y);
	}

	public void paint(Graphics g) {
		// g.drawImage(tower.img, (int) position.getX() + anchorX, (int) position.getY()
		// + anchorY, null);
		tower.paint(g);

	}

	public void setPosition(Point p) {
		position = p;
	}

	public void setTarget(Balloon target) {
		this.target = target;
	}

	public boolean hitsTarget() {
		return true;
	}

	public boolean withinRange() {
		return true;
	}

	public void findTarget(ArrayList<Balloon> balloons) {
		if (getTarget() != null) {
			return;

		}
		double closestDist = 0.0;
		Sprite closestTarget = null;
		for (Sprite t : balloons) {
			double distanceX = target.x - this.x;
			double distanceY = target.y - this.y;

			double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
			if (Double.compare(distance, attackRadius) > 0) {
				balloons.remove(t);
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
	}

	public void attack(Sprite target) {
		
	}

	public Sprite getTarget() {
		return target;
	}

	public void setTarget(Sprite t) {
		target = t;
	}

}
