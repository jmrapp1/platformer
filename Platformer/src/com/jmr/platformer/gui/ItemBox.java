package com.jmr.platformer.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.jmr.platformer.TextureManager;
import com.jmr.platformer.items.ItemStack;
import com.jmr.platformer.model.Model;

public class ItemBox extends Button {

	private ItemStack itemStack;
	private boolean selected = false;
	
	public ItemBox(Vector2 pos, ItemStack itemStack) {
		super(TextureManager.ITEMBOX, pos);
		this.itemStack = itemStack;
	}
	
	public ItemBox(Vector2 pos, ItemStack itemStack, int clickTime) {
		super(TextureManager.ITEMBOX, pos, clickTime);
		this.itemStack = itemStack;
	}

	@Override
	public void render(SpriteBatch sb) {
		super.render(sb);
		if (itemStack != null && itemStack.getCount() == 0)
			itemStack = null;
		if (itemStack != null) {
			Model model = itemStack.getItem().getModel();
			model.scale(.45f);
			if (!selected)
				model.render(sb, new Vector2(pos.x + 16, pos.y + 16));
			else
				model.render(sb, new Vector2(pos.x + 10, pos.y + 30));
			if ((itemStack.getCount() + "").length() == 1)
				TextureManager.font.draw(sb, itemStack.getCount() + "", pos.x + 52, pos.y + 55);
			else
				TextureManager.font.draw(sb, itemStack.getCount() + "", pos.x + 44, pos.y + 55);
		}
	}
	
	public ItemStack getItemStack() {
		return itemStack;
	}
	
	public void setItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
	}
	
	public void setSelected(boolean b) {
		selected = b;
	}
	
}
