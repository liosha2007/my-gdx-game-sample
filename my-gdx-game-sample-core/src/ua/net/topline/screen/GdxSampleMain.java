package ua.net.topline.screen;

import ua.net.topline.controller.WorldController;
import ua.net.topline.model.Player;
import ua.net.topline.model.World;
import ua.net.topline.view.WorldRenderer;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;

public class GdxSampleMain implements Screen, InputProcessor {
	protected World world;
	protected WorldRenderer renderer;
	protected WorldController controller;
	
	protected int width;
	protected int height;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		controller.update(delta);
		renderer.render();
	}

	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
		this.width = width;
		this.height = height;
	}

	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)){
		return false;
		}
		ChangeNavigation(screenX, screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!Gdx.app.getType().equals(ApplicationType.Android)){
		return false;
		}
		controller.releaseWay();
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		ChangeNavigation(screenX, screenY);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void ChangeNavigation(int x, int y) {
		controller.releaseWay();
		if (height - y > controller.player.getPosition().y * renderer.ppuY){
			controller.upPressed();
		}
		if (height - y < controller.player.getPosition().y * renderer.ppuY){
			controller.downPressed();
		}
		if (x > (controller.player.getPosition().x + Player.SIZE) * renderer.ppuX){
			controller.rightPressed();
		}
		if (x < controller.player.getPosition().x * renderer.ppuX){
			controller.leftPressed();
		}
	}

}
