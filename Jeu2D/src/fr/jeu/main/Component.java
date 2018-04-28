package fr.jeu.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;


import static org.lwjgl.opengl.GL11.*;
public class Component {
	
	
	public static int scale = 3;
	public static int CUBE_SIZE = 16;
	public static int width = 720 / scale;
	public static int height = 480/ scale;
	
	private boolean running = false;
	
	int time =0;
	
	private static boolean tick = false;
	private static boolean render = false;
	
	private static String title = "Jeu 2D";
	
	private DisplayMode mode = new DisplayMode(width*scale, height*scale);
	
	private Game game;
	
	public Component(){
		display();
		game = new Game();
	}
	
	public void display(){
		try {
			Display.setDisplayMode(mode);
			Display.setResizable(true);
			Display.setFullscreen(false);
			Display.setTitle(title);
			Display.create();
			
			view2D(width,height);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	private void view2D(int width,int height){
		glViewport(0, 0, width * scale, height*scale);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	public void update(){
		time++;
		game.update();
	}
	public void render(){
		
		view2D(width,height );
		glClear(GL_COLOR_BUFFER_BIT);
		
		game.render();
		
	}
	
	public void start(){
		running = true;
		loop();
	}
	
	public void stop(){
		running = false;
	}
	
	public void loop(){
		long timer = System.currentTimeMillis();
		long before = System.nanoTime();
		double elapsed = 0;
		double nanoSeconds = 1000000000.0/60.0;
		
		int ticks =0;
		int frames = 0;
		
		game.init();
		
		while(running == true){
			if(Display.isCloseRequested()){
				stop();
			}
			Display.update();
			width = Display.getWidth()/scale;
			height =Display.getHeight()/scale;
			
			tick = false;
			render = false;
			
			long now = System.nanoTime();
			elapsed = now - before;
			if(elapsed > nanoSeconds){
				before += nanoSeconds;
				tick = true;
				ticks++;
			}else{
				render = true;
				frames++;
			}
			if(tick) update();
			if(render)render();
			
			if(System.currentTimeMillis() - timer > 1000){
				timer +=1000;
				Display.setTitle(title + " --- ticks: "+ ticks +" ,fps :"+frames);
				ticks = 0;
				frames = 0;
			}
		}
		exit();
	}
	private void exit() {
		Display.destroy();
		System.exit(0);
	}

	public static void main(String[]args){
		Component main = new Component();
		main.start();
	}
}
