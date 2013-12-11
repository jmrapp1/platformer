package com.jmr.platformer.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jmr.platformer.model.Model;

public abstract class Entity {

	protected Vector2 pos, vel, desired, lastPos;
	protected boolean jumping = false;
	protected Model model;
	protected float health, maxHealth;
	
	public Entity(float x, float y, Model model, int health) {
		this.pos = new Vector2(x , y);
		desired = new Vector2(x, y);
		lastPos = new Vector2(x, y);
		vel = Vector2.Zero;
		this.model = model;
		this.health = health;
		maxHealth = health;
	}
	
	public Vector2 getPos() {
		return pos;
	}
	
	public Vector2 getVel() {
		return vel;
	}
	
	public Vector2 getDesired() {
		return desired;
	}
	
	public Vector2 getLastPos() {
		return lastPos;
	}
	
	public float getWidth() {
		return model.getWidth();
	}
	
	public float getHeight() {
		return model.getHeight();
	}
	
	public boolean isJumping() {
		return jumping;
	}
	
	public void setJumping(boolean b) {
		jumping = b;
	}
	
	public float getHealth() {
		return health;
	}
	
	public void removeHealth(int amount) {
		health -= amount;
	}
	
	public abstract void update();
	
	public void render(SpriteBatch sb) {
		model.render(sb, pos);
	}
	
	public Vector2 convertCoords() {
		return new Vector2((int)(pos.x / getWidth()), (int)(pos.y / getHeight()));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(pos.x, pos.y, getWidth(), getHeight());
	}
	
	public void dispose() {
		model.dispose();
	}
	
}
