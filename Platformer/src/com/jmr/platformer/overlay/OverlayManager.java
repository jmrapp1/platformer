package com.jmr.platformer.overlay;

public class OverlayManager {

	private static Overlay current, last;

	public static void setOverlay(Overlay s) {
		if (current != null) {
			current.dispose();
		}
		last = current;
		current = s;
		current.create();
	}
	
	public static void goBack() {
		setOverlay(last);
	}
	
	public static Overlay getCurrent() {
		return current;
	}
	
	public static void close() {
		last = current;
		current.close();
		current = null;
	}
	
}
