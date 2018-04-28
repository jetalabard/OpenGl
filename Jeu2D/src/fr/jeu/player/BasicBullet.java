package fr.jeu.player;

import fr.jeu.main.Level;
import fr.jeu.math.Vector2f;

public class BasicBullet extends Bullet {

	public BasicBullet(float x, float y, int size, Vector2f dir) {
		super(x, y, size, dir);
		setTex(0);
		setSpeed(1.5f);
		setDammage(1);
		setMass(0.001f);
	}

	
	@Override
	public void init(Level level) {
		setLevel(level);
		
	}

	@Override
	public void update() {
		x+= getDirection().getX() * getSpeed();
		y+= getDirection().getY() * getSpeed();
	}

}
