package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class EnigmeArea extends Area {
	private EnigmePlayer player;

	public static final float SCALE_FACTOR = 22f;

	@Override
	public String getTitle() {
		return null;
	}

	@Override
	public float getCameraScaleFactor() {
		return SCALE_FACTOR;
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		setBehavior(new EnigmeBehavior(window, getTitle()));
		registerActor(new Background(this));
		return true;
	}

	public EnigmePlayer getPlayer() {
		return player;
	}

	public void setPlayer(EnigmePlayer player) {
		this.player = player;
	}

}
