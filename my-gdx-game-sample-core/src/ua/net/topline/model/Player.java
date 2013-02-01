package ua.net.topline.model;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Player extends Actor {

	public enum State {
		NONE, WALKING, DEAD
	}

	public static enum Direction {
		LEFT, RIGHT, UP, DOWN, NONE
	}

	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}

	protected static Map<Keys, Boolean> direction = new HashMap<Keys, Boolean>();
	public static final float SPEED = 2f;
	public static final float SIZE = 1f;

	protected World world;
	protected Vector2 position = new Vector2();
	protected Vector2 velocity = new Vector2();
	protected State state = State.NONE;
	protected boolean facingLeft = true;

	static {
		direction.put(Keys.LEFT, false);
		direction.put(Keys.RIGHT, false);
		direction.put(Keys.UP, false);
		direction.put(Keys.DOWN, false);
	};

	public Player(Vector2 position, World world) {
		this.world = world;
		this.position = position;
		setHeight(SIZE * world.ppuY);
		setWidth(SIZE * world.ppuX);
		setX(position.x * world.ppuX);
		setY(position.y * world.ppuY);
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlfa) {
		if (this.equals(world.selectedActor)) {
			batch.setColor(0.5f, 0.5f, 0.5f, 0.5f);
		}
		batch.draw(world.atlasRegions.get("player"), getX(), getY(),
				getWidth(), getHeight());
		batch.setColor(1, 1, 1, 1);
	}

	public void update(float delta) {
		position.add(velocity.tmp().mul(delta));
		setX(position.x * world.ppuX);
		setY(position.y * world.ppuY);
	}

	public Actor hit(float x, float y, boolean touchable) {
		return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this
				: null;
	}

	public void ChangeNavigation(float x, float y) {
		resetWay();
		if (y > getY()) {
			upPressed();
		}
		if (y < getY()) {
			downPressed();
		}
		if (x < getX()) {
			leftPressed();
		}
		if (x > (getPosition().x + SIZE) * world.ppuX) {
			rightPressed();
		}
		processInput();
	}

	public void resetWay() {
		rightReleased();
		leftReleased();
		downReleased();
		upReleased();
		getVelocity().x = 0;
		getVelocity().y = 0;
	}

	private void processInput() {
		if (direction.get(Keys.LEFT)) {
			getVelocity().x = -Player.SPEED;
		}
		if (direction.get(Keys.RIGHT)) {
			getVelocity().x = Player.SPEED;
		}
		if (direction.get(Keys.UP)) {
			getVelocity().y = Player.SPEED;
		}
		if (direction.get(Keys.DOWN)) {
			getVelocity().y = -Player.SPEED;
		}
		if ((direction.get(Keys.LEFT) && direction.get(Keys.RIGHT))
				|| (!direction.get(Keys.LEFT) && (!direction.get(Keys.RIGHT)))) {
			getVelocity().x = 0;
		}
		if ((direction.get(Keys.UP) && direction.get(Keys.DOWN))
				|| (!direction.get(Keys.UP) && (!direction.get(Keys.DOWN)))) {
			getVelocity().y = 0;
		}
	}

	public void leftPressed() {
		direction.get(direction.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		direction.get(direction.put(Keys.RIGHT, true));
	}

	public void upPressed() {
		direction.get(direction.put(Keys.UP, true));
	}

	public void downPressed() {
		direction.get(direction.put(Keys.DOWN, true));
	}

	public void leftReleased() {
		direction.get(direction.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		direction.get(direction.put(Keys.RIGHT, false));
	}

	public void upReleased() {
		direction.get(direction.put(Keys.UP, false));
	}

	public void downReleased() {
		direction.get(direction.put(Keys.DOWN, false));
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}
}