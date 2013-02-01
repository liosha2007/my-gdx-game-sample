package ua.net.topline.model;

import java.util.ArrayList;
import java.util.List;

import ua.net.topline.controller.MyContactListener;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MyWorld {
	public static float CAMERA_WIDTH = 12f;
	public static float CAMERA_HEIGHT = 8f;
	// Array of blocks
	protected ArrayList<Brick> bricks = new ArrayList<Brick>();
	// Player
	protected Player player;
	// World width
	public int width;
	// World height
	public int height;
	//
	public World world;
	//
	public List<MovingPlatform> platforms;
	//
	public MovingPlatform groundedPlatform = null;

	public MyWorld() {
		this.width = 8;
		this.height = 5;
		world = new World(new Vector2(0, -50), true);
		platforms = new ArrayList<MovingPlatform>();
		world.setContactListener(new MyContactListener(world));
		createWorld();
	}

	private void createWorld() {
		// создание игрока
		BodyDef def = new BodyDef();
		// установить тип тела
		def.type = BodyType.DynamicBody;
		// создать объект с определёнными заранее параметрами
		Body boxP = world.createBody(def);
		player = new Player(boxP);
		// переместить объект по указанным координатам
		player.getBody().setTransform(1.0f, 4.0f, 0);
		player.getBody().setFixedRotation(true);
		// создание платформы
		platforms.add(new MovingPlatform(world, 3F, 3, 1, 0.25F, 2, 0, 2));
		// создание блоков
		for (int i = 0; i < width; ++i) {
			Body boxGround = createBox(BodyType.StaticBody, 0.5F, 0.5F, 2);
			boxGround.setTransform(i, 0, 0);
			boxGround.getFixtureList().get(0).setUserData("bd");
			boxGround = createBox(BodyType.StaticBody, 0.5F, 0.5F, 0);
			boxGround.setTransform(i, height - 1, 0);
			boxGround.getFixtureList().get(0).setUserData("b");
		}
	}

	private Body createBox(BodyType type, float width, float height,
			float density) {
		BodyDef def = new BodyDef();
		def.type = type;
		Body box = world.createBody(def);
		PolygonShape poly = new PolygonShape();
		poly.setAsBox(width, height);
		box.createFixture(poly, density);
		poly.dispose();
		return box;
	}

	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	public Player getPlayer() {
		return player;
	}
}
