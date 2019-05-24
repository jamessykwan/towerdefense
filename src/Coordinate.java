
public class Coordinate {
	
	int x;
	int y;
	
	public Coordinate(int a, int b) {
		x = a;
		y = b;
	}
	
	public boolean compare(Coordinate c) {
		return (x == c.x && y == c.y);
	}
	
}
