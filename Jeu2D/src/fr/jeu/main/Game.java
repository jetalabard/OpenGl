package fr.jeu.main;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Game {

	private Level level;
	
	public static float xScroll,yScroll;
	
	private float xa = 0,ya = 0;
	

	public Game() {
		level = new Level("level_metal");
		xScroll = level.getBounds(0);
		yScroll = level.getBounds(1);
	}
	
	public void translateView(float xa, float ya){
		xScroll = xa;
		yScroll = ya;
		if(xScroll >level.getBounds(0)){
			xScroll = level.getBounds(0);
		}
		if(xScroll <level.getBounds(2)){
			xScroll = level.getBounds(2);
		}
		if(yScroll >level.getBounds(1)){
			yScroll = level.getBounds(1);
		}
		if(yScroll <level.getBounds(3)){
			yScroll = level.getBounds(3);
		}
		
	}
	
	public void init() {
		level.init();
	}

	public void update() {
		level.update();
		xa = -Level.getPlayer().getX() + Component.width/2 - Component.CUBE_SIZE/2;
		ya = -Level.getPlayer().getY()+ Component.height/2 - Component.CUBE_SIZE/2;
		translateView(xa, ya);
		
	}

	public void render() {
		GL11.glTranslatef(xScroll, yScroll, 0);
		level.render();
		
	}
	
	public static float getMouseX(){
		return Mouse.getX() / Component.scale - xScroll;
	}
	
	
	public static float getMouseY(){
		return( Display.getHeight() -Mouse.getY() )/ Component.scale - yScroll;
	}
}
