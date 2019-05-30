import java.awt.*;

abstract class Tower {

    public int damage;
    public int cost;
    public Sprite tower;
    protected double timeSinceLastFire;// time since last effect was fired
    private int anchorX;            // shifts X coordinate
    private int anchorY;            // shifts Y coordinate
    private Point position;


    public Tower() {
        tower = new Sprite("weirdPixelMonkey.png");
    }

    public void draw(Graphics g) {
        //g.drawImage(tower.img, (int) position.getX() + anchorX, (int) position.getY() + anchorY, null);
        tower.paint(g, position);
        System.out.println("x: " + position.getX() + " y: " + position.getY());
    }

    public void setPosition(Point p) {
        position = p;
    }

    abstract void interact(Game game, double deltaTime);

}
