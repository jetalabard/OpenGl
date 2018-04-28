package fr.jeu.player;

import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import fr.jeu.graphics.Texture;
import fr.jeu.graphics.entities.Color;
import fr.jeu.graphics.entities.Entity;
import fr.jeu.graphics.entities.Particle;
import fr.jeu.graphics.entities.ParticleSystem;
import fr.jeu.main.Component;
import fr.jeu.main.Game;
import fr.jeu.main.Level;
import fr.jeu.math.Vector2f;
import fr.jeu.render.Renderer;

public class Player extends Entity {
	
	private int dir = 0;
	private int timeBullet = 0;
	private Animation anim;
	
	private float xa,ya;
	private float speed = 0.2f;
	
	public Player(int x, int y) {
		super(x, y);
		setTexture(Texture.player);
		anim = new Animation(3, 10, true);
		setMass(0.1f);
		setDrag( 0.95f);
		setShootPoint( new Vector2f(8,8));
		setMouseDirection(new Vector2f());
	}

	
	@Override
	public void update() {
		ya +=getLevel().getGravity()*getMass();
		anim.update();
		anim.pause();
		if(timeBullet >0){
			timeBullet--;
		}
		if(isGrounded()){
			setDrag(0.8f);
		}
		else{
			setDrag(0.95f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
				ya -= speed*3;
				Particle particule = new Particle();
				particule.setColor(Color.TRANSPARENT);
				particule.setTexture(Texture.fireParticle);
				particule.setDirection(new Vector2f(0,4));
				particule.setLifeTime(20);
				particule.setSpeed(0.3f);
				particule.setSize(10);
				getLevel().addEntity(new ParticleSystem((int)x+8 -5,(int) y+8, 1, particule));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			if(isGrounded()){
				ya -= 3.6f;
				dir = 1;
				anim.play();
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			ya += speed;
			dir = 0;
			anim.play();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			xa -= speed;
			dir = 2;
			anim.play();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			xa += speed;
			dir = 1;
			anim.play();
		}
		
	
		if(Mouse.isButtonDown(0)){
			if(timeBullet ==0){
				getLevel().addEntity(new BasicBullet(x+getShootPoint().getX(), y+getShootPoint().getY(), 16, getMouseDirection()));
				timeBullet = 50;
			}
		}
		if(Game.getMouseX()> x+8){
			dir =1;
		}
		else if(Game.getMouseX()< x + 8){
			dir =2;
		}
		getMouseDirection().set(Game.getMouseX() - (x +getShootPoint().getX()), 
				Game.getMouseY() - (y + getShootPoint().getY())).normalize();
		
		
		
		int xstep = (int) Math.abs(xa*1000);
		for(int i = 0; i<xstep ;i++){
			if(!isSolidTile(xa / xstep, 0)){
				x += xa/xstep;
			}else{
				xa =0;
			}
		}
		int ystep = (int) Math.abs(ya *1000);
		for(int i = 0; i<ystep ;i++){
			if(!isSolidTile(0,ya / ystep)){
				y += ya/ystep;
			}else{
				ya =0;
			}
		}
		xa *=getDrag();
		ya *=getDrag();
		
		
		
		
	}

	@Override
	public void render() {
		getTexture().bind();

		Renderer.renderEntity(getX(),getY(),Component.CUBE_SIZE,Component.CUBE_SIZE, Color.WHITE,4.0f, dir, anim.getFrame());
		getTexture().unbind();
			glVertex2f(x+getShootPoint().getX(), y + getShootPoint().getY());
			glVertex2f(x+getShootPoint().getX()+getMouseDirection().getX() *16, y + getShootPoint().getY()+getMouseDirection().getY()*16);
	}


	@Override
	public void init(Level level) {
		setLevel(level);
		
	}

}
