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

<<<<<<< HEAD
    public void draw(Graphics g) {
        //g.drawImage(tower.img, (int) position.getX() + anchorX, (int) position.getY() + anchorY, null);
        tower.paint(g);
        System.out.println("x: " + position.getX() + " y: " + position.getY());
    }
=======
	}
>>>>>>> master

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
		for (Balloon t : balloons) {
			System.out.println("detecting");

			double distanceX = t.x+ 10 - this.x;
			double distanceY = t.y+10 - this.y;

			double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2)) + 110;
			System.out.println(distance);
			if (Double.compare(distance, attackRadius) > 0) {
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
