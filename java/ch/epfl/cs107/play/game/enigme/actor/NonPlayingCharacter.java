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

public class NonPlayingCharacter extends AreaEntity implements Logic {
	private Logic appearance;
	private ImageGraphics sprite;

	public NonPlayingCharacter(Area area, DiscreteCoordinates coordinates, Logic logic) {
		super(area, Orientation.DOWN, coordinates);
		sprite = new Sprite("ghost.1", 1, 1.f, this);
		appearance = logic;
	}

	public NonPlayingCharacter(Area area, Orientation orientation, DiscreteCoordinates coordinates, Logic logic) {
		super(area, orientation, coordinates);
		sprite = new Sprite("ghost.1", 1, 1.f, this);
		appearance = logic;
	}

	@Override
	public boolean isOn() {
		if (appearance.isOn())
			return true;
		else
			return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn())
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

	public Logic getAppearance() {
		return appearance;
	}

	public void setAppearance(Logic appearance) {
		this.appearance = appearance;
	}

}
