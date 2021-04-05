package ch.epfl.cs107.play.game.enigme.area.demo2;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2Cell;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior.Demo2CellType;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class Demo2Player extends MovableAreaEntity {
	private boolean isPassingDoor;

	private ImageGraphics sprite;
	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;

	public void enterArea(Area area, DiscreteCoordinates position) {
		setOwnerArea(area);
		area.registerActor(this);
		setCurrentPosition(position.toVector());
		resetMotion();
		getOwnerArea().setViewCandidate(this);
	}

	public void leaveArea() {
		getOwnerArea().unregisterActor(this);

	}

	public boolean isPassingDoor() {
		return isPassingDoor;
	}

	public void setPassingDoor(boolean isThroughDoor) {
		this.isPassingDoor = isThroughDoor;
	}

	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		sprite = new Sprite("ghost.1", 1, 1.f, this);
	}

	public Demo2Player(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN, coordinates);
		sprite = new Sprite("ghost.1", 1, 1.f, this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {

		return Collections.singletonList(getCurrentMainCellCoordinates());

	}

	public void update(float deltaTime) {
		Keyboard keyboard = this.getOwnerArea().getKeyboard();
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button upArrow = keyboard.get(Keyboard.UP);
		Button downArrow = keyboard.get(Keyboard.DOWN);
		if (leftArrow.isDown()) {
			if (getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.LEFT);

			}
		}
		if (rightArrow.isDown()) {
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.RIGHT);

			}
		}
		if (upArrow.isDown()) {
			if (getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.UP);

			}
		}
		if (downArrow.isDown()) {
			if (getOrientation().equals(Orientation.DOWN)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.DOWN);

			}
		}
		super.update(deltaTime);
	}

	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);

	}

	@Override

	protected boolean move(int framesForMove) {

		if (super.move(framesForMove)) {
			isPassingDoor = false;
			for (int i = 0; i < getEnteringCells().size() && isPassingDoor == false; ++i) {
				for (int j = 0; j < getEnteringCells().size(); ++j) {
					int x = getEnteringCells().get(i).x;
					int y = getEnteringCells().get(j).y;
					if (((Demo2Cell) getOwnerArea().getAreaBehavior().getCells()[x][y]).getDemo2CellType()
							.equals(Demo2CellType.DOOR)) {
						isPassingDoor = true;
					}

				}

			}

			return true;
		}
		return false;
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
		return true;

	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
