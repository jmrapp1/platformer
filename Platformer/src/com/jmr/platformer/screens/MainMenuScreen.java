package com.jmr.platformer.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jmr.platformer.MainGame;
import com.jmr.platformer.OrthoCameraVV;
import com.jmr.platformer.TextureManager;
import com.jmr.platformer.VirtualViewport;
import com.jmr.platformer.gui.Button;
import com.jmr.platformer.screens.Screen;

public class MainMenuScreen extends Screen{

	private Button button = new Button(TextureManager.RIGHT_BUTTON, new Vector2(300, 250));
	private OrthoCameraVV camera;
   
	@Override
	public void create() {
		camera = new OrthoCameraVV(new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT));
		resize((int)MainGame.WIDTH, (int)MainGame.HEIGHT);
	}
	
	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		button.render(sb);
		sb.end();
	}

	@Override
	public void update() {
		checkInput();
		camera.update();
	}

	@Override
	public void dispose() {
		
	}
	
	public void checkInput() {
		for (int i = 0; i < 20; i++) {
			if (Gdx.input.isTouched(i)) {
				Vector2 pos = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
				if (button.pressed(pos.x, pos.y)) {
					ScreenManager.setScreen(new GameScreen());
				}
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		VirtualViewport virtualViewport = new VirtualViewport(MainGame.WIDTH, MainGame.HEIGHT);  
        camera.setVirtualViewport(virtualViewport);  
        camera.updateViewport();  
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
		
	}	
	
}
