package ua.net.topline.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.net.topline.model.MovingPlatform;
import ua.net.topline.model.MyWorld;
import ua.net.topline.model.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.WorldManifold;

public class WorldController {
	// Move to
	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}

	// Player
	public Player player;
	//
	public MyWorld myWorld;
	// Move to
	protected static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();

	public static boolean grounded;

	static {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	}

	public WorldController(MyWorld world) {
		this.myWorld = world;
		this.player = world.getPlayer();
	}

	// флаг устанавливаем, что движемся влево
	public void leftPressed() {

		keys.get(keys.put(Keys.LEFT, true));
	}

	// флаг устанавливаем, что движемся вправо
	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	// освобождаем флаги
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	// флаг устанавливаем, что движемся вверх
	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}

	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}

	public void resetWay() {
		rightReleased();
		leftReleased();
		upReleased();
		myWorld.getPlayer().resetVelocity();
	}

	//
	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}

	//
	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}

	/**
	 * Апдейтим логику - delta время, прошешее с прошлой прорисовки.
	 */
	public void update(float delta) {
		List<MovingPlatform> platforms = myWorld.platforms;
		for (int i = 0; i < platforms.size(); i++) {
			MovingPlatform platform = platforms.get(i);
			platform.update(Math.max(1 / 60.0f, delta));
		}
		grounded = isPlayerGrounded(Gdx.graphics.getDeltaTime());
		processInput();
		myWorld.getPlayer().update(delta);
	}

	/**
	 * Опреелить, нахоится ли игрок на земле/платформе. deltaTime - время,
	 * прошешее с прошлой прорисовки.
	 */
	private boolean isPlayerGrounded(float deltaTime) {
		myWorld.groundedPlatform = null;
		List<Contact> contactList = myWorld.world.getContactList();
		for (int i = 0; i < contactList.size(); i++) {
			Contact contact = contactList.get(i);
			if (contact.isTouching()
					&& (contact.getFixtureA() == myWorld.getPlayer().playerSensorFixture || contact
							.getFixtureB() == myWorld.getPlayer().playerSensorFixture)) {
				Vector2 pos = myWorld.getPlayer().getPosition();
				WorldManifold manifold = contact.getWorldManifold();
				boolean below = true;
				for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
					below &= (manifold.getPoints()[j].y < pos.y - 0.4f);
				}
				if (below) {
					if (contact.getFixtureA().getUserData() != null
							&& contact.getFixtureA().getUserData().equals("p")) {
						myWorld.groundedPlatform = (MovingPlatform) contact
								.getFixtureA().getBody().getUserData();
						if (!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)) {
							contact.setFriction(200F);
						} else {
							contact.setFriction(0F);
						}
					}
					if (contact.getFixtureB().getUserData() != null
							&& contact.getFixtureB().getUserData().equals("p")) {
						myWorld.groundedPlatform = (MovingPlatform) contact
								.getFixtureB().getBody().getUserData();
						if (!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)) {
							contact.setFriction(200F);
						} else {
							contact.setFriction(0F);
						}
					}
					return true;
				}

				return false;
			}
		}
		return false;
	}

	/**
	 * В зависимости от касаний по тачу задать направление движения игроку.
	 */
	private void processInput() {

		Player player = myWorld.getPlayer();
		if (keys.get(Keys.LEFT)) {
			player.getVelocity().x = -Player.SPEED;
		}
		if (keys.get(Keys.RIGHT)) {
			player.getVelocity().x = Player.SPEED;
		}
		if (!grounded) {
			player.setFriction(0F);
		} else {
			if (!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)) {
				player.setFriction(200F);
			} else {
				player.setFriction(0.2F);
			}
		}
		if (keys.get(Keys.UP)) {
			if (grounded) {
				player.jump();
				this.upReleased();
			}
		}
		if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT))
				|| (!keys.get(Keys.LEFT) && (!keys.get(Keys.RIGHT)))) {
			player.getVelocity().x = 0;
		}
	}
}
