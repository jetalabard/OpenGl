package fr.jeu.main;


import static org.lwjgl.opengl.GL11.glViewport;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import fr.jeu.game.Game;
import fr.jeu.main.math.Vector3f;
import fr.jeu.main.render.Camera;
import fr.jeu.main.render.DisplayManager;
import fr.jeu.main.render.Shader;


public class Main {

	private boolean running = false;

	public static final float FRAME_CAP = 60;

	private Camera cam;
	
	private Game game;

	public Main() {
		DisplayManager.create(1000, 600, "Jeu 3D");
		game = new Game();
		cam = new Camera(new Vector3f(6, 0.45f, 7.63f));
		cam.setPerspectiveProjection(70.0f, 0.1f, 1000.0f);
	}

	public void start() {
		running = true;
		loop();
	}

	public void stop() {
		running = false;
	}

	public void exit() {
		DisplayManager.dispose();
		System.exit(0);
	}

	public void update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Mouse.setGrabbed(false);
		}
		if(Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
		if(!Mouse.isGrabbed()){
			return;
		}
		cam.input();
		game.update();
	}

	public void loop() {
		
		long lastTickTime = System.nanoTime();
		long lastRenderTime = System.nanoTime();
		
		double TickTime = 1000000000.0/60.0;
		double RenderTime = 1000000000.0/FRAME_CAP;
		
		int ticks = 0;
		int frames = 0;
		
		long timer = System.currentTimeMillis();
		
		while (running) {
			if (DisplayManager.isClose()) {
				stop();
			}
			boolean rendered = false;
			if(System.nanoTime()-lastTickTime >TickTime){
				update();
				ticks++;
				lastTickTime +=TickTime;
			}
			else if(System.nanoTime()-lastRenderTime >RenderTime){
				render();
				DisplayManager.update();
				frames++;
				rendered =true;
				lastRenderTime+= RenderTime;
				
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(ticks + " ticks, " +frames + " fps");
				ticks = 0;
				frames = 0;
			}
		}
		exit();
	}

	public void render() {
		if(Display.wasResized()){
			glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}
		DisplayManager.clearBuffers();
		cam.getPerspectiveProjection();
		cam.update();
		
		Shader.MAIN.bind();

		game.render();
		
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

}
