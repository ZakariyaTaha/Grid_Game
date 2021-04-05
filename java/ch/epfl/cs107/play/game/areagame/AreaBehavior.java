package ch.epfl.cs107.play.game.areagame;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

/**
 * AreaBehavior manages a map of Cells.xXXXX
 */
public abstract class AreaBehavior {
	/// The behavior is an Image of size height x width
	private final Image behaviorMap;
	private final int width, height;
	/// We will convert the image into an array of cells
	private final Cell[][] cells;

	/// The behavior is an Image of size height x width

	/**
	 * Default AreaBehavior Constructor
	 * 
	 * @param window   (Window): graphic context, not null
	 * @param fileName (String): name of the file containing the behavior image, not
	 *                 null
	 */
	public AreaBehavior(Window window, String fileName) {
		behaviorMap = window.getImage(ResourcePath.getBehaviors(fileName), null, false);
		width = behaviorMap.getWidth();
		height = behaviorMap.getHeight();
		cells = new Cell[width][height];
	}

	public Image getBehaviorMap() {
		return behaviorMap;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public boolean isInGrid(int x, int y) {
		// true si l'objet appartient au teableau, sinon false
		if (x < width && x >= 0 && y < height && y >= 0) {
			return true;
		} else
			return false;

	}

	public boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		boolean temp = true;

		for (int i = 0; i < coordinates.size() && temp == true; ++i) {

			if ((cells[coordinates.get(i).x][coordinates.get(i).y]).canLeave(entity) == false) {
				temp = false;
			}
		}
		return temp;
	}

	public boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		boolean temp = true;
		for (int i = 0; i < coordinates.size() && temp == true; ++i) {
			if ((cells[coordinates.get(i).x][coordinates.get(i).y].canEnter(entity) == false)
					|| isInGrid(coordinates.get(i).x, coordinates.get(i).y) == false) {
				temp = false;
			}
		}
		return temp;
	}

	protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < coordinates.size(); ++i) {
			cells[coordinates.get(i).x][coordinates.get(i).y].leave(entity);
		}
	}

	protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
		for (int i = 0; i < coordinates.size(); ++i) {
			cells[coordinates.get(i).x][coordinates.get(i).y].enter(entity);
		}
	}

	public void cellInteractionOf(Interactor interactor) {
		for (int i = 0; i < interactor.getCurrentCells().size(); ++i) {
			getCells()[interactor.getCurrentCells().get(i).x][interactor.getCurrentCells().get(i).y]
					.cellInteractionOf(interactor);
		}
	}

	public void viewInteractionOf(Interactor interactor) {
		for (int i = 0; i < interactor.getFieldOfViewCells().size(); ++i) {
			getCells()[interactor.getFieldOfViewCells().get(i).x][interactor.getFieldOfViewCells().get(i).y]
					.viewInteractionOf(interactor);
		}
	}

	public abstract class Cell implements Interactable {
		protected Set<Interactable> interactables;
		private DiscreteCoordinates discreteCoordinates;

		public Cell(int x, int y) {
			discreteCoordinates = new DiscreteCoordinates(x, y);
			interactables = new HashSet<>();
		}

		public List<DiscreteCoordinates> getCurrentCells() {
			LinkedList<DiscreteCoordinates> ds = new LinkedList<DiscreteCoordinates>();
			ds.add(this.discreteCoordinates);
			return ds;
		}

		public DiscreteCoordinates getDiscreteCoordinates() {
			return discreteCoordinates;
		}

		public void setDiscreteCoordinates(DiscreteCoordinates discreteCoordinates) {
			this.discreteCoordinates = discreteCoordinates;
		}

		private void enter(Interactable entity) {
			interactables.add(entity);
		}

		private void leave(Interactable entity) {
			interactables.remove(entity);
		}

		protected abstract boolean canEnter(Interactable entity);

		protected abstract boolean canLeave(Interactable entity);

		private void cellInteractionOf(Interactor interactor) {
			for (Interactable interactable : interactables) {
				if (interactable.isCellInteractable())
					interactor.interactWith(interactable);
			}
		}

		private void viewInteractionOf(Interactor interactor) {
			for (int i = 0; i < interactor.getFieldOfViewCells().size(); ++i) {
				for (Interactable interactable : getCells()[interactor.getFieldOfViewCells().get(i).x][interactor
						.getFieldOfViewCells().get(i).y].interactables) {
					if (interactable.isViewInteractable())
						interactor.interactWith(interactable);

				}
			}
		}
	}
}
