package fr.jeu.game.block;

public class Block {

	protected int x, y;
	protected boolean solid;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isSolid(){
		return solid;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
