package ua.net.topline.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Brick extends Actor {
	// Object size
	public static final float SIZE = 1f;
	// Coordinates
	protected Vector2 coordinates = new Vector2();
	// Size
	protected Rectangle bounds = new Rectangle();
	protected World world;
	protected AtlasRegion atlasRegion;

	public Brick(Vector2 position, World world, AtlasRegion atlasRegion) {
		this.coordinates = position;
		this.world = world;
		this.atlasRegion = atlasRegion;
		bounds.width = SIZE;
		bounds.height = SIZE;
	}

	public Vector2 getCoordinates() {
		return coordinates;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		batch.draw(atlasRegion, coordinates.x * world.ppuX, coordinates.y * world.ppuY, world.ppuX, world.ppuY);
	}
}
