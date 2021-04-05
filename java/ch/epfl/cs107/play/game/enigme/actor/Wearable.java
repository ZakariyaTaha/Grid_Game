package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Wearable extends AreaEntity {
	private boolean isWeared = false;

	public Wearable(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN, coordinates);
	}

	public Wearable(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
	}

	public void wear() {
		isWeared = true;
		getOwnerArea().unregisterActor(this);
	}

	public boolean getIsWeared() {
		return isWeared;
	}

}
