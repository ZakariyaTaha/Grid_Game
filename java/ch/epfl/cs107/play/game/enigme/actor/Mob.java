package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public abstract class Mob extends AreaEntity implements Logic {
	private Logic appearance;
	private Logic beatable;

	public Mob(Area area, DiscreteCoordinates coordinates, Logic logic, Logic beatable) {
		super(area, Orientation.DOWN, coordinates);
		appearance = logic;
		this.beatable = beatable;
	}

	public Mob(Area area, Orientation orientation, DiscreteCoordinates coordinates, Logic logic, Logic beatable) {
		super(area, orientation, coordinates);
		appearance = logic;
		this.beatable = beatable;
	}

	@Override
	public boolean isOn() {
		if (appearance.isOn())
			return true;
		else
			return false;
	}

	public boolean beat() {
		if (beatable.isOn()) {
			getOwnerArea().unregisterActor(this);
			setAppearance(Logic.FALSE);
			return true;
		} else
			return false;
	}

	@Override
	public boolean takeCellSpace() {
		if (isOn())
			return true;
		else
			return false;
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

	public Logic getBeatable() {
		return beatable;
	}

	public void setBeatable(Logic beatable) {
		this.beatable = beatable;
	}

}
