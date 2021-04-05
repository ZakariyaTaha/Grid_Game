package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class LevelSelector extends EnigmeArea {
	private Dialog dialog;

	@Override
	public String getTitle() {
		return "LevelSelector";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		dialog = new Dialog("Tu es au Level Selector", "dialog.3", this);
		SignalDoor door1 = new SignalDoor(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.DOWN,
				new DiscreteCoordinates(1, 7), null, Logic.TRUE);
		SignalDoor door2 = new SignalDoor(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.DOWN,
				new DiscreteCoordinates(2, 7), null, Logic.TRUE);
		SignalDoor door3 = new SignalDoor(this, "Level3", new DiscreteCoordinates(5, 1), Orientation.DOWN,
				new DiscreteCoordinates(3, 7), null, Logic.TRUE);
		SignalDoor door4 = new SignalDoor(this, "Enigme1", new DiscreteCoordinates(6, 2), Orientation.DOWN,
				new DiscreteCoordinates(4, 7), null, Logic.TRUE);
		SignalDoor door5 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(5, 7), null, Logic.FALSE);
		SignalDoor door6 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(6, 7), null, Logic.FALSE);
		SignalDoor door7 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(7, 7), null, Logic.FALSE);
		SignalDoor door8 = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(5, 5), Orientation.DOWN,
				new DiscreteCoordinates(8, 7), null, Logic.FALSE);
		registerActor(door1);
		registerActor(door2);
		registerActor(door3);
		registerActor(door4);
		registerActor(door5);
		registerActor(door6);
		registerActor(door7);
		registerActor(door8);
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