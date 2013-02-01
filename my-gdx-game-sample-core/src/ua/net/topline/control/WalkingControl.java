package ua.net.topline.control;

import ua.net.topline.model.Player;
import ua.net.topline.model.World;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class WalkingControl extends Actor {
	// размер джоя
	public static float SIZE = 3.5f;
	// размер движущейся части (khob)
	public static float CSIZE = 3f;
	//
	public static float CIRCLERADIUS = 2f;
	//
	public static float CONTRLRADIUS = 3.5F;
	// угол для определения направления
	protected float angle;
	//
	protected World world;
	// координаты отклонения khob
	protected Vector2 offsetPosition = new Vector2();
	// Joystic position
	protected Vector2 position = new Vector2();
	// Joystic size
	protected Rectangle bounds = new Rectangle();

	public WalkingControl(Vector2 pos, World world) {
		this.position = pos;
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.world = world;

		getOffsetPosition().x = 0;
		getOffsetPosition().y = 0;

		setHeight(SIZE * world.ppuY);
		setWidth(SIZE * world.ppuX);
		setX(position.x * world.ppuX);
		setY(position.y * world.ppuY);

		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			// при перетаскивании
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				withControl(x, y);
			}

			// убираем палец с экрана
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				getOffsetPosition().x = 0;
				getOffsetPosition().y = 0;
			}
		});
	}

	// отрисовка
	@Override
	public void draw(SpriteBatch batch, float parentAlfa) {
		batch.draw(world.atlasRegions.get("joystic"), getX(), getY(),
				getWidth(), getHeight());
	}

	public Actor hit(float x, float y, boolean touchable) {
		return x > 0 && x < getWidth() && y > 0 && y < getHeight() ? this
				: null;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getOffsetPosition() {
		return offsetPosition;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void withControl(float x, float y) {
		float calcX = x / world.ppuX - SIZE / 2;
		float calcY = y / world.ppuY - SIZE / 2;

		// определяем лежит ли точка касания в окружности джойстика
		if (((calcX * calcX + calcY * calcY) <= WalkingControl.CONTRLRADIUS
				* WalkingControl.CONTRLRADIUS)) {
			world.resetSelected();
			// пределяем угол касания
			double angle = Math.atan(calcY / calcX) * 180 / Math.PI;
			// угол будет в диапозоне [-90;90]. Удобнее работать, если он в
			// диапозоне [0;360]
			// поэтому пошаманим немного
			if (angle > 0 && calcY < 0) {
				angle += 180;
			}
			if (angle < 0) {
				if (calcX < 0) {
					angle = 180 + angle;
				} else {
					angle += 360;
				}
			}
			// в зависимости от угла указываем направление, куда двигать игрока
			if (angle > 40 && angle < 140) {
				((Player) world.selectedActor).upPressed();
			}
			if (angle > 220 && angle < 320) {
				((Player) world.selectedActor).downPressed();
			}
			if (angle > 130 && angle < 230) {
				((Player) world.selectedActor).leftPressed();
			}
			if (angle < 50 || angle > 310) {
				((Player) world.selectedActor).rightPressed();
			}
			// двигаем игрока
			((Player) world.selectedActor).processInput();
			angle = (float) (angle * Math.PI / 180);
			getOffsetPosition().x = (float) ((calcX * calcX + calcY * calcY > 1F) ? Math
					.cos(angle) * 0.75F : calcX);
			getOffsetPosition().y = (float) ((calcX * calcX + calcY * calcY > 1F) ? Math
					.sin(angle) * 0.75F : calcY);
		} else {
			world.resetSelected();
			getOffsetPosition().x = 0;
			getOffsetPosition().y = 0;
		}
	}
}
