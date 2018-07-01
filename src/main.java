import actors.ActorPlayer;
import board.Board;
import game.Control;
import game.Game;
import game.Player;
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
		

		

		// control.connectToNetGames();
		// control.setGame(new Game(control));
		// // Board board = new Board(control.getGame(), 16, 16);
		// //localhost Player player = control.getGame().createPlayer("PlayerName");
		// // control.getGame().setPlayersOnBoard(true, player);
		// control.openSelectCharacterMenu();
		// control.createGame(control.getActorPlayer().isTurn());
		// // control.getGame().getBoard().boardSetup();
		// control.askForBoardSide();
		// new GUIBoard(control);


		Tests tests = new Tests(control);
		tests.testMockGameStart();
		


	}

}
