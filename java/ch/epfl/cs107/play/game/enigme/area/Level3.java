package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Torch;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.signal.logic.Or;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {
	private Dialog dialog;

	@Override
	public String getTitle() {
		return "Level3";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		dialog = new Dialog("Tu es au 3eme Level", "dialog.3", this);
		Key key = new Key(this, new DiscreteCoordinates(1, 3));
		Torch torche = new Torch(this, new DiscreteCoordinates(7, 5), false);
		PressurePlate plate = new PressurePlate(this, new DiscreteCoordinates(9, 8));
		PressureSwitch button1 = new PressureSwitch(this, new DiscreteCoordinates(4, 4));
		PressureSwitch button2 = new PressureSwitch(this, new DiscreteCoordinates(5, 4));
		PressureSwitch button3 = new PressureSwitch(this, new DiscreteCoordinates(6, 4));
		PressureSwitch button4 = new PressureSwitch(this, new DiscreteCoordinates(5, 5));
		PressureSwitch button5 = new PressureSwitch(this, new DiscreteCoordinates(4, 6));
		PressureSwitch button6 = new PressureSwitch(this, new DiscreteCoordinates(5, 6));
		PressureSwitch button7 = new PressureSwitch(this, new DiscreteCoordinates(6, 6));
		Lever lever1 = new Lever(this, new DiscreteCoordinates(10, 5));
		Lever lever2 = new Lever(this, new DiscreteCoordinates(9, 5));
		Lever lever3 = new Lever(this, new DiscreteCoordinates(8, 5));
		SignalDoor door = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.DOWN,
				new DiscreteCoordinates(5, 9), null, key);
		List<Logic> list1 = new LinkedList<Logic>();
		list1.add(button1);
		list1.add(button2);
		list1.add(button3);
		list1.add(button4);
		list1.add(button5);
		list1.add(button6);
		list1.add(button7);
		SignalRock rock1 = new SignalRock(this, new DiscreteCoordinates(5, 8), new MultipleAnd(list1));
		SignalRock rock2 = new SignalRock(this, new DiscreteCoordinates(6, 8), plate);
		List<Logic> list2 = new LinkedList<Logic>();
		list2.add(lever1);
		list2.add(lever2);
		list2.add(lever3);
		SignalRock rock3 = new SignalRock(this, new DiscreteCoordinates(4, 8),
				new Or(new LogicNumber(5f, list2), torche));
		// LogicNumber avec 5f donne exactement true,false,true
		registerActor(rock1);
		registerActor(rock2);
		registerActor(rock3);
		registerActor(door);
		registerActor(plate);
		registerActor(key);
		registerActor(button1);
		registerActor(button2);
		registerActor(button3);
		registerActor(button4);
		registerActor(button5);
		registerActor(button6);
		registerActor(button7);
		registerActor(lever1);
		registerActor(lever2);
		registerActor(lever3);
		registerActor(torche);
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
