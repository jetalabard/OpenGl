package fr.jeu.main.render;

import fr.jeu.main.math.Vector3f;
import static org.lwjgl.opengl.GL11.*;
public class Renderer {
	
	private static float offs = 0.0f;
	private static float envWidth = 4f;
	private static float envHeight = 4f;
	
	public static void setFloorData(float x, float z, Vector3f color,int texture){
		glColor3f(color.getX(),color.getY(),color.getZ());
		
		float xo = texture % (int)envWidth;
		float yo = texture / (int)envHeight;
		
		glTexCoord2f((0+ xo )/envWidth + offs,(0+ yo )/envHeight +offs);glVertex3f(x+1, 0, z);
		glTexCoord2f((1+ xo )/envWidth - offs,(0+ yo )/envHeight +offs);glVertex3f(x, 0, z);
		glTexCoord2f((1+ xo )/envWidth - offs,(1+ yo )/envHeight -offs);glVertex3f(x, 0, z+1);
		glTexCoord2f((0+ xo )/envWidth + offs,(1+ yo )/envHeight -offs);glVertex3f(x+1, 0, z+1);
	}
	
	public static void setCeilingData(float x, float z, Vector3f color,int texture){
		glColor3f(color.getX(),color.getY(),color.getZ());
		
		float xo = texture % (int)envWidth;
		float yo = texture / (int)envHeight;
		glTexCoord2f((0+ xo )/envWidth + offs,(0+ yo )/envHeight +offs);glVertex3f(x, 1, z);
		glTexCoord2f((1+ xo )/envWidth - offs,(0+ yo )/envHeight +offs);glVertex3f(x+1, 1, z);
		glTexCoord2f((1+ xo )/envWidth - offs,(1+ yo )/envHeight -offs);glVertex3f(x+1, 1, z+1);
		glTexCoord2f((0+ xo )/envWidth + offs,(1+ yo )/envHeight -offs);glVertex3f(x, 1, z+1);
	}
	
	public static void setWallgData(float x0, float z0,float x1, float z1, Vector3f color,int texture){
		glColor3f(color.getX(),color.getY(),color.getZ());
		float xo = texture % (int)envWidth;
		float yo = texture / (int)envHeight;
		glTexCoord2f((0+ xo )/envWidth + offs,(0+ yo )/envHeight +offs);glVertex3f(x0, 0, z0);
		glTexCoord2f((1+ xo )/envWidth - offs,(0+ yo )/envHeight +offs);glVertex3f(x1, 0, z1);
		glTexCoord2f((1+ xo )/envWidth - offs,(1+ yo )/envHeight -offs);glVertex3f(x1, 1, z1);
		glTexCoord2f((0+ xo )/envWidth + offs,(1+ yo )/envHeight -offs);glVertex3f(x0, 1, z0);
	}
	
	public static void addFog(float density , Vector3f color){
		Shader.MAIN.setUniform("fogColor", color);
		Shader.MAIN.setUniform("fogDensity", density);
	}
	
	
}
