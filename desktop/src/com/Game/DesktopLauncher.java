package com.Game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher { //desktop, don't touch unless you want to change fps, most of your work is done in core, assets is where sprites go
	public static void main (String[] arg) { //the whole idea of the game is that you're a girl at an anime con LMAO, and now u fight off ppl ig
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Holocure Made In China");

		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
		new Lwjgl3Application(new myGdxGame(), config);
	}
}
