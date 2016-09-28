package com.yair.two.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.yair.two.TwoCars;
import com.yair.two.consts.GameConstants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConstants.SCREEN_WIDTH;
		config.height = GameConstants.SCREEN_HEIGHT;

		new LwjglApplication(new TwoCars(), config);
	}
}
