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
import ch.epfl.cs107.play.window.Canvas;

public class Door extends AreaEntity {
	private String airTransition;
	private DiscreteCoordinates coordonneesAireDestination;
	private ImageGraphics sprite;

	public Door(Area area, String aireDestination, DiscreteCoordinates coordAirdestination, Orientation orientation,
			DiscreteCoordinates currentMainCellCoordinates, List<DiscreteCoordinates> coordonnees) {
		super(area, orientation, currentMainCellCoordinates);
		this.airTransition = aireDestination;
		this.coordonneesAireDestination = coordAirdestination;
		if (aireDestination.equals("LevelSelector") && this.getOwnerArea().getTitle().equals("LevelSelector")) {
			sprite = new Sprite("door.close.1", 1, 1.f, this);
		} else {
			sprite = new Sprite("door.open.1", 1, 1.f, this);
		}
	}

	public ImageGraphics getSprite() {
		return sprite;
	}

	public void setSprite(ImageGraphics sprite) {
		this.sprite = sprite;
	}

	public String getAirTransition() {
		return airTransition;
	}

	public DiscreteCoordinates getCoordonneesAireDestination() {
		return coordonneesAireDestination;
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
	public void draw(Canvas canvas) {
		sprite.draw(canvas);
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
