package fr.jeu.player;

import fr.jeu.graphics.Texture;
import fr.jeu.graphics.entities.Entity;
import fr.jeu.main.Level;
import fr.jeu.math.Vector2f;
import fr.jeu.render.Renderer;

public abstract class Bullet extends Entity{
	
	private int size;
	private Vector2f direction;
	private int tex;
	private float speed;
	private int dammage;
	private float angle;

	public Bullet(float x, float y,int size,Vector2f dir) {
		super(x/16, y/16);
		this.size = size;
		this.direction = new Vector2f(dir);
		this.angle = (float)Math.toDegrees(Math.atan2(direction.getY(),direction.getX()));
	}

	public abstract void init(Level level) ;

	public abstract void update() ;

	@Override
	public void render() {
		Texture.bullets.bind();
		Renderer.renderBullet(x - size/2, y- size/2, size,tex , angle);
		
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Vector2f getDirection() {
		return direction;
	}

	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}

	public int getTex() {
		return tex;
	}

	public void setTex(int texture) {
		this.tex = texture;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getDammage() {
		return dammage;
	}

	public void setDammage(int dammage) {
		this.dammage = dammage;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	

}
