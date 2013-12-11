package com.jmr.platformer.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jmr.platformer.items.ItemStack;

public class Drop extends Entity {

	private final ItemStack item;
	private long startTime;
	
	public Drop(float x, float y, ItemStack item) {
		super(x, y, item.getItem().getModel(), 100);
		this.item = item;
		startTime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		
	}
	
	@Override
	public void render(SpriteBatch sb) {
		model.scale(.25f);
		model.render(sb, pos);
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(pos.x, pos.y, getWidth(), getHeight());
	}
	
	@Override
	public float getWidth() {
		return super.getWidth() * .25f;
	}
	
	@Override
	public float getHeight() {
		return super.getHeight() * .25f;
	}
	
	public ItemStack getItemStack() {
		return item;
	}
	
	public boolean canPickup() {
		return System.currentTimeMillis() - startTime >= 1500;
	}

}
