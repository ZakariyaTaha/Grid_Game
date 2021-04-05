package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Victim extends AreaEntity {
	private ImageGraphics sprite;

	public Victim(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN, coordinates);
		Vector anchor = new Vector(0.25f, 0.32f);
		sprite = new Sprite("max.new.1", 0.6f, 0.6f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
	}

	public Victim(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		Vector anchor = new Vector(0.25f, 0.32f);
		sprite = new Sprite("max.new.1", 0.6f, 0.6f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

}
