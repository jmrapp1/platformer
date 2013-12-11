package com.jmr.platformer.entity;

import com.jmr.platformer.model.Model;

public abstract class InteractableTile extends Tile {

	public InteractableTile(int id, int x, int y, Model model, boolean solid, int health) {
		super(id, x, y, model, solid, health);
	}

	public abstract void onInteracting(Entity entity);
	
}
