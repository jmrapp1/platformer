package com.jmr.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextureManager {

	public static BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"),Gdx.files.internal("fonts/font.png"),false);;
	
	public static Texture LEFT_BUTTON = new Texture(Gdx.files.internal("left_button.png"));
	public static Texture RIGHT_BUTTON = new Texture(Gdx.files.internal("right_button.png"));
	public static Texture JUMP_BUTTON = new Texture(Gdx.files.internal("up_button.png"));
	public static Texture BACKPACK_BUTTON = new Texture(Gdx.files.internal("backpack_button.png"));
	
	public static Texture INVENTORY_BG = new Texture(Gdx.files.internal("inventory_bg.png"));
	public static Texture ITEMBOX = new Texture(Gdx.files.internal("itembox.png"));
	
	public static Texture BREAKING1 = new Texture(Gdx.files.internal("breaking1.png"));
	public static Texture BREAKING2 = new Texture(Gdx.files.internal("breaking2.png"));
	public static Texture BREAKING3 = new Texture(Gdx.files.internal("breaking3.png"));
	public static Texture BREAKING4 = new Texture(Gdx.files.internal("breaking4.png"));
	
	public static Texture TILE_GRASS = new Texture(Gdx.files.internal("tiles/grass.png"));
	public static Texture TILE_DIRT = new Texture(Gdx.files.internal("tiles/dirt.png"));
	public static Texture TILE_LOG = new Texture(Gdx.files.internal("tiles/log.png"));
	public static Texture TILE_LEAVES = new Texture(Gdx.files.internal("tiles/leaves.png"));
	public static Texture TILE_STONE = new Texture(Gdx.files.internal("tiles/stone.png"));
	
}
