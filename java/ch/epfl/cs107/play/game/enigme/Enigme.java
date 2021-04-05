package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.area.Enigme1;
import ch.epfl.cs107.play.game.enigme.area.Enigme2;
import ch.epfl.cs107.play.game.enigme.area.IntermediateRoom;
import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the
 * notion of Player When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {
	private EnigmePlayer enigmePlayer;
	private LevelSelector levelSelector;
	private Level1 level1;
	private Level2 level2;
	private Level3 level3;
//	private Dialog dialog;
	private Enigme1 enigme1;
	private Enigme2 enigme2;
	private IntermediateRoom intermediateRoom;

	/// The player is a concept of RPG games

	/// Enigme implements Playable

	@Override
	public String getTitle() {
		return "Enigme";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		levelSelector = new LevelSelector();
		level1 = new Level1();
		level2 = new Level2();
		level3 = new Level3();
		enigme1 = new Enigme1();
		enigme2 = new Enigme2();
		intermediateRoom = new IntermediateRoom();
		this.addArea(levelSelector);
		this.addArea(level1);
		this.addArea(level2);
		this.addArea(level3);
		this.addArea(enigme1);
		this.addArea(enigme2);
		this.addArea(intermediateRoom);
		setCurrentArea(levelSelector.getTitle(), false);
		enigmePlayer = new EnigmePlayer(getCurrentArea(), new DiscreteCoordinates(5, 5));
		enigmePlayer.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));
		levelSelector.setPlayer(enigmePlayer);
		level1.setPlayer(enigmePlayer);
		level2.setPlayer(enigmePlayer);
		level3.setPlayer(enigmePlayer);
		enigme1.setPlayer(enigmePlayer);
		enigme2.setPlayer(enigmePlayer);
		intermediateRoom.setPlayer(enigmePlayer);
		return true;
	}

	@Override
	public void update(float deltaTime) {
		if (enigmePlayer.isPassingDoor()) {
			enigmePlayer.leaveArea();
			setCurrentArea(enigmePlayer.passedDoor().getAirTransition(), false);
			enigmePlayer.enterArea(getCurrentArea(), enigmePlayer.passedDoor().getCoordonneesAireDestination());
			enigmePlayer.setPassingDoor(false);
		}
		super.update(deltaTime);
	}

	@Override
	public int getFrameRate() {
		return 50;
	}
}
