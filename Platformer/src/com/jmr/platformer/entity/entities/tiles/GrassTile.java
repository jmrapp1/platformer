package com.jmr.platformer.entity.entities.tiles;

import com.jmr.platformer.TextureManager;
import com.jmr.platformer.entity.Drop;
import com.jmr.platformer.entity.EntityManager;
import com.jmr.platformer.entity.Tile;
import com.jmr.platformer.items.Item;
import com.jmr.platformer.items.ItemStack;
import com.jmr.platformer.model.BasicModel;

public class GrassTile extends Tile {

	public GrassTile(float x, float y) {
		super(2, x, y, new BasicModel(TextureManager.TILE_GRASS, "body"), true, 50);
	}

	@Override
	public void update() {
		if (getTileAbove() != null)
			EntityManager.tiles[(int) convertCoords().x][(int) convertCoords().y] = new DirtTile(pos.x, pos.y);
	}
	
	@Override
	protected void drop() {
		EntityManager.drops.add(new Drop(pos.x + random.nextInt(EntityManager.TILE_SIZE - (int)(EntityManager.TILE_SIZE * .25f)), pos.y, new ItemStack(new Item("Dirt", model), 1)));
	}

}
