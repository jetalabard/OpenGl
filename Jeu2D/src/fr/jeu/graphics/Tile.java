package fr.jeu.graphics;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;

import fr.jeu.main.Component;
import fr.jeu.main.Game;
import fr.jeu.render.Renderer;

public class Tile {

	private int x,y;
	private int size = Component.CUBE_SIZE;
	private int halfSize = size /2;
	private boolean tileSet = false;
	private int[] tileSprite = new int[8];

	int xo = 0,yo=0;
	private float[] color =new float[]{1,1,1,1};

	private Tiles tile;

	public enum Tiles{
		SOLID_METAL,BG_METAL
	}
	public Tile(int x, int y,Tiles type){
		this.x = x;
		this.y = y;
		this.tile = type;
		tileSprite = new int[8];
		if(tile == Tiles.SOLID_METAL){
			xo =0;
			yo = 0;
			tileSet = true;
		}
		if(tile == Tiles.BG_METAL){
			xo=3;
			yo = 2;
		}
		if(tileSet){
			tileSprite = new int[]{1,1,1,1,1,1,1,1};
		}
	}

	public void setTiles(boolean vr, boolean vl, boolean vd, boolean vu, boolean vur, boolean vul, boolean vdr, boolean vdl) {
		if (!tileSet) return;
		if (vl) {
			tileSprite[0] = 0;
			tileSprite[1] = 1;
			tileSprite[6] = 0;
			tileSprite[7] = 1;
		}
		if (vr) {
			tileSprite[2] = 2;
			tileSprite[3] = 1;
			tileSprite[4] = 2;
			tileSprite[5] = 1;
		}
		if (vu) {
			tileSprite[0] = 1;
			tileSprite[1] = 0;
			tileSprite[2] = 1;
			tileSprite[3] = 0;
			if (vr) {
				tileSprite[2] = 2;
				tileSprite[3] = 0;
			}
			if (vl) {
				tileSprite[0] = 0;
				tileSprite[1] = 0;
			}
		}
		if (vd) {
			tileSprite[4] = 1;
			tileSprite[5] = 2;
			tileSprite[6] = 1;
			tileSprite[7] = 2;
			if (vr) {
				tileSprite[4] = 2;
				tileSprite[5] = 2;
			}
			if (vl) {
				tileSprite[6] = 0;
				tileSprite[7] = 2;
			}
		}

		if (vd && vr) {
			tileSprite[4] = 2;
			tileSprite[5] = 2;
		}
		if (vd && vl) {
			tileSprite[6] = 0;
			tileSprite[7] = 2;
		}

		if (vur && !vu && !vr) {
			tileSprite[2] = 3;
			tileSprite[3] = 1;
		}
		if (vdr && !vd && !vr) {
			tileSprite[4] = 3;
			tileSprite[5] = 0;
		}
		if (vul && !vu && !vl) {
			tileSprite[0] = 4;
			tileSprite[1] = 1;
		}
		if (vdl && !vd && !vl) {
			tileSprite[6] = 4;
			tileSprite[7] = 0;
		}
	}

	public void render() {
		float x0 = x + Game.xScroll / Component.CUBE_SIZE;
		float y0 = y + Game.yScroll / Component.CUBE_SIZE;

		float x1 = x + 1 + Game.xScroll / Component.CUBE_SIZE;
		float y1 = y + 1 + Game.yScroll / Component.CUBE_SIZE;

		if (x1 < 0 || y1 < 0 || x0 > Component.width / Component.CUBE_SIZE || y0 > Component.height / Component.CUBE_SIZE) return;
		Texture.tiles.bind();
		glBegin(GL_QUADS);	
		Renderer.quadData(x * size, y * size, halfSize, halfSize, color, xo + tileSprite[0], yo + tileSprite[1]);
		Renderer.quadData(x * size + halfSize, y * size, halfSize, halfSize, color, xo + tileSprite[2], yo + tileSprite[3]);
		Renderer.quadData(x * size + halfSize, y * size + halfSize, halfSize, halfSize, color, xo + tileSprite[4], yo + tileSprite[5]);
		Renderer.quadData(x * size, y * size + halfSize, halfSize, halfSize, color, xo + tileSprite[6], yo + tileSprite[7]);
		glEnd();
		Texture.tiles.unbind();
	}

}
