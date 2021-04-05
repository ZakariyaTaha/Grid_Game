package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {

	/// an AreaEntity knows its own Area
	private Area ownerArea;
	/// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	/// Coordinate of the main Cell linked to the entity
	private DiscreteCoordinates currentMainCellCoordinates;

	/**
	 * Default AreaEntity constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param orientation (Orientation): Initial orientation of the entity in the
	 *                    Area. Not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity in
	 *                    the Area. Not null
	 */

	public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(position.toVector());
		if (area != null) {
			ownerArea = area;
		}
		this.orientation = orientation;
		currentMainCellCoordinates = position;

	}

	public Area getOwnerArea() {
		return ownerArea;
	}

	public void setOwnerArea(Area ownerArea) {
		this.ownerArea = ownerArea;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	protected void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public void setCurrentPosition(Vector v) {
		if (DiscreteCoordinates.isCoordinates(v)) {
			v = v.round();
			super.setCurrentPosition(v);
			currentMainCellCoordinates = new DiscreteCoordinates((int) v.x, (int) v.y);
		} else
			super.setCurrentPosition(v);
	}

	/**
	 * Getter for the coordinates of the main cell occupied by the AreaEntity
	 * 
	 * @return (DiscreteCoordinates)
	 */

	protected DiscreteCoordinates getCurrentMainCellCoordinates() {
		return currentMainCellCoordinates;
	}

	public void setCurrentMainCellCoordinates(DiscreteCoordinates currentMainCellCoordinates) {
		this.currentMainCellCoordinates = currentMainCellCoordinates;
	}

}
