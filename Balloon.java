import javax.swing.JLabel;

public class Balloon {
	private int speed, health;
	private int x,y;
	private String direction;
	private boolean isAlive;
	private JLabel img;
	private int type;
	
	public Balloon(int category, int startx, int starty, String point) {
		type=category;
		x=startx;
		y=starty;
		direction=point;
		isAlive=true;
		if(type==0) {
			health=1;
			speed=10;
		}
		if(type==0) {
			health=2;
			speed=12;
		}
	}
	public void changeDirection(String point){
		direction=point;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public JLabel getImg() {
		return img;
	}
	public void setImg(JLabel img) {
		this.img = img;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
