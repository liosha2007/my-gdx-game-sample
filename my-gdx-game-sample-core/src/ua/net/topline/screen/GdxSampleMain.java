package ua.net.topline.screen;

import java.util.HashMap;
import java.util.Map;

import ua.net.topline.model.World;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GdxSampleMain implements Screen, InputProcessor {
	protected OrthographicCamera cam;
	protected World world;
	protected SpriteBatch spriteBatch;
	protected Map<String, AtlasRegion> textureRegions = new HashMap<String, AtlasRegion>();
	protected int width;
	protected int height;

	@Override
	public void show() {
		this.cam = new OrthographicCamera(World.CAMERA_WIDTH,
				World.CAMERA_HEIGHT);
		SetCamera(World.CAMERA_WIDTH / 2f, World.CAMERA_HEIGHT / 2f);
		spriteBatch = new SpriteBatch();
		loadTextures();
		world = new World(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false, spriteBatch, textureRegions);
		Gdx.input.setInputProcessor(world);
	}

	private void loadTextures() {
		TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("images/atlas.txt"));
		textureRegions.put("joystic", textureAtlas.findRegion("joystic"));
		textureRegions.put("player", textureAtlas.findRegion("player"));
		textureRegions.put("brick_a", textureAtlas.findRegion("brick_a"));
		textureRegions.put("brick_b", textureAtlas.findRegion("brick_b"));
		textureRegions.put("brick_c", textureAtlas.findRegion("brick_c"));
	}

	public void SetCamera(float x, float y) {
		this.cam.position.set(x, y, 0);
		this.cam.update();
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		world.setViewport(width, height, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		world.update(delta);
		world.draw();
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)) {
			return false;
		}
		return true;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
