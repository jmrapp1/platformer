package com.jmr.platformer.entity.entities.actors;

import com.jmr.platformer.FPS;
import com.jmr.platformer.entity.Entity;
import com.jmr.platformer.entity.NPC;
import com.jmr.platformer.items.Inventory;
import com.jmr.platformer.model.PlayerModel;
import com.jmr.platformer.types.move.MoveXY;
import com.jmr.platformer.types.movespeed.MoveSpeedMedium;

public class Player extends NPC {

	private final Inventory inventory;
	
	public Player(float x, float y) {
		super(x, y, new PlayerModel(), new MoveXY(), new MoveSpeedMedium(), 100);
		this.inventory = new Inventory(8, 4);
	}

	@Override
	public void update() {
		desired.add(vel);
		vel.sub(.025f * FPS.getDelta(), .025f * FPS.getDelta());
		vel.x = Math.max(0, vel.x);
		vel.y = Math.max(0, vel.y);	
	}

	@Override
	public void onContact(Entity entity) {

	}
	
	public Inventory getInventory() {
		return inventory;
	}

}
