package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Driver extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	int towerType = 0;
	int numBalloons = 10;
	int screen_width = 1500;
	int screen_height = 1000;
	String selected = "";

	ArrayList<Balloon> bs = new ArrayList<Balloon>();
	ArrayList<Blimp> bigBS = new ArrayList<Blimp>();
	double[] rarity = {0, 0, 0, 0, 0};
	double start;
	Background bg;
	int level = 1;
	Sprite cursorTracker = new Sprite("../resources/Dart_Monkey.png", 1500, 100);

	int pHealth = 100; // example
	int WaveNumber = 0;
	int Money = 300; // example

	private Sprite dartTowerSelector;
	private Sprite tackShooterSelector;
	private Sprite superMonkeySelector;
	private Sprite sniperMonkeySelector;
	private static ArrayList<Tower> towers = new ArrayList<>();
	private static ArrayList<Balloon> attackedBalloons = new ArrayList<>();
	public static ArrayList<GameEffect> gameEffects = new ArrayList<>();

	private boolean placingTower;
	private boolean pressed = false;
	private int mouseX;
	private int mouseY;
	Tower tempTower;
	private double startTime;
	private double currentTime;

	// fonts
	private Font font = new Font("Courier New", Font.BOLD, 50);
	private Font font2 = new Font("Courier New", Font.BOLD, 30);
	private Font font3 = new Font("Courier New", Font.BOLD, 75);
	private Font font4 = new Font("Courier New", Font.BOLD, 25);

	public void paint(Graphics g) {
		super.paintComponent(g);
		bg.paint(g);
		dartTowerSelector.paint(g);
		tackShooterSelector.paint(g);
		superMonkeySelector.paint(g);
		sniperMonkeySelector.paint(g);
		cursorTracker.paint(g);
		g.setFont(font);

		g.setColor(Color.RED);
		g.drawString(("Health:") + pHealth, 1100, 870);
		g.drawString("Money:$" + Money, 1100, 910);
		g.drawString("Wave: " + WaveNumber, 1100, 50);
		g.drawString(selected, 1000, 950);
		g.setFont(font2);
		g.setColor(Color.BLACK);
		g.drawString("$100", 1350, 150);
		g.drawString("$500", 1350, 250);
		g.drawString("$2000", 1350, 350);
		g.drawString("$150", 1350, 450);
		if(pHealth <= 0) {
			g.setFont(font3);
			g.drawString("Game Over", 1050, 600);
			g.setFont(font4);
			g.drawString("press space to start again", 1050, 750);
		}


		for (Balloon b : bs) {
			if (b.isAlive()) {
				b.paint(g);
			}
		}

		g.setFont(font2);
		g.setColor(Color.CYAN);

		g.setColor(Color.BLACK);

		for (Sprite b : bs) {
			//g.drawOval((int) b.getX(), (int) b.getY(), 30, 30);
		}
		for (Tower t : towers) {
			t.paint(g);
			//g.drawOval(t.getX(), t.getY(), 30, 30);
			// for debugging
			if (t.getCost() == 100 || t.getCost() == 500) {
			//	g.drawOval(t.getX() - 120, t.getY() + -100, (int) t.getAttackRadius(), (int) t.getAttackRadius());
			}
			if (t.getCost() == 2000) {
		//		g.drawOval(t.getX() - 350, t.getY() + -350, (int) t.getAttackRadius(), (int) t.getAttackRadius());
			}
		}
		for (GameEffect gameEffect : gameEffects) {
			gameEffect.paint(g);
		}

	}

	private void update() {

		if(pHealth <= 0) {

		}
		else {
			if (placingTower) {
				cursorTracker.setx(mouseX - 20);
				cursorTracker.sety(mouseY - 75);
			} else {
				cursorTracker.setx(5000);
			}
			placeTower();
			for (Tower t : towers) {
				if (t.getCost() >= 2000 && t.getCooldown() <= 0) {
					t.setCooldown(10);
					t.findTarget(bs);
					t.update(t.getTarget());
				} else if (t.getCooldown() <= 0 && t.getCost() == 150) {
					t.findTarget(bs);
					t.update(t.getTarget());
					t.setCooldown(500);
				} else if (t.getCooldown() <= 0) {
					t.findTarget(bs);
					t.update(t.getTarget());
					t.setCooldown(30);

				} else if (t.getCooldown()<=0&&t.getCost()==500) {
					t.findTarget(bs);
					t.update(t.getTarget());
					t.setCooldown(90);
				}
				else {
					t.setCooldown(t.getCooldown() - (System.currentTimeMillis() - currentTime) / 10);
				}
			}

			Iterator<GameEffect> iter = gameEffects.iterator();
			while (iter.hasNext()) {
				GameEffect effect = iter.next();
				effect.move(bs);
				if (effect.isDone()) {
					iter.remove();
				}
			}

			for (int i = 0; i < bs.size(); i++) {
				Balloon b;
				if (bs.get(i).getClass() == Balloon.class) {
					b = bs.get(i);
					if (!b.isAlive()) {
						bs.remove(b);
						attackedBalloons.remove(b);
						Money += 2;
					}
				} else {
					b = (Blimp) bs.get(i);
					if (!b.isAlive()) {
						newBalloon((Blimp) b);
						bs.remove(b);
						attackedBalloons.remove(b);
						Money += 40;
					}

				}
				b.move();
				if (b.isFinished()) {
					int damage = b.getDamage();
					bs.remove(i);
					i--;
					pHealth -= damage;
				}
			}

			if (bs.size() == 0) {
				level++;
				newLevel(level);
				Money += 150 - pHealth;
				WaveNumber += 1;
			}

			currentTime = System.currentTimeMillis() - startTime;
		}
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
		dartTowerSelector = new Sprite("../resources/Dart_Monkey.png", 1200, 100);
		tackShooterSelector = new Sprite("../resources/Tack_Shooter.png", 1200, 200);
		superMonkeySelector = new Sprite("../resources/Super_Monkey.png", 1200, 300);
		sniperMonkeySelector = new Sprite("../resources/Sniper_Monkey.png", 1200, 400);

		newLevel(level);
		f.add(this);

		// drawing timer
		Timer t = new Timer(5, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		start = System.currentTimeMillis();
	}

	// once a blimp balloon pops, it spawns in several new ones
	public void newBalloon(Blimp b) {
		for (int i = 0; i < b.getTier() * 2; i++) {
			bs.add(new Balloon(3, b.getx(), b.gety()));
			bs.get(bs.size() - 1).setSpeed(0);
			ArrayList<Coordinate> newPath = new ArrayList<Coordinate>();
			for (Coordinate c : b.getPath()) {
				newPath.add(c);
			}
			bs.get(bs.size() - 1).setPath(newPath);
			bs.get(bs.size() - 1).setSpeed((int) (1.2 * bs.get(bs.size() - 1).getTier()));
		}
	}

	// a wait method in milliseconds
	public void delay(int mill) {
		double now = System.currentTimeMillis();
		while (System.currentTimeMillis() - now < mill) {

		}
	}

	// creates "waves of balloons"
	public void newLevel(int l) {
		for (int i = 0; i < numBalloons; i++) {
			double r = Math.random(); // generates random number
			double total = 0;
			for (int j = 0; j < rarity.length; j++) {
				if(rarity[j] > 0) {
					total += rarity[j];
				}
			}

			double percent = 0; // weighted percent (kind of)
			for (int j = 0; j < rarity.length; j++) { // finds which percentile the number lands in
				percent += rarity[j]/total;
				if (r < percent && j < 3) { // if it is a balloon
					bs.add(new Balloon(j + 1, -75 * i, 405)); // chooses balloon based on that percentile range
					j += rarity.length;
				} else if (r < percent) { // if it is a blimp
					bs.add(new Blimp(j - 2, -75 * i, 405));
				}
			}
		}

		numBalloons += 3; // updates number of balloons
		rarity[0] = 1*level;
		rarity[1] = 1.5*level - (3 * 1.5); // wont show up until ~level 3
		rarity[2] = 1.3*level - (5 * 1.3); // wont show up until ~level 5
		rarity[3] = 1.3*level - (10 * 1.3); // wont show up until ~level 10
		rarity[4] = 1.2*level - (15 * 1.2); // wont show up until ~level 15

	}


	private void placeTower() {
		if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 125 && mouseY < 225) {
			placingTower = true;
			towerType = 1;
			selected = "Dart Monkey 100";
			cursorTracker = new Sprite("../resources/Dart_Monkey.png", mouseX - 20, mouseY - 75);
		}

		if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 225 && mouseY < 325) {
			placingTower = true;
			towerType = 2;
			selected = "Tack Shooter 500";
			cursorTracker = new Sprite("../resources/Tack_Shooter.png", mouseX - 20, mouseY - 75);
		}
		if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 325 && mouseY < 425) {
			placingTower = true;
			towerType = 3;
			selected = "Super Monkey 2000";
			cursorTracker = new Sprite("../resources/Super_Monkey.png", mouseX - 20, mouseY - 75);
		}
		if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 425 && mouseY < 525) {
			placingTower = true;
			towerType = 4;
			selected = "Sniper Monkey 150";
			cursorTracker = new Sprite("../resources/Sniper_Monkey.png", mouseX - 20, mouseY - 75);
		}

		if (pressed && placingTower && mouseX < 1100 && towerType == 1) {
			if (isValid(100, Money)) {
				DartTower tower = new DartTower(mouseX - 20, mouseY - 75);
				towers.add(tower);
				placingTower = false;
				towerType = 0;
				Money -= 100;
				selected = "";
			} else {
				placingTower = false;
				towerType = 0;
				selected = "Not Enough Money";
			}
		}
		if (pressed && placingTower && mouseX < 1100 && towerType == 2) {
			if (isValid(500, Money)) {
				TackShooter tower = new TackShooter(mouseX - 20, mouseY - 75);
				towers.add(tower);
				placingTower = false;
				towerType = 0;
				Money -= 500;
				selected = "";
			} else {
				placingTower = false;
				towerType = 0;
				selected = "Not Enough Money";
			}
		}
		if (pressed && placingTower && mouseX < 1100 && towerType == 3) {
			if (isValid(2000, Money)) {
				SuperMonkey tower = new SuperMonkey(mouseX - 20, mouseY - 75);
				towers.add(tower);
				placingTower = false;
				towerType = 0;
				Money -= 2000;
				selected = "";
			} else {
				placingTower = false;
				towerType = 0;
				selected = "Not Enough Money";
			}
		}
		if (pressed && placingTower && mouseX < 1100 && towerType == 4) {
			if (isValid(150, Money)) {
				SniperMonkey tower = new SniperMonkey(mouseX - 20, mouseY - 75);
				towers.add(tower);
				placingTower = false;
				towerType = 0;
				Money -= 150;
				selected = "";
			} else {
				placingTower = false;
				towerType = 0;
				selected = "Not Enough Money";
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 32) {
			pHealth = 100;
			attackedBalloons = new ArrayList<Balloon>();
			bs = new ArrayList<Balloon>();
			towers = new ArrayList<Tower>();
			gameEffects = new ArrayList<GameEffect>();
			level = 1;
			for(double i: rarity) {
				i = 0;
			}
			numBalloons = 10;
			towerType = 0;
			selected = "";
			start = System.currentTimeMillis();
			WaveNumber = 0;
			Money = 300;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

		if (e.getKeyCode() == 38) {
			pHealth -= 100;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		pressed = false;

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
		if (price > money)
			return false;
		return true;
	}

}
