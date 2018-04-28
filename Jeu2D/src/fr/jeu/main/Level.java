package fr.jeu.main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import fr.jeu.graphics.Tile;
import fr.jeu.graphics.Tile.Tiles;
import fr.jeu.graphics.entities.Entity;
import fr.jeu.player.Player;

public class Level {
	
	
	private int width, height;
	
	private Tile[][] solidTiles;
	private Tile[][] bgTiles;
	private float gravity = 1.8f;
	private int[] bounds = new int[4];
	
	private  int cube_size;
	
	private List<Tile> tiles = new ArrayList<>();
	
	
	private List<Entity> entities = new ArrayList<>();
	
	private static Player player = new Player(5, 5);
	
	public Tile getSolitTile(int x,int y){
		if(x <0 || y <0 || x >= width || y >= height) return null;
		return solidTiles[x][y];
	}

	public Level(String path){
		cube_size = Component.CUBE_SIZE;
		loadLevel( path);
		spawner();
		
	}
	
	private void loadLevel(String name) {
		int[] pixels;
		BufferedImage image = null;
		try {
			image = ImageIO.read(Level.class.getResource("/levels/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		
		bounds[0] = -cube_size;
		bounds[1] = -cube_size;
		bounds[2] = -width *cube_size +cube_size + Display.getWidth()/Component.scale;
		bounds[3] = -height*cube_size +cube_size +Display.getHeight()/Component.scale;
		
		
		pixels = new int[width * height];
		image.getRGB(0, 0, width, height, pixels, 0, width);
		
		solidTiles = new Tile[width][height];
		bgTiles = new Tile[width][height];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (pixels[x + y * width] == 0xFFffffff) {
					solidTiles[x][y] = new Tile(x, y, Tiles.SOLID_METAL);
				}
				if (pixels[x + y * width] == 0xFF000000) {
					bgTiles[x][y] = new Tile(x, y, Tiles.BG_METAL);
				}
				if (pixels[x + y * width] == 0xFFffff00 ||
					pixels[x + y * width] == 0xFFffff01 ||
					pixels[x + y * width] == 0xFFffff02 ||
					pixels[x + y * width] == 0xFFffff03 ||
					pixels[x + y * width] == 0xFFffff04 ||
					pixels[x + y * width] == 0xFFffff05) {
					
					bgTiles[x][y] = new Tile(x, y, Tiles.BG_METAL);
				}
			}	
		}
		setTiles();
	}
	
	public void setTiles() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x - 1 < 0 || y - 1 < 0 || x + 1 >= width || y + 1 >= height) continue;
				boolean vu = false, vd = false, vl = false, vr = false;
				boolean vur = false, vdr = false, vul = false, vdl = false;
				
				if (solidTiles[x + 1][y] == null) {
					vr = true;
				}
				if (solidTiles[x - 1][y] == null) {
					vl = true;
				}
				if (solidTiles[x][y + 1] == null) {
					vd = true;
				}
				if (solidTiles[x][y - 1] == null) {
					vu = true;
				}
				//
				if (solidTiles[x + 1][y + 1] == null) {
					vdr = true;
				}
				if (solidTiles[x - 1][y - 1] == null) {
					vul = true;
				}
				if (solidTiles[x - 1][y + 1] == null) {
					vdl = true;
				}
				if (solidTiles[x + 1][y - 1] == null) {
					vur = true;
				}
				if (solidTiles[x][y] != null) {
					solidTiles[x][y].setTiles(vr, vl, vd, vu, vur, vul, vdr, vdl);
				}
				addTiles(x, y);
			}	
		}
	}

	
	public void addTiles(int x,int y){
		if(solidTiles[x][y] !=null){
			tiles.add(solidTiles[x][y]);
		}else if(bgTiles[x][y] !=null){
			tiles.add(bgTiles[x][y]);
		}
	}
	
	public void update(){
		for(int i=0;i<entities.size();i++){
			if(entities.get(i).isRemoved()) entities.remove(i);
			entities.get(i).update();
		}
	}
	
	public void render(){
		for(Tile t: tiles){
			t.render();
		}
		for(Entity e: entities){
			if(!(e instanceof Player))e.render();
		}
		entities.get(0).render();
	}
	
	public void addEntity(Entity e){
		e.init(this);
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
	}
	
	public void spawner(){
		player.init(this);
		addEntity(player);
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public int getBounds(int index){
		return bounds[index];
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static Player getPlayer() {
		return player;
	}

	public float getGravity() {
		return gravity;
	}
	
	
	
	
}
