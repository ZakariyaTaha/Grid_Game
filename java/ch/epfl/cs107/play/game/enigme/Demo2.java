package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
	private Demo2Player player;
	private Room0 room0;
	private Room1 room1;

	@Override
	public int getFrameRate() {
		return 24;
	}

	@Override
	public String getTitle() {
		return "Demo2";
	}

	private void echanger() {
		if (getCurrentArea().getTitle().equals(room0.getTitle()) && player.isPassingDoor()) {
			setCurrentArea(room1.getTitle(), false);
			player.leaveArea();
			player.enterArea(room1, new DiscreteCoordinates(5, 2));
			player.setPassingDoor(false);

		}
		if (getCurrentArea().getTitle().equals(room1.getTitle()) && player.isPassingDoor()) {
			setCurrentArea(room0.getTitle(), false);
			player.leaveArea();
			player.enterArea(room0, new DiscreteCoordinates(5, 5));
			player.setPassingDoor(false);
		}
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		room0 = new Room0();
		room1 = new Room1();
		this.addArea(room0);
		this.addArea(room1);
		setCurrentArea(room0.getTitle(), false);
		player = new Demo2Player(getCurrentArea(), new DiscreteCoordinates(5, 5));
		player.enterArea(getCurrentArea(), new DiscreteCoordinates(5, 5));
		getCurrentArea().setViewCandidate(player);
		return true;
	}

	public void update(float deltaTime) {
		echanger();
		super.update(deltaTime);
	}

}
