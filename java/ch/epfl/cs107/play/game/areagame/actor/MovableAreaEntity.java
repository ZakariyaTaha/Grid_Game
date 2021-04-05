package ch.epfl.cs107.play.game.areagame.actor;

import java.util.LinkedList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

	/// Indicate if the actor is currently moving
	private boolean isMoving;
	/// Indicate how many frames the current move is supposed to take
	private int framesForCurrentMove;
	/// The target cell (i.e. where the mainCell will be after the motion)
	private DiscreteCoordinates targetMainCellCoordinates;

	/**
	 * Default MovableAreaEntity constructor
	 * 
	 * @param area        (Area): Owner area. Not null
	 * @param position    (Coordinate): Initial position of the entity. Not null
	 * @param orientation (Orientation): Initial orientation of the entity. Not null
	 */
	public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		resetMotion();
	}

	public DiscreteCoordinates getTargetMainCellCoordinates() {
		return targetMainCellCoordinates;
	}

	public void setTargetMainCellCoordinates(DiscreteCoordinates targetMainCellCoordinates) {
		this.targetMainCellCoordinates = targetMainCellCoordinates;
	}

	protected final List<DiscreteCoordinates> getLeavingCells() {
		return this.getCurrentCells();
	}

	protected final List<DiscreteCoordinates> getEnteringCells() {
		List<DiscreteCoordinates> discCoor = new LinkedList<DiscreteCoordinates>();
		DiscreteCoordinates tempo = new DiscreteCoordinates(
				getCurrentCells().get(0).jump(getOrientation().toVector()).x,
				getCurrentCells().get(0).jump(getOrientation().toVector()).y);
		if (this.getOwnerArea().getAreaBehavior().isInGrid(tempo.x, tempo.y)) {
			discCoor.add(tempo);
		}
		return discCoor;
	}

	public void setOrientation(Orientation orientation) {
		if (isMoving == false) {
			super.setOrientation(orientation);
		}
	}

	/**
	 * Initialize or reset the current motion information
	 */
	protected void resetMotion() {
		isMoving = false;
		framesForCurrentMove = 0;
		targetMainCellCoordinates = getCurrentMainCellCoordinates();
	}

	/**
	 * 
	 * @param frameForMove (int): number of frames used for simulating motion
	 * @return (boolean): returns true if motion can occur
	 */

	protected boolean move(int framesForMove) {
		if (isMoving == false || targetMainCellCoordinates.equals(getCurrentMainCellCoordinates())) {
			boolean tempo = ((getOwnerArea().leaveAreaCells(this, getLeavingCells()))
					&& getOwnerArea().enterAreaCells(this, getEnteringCells()));
			if (tempo == false) {
				return false;
			} else {
				if (framesForMove < 1) {
					framesForCurrentMove = 1;
				} else {
					framesForCurrentMove = framesForMove;
				}
				Vector orientation = getOrientation().toVector();
				targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
				isMoving = true;
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(float deltaTime) {
		if (isMoving && !(targetMainCellCoordinates.equals(getCurrentMainCellCoordinates()))) {
			Vector distance = getOrientation().toVector();
			distance = distance.mul(1.0f / framesForCurrentMove);
			setCurrentPosition(getPosition().add(distance));
		} else {
			resetMotion();
		}
	}

	/// Implements Positionable

	@Override
	public Vector getVelocity() {
		// the velocity must be computed as the orientation vector
		// (getOrientation().toVector() mutiplied by
		// framesForCurrentMove
		return new Vector((getOrientation().toVector().x) * framesForCurrentMove,
				(getOrientation().toVector().y) * framesForCurrentMove);
	}
}
