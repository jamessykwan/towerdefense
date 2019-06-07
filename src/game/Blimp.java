package game;

import java.util.ArrayList;

public class Blimp extends Balloon{
		
	public Blimp(int tier, int x, int y) {
		super(tier, x, y);
		this.setTier(tier);
        if(tier == 1) {
        	setHealth(1);
        	setDamage(4);
            setSpeed(5);
        }
        else {
        	setHealth(1);
        	setDamage(20);
            setSpeed(1);
        }
        setImg(updateImage(tier));
        setAlive(true);
        createPath();
        setBlimp(true);
		// TODO Auto-generated constructor stub
	}
	
	
	public void takeDamage(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) {
        	setAlive(false);
        }
    }
	
	
	public static String updateImage(int tier) {
		if(tier == 1) {
			return "../resources/whiteBalloon.png";
		}
		else{
			return "../resources/blimp.png";
		}
	}
	
	public boolean isBlimp() {
		return true;
	}
	
}
