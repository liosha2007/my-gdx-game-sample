package ua.net.topline;

import ua.net.topline.screen.GdxSampleMain;

import com.badlogic.gdx.Game;

public class GdxSampleGame extends Game {
	protected GdxSampleMain game;

	@Override
	public void create() {
		game = new GdxSampleMain();
		setScreen(game);
	}
}
