package ua.net.topline.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	// player state
	public enum State {
		NONE, WALKING, DEAD
	}
	public static enum Direction {
		LEFT, RIGHT, TOP, DOWN, NONE
	}
	// Player speed
	public static final float SPEED = 2f;
	// Player size
	public static final float SIZE = 0.7f;
	// Position in world
	protected Vector2 position = new Vector2();
	// Player velocity
	protected Vector2 velocity = new Vector2();
	// For check player colision
	protected Rectangle bounds = new Rectangle();
	// Currect state
	protected State state = State.NONE;
	
	public Player(Vector2 position){
		this.position = position;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
	}
	
	public void update(float delta){
		position.add(velocity.tmp().mul(delta));
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	
}
