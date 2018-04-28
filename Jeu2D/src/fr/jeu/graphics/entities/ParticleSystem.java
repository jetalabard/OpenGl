package fr.jeu.graphics.entities;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import fr.jeu.main.Level;

public class ParticleSystem extends Entity {
	
	private List<Particle> particles;
	
	public ParticleSystem(int x,int y,int num,Particle p){
		super(x,y);
		particles = new ArrayList<>();
		for(int i=0;i<num;i++){
			particles.add(new Particle(p, x, y));
		}
		if(p != null){
			setTexture(p.getTexture());
		}
	}

	@Override
	public void init(Level level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		for (int i = 0; i < particles.size(); i++) {
			Particle p = particles.get(i);
			if (p.isRemoved()) particles.remove(p);
			p.update();
		}
		
	}

	@Override
	public void render() {
		if(getTexture() != null){
			getTexture().bind();
		}
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glBegin(GL_QUADS);
		for(Particle p : particles){
			p.render();
		}
		glEnd();
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
}
