package com.jmr.platformer.entity;

import java.util.Random;

import com.jmr.platformer.entity.entities.tiles.DirtTile;
import com.jmr.platformer.entity.entities.tiles.GrassTile;
import com.jmr.platformer.entity.entities.tiles.LeavesTile;
import com.jmr.platformer.entity.entities.tiles.LogTile;
import com.jmr.platformer.entity.entities.tiles.StoneTile;

public class TerrainGenerator {
	
	private static final Random random = new Random();
	
	public TerrainGenerator() {
		generateGrassLayer();
		generateDirtLayer();
		generateStoneLayer();
	}
	
	private void generateGrassLayer() {
		int startY = EntityManager.TERRAIN_HEIGHT - 256;
        for (int x = 0; x < EntityManager.TERRAIN_WIDTH; x++) {
        	int rand = random.nextInt(99) + 1;
        	if (rand < 25)
        		generateHill(x, startY);
        	else
        		EntityManager.setTile(x, startY, new GrassTile(x * EntityManager.TILE_SIZE, startY * EntityManager.TILE_SIZE));
        	rand = random.nextInt(99) + 1;
        	if (rand < 20)
        		generateTree(x, startY + 1);
        }
    }
	
	private void generateDirtLayer() {
		int startY = EntityManager.TERRAIN_HEIGHT - 257;

        for (int x = 0; x < EntityManager.TERRAIN_WIDTH; x++) {
            for (int y = 0; y < 5; y++) {
            	EntityManager.setTile(x, startY - y, new DirtTile(x * EntityManager.TILE_SIZE, (startY - y) * EntityManager.TILE_SIZE));
            }
        }
	}
	
	private void generateHill(int oX, int oY) {
		int sX = random.nextInt(4) + 4;
		int sY = random.nextInt(3) + 2;
		int startX = 0;
		for (int y = 0; y < sY; y++) {
			for (int x = 0; x < sX; x++) {
				if (x == 0 || x == sX - 1)
					EntityManager.setTile(x + startX + oX, y + oY, new GrassTile((x + startX + oX) * EntityManager.TILE_SIZE, (y + oY) * EntityManager.TILE_SIZE));
				else
					EntityManager.setTile(x + startX + oX, y + oY, new DirtTile((x + startX + oX) * EntityManager.TILE_SIZE, (y + oY) * EntityManager.TILE_SIZE));
			}
			sX -= 2;
			startX += 1;
		}
	}
	
	private void generateTree(int oX, int oY) {
		Tile t = EntityManager.tiles[oX][oY];
		while (t != null) 
				t = t.getTileAbove();
		if (t == null) {
			int tall = random.nextInt(2) + 3;
			for (int y = 0; y < tall; y++) 
				EntityManager.setTile(oX, oY + y, new LogTile(oX * EntityManager.TILE_SIZE, (oY + y) * EntityManager.TILE_SIZE));
			
			int top = oY + tall;
			EntityManager.setTile(oX - 1, top, new LeavesTile((oX - 1) * EntityManager.TILE_SIZE, top * EntityManager.TILE_SIZE));
			EntityManager.setTile(oX, top, new LeavesTile(oX * EntityManager.TILE_SIZE, top * EntityManager.TILE_SIZE));
			EntityManager.setTile(oX + 1, top, new LeavesTile((oX + 1) * EntityManager.TILE_SIZE, top * EntityManager.TILE_SIZE));
			EntityManager.setTile(oX, top + 1, new LeavesTile(oX * EntityManager.TILE_SIZE, (top + 1) * EntityManager.TILE_SIZE));
		}
	}
	
	private void generateStoneLayer() {
		int startY = EntityManager.TERRAIN_HEIGHT - 262;

        for (int x = 0; x < EntityManager.TERRAIN_WIDTH; x++) {
            for (int y = 0; y < 30; y++) {
            	EntityManager.setTile(x, startY - y, new StoneTile(x * EntityManager.TILE_SIZE, (startY - y) * EntityManager.TILE_SIZE));
            }
        }
	}
	
}
