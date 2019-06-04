package game.GameEffects;

import game.Coordinate;
import game.GameEffect;
import game.Sprite;

public class Tack extends GameEffect {

	public Tack(Coordinate pos, Coordinate targetPos) {
		setEffect(new Sprite("../resources/arrow.png", pos.getX(), pos.getY()));
		setX(pos.getX());
		setY(pos.getY());
		setVelX((targetPos.getX() - pos.getX()));
		setVelY((targetPos.getY() - pos.getY()));
		setAge(0);
	}
}
