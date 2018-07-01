package game;

import board.Position;

import java.util.List;

import board.Board;

public class Game {

	protected Board board;
	protected Player player;
	protected Player opponent;
	protected Control control;
	protected boolean isOngoing;
	protected boolean isInterrupted;
	protected boolean hasWinner;

	public Game(Control control) {
		this.board = null;
		this.player = new Player();
		this.opponent = new Player();
		this.control = control;
		this.isOngoing = false;
		this.isInterrupted = false;
		this.hasWinner = false;
	}

	public Game(Control control, Player player, Player opponent) {
		this.board = null;
		this.player = player;
		this.opponent = opponent;
		this.control = control;
		this.isOngoing = false;
		this.isInterrupted = false;
		this.hasWinner = false;
	}


	public Position getPosition(int x, int y) {
		return this.board.getPosition(x, y);
	}

	public void setBasesForPlayers() {
		this.player.setMainBase(this.board.getSelfMainBase());
		this.opponent.setMainBase(this.board.getOpponentMainBase());
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

	public Character selectCharacter(TypeCharacter type) {
		switch (type) {

		case SWORDSMAN:
			return new Character(100, 50, 3, 4, TypeCharacter.SWORDSMAN);
			// player.addCharacter(character);
			// this.board.setCharacterOnBoard(player, character);
			// break;

		case ARCHER:
			return new Character(100, 35, 7, 3, TypeCharacter.ARCHER);
			// player.addCharacter(character);
			// this.board.setCharacterOnBoard(player, character);
			// break;

		case BARD:
			return new Character(100, 30, 5, 5, TypeCharacter.BARD);
			// player.addCharacter(character);
			// this.board.setCharacterOnBoard(player, character);
			// break;

		case CLERIG:
			return new Character(100, 25, 2, 8, TypeCharacter.CLERIG);
			// player.addCharacter(character);
			// this.board.setCharacterOnBoard(player, character);
			// break;


		default: 
			System.out.println("Invalid character type. Returning Null.");
		}
		return null;
	}

	public Player getOpponent() {
		return this.opponent;
	}

	public String getOpponentName() {
		return this.opponent.getName();
	}

	public Player createPlayer(String name) {
		return this.player = new Player(name);
	}

	public void setOpponentName(String opponentName) {
		this.opponent.setName(opponentName);
	}

	public void createBoard(Game game, int i, int j) {
		this.board = new Board(game, i, j, this.player.getBoardSide());
	}

	public void openSelectCharacterMenu() {
		this.control.openSelectCharacterMenu();
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void addCharacters(List<Character> listOfCharacters) {
		this.board.setCharactersOnBoard(listOfCharacters);
	}

	public Board getBoard() {
		return this.board;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void addSelectedCharacters(List<Character> selectedCharacters) {
		player.addCharacters(selectedCharacters);
	}

}
