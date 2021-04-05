package ch.epfl.cs107.play.game.demo1;

import java.awt.Color;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo1 implements Game {
	private Actor actor1;
	private Actor actor2;
	private Window window;
	private FileSystem fileSystem;

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		float radius = 0.2f;
		actor1 = new GraphicsEntity(Vector.ZERO, new ShapeGraphics(new Circle(radius), null, Color.RED, 0.005f));
		actor2 = new MovingRock(new Vector(0.3f, 0.3f), new String("Hello, I'm a moving rock"));
		this.window = window;
		this.fileSystem = fileSystem;
		// Transform viewTransform = Transform.I.scaled (10).translated(Vector.ZERO) ;
		// window.setRelativeTransform(viewTransform) ;
		// Transform viewTransform = Transform.I.scaled(1).translated(new Vector(-0.2f,
		// 0.0f)) ;
		// window.setRelativeTransform(viewTransform) ;
		return true;
	}

	@Override
	public void end() {

	}

	@Override
	public String getTitle() {
		return "Demo1";
	}

	@Override
	public void update(float deltaTime) {
		actor1.draw(window);
		actor2.draw(window);
		Keyboard keyboard = window.getKeyboard();
		Button downArrow = keyboard.get(Keyboard.DOWN);
		if (downArrow.isDown()) {
			actor2.update(deltaTime);
		}
		float x1 = actor1.getPosition().x;
		float y1 = actor1.getPosition().y;
		float x2 = actor2.getPosition().x;
		float y2 = actor2.getPosition().y;
		double distance = Math.sqrt((x1 - x2) * ((x1 - x2) + (y1 - y2) * (y1 - y2)));
		double radius = (double) 0.2f;
		if (distance < radius) {
			(new TextGraphics("BOOM", 0.04f, Color.RED)).draw(window);
		}
	}

	@Override
	public int getFrameRate() {
		return 24;
	}

}
