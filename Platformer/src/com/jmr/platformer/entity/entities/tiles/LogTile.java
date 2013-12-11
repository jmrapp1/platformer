package com.jmr.platformer.entity.entities.tiles;

import com.jmr.platformer.TextureManager;
import com.jmr.platformer.entity.Drop;
import com.jmr.platformer.entity.EntityManager;
import com.jmr.platformer.entity.Tile;
import com.jmr.platformer.items.Item;
import com.jmr.platformer.items.ItemStack;
import com.jmr.platformer.model.BasicModel;

public class LogTile extends Tile {

	public LogTile(float x, float y) {
		super(4, x, y, new BasicModel(TextureManager.TILE_LOG, "body"), true, 75);
	}

	@Override
	protected void drop() {
		EntityManager.drops.add(new Drop(pos.x + random.nextInt(EntityManager.TILE_SIZE - (int)(EntityManager.TILE_SIZE * .25f)), pos.y, new ItemStack(new Item("Log", model), 1)));
	}
	
}
