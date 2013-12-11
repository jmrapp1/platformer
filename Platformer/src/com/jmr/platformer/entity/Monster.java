package com.jmr.platformer.entity;

import com.jmr.platformer.model.Model;
import com.jmr.platformer.types.DamageType;
import com.jmr.platformer.types.MoveSpeedType;
import com.jmr.platformer.types.MoveType;

public abstract class Monster extends NPC {

	protected DamageType damageType;
	
	public Monster(int x, int y, Model model, MoveType moveType, MoveSpeedType moveSpeedType, DamageType damageType, int health) {
		super(x, y, model, moveType, moveSpeedType, health);
		this.damageType = damageType;
	}
	
	@Override
	public void update() {
		//Do Movement and Jumping
	}

}
