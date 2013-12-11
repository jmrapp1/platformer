package com.jmr.platformer.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BasicModel extends Model {

	public BasicModel(Texture texture, String name) {
		super(texture, name);
	}

	@Override
	public void render(SpriteBatch sb, Vector2 pos) {
		ModelPiece mp = getPiece("body");
		mp.render(sb, pos);
	}

	@Override
	public float getWidth() {
		return getPiece("body").getWidth();
	}

	@Override
	public float getHeight() {
		return getPiece("body").getHeight();
	}

}
