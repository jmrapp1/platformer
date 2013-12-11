package com.jmr.platformer.entity;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmr.platformer.TextureManager;
import com.jmr.platformer.model.Model;

public abstract class Tile extends Entity {

	protected static final Random random = new Random();
	protected final int id;
	protected final boolean solid;
	
	public Tile(int id, float x, float y, Model model, boolean solid, int health) {
		super(x, y, model, health);
		this.solid = solid;
		this.id = id;
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		float percent = health / maxHealth;
		Texture breaking = null;
		if (percent > 0 && percent <= .99) {
			if (percent <= .25) {
				breaking = TextureManager.BREAKING4;
			} else if (percent <= .5) {
				breaking = TextureManager.BREAKING3;
			} else if (percent <= .75) {
				breaking = TextureManager.BREAKING2;
			} else if (percent <= .99) {
				breaking = TextureManager.BREAKING1;
			}
		}
		if (breaking != null) {
			sb.draw(breaking, pos.x, pos.y);
		}
	}
	
	public void remove() {
		drop();
		EntityManager.tiles[(int) convertCoords().x][(int) convertCoords().y] = null;
	}
	
	protected abstract void drop();
	
	@Override
	public void removeHealth(int amount) {
		health -= amount;
		if (health <= 0)
			remove();
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public int getId() {
		return id;
	}
	
	public Tile getTileAbove() {
		return EntityManager.tiles[(int)convertCoords().x][(int)convertCoords().y + 1];
	}
	
	public Tile getTileLeft() {
		return EntityManager.tiles[(int)convertCoords().x - 1][(int)convertCoords().y];
	}
	
	public Tile getTileRight() {
		return EntityManager.tiles[(int)convertCoords().x + 1][(int)convertCoords().y];
	}

}
