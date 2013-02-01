package ua.net.topline.model;

import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World extends Stage {
	protected float ppuX;
	protected float ppuY;
	protected Actor selectedActor = null;
	protected Map<String, AtlasRegion> atlasRegions;
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

		addActor(new Brick(new Vector2(7, 4), this, textureRegions.get("brick_a")));
		addActor(new Brick(new Vector2(0, 4), this, textureRegions.get("brick_a")));

		addActor(new Brick(new Vector2(0, 0), this, textureRegions.get("brick_a")));
		addActor(new Brick(new Vector2(1, 0), this, textureRegions.get("brick_b")));
		addActor(new Brick(new Vector2(2, 0), this, textureRegions.get("brick_c")));
		addActor(new Brick(new Vector2(3, 0), this, textureRegions.get("brick_b")));
		addActor(new Brick(new Vector2(4, 0), this, textureRegions.get("brick_c")));
		addActor(new Brick(new Vector2(5, 0), this, textureRegions.get("brick_b")));
		addActor(new Brick(new Vector2(6, 0), this, textureRegions.get("brick_c")));
		addActor(new Brick(new Vector2(7, 0), this, textureRegions.get("brick_a")));
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		super.touchDown(x, y, pointer, button);
		moveSelected(x, y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		super.touchUp(x, y, pointer, button);
		resetSelected();
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

	private void moveSelected(float x, float y) {
		if (selectedActor != null && selectedActor instanceof Player) {
			((Player) selectedActor).ChangeNavigation(x, this.getHeight() - y);
		}
	}

	private void resetSelected() {
		if (selectedActor != null && selectedActor instanceof Player) {
			((Player) selectedActor).resetWay();
		}
	}

	public Actor hit(float x, float y, boolean touchable) {

		Actor actor = super.hit(x, y, touchable);
		if (actor != null)
			selectedActor = actor;
		return actor;
	}
}
