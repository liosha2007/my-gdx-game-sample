package ua.net.topline.model;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class World extends Stage {
	// Array of blocks
	protected ArrayList<Brick> bricks = new ArrayList<Brick>();
	// Player
	protected Player player;
	// World width
	public int width;
	// World height
	public int height;
	//
	protected Actor selectedActor;

	public World() {
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

	protected void moveSelected(float x, float y) {
		if (selectedActor != null && selectedActor instanceof Player) {
			((Player) selectedActor).changeNavigation(x, this.getHeight() - y);
		}
	}

	protected void resetSelected() {
		if (selectedActor != null && selectedActor instanceof Player) {
			((Player) selectedActor).resetWay();
		}
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		super.touchDown(screenX, screenY, pointer, button);
		moveSelected(screenX, screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		super.touchUp(screenX, screenY, pointer, button);
		resetSelected();
		return true;
	}

	public Actor hit(float x, float y, boolean touchable) {
		Actor actor = super.hit(x, y, touchable);
		if (actor != null) {
			selectedActor = actor;
		}
		return actor;
	}
}
