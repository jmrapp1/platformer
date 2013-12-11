package com.jmr.platformer.entity;

import com.jmr.platformer.model.Model;
import com.jmr.platformer.types.MoveSpeedType;
import com.jmr.platformer.types.MoveType;

public abstract class NPC extends Entity {

	protected MoveType moveType;
	protected MoveSpeedType moveSpeedType;
	
	public NPC(float x, float y, Model model, MoveType moveType, MoveSpeedType moveSpeedType, int health) {
		super(x, y, model, health);
		this.moveType = moveType;
		this.moveSpeedType = moveSpeedType;
	}
	
	public abstract void onContact(Entity entity);
	
}
