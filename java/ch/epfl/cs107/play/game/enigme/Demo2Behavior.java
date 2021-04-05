package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.window.Window;

public class Demo2Behavior extends AreaBehavior {

	public enum Demo2CellType {
		NULL(0), WALL(-16777216), // RGB code of black
		DOOR(-65536), // RGB code of red
		WATER(-16776961), // RGB code of blue
		INDOOR_WALKABLE(-1), OUTDOOR_WALKABLE(-14112955);
		final int type;

		Demo2CellType(int type) {
			this.type = type;
		}

		public static Demo2CellType toType(int type) {
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

	public Demo2Behavior(Window window, String fileName) {
		super(window, fileName);
		for (int i = 0; i < getWidth(); ++i) {
			for (int j = 0; j < getHeight(); ++j) {
				Demo2CellType cellType = Demo2CellType.toType(getBehaviorMap().getRGB(getHeight() - 1 - j, i));
				getCells()[i][j] = new Demo2Cell(i, j, cellType);
			}
		}
	}

	public class Demo2Cell extends Cell {

		private Demo2CellType demo2CellType;

		private Demo2Cell(int x, int y, Demo2CellType type) {
			super(x, y);
			demo2CellType = type;
		}

		public Demo2CellType getDemo2CellType() {
			return demo2CellType;
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
		protected boolean canEnter(Interactable entity) {
			if (demo2CellType == Demo2CellType.NULL || demo2CellType == Demo2CellType.WALL) {
				return false;
			}
			return true;
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
