package fr.jeu.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL12.*;


public class Texture {
	
	public static Texture tiles = load("/textures/Tiles.png");
	public static Texture player = load("/textures/PlayerSprites.png");
	public static Texture fireParticle = load("/textures/fire.png");
	public static Texture bullets = load("/textures/Bullets.png");
	
	private int width,height,id;

	public Texture(int width, int height, int id) {
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	public void bind(){
		glBindTexture(GL_TEXTURE_2D, id);
	}
	
	public void unbind(){
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public static Texture load(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(Texture.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int w = image.getWidth();
		int h = image .getHeight();
		
		int[]pixels = new int[w*h];
		image.getRGB(0, 0,w,h,pixels,0,w);
		
		ByteBuffer buffer = BufferUtils.createByteBuffer(w*h*4);
		
		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				int i = pixels[x+y*w];
				buffer.put((byte)((i >> 16) & 0xFF));
				buffer.put((byte)((i >> 8) & 0xFF));
				buffer.put((byte)(i & 0xFF));
				buffer.put((byte)((i >> 24) & 0xFF));
			}
		}
		buffer.flip();
		
		int id = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, id);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D,  GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		
		glTexImage2D(GL_TEXTURE_2D,0,GL_RGBA,w,h,0,GL_RGBA,GL_UNSIGNED_BYTE,buffer);
		return new Texture(w, h, id);
	}
	
	

}
