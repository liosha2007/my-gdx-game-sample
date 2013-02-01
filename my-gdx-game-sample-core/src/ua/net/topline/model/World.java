package ua.net.topline.model;

import java.util.Map;

import ua.net.topline.control.WalkingControl;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World extends Stage {
	public float ppuX;
	public float ppuY;
	public Actor selectedActor = null;
	public Map<String, AtlasRegion> atlasRegions;
	public static float CAMERA_WIDTH = 8f;
	public static float CAMERA_HEIGHT = 5f;

	public World(int x, int y, boolean b, SpriteBatch spriteBatch,
			Map<String, AtlasRegion> textureRegions) {
		super(x, y, b, spriteBatch);
		this.atlasRegions = textureRegions;
		ppuX = getWidth() / CAMERA_WIDTH;
		ppuY = getHeight() / CAMERA_HEIGHT;
		addActor(new Player(new Vector2(4, 2), this));
		addActor(new Player(new Vector2(4, 4), this));

		addActor(new Brick(new Vector2(7, 4), this,
				textureRegions.get("brick_a")));
		addActor(new Brick(new Vector2(0, 4), this,
				textureRegions.get("brick_a")));

		addActor(new Brick(new Vector2(0, 0), this,
				textureRegions.get("brick_a")));
		addActor(new Brick(new Vector2(1, 0), this,
				textureRegions.get("brick_b")));
		addActor(new Brick(new Vector2(2, 0), this,
				textureRegions.get("brick_c")));
		addActor(new Brick(new Vector2(3, 0), this,
				textureRegions.get("brick_b")));
		addActor(new Brick(new Vector2(4, 0), this,
				textureRegions.get("brick_c")));
		addActor(new Brick(new Vector2(5, 0), this,
				textureRegions.get("brick_b")));
		addActor(new Brick(new Vector2(6, 0), this,
				textureRegions.get("brick_c")));
		addActor(new Brick(new Vector2(7, 0), this,
				textureRegions.get("brick_a")));

		addActor(new WalkingControl(new Vector2(0F, 0F), this));
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// если предварительно выбран игрок
		if (selectedActor != null)
			super.touchDragged(x, y, pointer);

		return true;
	}

	public void update(float delta) {
		for (Actor actor : this.getActors())
			if (actor instanceof Player) {
				((Player) actor).update(delta);
			}
	}

	public void setPP(float x, float y) {
		ppuX = x;
		ppuY = y;
	}

	public void resetSelected() {
		if (selectedActor != null && selectedActor instanceof Player) {
			((Player) selectedActor).resetWay();
		}
	}

	public Actor hit(float x, float y, boolean touchable) {

		Actor actor = super.hit(x, y, touchable);
		// если выбрали актёра
		if (actor != null && actor instanceof Player)
			// запоминаем
			selectedActor = actor;
		return actor;
	}
}
