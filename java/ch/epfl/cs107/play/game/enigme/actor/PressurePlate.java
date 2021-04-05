package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends AreaEntity implements Logic {
	public boolean isPressed;
	private ImageGraphics spriteOff = new Sprite("GroundPlateOff", 1, 1.f, this);;
	private ImageGraphics spriteOn = new Sprite("GroundLightOn", 1, 1.f, this);;
	private static float timeElapsed = 0;
	private static float limit = 5f;

	public PressurePlate(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN, coordinates);
	}

	public PressurePlate(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn()) {
			spriteOn.draw(canvas);
		} else
			spriteOff.draw(canvas);
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isOn() {
		if (isPressed)
			return true;
		else
			return false;
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void update(float deltaTime) {
		if (isPressed) {
			timeElapsed += deltaTime;
		}
		if (timeElapsed > limit) {
			isPressed = false;
			timeElapsed = 0;
		}
	}
}
