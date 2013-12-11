package com.jmr.platformer.overlay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.jmr.platformer.TextureManager;
import com.jmr.platformer.entity.EntityManager;
import com.jmr.platformer.gui.Button;
import com.jmr.platformer.gui.ItemBox;

public class InventoryOverlay extends Overlay {

	private static final Texture bg = TextureManager.INVENTORY_BG;
	private static final ItemBox[][] itemBoxes = new ItemBox[8][4];
	
	private Button backBtn = new Button(TextureManager.RIGHT_BUTTON, new Vector2(bg.getWidth() - 40, 50 + bg.getHeight() - TextureManager.RIGHT_BUTTON.getHeight() - 20));
	private ItemBox selected = null;
	
	@Override
	public void create() {
		super.create();
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 8; x++)
				itemBoxes[x][y] = new ItemBox(new Vector2(120 + (x * 70), 70 + (y * 70)), EntityManager.player.getInventory().getItemStack(x, y), 250);
	}
	
	@Override
	public void update() {
		camera.update();
		for (int y = 0; y < 4; y++) { 
			for (int x = 0; x < 8; x++) {
				itemBoxes[x][y].setItemStack(EntityManager.player.getInventory().getItemStack(x, y));
				if (Gdx.input.isTouched()) {
					if (itemBoxes[x][y].pressed(camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY()))) {
						if (selected == null && itemBoxes[x][y] != null) {
							selected = itemBoxes[x][y];
							itemBoxes[x][y].setSelected(true);
						} else {
							if (selected == itemBoxes[x][y]) {
								selected.setSelected(false);
								selected = null;
							} else {
								EntityManager.player.getInventory().add(selected.getItemStack(), x, y);
								selected.setSelected(false);
								selected = null;
							}
						}
					}
				}
			}
		}
		if (Gdx.input.isTouched())
			if (backBtn.pressed(camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY())))
				OverlayManager.close();
	}

	@Override
	public void render() {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(bg, 100, 50);
		for (int y = 0; y < 4; y++)
			for (int x = 0; x < 8; x++)
				if (itemBoxes[x][y] != null)
					itemBoxes[x][y].render(sb);
		backBtn.render(sb);
		sb.end();
	}

	@Override
	public void close() {
		
	}

	@Override
	public void dispose() {
		
	}

}
