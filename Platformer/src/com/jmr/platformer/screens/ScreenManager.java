package com.jmr.platformer.screens;


public class ScreenManager {

	private static Screen current, last;

	public static void setScreen(Screen s) {
		if (current != null) {
			current.dispose();
		}
		last = current;
		current = s;
		current.create();
	}
	
	public static void goBack() {
		setScreen(last);
	}
	
	public static Screen getCurrent() {
		return current;
	}
	
}
