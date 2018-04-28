package fr.jeu.game;

import fr.jeu.main.math.Vector3f;
import fr.jeu.main.render.Renderer;

public class Game {
	/*manage the level*/

	private Level level;

	public Game() {
		level = new Level("map");
	}

	public void update() {
		level.update();
	}

	/**
	 * render level with fog
	 */
	public void render() {
		Renderer.addFog(0.1f, new Vector3f(0,0,0));
		level.render();
	}
	
}
