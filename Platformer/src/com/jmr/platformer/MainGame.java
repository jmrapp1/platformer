package com.jmr.platformer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.platformer.screens.MainMenuScreen;
import com.jmr.platformer.screens.ScreenManager;

public class MainGame extends Game {
	
	public static float WIDTH = 800, HEIGHT = 480;
	public static float ASPECT_RATIO = (float)WIDTH/(float)HEIGHT;
	private static SpriteBatch sb;
	
	@Override
	public void create() {	
		sb = new SpriteBatch();
		FPS.setup();
		ScreenManager.setScreen(new MainMenuScreen());
		TextureManager.font.setScale(.4f);
		TextureManager.font.setColor(Color.BLACK);
	}

	@Override
	public void dispose() {
		ScreenManager.getCurrent().dispose();
	}

	@Override
	public void render() {	
		ScreenManager.getCurrent().update();
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(.529f, .807f, .951f, 1);
        FPS.calcAverageTick(Gdx.graphics.getDeltaTime());
		ScreenManager.getCurrent().render(sb);
	}

	@Override
	public void resize(int width, int height) {
		ScreenManager.getCurrent().resize(width, height);
	}

	@Override
	public void pause() {
		ScreenManager.getCurrent().pause();
	}

	@Override
	public void resume() {
		ScreenManager.getCurrent().resume();
	}
	
}
