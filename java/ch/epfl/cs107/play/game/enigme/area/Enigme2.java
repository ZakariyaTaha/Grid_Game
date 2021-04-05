package ch.epfl.cs107.play.game.enigme.area;

import java.awt.Color;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Bow;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Helmet;
import ch.epfl.cs107.play.game.enigme.actor.Mob3;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Victim;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class Enigme2 extends EnigmeArea {
	private Dialog dialog;
	private Dialog dialogArmes;
	private Dialog dialogPassage;
	private Dialog dialogSurprise;
	private Mob3 ultimateMaster;
	private Helmet helmet;
	private static boolean temp1 = true;
	private static boolean temp2 = true;
	private static boolean temp3 = true;

	@Override
	public String getTitle() {
		return "Enigme0";
	}

	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		dialog = new Dialog("Tu dois battre le boss et sauvant la victime, tu ne peux plus reculer maintenant",
				"dialog.3", this);
		dialogArmes = new Dialog(
				"Pour ce faire, il faut prendre le casque et l'arc derrière la grange, sinon tu mourras !", "dialog.3",
				this);
		dialogPassage = new Dialog(
				"Maintenant rentre dans la grange, mange une pomme pour te redonner des forces et emprunte le passage secret, tu pourras le surprendre",
				"dialog.3", this);
		dialogSurprise = new Dialog(
				"Tu y es presque, le monstre ne s'est pas rendu compte de ta présence attaque le par surprise et finis-le !",
				"dialog.3", this);
		SignalDoor doorEnigme2 = new SignalDoor(this, "Enigme2", new DiscreteCoordinates(7, 1), Orientation.DOWN,
				new DiscreteCoordinates(23, 18), null, Logic.TRUE);
		SignalRock rock1 = new SignalRock(this, new DiscreteCoordinates(9, 29), Logic.FALSE);
		SignalRock rock2 = new SignalRock(this, new DiscreteCoordinates(10, 29), Logic.FALSE);
		SignalRock rock3 = new SignalRock(this, new DiscreteCoordinates(11, 29), Logic.FALSE);
		SignalDoor sortiePassage = new SignalDoor(this, "Enigme2", new DiscreteCoordinates(13, 7), Orientation.DOWN,
				new DiscreteCoordinates(15, 2), null, Logic.TRUE);
		Bow bow = new Bow(this, new DiscreteCoordinates(25, 25));
		helmet = new Helmet(this, new DiscreteCoordinates(26, 25));
		ultimateMaster = new Mob3(this, new DiscreteCoordinates(10, 2), Logic.TRUE, new And(helmet, bow));
		Victim victim = new Victim(this, new DiscreteCoordinates(10, 1));
		registerActor(rock1);
		registerActor(rock2);
		registerActor(rock3);
		registerActor(bow);
		registerActor(helmet);
		registerActor(sortiePassage);
		registerActor(doorEnigme2);
		registerActor(ultimateMaster);
		registerActor(victim);
		return true;
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void update(float deltaTime) {
		if (getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(10, 28)))
			dialog.draw(getWindow());
		if (!getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(10, 28)) && temp1)
			dialogArmes.draw(getWindow());
		if (helmet.isOn() && temp2) {
			temp1 = false;
			dialogPassage.draw(getWindow());
		}
		if (getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(14, 2)) && temp3) {
			temp2 = false;
			dialogSurprise.draw(getWindow());
		}
		if (ultimateMaster.getAppearance().isOn() == false) {
			(new TextGraphics("BRAVO, mission terminée !", 1f, Color.RED)).draw(getWindow());
		}
		super.update(deltaTime);
	}
}
