import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {
	
	int numBall;
	int difficulty;
	
	ArrayList<Balloon> balloons = new ArrayList<Balloon>();
	
	public Level(int num) {		
		numBall = num;
		for(int i = 0; i< numBall; i++) {
			balloons.add(new Balloon(3, -75*i, 405));
		} // (int)(Math.random()*4)
	}

	public void paintAll(Graphics g) {
		for(Balloon b: balloons) {		
			b.paint(g);
		}
	}
}
