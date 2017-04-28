package com.mygdx.gettower;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.gettower.GetTowerGameClass;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		// TODO change orientation in AndroidManifest.xml
		// android:screenOrientation="landscape"
		// android:screenOrientation="portrait"
		// android:screenOrientation="sensor"
		initialize(new GetTowerGameClass(), config);
	}
}
