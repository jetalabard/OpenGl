package fr.jeu.graphics.entities;

public class Color {
	
	public static final float[] WHITE = new Color(1,1,1,1).getColor();
	public static final float[] BLACK = new Color(0,0,0,1).getColor();
	
	public static final float[] RED = new Color(1,0,0,1).getColor();
	public static final float[] GREEN = new Color(0,1,0,1).getColor();
	public static final float[] BLUE = new Color(0,0,1,1).getColor();
	public static final float[] TRANSPARENT = new Color(1,1,1,0.5f).getColor();
	
	
	private float r,g,b,a;
	
	private Color(float r, float g, float b, float a) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public float[] getColor(){
		return new float[]{r,g,b,a};
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}
	
	

}
