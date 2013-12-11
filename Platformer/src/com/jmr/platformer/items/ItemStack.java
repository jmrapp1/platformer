package com.jmr.platformer.items;

public class ItemStack {

	public static final int MAX_AMOUNT = 64;
	private final Item item;
	private int count;
	
	public ItemStack(Item item, int count) {
		this.item = item;
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public Item getItem() {
		return item;
	}
	
	public boolean isFull() {
		return count >= MAX_AMOUNT;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int sub(int amount) { //returns remainder
		if (amount > count) {
			int ret = Math.abs(count - amount); //returns remaining amount;
			count = 0;
			return ret;
		} else
			count -= amount;
		return 0;
	}
	
	public int add(int amount) { //returns remainder
		if (amount + count > MAX_AMOUNT) {
			int ret = (amount + count) - MAX_AMOUNT; //returns remaining amount;
			count = 64;
			return ret;
		} else 
			count += amount;
		return 0;
	}
	
	public int add(ItemStack i) {
		if (item.getName().equalsIgnoreCase(i.getItem().getName())) {
			int ret = add(i.getCount());
			i.sub(i.getCount());
			return ret;
		}
		return -1;
	}
	
	public int sub(ItemStack i) {
		if (item.getName().equalsIgnoreCase(i.getItem().getName())) {
			int ret = sub(i.getCount());
			i.sub(i.getCount());
			return ret;
		}
		return -1;
	}
	
	public ItemStack copy() {
		return new ItemStack(item, count);
	}
	
}
