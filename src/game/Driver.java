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

    int numBalloons = 1;
    int screen_width = 1500;
    int screen_height = 1000;
    ArrayList<Balloon> bs = new ArrayList<Balloon>();
    ArrayList<Blimp> bigBS = new ArrayList<Blimp>();
    double[] rarity = {0, 0, 0, 0, 1};
    double start;
    Background bg;
    int level = 1;

    int pHealth = 100; //example

    int my_variable = 0; // example

    
    private Sprite dartTowerSelector;

    private ArrayList<Particle> particles = new ArrayList<>();
    private static ArrayList<DartTower> towers = new ArrayList<>();
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
        g.setFont(font);

        g.setColor(Color.RED);
        g.drawString(("Health:") + pHealth, 1100, 870);
        g.setFont(font2);
        g.setColor(Color.CYAN);

        for (Balloon b : bs) {
            if (b.isAlive()) {
                b.paint(g);
            }
        }

        g.setFont(font2);
        g.setColor(Color.CYAN);

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
            effect.move();
            if (effect.isDone()) {
                iter.remove();
                //System.out.println("removed");
            }
        }
       /* for (GameEffect gameEffect : gameEffects) {
            gameEffect.move();

        }
        */
       
        for (int i = 0; i < bs.size(); i++) {
            Balloon b;
            if(bs.get(i).getClass() == Balloon.class) {
            	b = bs.get(i);
            	if (!b.isAlive()) {
                	bs.remove(b);
                    attackedBalloons.remove(b);
                }
            } else {
            	b = (Blimp) bs.get(i);
        		if (!b.isAlive()) {
                	newBalloon((Blimp) b);
                	bs.remove(b);
                    attackedBalloons.remove(b);
                }
            }
            b.move();
            
            System.out.println();
            if (b.isFinished()) {
                int damage = b.getDamage();
                bs.remove(i);
                i--;
                pHealth -= damage;
            }
        }

        if(bs.size()==0) {
        	level++;
        	newLevel(level);
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
        dartTowerSelector = new Sprite("../resources/weirdPixelMonkey.png", 1200, 100);

        // sprite instantiation
        newLevel(level);
        
        // particles
        particles.add(new Particle(50, 50));

        f.add(this);

        // drawing timer
        Timer t = new Timer(17, this);
        t.start();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        start = System.currentTimeMillis();
    }
    
    
    // once a blimp balloon pops, it spawns in several new ones
    public void newBalloon(Blimp b) { 
    	System.out.println("spawned new");
    	for(int i = 0; i < b.getTier() * 2; i++) {
    		bs.add(new Balloon(3, b.getx(), b.gety()));
    	}
    }
    
    // a wait method in milliseconds
    public void delay(int mill) {
    	double now = System.currentTimeMillis();
		while(System.currentTimeMillis() - now < mill) {
			
		}
    }
    
    //creates "waves of balloons"
    public void newLevel(int l){
    	for(int i = 0; i< numBalloons; i++) {
    		double r = Math.random(); //generates random number
    		double percent = 0;
    		for(int j = 0; j < rarity.length; j++) { // finds which percentile the number lands in
    			percent += rarity[j];
    			if(r < percent && j < 3) { // if it is a balloon
    				bs.add(new Balloon(j+1, -75 * i, 405)); //chooses balloon based on that percentile range
        			j += rarity.length;
    			}
    			else if(r < percent) { // if it is a blimp
    				bs.add(new Blimp(j-2, -75 * i, 405));
    			}
    		}
    	}
    	
//    	numBalloons += 5; // updates number of balloons
//    	rarity[0] -= 0.075; // updates probability
//    	rarity[1] += 0.05;
//    	rarity[2] += 0.025;
    }

    private void placeTower() {
        if (pressed && mouseX > 1195 && mouseX < 1300 && mouseY > 125 && mouseY < 213) {
            placingTower = true;
            // System.out.println(placingTower);
        }
        if (pressed && placingTower && mouseX < 1100) {
            DartTower tower = new DartTower(mouseX - 20, mouseY - 75);
            towers.add(tower);
            placingTower = false;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

        // System.out.println("key press " + e.getKeyCode());
        if (e.getKeyCode() == 38) {
            //up
            //b.moveTo(0,0);
            if (bs.size() != 0) {
                bs.get(0).takeDamage(1);
//                if (!bs.get(0).isAlive()) {
//                    bs.remove(0);
//                }
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

        //System.out.println("key press "+e.getKeyCode());
        if (e.getKeyCode() == 38) {
            //up
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println(e.getX() + " " + e.getY());
        pressed = false;
        e.getComponent();// System.out.println("clicked on a sprite");
        BufferedImage img = null;
        File f = new File("../resources/hqdefault.jpg");
        try {
			img = ImageIO.read(f);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        
        int p = img.getRGB(e.getX(), e.getY());
        int r = (p>>16) & 0xff;
        int g = (p>>8) & 0xff;
        int b = p & 0xff;
        System.out.println("red: " + r + " greeen: " + g + " blue: " + b);
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


}
