import actors.ActorPlayer;
import game.Control;
import gui.GUIBoard;
import netgames.ActorNetGames;
import tests.Tests;

public class main {

	public static void main(String[] args) {

		Control control = new Control();

		// ActorNetGames netGames = new ActorNetGames(control);
		// netGames.connect("localhost", "");

		// ActorPlayer actorPlayer = new ActorPlayer(control);

		// GUIBoard guiBoard = new GUIBoard(control);

		// control.setBoard(guiBoard);
		
		// control.tellIsFirstToPlay();
		
		control.runInitialMenu();

	}

}
