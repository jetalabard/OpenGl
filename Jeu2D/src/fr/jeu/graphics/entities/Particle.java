package fr.jeu.graphics.entities;

import java.util.Random;

import fr.jeu.graphics.Texture;
import fr.jeu.math.Vector2f;
import fr.jeu.render.Renderer;

public class Particle {
	private boolean removed = false;
	private float x,y;
	private double rx,ry;
	private Random rand = new Random();
	private float[] color;
	private int size;
	private float speed;
	private int lifeTime;
	private Texture texture;
	private Vector2f direction;
	
	public Particle(float[] color,Vector2f direction,int size, float speed,int lifeTime){
		this.direction = direction;
		this.size = size;
		this.speed  =speed;
		this.color = color;
		this.lifeTime = lifeTime;
	}
	
	public Particle(){}
	

	public Particle(Particle p,int x,int y){
		this.x = x;
		this.y = y;
		this.direction = p.direction;
		this.size = p.size;
		this.color = p.color;
		this.speed = p.speed;
		this.lifeTime = p.lifeTime;
		rx = rand.nextGaussian();// entre -1 et 1
		ry = rand.nextGaussian();
	}
	

	
	public Particle(Texture texture,int size,float speed,int lifeTime,int[] randomness){
	}
	int time = 0;
	public void update(){
		time++;
		if(time >lifeTime){
			removed = true;
			return;
		}
		x +=(rx + direction.getX())*speed;
		y += (ry + direction.getY())*speed;
	}
	public void render(){
		Renderer.particleData(x, y, size, color);
	}

	public boolean isRemoved() {
		return removed;
	}

	public float[] getColor() {
		return color;
	}

	public Particle setColor(float[] color) {
		this.color = color;
		return this;
	}

	public int getSize() {
		return size;
	}

	public Particle setSize(int size) {
		this.size = size;
		return this;
	}

	public float getSpeed() {
		return speed;
	}

	public Particle setSpeed(float speed) {
		this.speed = speed;
		return this;
	}

	public int getLifeTime() {
		return lifeTime;
	}

	public Particle setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
		return this;
	}

	public Vector2f getDirection() {
		return direction;
	}

	public Particle setDirection(Vector2f direction) {
		this.direction = direction;
		return this;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	
	
	
}
