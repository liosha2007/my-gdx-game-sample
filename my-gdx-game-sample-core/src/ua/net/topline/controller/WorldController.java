package ua.net.topline.controller;

import java.util.HashMap;
import java.util.Map;

import ua.net.topline.model.Player;
import ua.net.topline.model.World;

public class WorldController {
	// Move to
	enum Keys {
		LEFT, RIGTH, UP, DOWN
	}

	// Player
	public Player player;
	// Move to
	protected static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();

	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGTH, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	}

	public WorldController(World world) {
		this.player = world.getPlayer();
	}

	public void leftPressed() {
		keys.put(Keys.LEFT, true);
	}

	public void rightPressed() {
		keys.put(Keys.RIGTH, true);
	}

	public void upPressed() {
		keys.put(Keys.UP, true);
	}

	public void downPressed() {
		keys.put(Keys.DOWN, true);
	}

	public void leftReleased() {
		keys.put(Keys.LEFT, false);
	}

	public void rightReleased() {
		keys.put(Keys.RIGTH, false);
	}

	public void upReleased() {
		keys.put(Keys.UP, false);
	}

	public void downReleased() {
		keys.put(Keys.DOWN, false);
	}
	
	public void update(float delta) {
		processInput();
		player.update(delta);
	}
	
	public void releaseWay() {
		rightReleased();
		leftReleased();
		upReleased();
		downReleased();
	}
	
	public void processInput() {
		if (keys.get(Keys.LEFT)){
			player.getVelocity().x = -Player.SPEED;
		}
		if (keys.get(Keys.RIGTH)){
			player.getVelocity().x = Player.SPEED;
		}
		if (keys.get(Keys.UP)){
			player.getVelocity().y = Player.SPEED;
		}
		if (keys.get(Keys.DOWN)){
			player.getVelocity().y = -Player.SPEED;
		}
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGTH)) || (!keys.get(Keys.LEFT) && !keys.get(Keys.RIGTH))){
			player.getVelocity().x = 0;
		}
		if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) || (!keys.get(Keys.UP) && !keys.get(Keys.DOWN))){
			player.getVelocity().y = 0;
		}
	}
}
