package fr.jeu.main.render;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

import java.nio.FloatBuffer;

public class DisplayManager {

	public static void create(int width, int height, String title){
		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.setResizable(true);
			Display.create();
			
			glEnable(GL_DEPTH_TEST);
			glEnable(GL_CULL_FACE);
			glEnable(GL_FOG);
			glEnable(GL_TEXTURE_2D);
			
			FloatBuffer fogColor =(FloatBuffer) BufferUtils.createFloatBuffer(4);
			fogColor.put(new float[]{0,0,0,1});
			
			fogColor.flip();
			glFogi(GL_FOG_MODE, GL_EXP);
			glFogf(GL_FOG_DENSITY, 0.4f);
			glFog(GL_FOG_COLOR, fogColor);
		}
		catch(LWJGLException e){
			e.printStackTrace();
		}
	}

	public static void update(){
		Display.update();
	}

	public static void clearBuffers(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public static boolean isClose(){
		return Display.isCloseRequested();
	}
	
	public static void dispose(){
		Display.destroy();
	}

}
