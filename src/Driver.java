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

	int screen_width = 1500;
	int screen_height = 1000;
	Balloon b;

	Background bg;
	int my_variable = 0; //example
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
		g.drawString(("my_variable:")+Integer.toString(my_variable), 0, 870);
		g.setFont(font2);
		g.setColor(Color.CYAN);
	
		//paint sprite
		b.paint(g);
	
		
		g.setColor(Color.BLACK);
		for(Particle p : particles){
			p.paint(g);
			
		}
		
		
		
	
	}


	public void update() {
		b.move();
		
		//System.out.println("move");
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

	@Override
	public void keyPressed(KeyEvent e) {

		System.out.println("key press "+e.getKeyCode());
		if(e.getKeyCode()==38){
			//up
			//b.moveTo(0,0);
			b.takeDamage(1);

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
			b.deletePath();
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
