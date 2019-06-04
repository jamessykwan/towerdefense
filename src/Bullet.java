
public class Bullet extends Sprite {

	public int x;
	public int y;
	double distanceX;
	double distanceY;
	double sep;

	public Bullet(String filename) {
		super(filename);
		// TODO Auto-generated constructor stub
	}

    public void move(Sprite target) {
		distanceX = target.x - this.x;
		distanceY = target.y - this.y;
	}

}
