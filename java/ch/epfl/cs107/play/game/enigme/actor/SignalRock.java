package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class SignalRock extends AreaEntity implements Logic {
	private Logic isVisible;
	private ImageGraphics sprite;

	public SignalRock(Area area, DiscreteCoordinates coordinates, Logic logic) {
		super(area, Orientation.DOWN, coordinates);
		isVisible = logic;
		sprite = new Sprite("rock.3", 1, 1.f, this);
	}

	public SignalRock(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn() == false)
			sprite.draw(canvas);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());

	}

	@Override
	public boolean takeCellSpace() {
		if (isVisible.isOn())
			return false;
		else
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
	public boolean isOn() {
		if (isVisible.isOn())
			return true;
		else
			return false;
	}

}
