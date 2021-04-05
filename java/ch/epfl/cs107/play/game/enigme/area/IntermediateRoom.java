package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class IntermediateRoom extends EnigmeArea {
	private Dialog dialog;

	@Override
	public String getTitle() {
		return "Enigme2";
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		dialog = new Dialog("Salle de repos", "dialog.3", this);
		SignalDoor door = new SignalDoor(this, "Enigme0", new DiscreteCoordinates(23, 17), Orientation.DOWN,
				new DiscreteCoordinates(7, 0), null, Logic.TRUE);
		Apple apple = new Apple(this, new DiscreteCoordinates(5, 6));
		SignalDoor doorEnigme1 = new SignalDoor(this, "Enigme0", new DiscreteCoordinates(14, 2), Orientation.DOWN,
				new DiscreteCoordinates(13, 8), null, Logic.TRUE);
		registerActor(apple);
		registerActor(door);
		registerActor(doorEnigme1);
		return true;
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void update(float deltaTime) {
		if (getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(7, 1)))
			dialog.draw(getWindow());
		super.update(deltaTime);
	}
}
