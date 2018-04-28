package fr.jeu.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.jeu.game.block.Block;
import fr.jeu.game.block.SolidBlock;
import fr.jeu.main.math.Vector3f;
import fr.jeu.main.render.Renderer;
import fr.jeu.main.render.Texture;

public class Level {
	
	private int width, heigth;
	private int renderingList;
	/*
	 * list of blocks which allow to render level
	 */
	private Block[] blocks;

	public Level(String level){
		compile(level);
	}
	
	public void compile(String level){
		BufferedImage map = null;
		
		try {
			map = ImageIO.read(Level.class.getResource("/"+level + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = map.getWidth();
		heigth = map.getHeight();
		int[] pixels = new int[width*heigth];
		map.getRGB(0, 0,width,heigth,pixels,0,width);
		blocks = new Block[pixels.length];
		
		for(int x =0;x < width ;x++ ){
			for(int y =0;y < heigth ; y++ ){
				int i = x+y*width;
				if(pixels[i] == 0xFF000000){
					blocks[i] = new Block(x,y);
				}
				if(pixels[i] == 0xFFffffff){
					blocks[i] = new SolidBlock(x,y);
				}
			}
		}
		compileRendereing();
	}
	
	private void compileRendereing(){
		renderingList = glGenLists(1);
		glNewList(renderingList, GL_COMPILE);
		glBegin(GL_QUADS);
		
		for(int x =0;x < width ;x++ ){
			for(int y =0;y < heigth ; y++ ){
				Block block =getBlock(x, y); 
				if(!block.isSolid()){
					Renderer.setFloorData(x, y, new Vector3f(1,1,1),1);
					Renderer.setCeilingData(x,y, new Vector3f(1,1,1),2);
				}
				
				Block right = getBlock(x+1, y);
				Block down = getBlock(x, y+1);
				
				if(block.isSolid()){
					if(!right.isSolid()){
						Renderer.setWallgData(x+1, y+1, x+1, y, new Vector3f(0.9f,0.9f,0.9f),0);
					}
					if(!down.isSolid()){
						Renderer.setWallgData(x, y+1, x+1, y+1, new Vector3f(0.8f,0.8f,0.8f),0);
					}
				}
				else{
					if(right.isSolid()){
						Renderer.setWallgData(x+1, y, x+1, y+1, new Vector3f(0.9f,0.9f,0.9f),0);
					}
					if(down.isSolid()){
						Renderer.setWallgData(x+1, y+1, x, y+1, new Vector3f(0.8f,0.8f,0.8f),0);
					}
					
				}
			}
		}
		
		glEnd();
		glEndList();
		Texture.env.bind();
	}
	
	public Block getBlock(int x, int y)
	{
		if(x<0||y<0||x>= width|| y>= heigth){
			return new SolidBlock(x, y);
		}
		return blocks[x+y*width];
	}
	
	public void update(){
		
	}
	
	public void render(){
		
		glCallList(renderingList);
	}
}
