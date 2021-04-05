package ch.epfl.cs107.play.game.areagame;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ch.epfl.cs107.play.game.Playable;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

/**
 * Area is a "Part" of the AreaGame. It is characterized by its AreaBehavior and
 * a List of Actors
 */
public abstract class Area implements Playable {
	// Context objects
	private Window window;
	private FileSystem fileSystem;
	private List<Actor> actors;
	private List<Actor> registeredActors;
	private List<Actor> unregisteredActors;
	private boolean status = false;
	private Actor viewCandidate;
	private Vector viewCenter;
	private AreaBehavior areaBehavior;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToEnter;
	private Map<Interactable, List<DiscreteCoordinates>> interactablesToLeave;
	private List<Interactor> interactors;
	private static boolean pause;
	private static boolean isDrawn;

	/**
	 * @return (float): camera scale factor, assume it is the same in x and y
	 *         direction
	 */
	public abstract float getCameraScaleFactor();

	protected final void setBehavior(AreaBehavior ab) {
		areaBehavior = ab;
	}

	public final boolean leaveAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canLeave(entity, coordinates)) {
			interactablesToLeave.put(entity, coordinates);
			return true;
		}

		else
			return false;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public final boolean enterAreaCells(Interactable entity, List<DiscreteCoordinates> coordinates) {
		if (areaBehavior.canEnter(entity, coordinates)) {
			interactablesToEnter.put(entity, coordinates);
			return true;
		} else
			return false;
	}

	public Window getWindow() {
		return window;
	}

	/**
	 * Add an actor to the actors list
	 * 
	 * @param a      (Actor): the actor to add, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void addActor(Actor a, boolean forced) {
		// Here decisions at the area level to decide if an actor
		// must be added or not
		boolean errorOccured = !actors.add(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !enterAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.add((Interactor) a);
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added , so remove it from where it was");
			removeActor(a, true);
		}
	}

	/**
	 * Remove an actor form the actor list
	 * 
	 * @param a      (Actor): the actor to remove, not null
	 * @param forced (Boolean): if true, the method ends
	 */
	private void removeActor(Actor a, boolean forced) {
		boolean errorOccured = !actors.remove(a);
		if (a instanceof Interactable) {
			errorOccured = errorOccured || !leaveAreaCells(((Interactable) a), ((Interactable) a).getCurrentCells());
		}
		if (a instanceof Interactor) {
			errorOccured = errorOccured || !interactors.remove((Interactor) a);
		}
		if (errorOccured && !forced) {
			System.out.println("Actor " + a + " cannot be completely added , so remove it from where it was");
			addActor(a, true);
		}
	}

	public AreaBehavior getAreaBehavior() {
		return areaBehavior;
	}

	/**
	 * Register an actor : will be added at next update
	 * 
	 * @param a (Actor): the actor to register, not null
	 * @return (boolean): true if the actor is correctly registered
	 */
	public final boolean registerActor(Actor a) {
		return registeredActors.add(a);

	}

	/**
	 * Unregister an actor : will be removed at next update
	 * 
	 * @param a (Actor): the actor to unregister, not null
	 * @return (boolean): true if the actor is correctly unregistered
	 */
	public final boolean unregisterActor(Actor a) {
		return unregisteredActors.add(a);
	}

	/**
	 * Getter for the area width
	 * 
	 * @return (int) : the width in number of cols
	 */
	public final int getWidth() {
		return areaBehavior.getWidth();
	}

	/**
	 * Getter for the area height
	 * 
	 * @return (int) : the height in number of rows
	 */
	public final int getHeight() {
		return areaBehavior.getHeight();
	}

	/** @return the Window Keyboard for inputs */
	public final Keyboard getKeyboard() {
		return window.getKeyboard();
	}

	/// Area implements Playable
	public boolean getStatus() {
		return status;
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		status = true;
		viewCenter = Vector.ZERO;
		viewCandidate = null;
		actors = new LinkedList<>();
		registeredActors = new LinkedList<>();
		unregisteredActors = new LinkedList<>();
		interactablesToEnter = new HashMap<Interactable, List<DiscreteCoordinates>>();
		interactablesToLeave = new HashMap<Interactable, List<DiscreteCoordinates>>();
		interactors = new LinkedList<Interactor>();
		this.window = window;
		this.fileSystem = fileSystem;
		return true;
	}

	public final void setViewCandidate(Actor a) {
		this.viewCandidate = a;
	}

	/**
	 * Resume method: Can be overridden
	 * 
	 * @param window     (Window): display context, not null
	 * @param fileSystem (FileSystem): given file system, not null
	 * @return (boolean) : if the resume succeed, true by default
	 */
	public boolean resume(Window window, FileSystem fileSystem) {
		return true;
	}

	private void purgeRegistration() {
		for (int i = 0; i < registeredActors.size(); ++i) {
			addActor(registeredActors.get(i), false);
		}
		for (int i = 0; i < unregisteredActors.size(); ++i) {
			removeActor(unregisteredActors.get(i), false);
		}
		registeredActors.clear();
		unregisteredActors.clear();

		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToEnter.entrySet()) {
			areaBehavior.enter(pair.getKey(), pair.getValue());
		}
		interactablesToEnter.clear();

		for (Entry<Interactable, List<DiscreteCoordinates>> pair : interactablesToLeave.entrySet()) {
			areaBehavior.leave(pair.getKey(), pair.getValue());
		}
		interactablesToLeave.clear();
	}

	@Override
	public void update(float deltaTime) {
		// KeyBoard pour pour mettre une pause au jeu
		Keyboard keyboard = window.getKeyboard();
		Button space = keyboard.get(Keyboard.SPACE);
		if (space.isPressed() && pause == false) {
			pause = true;
			isDrawn = true;
		} else if (space.isPressed() && pause == true) {
			pause = false;
			isDrawn = false;
		}
		purgeRegistration();
		updateCamera();
		if (!pause) {
			for (int i = 0; i < actors.size(); ++i) {
				actors.get(i).update(deltaTime);
			}

			for (Interactor interactor : interactors) {
				if (interactor.wantsCellInteraction()) {
					getAreaBehavior().cellInteractionOf(interactor);
				}
				if (interactor.wantsViewInteraction()) {
					getAreaBehavior().viewInteractionOf(interactor);
				}
			}
		}
		// System.out.println(isDrawn);
		if (isDrawn)
			(new TextGraphics("PAUSE", 1f, Color.BLACK)).draw(getWindow());
		for (int i = 0; i < actors.size(); ++i) {
			actors.get(i).draw(window);
		}

	}

	private void updateCamera() {
		if (viewCandidate != null) {
			viewCenter = viewCandidate.getPosition();

		}
		Transform viewTransform = Transform.I.scaled(getCameraScaleFactor()).translated(viewCenter);
		window.setRelativeTransform(viewTransform);
	}

	/**
	 * Suspend method: Can be overridden, called before resume other
	 */
	public void suspend() {
		purgeRegistration();
	}

	@Override
	public void end() {
		// TODO save the AreaState somewhere
	}

}
