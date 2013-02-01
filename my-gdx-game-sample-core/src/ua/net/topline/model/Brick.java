package ua.net.topline.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick {
	// Object size
	public static final float SIZE = 1f;
	// Coordinates
	protected Vector2 coordinates = new Vector2();
	// Size
	protected Rectangle bounds = new Rectangle();

	public Brick(Vector2 position) {
		this.coordinates = position;
		bounds.width = SIZE;
		bounds.height = SIZE;
	}

	public Vector2 getCoordinates() {
		return coordinates;
	}

	public Rectangle getBounds() {
		return bounds;
	}
}
