package ua.net.topline.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class World {
	// Array of blocks
	protected ArrayList<Brick> bricks = new ArrayList<Brick>();
	// Player
	protected Player player;
	// World width
	public int width;
	// World height
	public int height;
	
	public World(){
		this.width = 8;
		this.height = 5;
		createWorld();
	}
	
	public void createWorld() {
		player = new Player(new Vector2(6, 2));
		bricks.add(new Brick(new Vector2(0, 0)));
		bricks.add(new Brick(new Vector2(1, 0)));
		bricks.add(new Brick(new Vector2(2, 0)));
		bricks.add(new Brick(new Vector2(3, 0)));
		bricks.add(new Brick(new Vector2(4, 0)));
		bricks.add(new Brick(new Vector2(5, 0)));
		bricks.add(new Brick(new Vector2(6, 0)));
		bricks.add(new Brick(new Vector2(7, 0)));
	}

	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	public Player getPlayer() {
		return player;
	}
}
