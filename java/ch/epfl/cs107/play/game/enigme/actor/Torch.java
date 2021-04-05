package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Torch extends Switchable {
	private boolean isAlight = false;
	private ImageGraphics sprite;
	private int step = 0;

	public Torch(Area area, DiscreteCoordinates coordinates, boolean allume) {
		super(area, Orientation.DOWN, coordinates);
		isAlight = allume;
		if (isAlight) {
			sprite = new Sprite("torch.ground.on.1", 1, 1.f, this);
		} else {
			sprite = new Sprite("torch.ground.off", 1, 1.f, this);
		}
	}

	public Torch(Area area, Orientation orientation, DiscreteCoordinates coordinates, boolean allume) {
		super(area, orientation, coordinates);
		isAlight = allume;
		if (isAlight) {
			sprite = new Sprite("torch.ground.on.1", 1, 1.f, this);
		} else {
			sprite = new Sprite("torch.ground.off", 1, 1.f, this);
		}
	}

	public void changer() {
		if (isAlight) {
			isAlight = false;
		} else {
			isAlight = true;
		}
	}

	@Override
	public boolean isOn() {
		if (isAlight)
			return true;
		else
			return false;
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn()) {
			if (step % 2 == 0)
				sprite = new Sprite("torch.ground.on.1", 1, 1.f, this);
			if (step % 2 == 1)
				sprite = new Sprite("torch.ground.on.2", 1, 1.f, this);
			++step;
		} else {
			sprite = new Sprite("torch.ground.off", 1, 1.f, this);
		}
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

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
