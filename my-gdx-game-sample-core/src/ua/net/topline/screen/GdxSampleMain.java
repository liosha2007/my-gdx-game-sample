package ua.net.topline.screen;

import ua.net.topline.controller.WorldController;
import ua.net.topline.model.MyWorld;
import ua.net.topline.view.WorldRenderer;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;

public class GdxSampleMain implements Screen, InputProcessor {
	private WorldRenderer renderer;
	public MyWorld world;
	public Texture texture;
	public int width;
	public int height;
	private WorldController controller;

	@Override
	public void show() {
		MyWorld.CAMERA_WIDTH = MyWorld.CAMERA_HEIGHT * Gdx.graphics.getWidth()
				/ Gdx.graphics.getHeight();
		world = new MyWorld();
		renderer = new WorldRenderer(world, MyWorld.CAMERA_WIDTH,
				MyWorld.CAMERA_HEIGHT, true);
		controller = new WorldController(world/* , renderer */);
		Gdx.input.setInputProcessor(this);
	}

	public boolean touchMoved(int x, int y) {
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
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public boolean keyDown(int keycode) {
		return true;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		renderer.render(delta);
	}

	@Override
	public boolean keyUp(int keycode) {
		return true;
	}

	private void ChangeNavigation(int x, int y) {
		controller.resetWay();
		if (height - y > world.getPlayer().getPosition().y * renderer.ppuY) {
			controller.upPressed();
		}
		if (x < world.getPlayer().getPosition().x * renderer.ppuX) {
			controller.leftPressed();
		}
		if (x > (world.getPlayer().getPosition().x) * renderer.ppuX) {
			controller.rightPressed();
		}
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)) {
			return false;
		}
		ChangeNavigation(x, y);
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)) {
			return false;
		}
		controller.resetWay();
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		ChangeNavigation(x, y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
