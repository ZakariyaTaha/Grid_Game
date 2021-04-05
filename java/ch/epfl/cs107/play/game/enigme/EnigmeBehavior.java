package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class EnigmeBehavior extends AreaBehavior {

	public enum EnigmeCellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		EnigmeCellType(int type) {
			this.type = type;
		}

		public static EnigmeCellType toType(int type) {
			switch (type) {
			case 0:
				return NULL;
			case -16777216:
				return WALL;
			case -65536:
				return DOOR;
			case -16776961:
				return WATER;
			case -1:
				return INDOOR_WALKABLE;
			case -14112955:
				return OUTDOOR_WALKABLE;
			default:
				return NULL;
			}

		}
	}

	public EnigmeBehavior(Window window, String fileName) {
		super(window, fileName);
		for (int i = 0; i < getWidth(); ++i) {
			for (int j = 0; j < getHeight(); ++j) {
				EnigmeCellType cellType = EnigmeCellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - j, i));
				if (cellType == EnigmeCellType.DOOR) {
				}
				getCells()[i][j] = new EnigmeCell(i, j, cellType);
			}
		}
	}

	public class EnigmeCell extends Cell {

		private EnigmeCellType enigmeCellType;

		private EnigmeCell(int x, int y, EnigmeCellType type) {
			super(x, y);
			enigmeCellType = type;
		}

		public EnigmeCellType getEnigmeCellType() {
			return enigmeCellType;
		}

		@Override
		public boolean takeCellSpace() {
			if (enigmeCellType == EnigmeCellType.NULL || enigmeCellType == EnigmeCellType.DOOR) {
				return true;
			}
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
		protected boolean canEnter(Interactable entity) {
			for (Interactable temp : interactables) {
				if (temp.takeCellSpace()) {
					return false;
				}
			}
			if (enigmeCellType == EnigmeCellType.NULL || enigmeCellType == EnigmeCellType.WALL) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
			((EnigmeInteractionVisitor) v).interactWith(this);
		}
	}

}