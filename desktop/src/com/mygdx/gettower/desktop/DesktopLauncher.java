package com.mygdx.gettower.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gettower.GetTowerGameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = GetTowerGameClass.GAME_NAME;
		config.width = GetTowerGameClass.WIDTH;
		config.height = GetTowerGameClass.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new GetTowerGameClass(), config);
	}
}
