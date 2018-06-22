import game.Control;
import netgames.ActorNetGames;
import tests.Tests;

public class main {

	public static void main(String[] args) {

		Control control = new Control();

		// ActorNetGames netGames = new ActorNetGames(control);
		// netGames.connect("localhost", "");

		control.runInitialMenu();

	}

}
