package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Collectable extends AreaEntity {
	private boolean isCollected = false;

	public Collectable(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN, coordinates);
	}

	public Collectable(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
	}

	public void collect() {
		isCollected = true;
		getOwnerArea().unregisterActor(this);
	}

	public boolean getIsCollected() {
		return isCollected;
	}

}
