package com.jmr.platformer.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.jmr.platformer.MainGame;
import com.jmr.platformer.VirtualViewport;

public abstract class Screen {
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void dispose();
	
	public abstract void resize(int width, int height);

	public abstract void show();

	public abstract void hide();

	public abstract void pause();
	
	public abstract void resume();
}
