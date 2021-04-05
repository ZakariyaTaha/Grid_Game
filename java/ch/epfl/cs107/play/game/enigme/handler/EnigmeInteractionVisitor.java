package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior.EnigmeCell;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Bow;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.Helmet;
import ch.epfl.cs107.play.game.enigme.actor.Key;
import ch.epfl.cs107.play.game.enigme.actor.Lever;
import ch.epfl.cs107.play.game.enigme.actor.Mob1;
import ch.epfl.cs107.play.game.enigme.actor.Mob2;
import ch.epfl.cs107.play.game.enigme.actor.Mob3;
import ch.epfl.cs107.play.game.enigme.actor.Potion;
import ch.epfl.cs107.play.game.enigme.actor.PressurePlate;
import ch.epfl.cs107.play.game.enigme.actor.PressureSwitch;
import ch.epfl.cs107.play.game.enigme.actor.Shield;
import ch.epfl.cs107.play.game.enigme.actor.Sword;
import ch.epfl.cs107.play.game.enigme.actor.Torch;

public interface EnigmeInteractionVisitor extends AreaInteractionVisitor {
	/**
	 * Simulates an interaction between Interactors and Apple in enigme
	 * 
	 * @param apple (Apple), not null
	 */
	default void interactWith(Apple apple) {
		// by default the interaction is empty
	}

	/**
	 * Simulates an interaction between Interactors and Door in enigme
	 * 
	 * @param door (Door), not null
	 */
	default void interactWith(Door door) {
		// by default the interaction is empty
	}

	default void interactWith(EnigmeCell cell) {
		// by default the interaction is empty
	}

	default void interactWith(Key key) {
		// by default the interaction is empty
	}

	default void interactWith(Torch torch) {
		// by default the interaction is empty
	}

	default void interactWith(PressureSwitch buttom) {
		// by default the interaction is empty
	}

	default void interactWith(PressurePlate plate) {
		// by default the interaction is empty
	}

	default void interactWith(Lever lever) {
		// by default the interaction is empty
	}

	default void interactWith(Shield shield) {
		// by default the interaction is empty
	}

	default void interactWith(Sword sword) {
		// by default the interaction is empty
	}

	default void interactWith(Potion potion) {
		// by default the interaction is empty
	}

	default void interactWith(Helmet helmet) {
		// by default the interaction is empty
	}

	default void interactWith(Bow bow) {
		// by default the interaction is empty
	}

	default void interactWith(Mob1 mob) {
		// by default the interaction is empty
	}

	default void interactWith(Mob2 mob) {
		// by default the interaction is empty
	}

	default void interactWith(Mob3 mob) {
		// by default the interaction is empty
	}
}
