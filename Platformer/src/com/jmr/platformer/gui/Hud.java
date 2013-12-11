package com.jmr.platformer.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.jmr.platformer.FPS;
import com.jmr.platformer.MainGame;
import com.jmr.platformer.OrthoCameraVV;
import com.jmr.platformer.TextureManager;
import com.jmr.platformer.VirtualViewport;
import com.jmr.platformer.entity.EntityManager;
import com.jmr.platformer.overlay.InventoryOverlay;
import com.jmr.platformer.overlay.OverlayManager;

public class Hud implements GestureListener {

	private OrthoCameraVV hudCamera, gameCamera;
	private SpriteBatch hudSb;
	
    private final Button btnLeft = new Button(TextureManager.LEFT_BUTTON, new Vector2(20, 20)); 
    private final Button btnRight = new Button(TextureManager.RIGHT_BUTTON, new Vector2(160, 20)); 
    private final Button btnJump = new Button(TextureManager.JUMP_BUTTON, new Vector2(MainGame.WIDTH - 148, 20)); 
    private final Button btnBackpack = new Button(TextureManager.BACKPACK_BUTTON, new Vector2(MainGame.WIDTH - 84, 94)); 
	private final EntityManager entityManager;
	
	private long lastBreak = 0;
    
	public Hud(EntityManager entityManager, OrthoCameraVV gameCamera) {
		hudCamera = new OrthoCameraVV(new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT));
		hudSb = new SpriteBatch();
		this.gameCamera = gameCamera;
		this.entityManager = entityManager;
		Gdx.input.setInputProcessor(new GestureDetector(this));
	}
	
	public void update() {
		checkInput();
		hudCamera.update();
	}
	
	public void render() {
		hudSb.setProjectionMatrix(hudCamera.combined);
		hudSb.begin();
		btnLeft.render(hudSb);
		btnRight.render(hudSb);
		btnJump.render(hudSb);
		btnBackpack.render(hudSb);
		hudSb.end();
	}	
	
	public void resize() {
        VirtualViewport virtualViewport = new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT);  
        hudCamera.setVirtualViewport(virtualViewport);  
        hudCamera.updateViewport();  
	}
	
	
	public void checkInput() {
		for (int i = 0; i < 20; i++) {
			if (Gdx.input.isTouched(i)) {
				Vector2 pos = hudCamera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
				if (btnLeft.pressed(pos.x, pos.y))
					EntityManager.player.getDesired().add(-.3f * FPS.getDelta(), 0);
				if (btnRight.pressed(pos.x, pos.y))
					EntityManager.player.getDesired().add(.3f * FPS.getDelta(), 0);
				if (btnBackpack.pressed(pos.x, pos.y))
					OverlayManager.setOverlay(new InventoryOverlay());
				
				float startX = gameCamera.position.x - MainGame.WIDTH / 2;
				float startY = gameCamera.position.y - MainGame.HEIGHT / 2;
				float resultX = startX + pos.x;
				float resultY = startY + pos.y;
				if (new Vector2(resultX, resultY).dst(EntityManager.player.getPos()) <= 150 && System.currentTimeMillis() - lastBreak >= 1000) {
					if (EntityManager.tiles[(int)(resultX / EntityManager.TILE_SIZE)][(int)(resultY / EntityManager.TILE_SIZE)] != null) {
						EntityManager.tiles[(int)(resultX / EntityManager.TILE_SIZE)][(int)(resultY / EntityManager.TILE_SIZE)].removeHealth(25);
						lastBreak = System.currentTimeMillis();
					} 
				}
			}
		}
	}
	
	public void resume() {
		hudSb = new SpriteBatch();
	}
	
	public void dispose() {
		hudSb.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if (OverlayManager.getCurrent() == null) {
			Vector2 pos = hudCamera.unprojectCoordinates(x, y);
			if (btnJump.pressed(pos.x, pos.y) && !EntityManager.player.isJumping()) {
				EntityManager.player.getVel().add(0, .93f * FPS.getDelta());
				EntityManager.player.setJumping(true);
			}
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return false;
	}
	
}
