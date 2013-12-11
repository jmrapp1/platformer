package com.jmr.platformer.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jmr.platformer.FPS;
import com.jmr.platformer.MainGame;
import com.jmr.platformer.entity.entities.actors.Player;

public class EntityManager {

	public static int TERRAIN_WIDTH = 1000, TERRAIN_HEIGHT = 2000, TILE_SIZE = 64;
	public static float GRAVITY = -.4f;
	
	public static Tile[][] tiles = new Tile[TERRAIN_WIDTH][TERRAIN_HEIGHT];
	public static Array<Drop> drops = new Array<Drop>();
	
    public static final Player player = new Player(400 * 64, (TERRAIN_HEIGHT - 230f) * 64);
    
	public EntityManager() {
		new TerrainGenerator();
	}
	
	public static void setTile(int x, int y, Tile tile) {
		if (x < TERRAIN_WIDTH && y < TERRAIN_HEIGHT && x >= 0 && y >= 0)
			tiles[x][y] = tile;
	}
	
	public Array<Tile> getTilesAround(Vector2 vec) {
		int cX = (int) (vec.x / TILE_SIZE);
		int cY = (int) (vec.y / TILE_SIZE);
		Array<Tile> ret = new Array<Tile>();
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {
				if (x >= 0 && y >= 0 && x < TERRAIN_WIDTH && y < TERRAIN_HEIGHT)
					ret.add(tiles[Math.max(0,cX - x + 8)][Math.max(0, cY - y + 8)]);
			}
		}
		return ret;
	}
	
	public Array<Drop> getDropsAround(Vector2 vec) {
		Array<Drop> ret = new Array<Drop>();
		float cX = vec.x - (MainGame.WIDTH / 2) - 100;
		float cY = vec.y - (MainGame.HEIGHT / 2) - 100;
		Rectangle bound = new Rectangle(cX, cY, MainGame.WIDTH + 200, MainGame.HEIGHT + 200);
		for (Drop d : drops) 
			if (d.getBounds().overlaps(bound))
				ret.add(d);
		return ret;
	}
	
	private void checkCollisions(Entity entity) {
		Array<Tile> around = getTilesAround(entity.getPos());
		Rectangle bound = new Rectangle(entity.getDesired().x, entity.getDesired().y, entity.getWidth(), entity.getHeight());
		for (Tile t : around) {
			if (t != null && bound.overlaps(t.getBounds())) {
				Rectangle prev = new Rectangle(entity.getLastPos().x, entity.getLastPos().y, entity.getWidth(), entity.getHeight());
				if (bound.y <= t.getBounds().y + t.getHeight() && prev.y >= t.getBounds().y + t.getHeight()) {//Bottom
					entity.getDesired().y = t.getPos().y + t.getHeight() + 1;
					entity.setJumping(false);
				} else if (bound.y + bound.height >= t.getBounds().y && prev.y + prev.height <= t.getBounds().y) { //Top
					entity.getDesired().y = t.getPos().y - entity.getHeight();
				}
				
				Rectangle bLeft = new Rectangle(entity.getDesired().x - entity.getWidth() + 1, entity.getDesired().y + (entity.getHeight() / 2), entity.getWidth(), 2);
				Rectangle bRight = new Rectangle(entity.getDesired().x + entity.getWidth(), entity.getDesired().y + (entity.getHeight() / 2), entity.getWidth() - 1, 2);
				
				if (bound.x + bound.width >= t.getBounds().x && prev.x + prev.width <= t.getBounds().x && bRight.overlaps(t.getBounds())) { //Right
					entity.getDesired().x = t.getBounds().x - entity.getWidth();
				} else if (bound.x <= t.getBounds().x + t.getWidth() && prev.x <= t.getBounds().x + t.getWidth() && bLeft.overlaps(t.getBounds())) {
					entity.getDesired().x = t.getBounds().x + t.getWidth();
				}
			}
		}
		entity.getLastPos().set(entity.getPos());
		entity.getPos().set(entity.getDesired());
	}
	
	public void update() {
		/*
		for (Tile t : getTilesAround(player.getPos()))
		 
			if (t != null)
				t.update();
		*/
		
		for (Drop d : getDropsAround(player.getPos())) {
			d.update();
			d.getDesired().add(0, GRAVITY * FPS.getDelta());
			checkCollisions(d);
			if (player.getBounds().overlaps(d.getBounds())) {
				if (d.canPickup()) {
					player.getInventory().add(d.getItemStack());
					drops.removeValue(d, false);
				}
			}
		}
		
		player.update();
		player.getDesired().add(0, GRAVITY * FPS.getDelta());
		checkCollisions(player);
	}
	
	public void render(SpriteBatch sb) {
		for (Tile t : getTilesAround(player.getPos()))
			if (t != null)
				t.render(sb);
		
		for (Drop d : getDropsAround(player.getPos()))
			d.render(sb);
		
		player.render(sb);
	}
	
	public void dispose() {
		player.dispose();
	}
	
}
