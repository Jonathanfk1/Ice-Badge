package game;

import gui.GUIMainMenu;
import gui.GUISelectCharacter;
import gui.GUIBoard;
import netgames.ActorNetGames;

import javax.swing.JFrame;

import actors.ActorPlayer;
import board.Position;

public class Control {

	protected static final int NUMBER_OF_CHARACTERS = 6;

	protected ActorNetGames actorNetGames;
	protected ActorPlayer actorPlayer;
	protected Game game;
	protected boolean connected;
	protected boolean isFirstPlayer;
	protected int charactersLeft;
	protected GUIMainMenu mainMenu;
	protected JFrame currentMenu;

	public Control() {
		this.actorPlayer = new ActorPlayer(this);
		this.actorNetGames = new ActorNetGames(this);
		this.charactersLeft = this.NUMBER_OF_CHARACTERS;
	}

	public void runInitialMenu() {
		this.mainMenu = new GUIMainMenu(this);
		currentMenu = mainMenu;
	}

	// NOT IMPLEMENTED CORRECTLY
	public void connect(String ip, String name) {
		this.actorNetGames.connect(ip, name);
	}

	public void disconnect() {
		this.actorNetGames.disconnect();
	}

	public void startGame() {
		this.actorNetGames.startGame();
	}

	public void receiveBeginMessage(int playerId) {
		this.game = new Game(32, 32);
		this.game.setPlayersOnBoard(playerId, this.actorPlayer);
		this.currentMenu = new GUISelectCharacter(this);

		if (playerId == 1) {
			this.actorPlayer.setTurn(true);
		}
	}

	public Action changeTurn() {
		if (this.actorPlayer.isTurn()) {
			return this.game.changeTurn();
		}
		return new Action(null, null, TypeAction.CHANGE_TURN);
	}

	public Action makeAction(int x, int y) {
		Position clickedPosition = null;
		Character character;

		if (this.actorPlayer.isTurn()) {
			clickedPosition = this.game.getPosition(x, y);
			character = clickedPosition.getCharacter();

			if (clickedPosition.getCharacter() != null) {

				if (this.actorPlayer.checkPlayerCharacter(character)) {
					return this.game.selectPosition(clickedPosition);
				}

				return this.game.attack(clickedPosition);
			}

			return this.game.move(clickedPosition);
		}
		return null;
	}

	public void selectCharacter(TypeCharacter type) {

		// SELECT CHARACTER LOOP
		if (this.charactersLeft > 0) {
			this.game.selectCharacter(this.actorPlayer, type);
			this.charactersLeft--;
		}

		if (this.charactersLeft == 0) {
			new GUIBoard(this);
			System.out.println(this.actorPlayer.isTurn());
		}
	}

	// #############################################
	// TESTS

	public Game getGame() {
		return this.game;
	}

	public ActorPlayer getActorPlayer() {
		return this.actorPlayer;
	}

	public void receiveLaunchedAction(Action launchAction) {

	}

	public void setActorPlayer(ActorPlayer actorPlayer) {
		this.actorPlayer = actorPlayer; 
	}

	public void connectToNetGames() {
		this.actorNetGames.connect(mainMenu.getConnectionIp(), mainMenu.getConnectionName());
	}

}