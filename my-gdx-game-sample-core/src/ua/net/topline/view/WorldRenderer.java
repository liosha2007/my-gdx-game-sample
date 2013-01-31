package ua.net.topline.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import ua.net.topline.model.Brick;
import ua.net.topline.model.Player;
import ua.net.topline.model.World;

public class WorldRenderer {
	public static float CAMERA_WIDTH = 8f;
	public static float CAMERA_HEIGHT = 5f;
	protected World world;
	protected OrthographicCamera camera;
	protected ShapeRenderer shapeRenderer = new ShapeRenderer();
	
	public int width;
	public int height;
	public float ppuX;
	public float ppuY;
	
	public WorldRenderer(World world){
		this.world = world;
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		setCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
	}
	
	public void setSize(int w, int h) {
		this.width = w;
		this.height = h;
		this.ppuX = (float) w / CAMERA_WIDTH;
		this.ppuY = (float) h / CAMERA_HEIGHT;
	}
	
	public void setCamera(float x, float y) {
		this.camera.position.set(x, y, 0);
		this.camera.update();
	}
	
	public void render() {
		drawBricks();
		drawPlayer();
	}
	
	public void drawBricks() {
		this.shapeRenderer.setProjectionMatrix(camera.combined);
		this.shapeRenderer.begin(ShapeType.FilledRectangle);
		for (Brick brick : world.getBricks()) {
			Rectangle rectangle = brick.getBounds();
			float x1 = brick.getCoordinates().x + rectangle.x;
			float y1 = brick.getCoordinates().y + rectangle.y;
			this.shapeRenderer.setColor(new Color(0, 0, 0, 1));
			this.shapeRenderer.filledRect(x1, y1, rectangle.width, rectangle.height);
		}
		this.shapeRenderer.end();
	}
	
	public void drawPlayer() {
		this.shapeRenderer.setProjectionMatrix(camera.combined);
		Player player = world.getPlayer();
		this.shapeRenderer.begin(ShapeType.Rectangle);
		Rectangle rectangle = player.getBounds();
		float x1 = player.getPosition().x + rectangle.x;
		float y1 = player.getPosition().y + rectangle.y;
		this.shapeRenderer.setColor(new Color(1, 0, 0, 1));
		this.shapeRenderer.rect(x1, y1, rectangle.width, rectangle.height);
		this.shapeRenderer.end();
	}
}
