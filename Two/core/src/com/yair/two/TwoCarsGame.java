package com.yair.two;

import com.badlogic.gdx.Game;
import com.yair.two.helpers.AssetLoader;
import com.yair.two.screens.MenuScreen;

public class TwoCarsGame extends Game {
	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new MenuScreen(this));
	}


	@Override
	public void dispose () {
		AssetLoader.dispose();
	}
}
