package fr.jeu.graphics.entities;

import fr.jeu.graphics.Texture;
import fr.jeu.main.Component;
import fr.jeu.main.Level;
import fr.jeu.math.Vector2f;

public abstract class Entity {
	
	protected float x,y;
	private boolean removed = false;
	private Texture texture;
	private float mass;
	private float drag;
	private Level level;
	private Vector2f shootPoint;
	private Vector2f mouseDirection;
	
	
	public Entity(float x, float y) {
		super();
		this.x = x * Component.CUBE_SIZE;
		this.y = y* Component.CUBE_SIZE;
	}
	public abstract void init(Level level);
	public abstract void update();
	public abstract void render();
	
	public boolean isSolidTile(float xa,float ya){
		
		int x0 = (int)(x + xa +3) /Component.CUBE_SIZE;
		int x1 = (int)(x + xa +13 ) /Component.CUBE_SIZE;
		int y0 = (int)(y+ya +2) /Component.CUBE_SIZE;
		int y1 =  (int)(y+ya + 14) /Component.CUBE_SIZE;
		
		if(level.getSolitTile(x0,y0) != null){
			return true;
		}
		if(level.getSolitTile(x1,y0) != null){
			return true;
		}
		if(level.getSolitTile(x1,y1) != null){
			return true;
		}
		if(level.getSolitTile(x0,y1) != null){
			return true;
		}
		return false;
	}
	
	public boolean isGrounded(){
		if(level.getSolitTile((int)(x + 5)/Component.CUBE_SIZE, (int)(y +14.1 )/Component.CUBE_SIZE) !=null )return true;
		return false;
	}
	

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	public boolean isRemoved() {
		return removed;
	}
	
	public void setMass(float f) {
		this.mass = f;
	}
	public float getDrag() {
		return drag;
	}
	public void setDrag(float drag) {
		this.drag = drag;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	public Vector2f getShootPoint() {
		return shootPoint;
	}
	public void setShootPoint(Vector2f shootPoint) {
		this.shootPoint = shootPoint;
	}
	public Vector2f getMouseDirection() {
		return mouseDirection;
	}
	public void setMouseDirection(Vector2f mouseDirection) {
		this.mouseDirection = mouseDirection;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public float getMass() {
		return mass;
	}
	
	
	
	
	
	

}
