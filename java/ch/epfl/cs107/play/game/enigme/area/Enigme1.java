package ch.epfl.cs107.play.game.enigme.area;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Mob1;
import ch.epfl.cs107.play.game.enigme.actor.Mob2;
import ch.epfl.cs107.play.game.enigme.actor.NonPlayingCharacter;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Shield;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.Sword;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea {
	private Dialog dialog;
	private Dialog dialogNPC;
	private NonPlayingCharacter npc;
	private PressurePlate plate;
	private static boolean temp = true;
	private Mob1 mob1;
	private Dialog dialogMob;
	private static boolean temp2 = true;
	private Mob2 master1;
	private static boolean temp3 = true;
	private Dialog dialogMaster1;
	private Dialog dialogFinMission1;
	private static boolean temp4 = true;
	private Dialog dialogMobs;
	private static boolean temp5;

	@Override
	public String getTitle() {
		return "Enigme1";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		dialogNPC = new Dialog(
				"Le monstre là-haut m'a tué alors que j'essayais de trouver la clef pour sauver mon ami, commence par trouver comment bouger les pierre puis ensuite équipe toi d'une épée et d'un bouclier pour prendre la clef, sinon tu mourras",
				"dialog.3", this);
		dialog = new Dialog(
				"Un enfant a été kidnappé, tu dois le sauver, commence par trouver la potion de réinvocation du fantome qui va te donner les instructions",
				"dialog.3", this);
		dialogMob = new Dialog("C'est ici que tu mourras petit inconscient !", "dialog.3", this);
		dialogMaster1 = new Dialog(
				"Tu as réussi à tuer mes subordonnés ?! Je suis fou de rage maintenant tu vas en subir les conséquences !",
				"dialog.3", this);
		dialogFinMission1 = new Dialog(
				"La première étape est un succès! Prends la clef et passe par la porte en dessous pour sauver la victime !",
				"dialog.3", this);
		dialogMobs = new Dialog("Tue les mobs sur ton chemin et dirige toi vers le bras droit du boss !", "dialog.3",
				this);
		Key key = new Key(this, new DiscreteCoordinates(13, 35));
		SignalDoor door = new SignalDoor(this, "LevelSelector", new DiscreteCoordinates(4, 6), Orientation.DOWN,
				new DiscreteCoordinates(6, 1), null, Logic.TRUE);
		SignalDoor doorEnigme2 = new SignalDoor(this, "Enigme0", new DiscreteCoordinates(10, 28), Orientation.DOWN,
				new DiscreteCoordinates(6, 32), null, Logic.TRUE);
		Potion potion = new Potion(this, new DiscreteCoordinates(10, 1));
		PressureSwitch button1 = new PressureSwitch(this, new DiscreteCoordinates(9, 7));
		PressureSwitch button2 = new PressureSwitch(this, new DiscreteCoordinates(8, 7));
		PressureSwitch button3 = new PressureSwitch(this, new DiscreteCoordinates(7, 7));
		PressureSwitch button4 = new PressureSwitch(this, new DiscreteCoordinates(6, 7));
		PressureSwitch button5 = new PressureSwitch(this, new DiscreteCoordinates(7, 6));
		PressureSwitch button6 = new PressureSwitch(this, new DiscreteCoordinates(8, 5));
		PressureSwitch button7 = new PressureSwitch(this, new DiscreteCoordinates(7, 4));
		PressureSwitch button8 = new PressureSwitch(this, new DiscreteCoordinates(6, 3));
		PressureSwitch button9 = new PressureSwitch(this, new DiscreteCoordinates(7, 3));
		PressureSwitch button10 = new PressureSwitch(this, new DiscreteCoordinates(8, 3));
		PressureSwitch button11 = new PressureSwitch(this, new DiscreteCoordinates(9, 3));
		registerActor(button1);
		registerActor(button2);
		registerActor(button3);
		registerActor(button4);
		registerActor(button5);
		registerActor(button6);
		registerActor(button7);
		registerActor(button8);
		registerActor(button9);
		registerActor(button10);
		registerActor(button11);
		List<Logic> list1 = new LinkedList<Logic>();
		list1.add(button1);
		list1.add(button2);
		list1.add(button3);
		list1.add(button4);
		list1.add(button5);
		list1.add(button6);
		list1.add(button7);
		list1.add(button8);
		list1.add(button9);
		list1.add(button10);
		list1.add(button11);
		SignalRock rock = new SignalRock(this, new DiscreteCoordinates(13, 5), new MultipleAnd(list1));
		npc = new NonPlayingCharacter(this, new DiscreteCoordinates(12, 3), potion);
		plate = new PressurePlate(this, new DiscreteCoordinates(15, 12));
		SignalRock rock2 = new SignalRock(this, new DiscreteCoordinates(23, 12), plate);
		SignalDoor doorOutOfHell = new SignalDoor(this, "Enigme1", new DiscreteCoordinates(21, 18), Orientation.DOWN,
				new DiscreteCoordinates(1, 14), null, Logic.TRUE);
		Sword sword = new Sword(this, new DiscreteCoordinates(10, 14));
		Lever lever = new Lever(this, new DiscreteCoordinates(18, 18));
		SignalDoor doorOfHell = new SignalDoor(this, "Enigme1", new DiscreteCoordinates(2, 14), Orientation.DOWN,
				new DiscreteCoordinates(21, 19), null, lever);
		mob1 = new Mob1(this, new DiscreteCoordinates(3, 14), sword, sword);
		Shield shield = new Shield(this, new DiscreteCoordinates(10, 30));
		Mob1 mob11 = new Mob1(this, new DiscreteCoordinates(15, 17), sword, sword);
		Mob1 mob12 = new Mob1(this, new DiscreteCoordinates(13, 24), sword, sword);
		Mob1 mob13 = new Mob1(this, new DiscreteCoordinates(12, 30), sword, sword);
		Mob1 mob14 = new Mob1(this, new DiscreteCoordinates(14, 30), sword, sword);
		Mob1 mob15 = new Mob1(this, new DiscreteCoordinates(13, 33), sword, sword);
		master1 = new Mob2(this, new DiscreteCoordinates(12, 34), Logic.TRUE, new And(sword, shield));
		SignalDoor doorAccessEnigme2 = new SignalDoor(this, "Enigme1", new DiscreteCoordinates(6, 31), Orientation.DOWN,
				new DiscreteCoordinates(18, 32), null, key);
		registerActor(doorAccessEnigme2);
		registerActor(sword);
		registerActor(npc);
		registerActor(key);
		registerActor(door);
		registerActor(doorEnigme2);
		registerActor(npc);
		registerActor(potion);
		registerActor(rock);
		registerActor(rock2);
		registerActor(plate);
		registerActor(doorOfHell);
		registerActor(doorOutOfHell);
		registerActor(lever);
		registerActor(mob1);
		registerActor(shield);
		registerActor(mob11);
		registerActor(mob12);
		registerActor(mob13);
		registerActor(mob14);
		registerActor(mob15);
		registerActor(master1);
		return true;
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void update(float deltaTime) {
		if (getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(6, 2)))
			dialog.draw(getWindow());

		if (npc.getAppearance().isOn() && temp4) {
			dialogNPC.draw(getWindow());
		}

		if (plate.isPressed && temp) {
			temp = false;
			npc = new NonPlayingCharacter(this, new DiscreteCoordinates(21, 15), Logic.TRUE);
			registerActor(npc);
			dialogNPC = new Dialog(
					"Passe par le couloir de l'enfer pour chercher l'épée qui te permettra de le vaincre,  mais fais attention, ses disciples t'attaqueront quand tu t'y attendras le moins",
					"dialog.3", this);
		}
		if (mob1.getAppearance().isOn()) {
			if (temp2) {
				unregisterActor(npc);
				temp2 = false;
			}
			temp4 = false;
			dialogMob.draw(getWindow());
			temp5 = true;
		}
		if (mob1.getAppearance().isOn() == false && temp5 == true) {
			dialogMobs.draw(getWindow());
		}

		if (getPlayer().getCurrentCells().get(0).equals(new DiscreteCoordinates(12, 33)) && temp3 == true) {
			dialogMaster1.draw(getWindow());
		}
		if (master1.getAppearance().isOn() == false) {
			temp3 = false;
			dialogFinMission1.draw(getWindow());
		}
		super.update(deltaTime);
	}

}
