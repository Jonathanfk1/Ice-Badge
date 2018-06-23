package game;

import board.Position;
import actors.ActorPlayer;
import board.Board;

public class Game {

	protected Board board;
	protected Player player;
	protected Player opponent;
	protected Control control;

	public Game(Control control, int width, int height) {
		this.board = new Board(height, width);
		this.opponent = new Player();
		this.control = control;
	}

	public Position getPosition(int x, int y) {
		return this.board.getPosition(x, y);
	}

	public boolean setPlayersOnBoard(boolean turn, Player player) {
		Position mainBases[] = this.board.getMainBases();

		this.player = player;

		boolean playersSet = false;
		
		if (turn) {
			this.player.setTurn(true);
			this.player.addBase(mainBases[0]);
			this.opponent.addBase(mainBases[1]);
			this.control.tellTurn(turn);
			playersSet = true;
		} else if (!turn) {
			this.player.addBase(mainBases[1]);
			this.opponent.addBase(mainBases[0]);
			this.control.tellTurn(turn);
			playersSet = true;
		}
		return playersSet;
	}

	public String getPlayerName() {
		return this.player.getName();
	}

	public Action selectPosition(Position clickedPosition) {
		if (this.player.getSelectedPosition() == null)
			this.player.setSelectedPosition(clickedPosition);
		return new Action(null, null, TypeAction.SELECT_CHARACTER);
	}

	public Action attack(Position clickedPosition) {
		System.out.println("attack");
		return null;
	}

	public Action move(Position clickedPosition) {
		Position selectedPosition = this.player.getSelectedPosition();
		Character character = selectedPosition.getCharacter();

		clickedPosition.setCharacter(character);
		selectedPosition.removeCharacter();

		return new Action(selectedPosition, clickedPosition, TypeAction.MOVE);
	}

	public Action changeTurn() {
		this.player.setTurn(false);
		this.opponent.setTurn(true);

		return new Action(null, null, TypeAction.CHANGE_TURN);
	}

	public void selectCharacter(ActorPlayer actorPlayer, TypeCharacter type) {
		Character character;

		switch (type) {

		case SWORDSMAN:
			character = new Character(100, 50, 3, 4, TypeCharacter.SWORDSMAN);
			player.addCharacter(character);
			this.board.setCharacterOnBoard(player, character);
			break;

		case ARCHER:
			character = new Character(100, 35, 7, 3, TypeCharacter.ARCHER);
			player.addCharacter(character);
			this.board.setCharacterOnBoard(player, character);
			break;

		case BARD:
			character = new Character(100, 30, 5, 5, TypeCharacter.BARD);
			player.addCharacter(character);
			this.board.setCharacterOnBoard(player, character);
			break;

		case CLERIG:
			character = new Character(100, 25, 2, 8, TypeCharacter.CLERIG);
			player.addCharacter(character);
			this.board.setCharacterOnBoard(player, character);
			break;

		}
	}

	// ##########################
	// teste

	public Player getOpponent() {
		return this.opponent;
	}

	public Player createPlayer(String name) {
		Player player = new Player(name);
		return player;
	}

	public void setOpponentName(String opponentName) {
		this.opponent.setName(opponentName);
	}

}
