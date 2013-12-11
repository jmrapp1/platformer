package com.jmr.platformer.overlay;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.platformer.MainGame;
import com.jmr.platformer.OrthoCameraVV;
import com.jmr.platformer.VirtualViewport;

public abstract class Overlay {

	protected OrthoCameraVV camera;
	protected SpriteBatch sb;
	
	public void create() {
		camera = new OrthoCameraVV(new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT));
		sb = new SpriteBatch();
		resize();
	}

	public abstract void update();
	
	public abstract void render();
	
	public abstract void close();
	
	public abstract void dispose();
	
	public void resize() {
		VirtualViewport virtualViewport = new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT);  
        camera.setVirtualViewport(virtualViewport);  
        camera.updateViewport(); 
	}
	
}
