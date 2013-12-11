package com.jmr.platformer.items;

import java.util.ArrayList;

public class Inventory {

	private final int maxSizeX, maxSizeY;
	public final ItemStack[][] items;
	
	public Inventory(int maxSizeX, int maxSizeY) {
		this.maxSizeX = maxSizeX;
		this.maxSizeY = maxSizeY;
		items = new ItemStack[maxSizeX][maxSizeY];
	}
	
	public boolean add(ItemStack item) { //if inventory is full it returns false
		if (isFull() || item.getCount() > ItemStack.MAX_AMOUNT * 2)
			return false;

		ArrayList<ItemStack> all = getItem(item.getItem().getName());
		ItemStack use = null;
		for (ItemStack i : all) 
			if (i != null && !i.isFull()) 
				use = i;

		if (use == null) {
			if (!isFull()) {
				if (item.getCount() > ItemStack.MAX_AMOUNT) {
					//int times = item.getCount() / ItemStack.MAX_AMOUNT // Used to see how many multiples of maxamount it is
					int remainder = item.getCount() - ItemStack.MAX_AMOUNT;
					addToOpenSpot(new ItemStack(item.getItem(), remainder));
					item.setCount(ItemStack.MAX_AMOUNT);
				}
				addToOpenSpot(item);
			}
		} else {
			int remainder = use.add(item);
			if (remainder > 0)
				add(new ItemStack(item.getItem(), remainder)); //loops through till it places the item in the inventory
		}
		return true;
	}
	
	public boolean add(ItemStack item, int x, int y) {
		if (item == null || item.getCount() > ItemStack.MAX_AMOUNT * 2)
			return false;

		ItemStack use = items[x][y];

		if (use != null && !use.getItem().getName().equalsIgnoreCase(item.getItem().getName())) {
			return false;
		} else {
			int remainder = 0;
			if (use != null)
				remainder = use.add(item);
			else
				items[x][y] = item.copy();
			item.setCount(remainder);
		}
		return true;
	}
	
	public int sub(ItemStack item) { //Returns remainder if the item isnt in inventory
		ArrayList<ItemStack> all = getItem(item.getItem().getName());
		ItemStack use = null;
		for (ItemStack i : all) 
			if (i != null) 
				use = i;
		if (use == null)
			return item.getCount();
		
		int remain = use.sub(item);
		if (item.getCount() <= 0)
			remove(item);
		if (use.getCount() <= 0)
			remove(use);
		if (remain > 0) {
			sub(new ItemStack(item.getItem(), remain));
		}
		return 0;
	}
	
	public ArrayList<ItemStack> getItem(String name) {
		ArrayList<ItemStack> all = new ArrayList<ItemStack>();
		for (int y = 0; y < maxSizeY; y++)
			for (int x = 0; x < maxSizeX; x++)
				if (items[x][y] != null) 
					if (items[x][y].getItem().getName().equalsIgnoreCase(name))
						all.add(items[x][y]);
		return all;
	}
	
	private void addToOpenSpot(ItemStack itemStack) {
		for (int y = 0; y < maxSizeY; y++) {
			for (int x = 0; x < maxSizeX; x++) {
				if (items[x][y] == null) {
					items[x][y] = itemStack;
					return;
				}
			}
		}
	}
	
	private void remove(ItemStack item) {
		for (int y = 0; y < maxSizeY; y++)
			for (int x = 0; x < maxSizeX; x++) 
				if (items[x][y] == item)
					items[x][y] = null;
	}
	
	public boolean isFull() {
		for (int y = 0; y < maxSizeY; y++) 
			for (int x = 0; x < maxSizeX; x++) 
				if (items[x][y] == null)
					return false;
		return true;
	}
	
	public int sizeX() {
		return items[0].length;
	}
	
	public int sizeY() {
		return items[1].length;
	}
	
	public ItemStack getItemStack(int x, int y) {
		return items[x][y];
	}
	
}
