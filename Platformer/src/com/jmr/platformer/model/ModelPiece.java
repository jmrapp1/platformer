package com.jmr.platformer.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ModelPiece {

	private Sprite sprite;
	private String name;
	private Vector2 position, offset;
	
	public ModelPiece(Texture texture, String name, Vector2 pos) {
		sprite = new Sprite(texture);
		this.name = name;
		position = pos;
		offset = Vector2.Zero;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public Vector2 getOffset() {
		return offset;
	}
	
	public String getName() {
		return name;
	}
	
	public void render(SpriteBatch sb, Vector2 origin) {
		sprite.setPosition(origin.x + position.x + offset.x, origin.y + position.y + offset.y);
		sprite.draw(sb);
	}
	
	public void setRotation(float rot) {
		sprite.setRotation(rot);
	}
	
	public void scale(float amount) {
		sprite.setScale(amount);
		sprite.setOrigin(0, 0);
	}
	
	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}
	
	public float getWidth() {
		return sprite.getWidth();
	}
	
	public float getHeight() {
		return sprite.getHeight();
	}
	
	public void dispose() {
		sprite.getTexture().dispose();
	}
	
}
