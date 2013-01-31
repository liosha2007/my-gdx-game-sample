package ua.net.topline.screen;

import ua.net.topline.model.Player;
import ua.net.topline.model.World;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GdxSampleMain implements Screen, InputProcessor {
	public static float CAMERA_WIDTH = 8f;
	public static float CAMERA_HEIGHT = 5f;
	protected OrthographicCamera camera;
	protected World world;
	
	protected int width;
	protected int height;

	public GdxSampleMain() {
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		setCamera(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f);
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
		world.draw();
	}

	public void setCamera(float x, float y) {
		this.camera.position.set(x, y, 0);
		this.camera.update();
	}


	@Override
	public void show() {
		world = new World();
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

	}

}
