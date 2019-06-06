package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
	int towerType = 0;
	int numBalloons = 10;
	int screen_width = 1500;
	int screen_height = 1000;
	String selected="";
	Balloon b;
	ArrayList<Balloon> bs = new ArrayList<Balloon>();
	double[] rarity = { 1, 0, 0 };
	double start;
	Background bg;
	int level = 1;

	int pHealth = 100; // example
	int WaveNumber=1;
	int Money = 500; // example

	private Sprite dartTowerSelector;
	private Sprite tackShooterSelector;

	private ArrayList<Particle> particles = new ArrayList<>();
	private static ArrayList<Tower> towers = new ArrayList<>();
	private static ArrayList<Sprite> balloons = new ArrayList<>();
	private static ArrayList<Balloon> attackedBalloons = new ArrayList<>();
	public static ArrayList<GameEffect> gameEffects = new ArrayList<>();

	private boolean placingTower;
	private boolean pressed = false;
	private int mouseX;
	private int mouseY;
	Tower tempTower;
	private double startTime;

	// fonts
	private Font font = new Font("Courier New", Font.BOLD, 50);
	private Font font2 = new Font("Courier New", Font.BOLD, 30);

	public void paint(Graphics g) {
		super.paintComponent(g);
		bg.paint(g);
		dartTowerSelector.paint(g);
		tackShooterSelector.paint(g);
		g.setFont(font);

		g.setColor(Color.RED);
		g.drawString(("Health:") + pHealth, 1100, 870);
		g.drawString("Money:"+Money, 1100, 910);
		g.drawString("Wave: "+WaveNumber, 1100, 50);
		g.drawString(selected, 1100, 950);
		g.setFont(font2);
		g.setColor(Color.CYAN);

//		if(b.isAlive) {
//			b.paint(g);
//		}
//		else {
//			deleteBalloon(b);
//		}

		//
		// System.out.println(bs.size());

		for (Balloon b : bs) {
			if (b.isAlive()) {
				b.paint(g);
			}

//			System.out.print(b.x + " ");
		}
		// System.out.println();

		g.setFont(font2);
		g.setColor(Color.CYAN);

		// paint sprite
		// b.paint(g);

		g.setColor(Color.BLACK);

		for (Sprite b : bs) {
			g.drawOval((int) b.x, (int) b.y, 30, 30);
		}
		for (Tower t : towers) {
			t.paint(g);
			g.drawOval(t.getX(), t.getY(), 30, 30);
			// for debugging
			g.drawOval(t.getX() - 120, t.getY() + -100, (int) t.getAttackRadius(), (int) t.getAttackRadius());
		}
		for (GameEffect gameEffect : gameEffects) {
			gameEffect.paint(g);
		}

	}

	private void update() {
		double currentTime = System.currentTimeMillis() - startTime;
		/*
		 * if (!attackedBalloons.isEmpty()) { for(int i = 0; i< attackedBalloons.size();
		 * i++) { attackedBalloons.get(i).takeDamage(1);
		 * if(!attackedBalloons.get(i).isAlive) { target= null;
		 * bs.remove(attackedBalloons.get(i));
		 * attackedBalloons.remove(attackedBalloons.get(i)); } } }
		 *
		 */
		placeTower();
		if (currentTime % 10 == 0) {
			for (Tower t : towers) {

				t.findTarget(bs);
				// attackedBalloons.add((Balloon) target);
			}
		}
		Iterator<GameEffect> iter = gameEffects.iterator();
		while (iter.hasNext()) {
			GameEffect effect = iter.next();
			effect.move(bs);
			if (effect.isDone()) {
				iter.remove();
				// System.out.println("removed");
			}
		}
		/*
		 * for (GameEffect gameEffect : gameEffects) { gameEffect.move();
		 * 
		 * }
		 */

		for (int i = 0; i < bs.size(); i++) {
			Balloon b = bs.get(i);
			b.move();
			if (!b.isAlive()) {
				bs.remove(b);
				attackedBalloons.remove(b);
				Money+=2;
			}
			if (b.isFinished()) {
				int damage = b.getDamage();
				bs.remove(i);
				i--;
//				System.out.println("Num Balloons " + bs.size());
				pHealth -= damage;
			}
		}

		if (bs.size() == 0) {
			level++;
			newLevel(level);
			Money+=200-pHealth;
			WaveNumber+=1;
		}

//        if(System.currentTimeMillis() - start > 1000) {
//            int r = (int) (Math.random() * 3) + 1;
//        	bs.add(new Balloon(r, 0, 405));
//        	start = System.currentTimeMillis();
//        }
		// System.out.println("move");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Frogger");

		int screen_height = 1000;
		int screen_width = 1500;
		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseListener(this);
		placingTower = false;
		bg = new Background("../resources/hqdefault.jpg", 0, 0);
		dartTowerSelector = new Sprite("../resources/weirdPixelMonkey.png", 1200, 100);
		tackShooterSelector = new Sprite("../resources/tackShooter.png", 1200, 700);
		// sprite instantiation
		b = new Balloon(3);
		b.addMouseListener(this);

//        for (int i = 0; i < numBalloons; i++) { // fills bs with random balloons
//            int r = (int) (Math.random() * 3) + 1;
//            //System.out.println(r);
//            bs.add(new Balloon(r, -75 * i, 405));
//            bs.get(i).addMouseListener(this);
//        }
		newLevel(level);

		// particles
		particles.add(new Particle(50, 50));
		balloons.add(b);

		f.add(this);

		// drawing timer
		Timer t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		start = System.currentTimeMillis();
	}

	public void newLevel(int l) {
		for (int i = 0; i < numBalloons; i++) {
			double r = Math.random();
			if (r < rarity[0]) {
				bs.add(new Balloon(1, -75 * i, 405));
			} else if (r < rarity[0] + rarity[1]) {
				bs.add(new Balloon(2, -75 * i, 405));
			} else if (r < rarity[0] + rarity[1] + rarity[2]) {
				bs.add(new Balloon(3, -75 * i, 405));
			}
		}

		numBalloons += 5;
		rarity[0] -= 0.075;
		rarity[1] += 0.05;
		rarity[2] += 0.025;
	}

	private void placeTower() {
		if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 125 && mouseY < 213 && isValid(400,Money)) {
			placingTower = true;
			towerType = 1;
			selected="Dart Monkey";
			//System.out.println(selected);
		}
		if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 650 && mouseY < 850 && isValid(500,Money)) {
			placingTower = true;
			towerType = 2;
			// System.out.println(placingTower);
			selected="Tack Shooter";
		}
		if (pressed && placingTower && mouseX < 1100 && towerType == 1) {
			DartTower tower = new DartTower(mouseX - 20, mouseY - 75);
			towers.add(tower);
			placingTower = false;
			towerType = 0;
			Money-=400;
			selected="";
		}
		if (pressed && placingTower && mouseX < 1100 && towerType == 2) {
			TackShooter tower = new TackShooter(mouseX - 20, mouseY - 75);
			towers.add(tower);
			placingTower = false;
			towerType = 0;
			Money-=500;
			selected="";
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		// System.out.println("key press " + e.getKeyCode());
		if (e.getKeyCode() == 38) {
			// up
			// b.moveTo(0,0);
			if (bs.size() != 0) {
				bs.get(0).takeDamage(1);
				if (!bs.get(0).isAlive()) {
					bs.remove(0);

				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		// System.out.println("key press "+e.getKeyCode());
		if (e.getKeyCode() == 38) {
			// up

			// System.out.println("key press "+e.getKeyCode());
			if (e.getKeyCode() == 38) {
				// up
				b.deletePath();

			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println(e.getX() + " " + e.getY());
		pressed = false;
		e.getComponent();// System.out.println("clicked on a sprite");

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// System.out.println("WAY");
		mouseX = e.getX();
		mouseY = e.getY();
		pressed = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		pressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		pressed = false;

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		// TODO Auto-generated method stub

	}
	public boolean isValid(int price, int money) {
		if(price>money)return false;
		return true;
	}

}
