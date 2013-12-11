package com.jmr.platformer.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {

	private Texture texture;
	protected Vector2 pos;
	protected int clickTime;
	private long lastClick;
	
	public Button(Texture texture, Vector2 pos) {
		this.texture = texture;
		this.pos = pos;
		clickTime = 0;
	}
	
	public Button(Texture texture, Vector2 pos, int clickTime) {
		this.texture = texture;
		this.pos = pos;
		this.clickTime = clickTime;
	}

	public boolean pressed(float x, float y) {
		if (System.currentTimeMillis() - lastClick < clickTime)
			return false;
		lastClick = System.currentTimeMillis();
		return new Rectangle(pos.x, pos.y, texture.getWidth(), texture.getHeight()).contains(x, y);
	}
	
	public boolean pressed(Vector2 vec) {
		return pressed(vec.x, vec.y);
	}
	
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}
	
	public void dispose() {
		texture.dispose();
	}
	
	public void setPos(float x, float y) {
		pos.set(x, y);
	}
	
}
