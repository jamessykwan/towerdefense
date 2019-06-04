import java.awt.*;
import java.util.ArrayList;

public class Level {
	
	int numBall;
	int difficulty;

	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	public Level(int num) {		
		numBall = num;
		for(int i = 0; i< numBall; i++) {
			sprites.add(new Balloon(3, -75 * i, 405));
		} // (int)(Math.random()*4)
	}

	public void paintAll(Graphics g) {
		for (Sprite b : sprites) {
			b.paint(g);
		}
	}
}
