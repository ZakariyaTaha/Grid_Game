package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level1 extends EnigmeArea {
	private Dialog dialog;

	@Override
	public String getTitle() {
		return "Level1";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		dialog = new Dialog("Tu es au 1er Level", "dialog.3", this);
		Door door = new Door(this, "LevelSelector", new DiscreteCoordinates(1, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 0), null);
		registerActor(door);
		return true;
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void update(float deltaTime) {
		if (getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(5, 1)))
			dialog.draw(getWindow());
		super.update(deltaTime);
	}
}
