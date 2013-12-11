package com.jmr.platformer;

import com.badlogic.gdx.Gdx;

public class FPS {

	private static int tickIndex;
	private static float tickSumm, delta;
	private static float[] tickList = new float[300];
	private static long lastUpdate;
	private static boolean first = true;
	
	public static void setup() {
		lastUpdate = System.currentTimeMillis();
	}
	
	public static void calcAverageTick(float newTick) {
		/*tickSum -= tickList[tickIndex];
		tickSum+= newTick;
		
		tickList[tickIndex] = newTick;
		tickIndex++;
		float tickSum = 0;
		for (float f : tickList)
			tickSum += f;
		
		if (tickIndex == 300)
			tickIndex = 0;
		delta = ((float)tickSum/300);
		*/
		if (first) {
			for (int i = 0; i < 300; i++)
				tickList[i] = System.currentTimeMillis() - lastUpdate;
			first = false;
		}
		
		float fps = System.currentTimeMillis() - lastUpdate;
		if (first) {
			for (int i = 0; i < 300; i++)
				tickList[i] = fps;
			first = false;
		}
		
		float av = 0;
		for (float i : tickList)
			av += i;
		delta = av / 300;
		
		lastUpdate = System.currentTimeMillis();
	}
	
	public static float getDelta() {
		if (delta > 16)
			delta = 16;
		return delta;
	}
	
}
