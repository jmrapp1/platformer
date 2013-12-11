package com.jmr.platformer.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.platformer.MainGame;
import com.jmr.platformer.OrthoCameraVV;
import com.jmr.platformer.TextureManager;
import com.jmr.platformer.VirtualViewport;
import com.jmr.platformer.entity.EntityManager;
import com.jmr.platformer.gui.Hud;
import com.jmr.platformer.items.Item;
import com.jmr.platformer.items.ItemStack;
import com.jmr.platformer.model.BasicModel;
import com.jmr.platformer.overlay.OverlayManager;

public class GameScreen extends Screen {

	private EntityManager entityManager;
	private Hud hud;
	private OrthoCameraVV camera;	
	
	@Override
	public void create() {
		camera = new OrthoCameraVV(new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT));
		entityManager = new EntityManager();
		hud = new Hud(entityManager, camera);
		resize((int)MainGame.WIDTH, (int)MainGame.HEIGHT);
		EntityManager.player.getInventory().add(new ItemStack(new Item("Dirt", new BasicModel(TextureManager.TILE_DIRT, "body")), 63));
	}

	@Override
	public void update() {
		camera.setPosition(EntityManager.player.getPos().x, EntityManager.player.getPos().y);
		camera.update();
		if (OverlayManager.getCurrent() == null)
			hud.update();
		else
			OverlayManager.getCurrent().update();
		entityManager.update();
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
		sb.end();
		if (OverlayManager.getCurrent() == null)
			hud.render();
		else
			OverlayManager.getCurrent().render();
	}

	@Override
	public void resize(int width, int height) {
		VirtualViewport virtualViewport = new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT);  
        camera.setVirtualViewport(virtualViewport);  
        camera.updateViewport();  
        
        if (OverlayManager.getCurrent() == null)
        	hud.resize();
        else
        	OverlayManager.getCurrent().resize();
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		hud.resume();
	}

	@Override
	public void dispose() {
		hud.dispose();
	}
	
}
