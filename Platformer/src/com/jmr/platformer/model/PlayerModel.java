package com.jmr.platformer.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PlayerModel extends Model {

	public PlayerModel() {
		super(Gdx.files.internal("models/player.txt"));
	}

	@Override
	public void render(SpriteBatch sb, Vector2 pos) {
		ModelPiece mp = getPiece("body");
		mp.render(sb, pos);
		mp = getPiece("head");
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
