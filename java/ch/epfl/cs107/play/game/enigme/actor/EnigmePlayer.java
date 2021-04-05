package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class EnigmePlayer extends MovableAreaEntity implements Interactor {
	private final EnigmePlayerHandler handler;
	private boolean isPassingDoor;
	private Door PassedDoor;
	private ImageGraphics sprite;
	/// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;
	private static int step = 0;
	private static Map<Orientation, Integer> indicesOrientation = new HashMap<Orientation, Integer>();

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

	public Door passedDoor() {
		return PassedDoor;
	}

	public void setIsPassingDoor(Door door) {
		isPassingDoor = true;
		PassedDoor = door;
	}

	public boolean isPassingDoor() {
		return isPassingDoor;
	}

	public void setPassingDoor(boolean isPassingDoor) {
		this.isPassingDoor = isPassingDoor;
	}

	public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
		super(area, orientation, coordinates);
		handler = new EnigmePlayerHandler();
		indicesOrientation.put(Orientation.DOWN, 0);
		indicesOrientation.put(Orientation.LEFT, 16);
		indicesOrientation.put(Orientation.UP, 32);
		indicesOrientation.put(Orientation.RIGHT, 48);
	}

	public EnigmePlayer(Area area, DiscreteCoordinates coordinates) {
		super(area, Orientation.DOWN, coordinates);
		handler = new EnigmePlayerHandler();
		indicesOrientation.put(Orientation.DOWN, 0);
		indicesOrientation.put(Orientation.LEFT, 16);
		indicesOrientation.put(Orientation.UP, 32);
		indicesOrientation.put(Orientation.RIGHT, 48);
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
			++step;
			if (getOrientation().equals(Orientation.LEFT)) {
				move(ANIMATION_DURATION);
			} else {

				setOrientation(Orientation.LEFT);
			}
		}
		if (rightArrow.isDown()) {
			++step;
			if (getOrientation().equals(Orientation.RIGHT)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.RIGHT);

			}
		}
		if (upArrow.isDown()) {
			++step;
			if (getOrientation().equals(Orientation.UP)) {
				move(ANIMATION_DURATION);
			} else {
				setOrientation(Orientation.UP);

			}
		}
		if (downArrow.isDown()) {
			++step;
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
		Vector anchor = new Vector(0.25f, 0.32f);
		sprite = new Sprite("max.new.2", 0.7f, 0.7f, this,
				new RegionOfInterest(indicesOrientation.get(getOrientation()), (step % 4) * 21, 16, 21), anchor);
		/*
		 * Methode plus explicite 
		 * if(getOrientation().equals(Orientation.DOWN)) {
		 * sprite=new Sprite("max.new.2", 0.5f, 0.65625f, this , new
		 * RegionOfInterest(indicesOrientation.get(getOrientation()), (step%4) * 21, 16,
		 * 21), anchor) ; } if(getOrientation().equals(Orientation.LEFT)) { sprite=new
		 * Sprite("max.new.2", 0.5f, 0.65625f, this , new RegionOfInterest(16, (step%4)
		 * * 21, 16, 21), anchor) ; } if(getOrientation().equals(Orientation.UP)) {
		 * sprite=new Sprite("max.new.2", 0.5f, 0.65625f, this , new
		 * RegionOfInterest(32, (step%4) * 21, 16, 21), anchor) ; }
		 * if(getOrientation().equals(Orientation.RIGHT)) { sprite=new
		 * Sprite("max.new.2", 0.5f, 0.65625f, this , new RegionOfInterest(48, (step%4)
		 * * 21, 16, 21), anchor) ; }
		 */
		sprite.draw(canvas);
	}

	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates().jump(this.getOrientation().toVector()));
	}

	@Override
	public boolean wantsCellInteraction() {
		return true;
	}

	@Override
	public boolean wantsViewInteraction() {
		Keyboard keyboard = this.getOwnerArea().getKeyboard();
		Button L = keyboard.get(Keyboard.L);
		if (L.isPressed()) {
			return true;
		} else
			return false;
	}

	@Override
	public void interactWith(Interactable other) {
		other.acceptInteraction(handler);
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

	/**
	 * Specific interaction handler for an EnigmePlayer
	 */
	private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
		@Override
		public void interactWith(Door door) {
			setIsPassingDoor(door);
		}

		@Override
		public void interactWith(Apple apple) {
			apple.collect();
		}

		public void interactWith(Key key) {
			key.collect();
		}

		public void interactWith(Torch torch) {
			torch.changer();
		}

		public void interactWith(Lever lever) {
			lever.changer();
		}

		public void interactWith(PressureSwitch button) {
			if (getTargetMainCellCoordinates().equals(getCurrentCells().get(0)))
				button.changer();
		}

		public void interactWith(PressurePlate plate) {
			plate.setPressed(true);
		}

		public void interactWith(Sword sword) {
			sword.wear();
		}

		public void interactWith(Shield shield) {
			shield.wear();
		}

		public void interactWith(Helmet helmet) {
			helmet.wear();
		}

		public void interactWith(Potion potion) {
			potion.use();
		}

		public void interactWith(Bow bow) {
			bow.wear();
		}

		public void interactWith(Mob1 mob) {
			mob.beat();
		}

		public void interactWith(Mob2 mob) {
			if (mob.beat())
				;
			else
				getOwnerArea().unregisterActor(EnigmePlayer.this);
		}

		public void interactWith(Mob3 mob) {
			if (mob.beat())
				;
			else
				getOwnerArea().unregisterActor(EnigmePlayer.this);
		}

	}
}
