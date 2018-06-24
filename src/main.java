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
		

		control.runInitialMenu();

		control.setGame(new Game(control));
		
		Board board = new Board(control.getGame(), 32, 32);
		control.getGame().setBoard(board);
		Player player = control.getGame().createPlayer("PlayerName");
		control.getGame().setPlayersOnBoard(true, player);
		control.openSelectCharacterMenu();
		// control.createGame(control.getActorPlayer().isTurn());


	}

}
