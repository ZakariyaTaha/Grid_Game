package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Mob2 extends Mob {
	private ImageGraphics sprite;

	public Mob2(Area area, DiscreteCoordinates coordinates, Logic logic, Logic beatable) {
		super(area, Orientation.DOWN, coordinates, logic, beatable);
		Vector anchor = new Vector(0.25f, 0.32f);
		sprite = new Sprite("old.man.1", 1f, 1f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
	}

	public Mob2(Area area, Orientation orientation, DiscreteCoordinates coordinates, Logic logic, Logic beatable) {
		super(area, orientation, coordinates, logic, beatable);
		Vector anchor = new Vector(0.25f, 0.32f);
		sprite = new Sprite("old.man.1", 1f, 1f, this, new RegionOfInterest(0, 0, 16, 21), anchor);
	}

	@Override
	public void draw(Canvas canvas) {
		if (isOn())
			sprite.draw(canvas);
	}

	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

}
