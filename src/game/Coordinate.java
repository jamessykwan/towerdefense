package game;

public class Coordinate {

    int x;
    int y;

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

    public Coordinate(int a, int b) {
        x = a;
        y = b;
    }

    public boolean compare(Coordinate c) {
        return (x == c.x && y == c.y);
    }

}
