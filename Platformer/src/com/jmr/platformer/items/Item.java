package com.jmr.platformer.items;

import com.jmr.platformer.model.Model;

public class Item {

	private Model model;
	private String name;
	
	public Item(String name, Model model) {
		this.name = name;
		this.model = model;
	}
	
	public Model getModel() {
		return model;
	}
	
	public String getName() {
		return name;
	}
	
}
