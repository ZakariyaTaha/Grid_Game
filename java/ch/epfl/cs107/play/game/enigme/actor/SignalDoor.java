package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalDoor extends Door implements Logic {
	private Logic acceptInteraction;

	public SignalDoor(Area area, String aireDestination, DiscreteCoordinates coordAirdestination,
			Orientation orientation, DiscreteCoordinates currentMainCellCoordinates,
			List<DiscreteCoordinates> coordonnees, Logic signal) {
		super(area, aireDestination, coordAirdestination, orientation, currentMainCellCoordinates, coordonnees);
		acceptInteraction = signal;
		if (acceptInteraction.isOn())
			setSprite(new Sprite("door.open.1", 1, 1.f, this));
		else
			setSprite(new Sprite("door.close.1", 1, 1.f, this));
	}

	@Override
	public boolean isOn() {
		if (acceptInteraction.isOn())
			return true;
		else
			return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (acceptInteraction.isOn())
			setSprite(new Sprite("door.open.1", 1, 1.f, this));
		else
			setSprite(new Sprite("door.close.1", 1, 1.f, this));
		getSprite().draw(canvas);
	}

	@Override
	public boolean takeCellSpace() {
		if (isOn() == false)
			return true;
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		if (acceptInteraction.isOn())
			return true;
		else
			return false;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
