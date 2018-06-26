package game;

import java.util.ArrayList;
import java.util.List;

import board.BoardSide;
import board.Position;

public class Player {

	protected String name;
	protected boolean turn;
	protected Position selectedPosition;
	protected Position mainBase;
	protected List<Character> listCharacter;
	private BoardSide boardSide;

	public Player(String name) {
		this.name = name;
		// this.mainBase = new Position(0, 0, null);
		this.turn = false;
		this.listCharacter = new ArrayList<>();
	}

	public Player() {
		// this.mainBase = new Position(0, 0, null);
		this.turn = false;
		this.name = "Opponent";
		this.listCharacter = new ArrayList<>();
	}

	public void addCharacter(Character character) {
		this.listCharacter.add(character);
		System.out.println(character.getType()+"'s been added");
	}

	public void removeCharacter(Character character) {
		this.listCharacter.remove(character);
	}

	public boolean checkPlayerCharacter(Character character) {
		if (this.listCharacter.contains(character))
			return true;
		return false;
	}

	public void setMainBase(Position base) {
		this.mainBase = base;
	}

	// public void removeMainBase(Position base) {
	// 	if (this.bases_.size() > 1) {
	// 		this.bases_.remove(base);
	// 	}
	// }

	public Position getMainBase() {
		System.out.println(this.name + "'s mainBase x: " + mainBase.getX() + " y: " + mainBase.getY());
		return this.mainBase;
	}

	public Position getBases() {
		return this.mainBase;
	}

	public void addCharacters(List<Character> selectedCharacters) {
		for (Character character : selectedCharacters) {
			addCharacter(character);
		}
	}

	public List<Character> getCharactersList() {
		return this.listCharacter;
	}

	public void setCharactersList(List<Character> charactersList) {
		this.listCharacter = charactersList;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTurn() {
		return this.turn;
	}

	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	public void setSelectedPosition(Position position) {
		this.selectedPosition = position;
	}

	public Position getSelectedPosition() {
		return this.selectedPosition;
	}

	public void setBoardSide(BoardSide boardSide) {
		this.boardSide = boardSide;
	}

	public BoardSide getBoardSide() {
		return this.boardSide;
	}

}