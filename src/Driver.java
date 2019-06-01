import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.image.*;
import java.awt.geom.AffineTransform;

public class Driver extends JPanel implements ActionListener, KeyListener,
		MouseListener, MouseMotionListener {

	int numBalloons = 10;
	int screen_width = 1500;
	int screen_height = 1000;
	Balloon b;
	ArrayList<Balloon> bs = new ArrayList<Balloon>();
	Level one;
	
	Background bg;
	int pHealth = 100; //example
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	ArrayList<Particle> particles = new ArrayList<Particle>();
	
	//fonts
	Font font = new Font("Courier New", 1, 50);
	Font font2 = new Font("Courier New", 1, 30);
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		bg.paint(g);
		
		g.setFont(font);
		
		g.setColor(Color.RED);
		g.drawString(("Health:")+Integer.toString(pHealth), 1100, 870);
		g.setFont(font2);
		g.setColor(Color.CYAN);
	
		//paint sprite
//		if(b.isAlive) {
//			b.paint(g);
//		}
//		else {
//			deleteBalloon(b);
//		}

		//System.out.println(bs.size());
				
		for(Balloon b: bs) {
			if(b.isAlive) {
				b.paint(g);
			}
			else {
				deleteBalloon(b);
			}
//			System.out.print(b.x + " ");
		}
		System.out.println();

		g.setColor(Color.BLACK);
		for(Particle p : particles){
			p.paint(g);
		}
	
	}

	public void update() {
//		b.move();
		for(int i = 0; i < bs.size(); i++) {
			Balloon b = bs.get(i);
			b.move();
			if(b.isFinished()) {
				int damage = b.damage;
				bs.remove(i);
//				System.out.println("Num Balloons " + bs.size());
				pHealth -= damage;	
			}
		}	
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		update();
		repaint();
	}

	public static void main(String[] arg) {
		Driver d = new Driver();
	}

	public Driver() {
		JFrame f = new JFrame();
		f.setTitle("Frogger");

		f.setSize(screen_width, screen_height);
		f.setResizable(false);
		f.addKeyListener(this);
		f.addMouseMotionListener(this);
		f.addMouseListener(this);
		
		bg = new Background("hqdefault.jpg");
		
		//sprite instantiation
		b = new Balloon(3);
		b.addMouseListener(this);
			
		for(int i = 0; i< numBalloons; i++) {
			int r = (int)(Math.random()* 3) + 1;
			System.out.println(r);
			bs.add(new Balloon(r, -75*i, 405));
			bs.get(i).addMouseListener(this);
		}
		
		//particles
		particles.add(new Particle(50,50));

	
		f.add(this);
		
		//drawing timer
		t = new Timer(17, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	Timer t;

	public void deleteBalloon(Balloon b) {
		b = null;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

		System.out.println("key press "+e.getKeyCode());
		if(e.getKeyCode()==38){
			//up
			//b.moveTo(0,0);
			if(bs.size() != 0) {
				bs.get(0).takeDamage(1);
				if(!bs.get(0).isAlive) {
					bs.remove(0);
				}
			}
//			b.takeDamage(1);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//System.out.println("key press "+e.getKeyCode());
		if(e.getKeyCode()==38){
			//up
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX() + " "+ e.getY());
		pressed = true;
		if(e.getComponent().getClass()==Sprite.class){
			System.out.println("clicked on a sprite");
		}
	
	}
	
	boolean pressed = false;
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public void reset() {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("WAY");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		pressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		particles.add(new Particle(e.getX()-5,e.getY()-5));

	}

	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
