import java.awt.Color;
import java.awt.Graphics;


public class Particle {
	public int life = 50;
	int x = 0, y = 0;
	int vx = 0, vy = -90, ay = 1;
	
	
	public void paint(Graphics g){
		g.setColor(Color.red);
		g.fillOval(x, y, 10, 10);
//		x++;
		
	}
	
	public Particle(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}
