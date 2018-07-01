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
	protected Action lastAction;
	protected List<Character> listOfCharacters;
	protected BoardSide boardSide;


	public Player() {
		this.name = "";
		this.turn = false;
		this.selectedPosition = null;
		this.mainBase = null;
		this.lastAction = null;
		this.listOfCharacters = new ArrayList<>();
		this.boardSide = null;
	}

	public Player(String name) {
		this.name = name;
		this.turn = false;
		this.selectedPosition = null;
		this.mainBase = null;
		this.lastAction = null;
		this.listOfCharacters = new ArrayList<>();
		this.boardSide = null;
	}

	public Player(String name, boolean turn, Position mainBase, List<Character> listOfCharacters, BoardSide boardSide) {
		this.name = name;
		this.turn = turn;
		this.selectedPosition = null;
		this.mainBase = mainBase;
		this.lastAction = null;
		this.listOfCharacters = listOfCharacters;
		this.boardSide = boardSide;
	}

	public void addCharacter(Character character) {
		this.listOfCharacters.add(character);
		System.out.println(character.getType()+"'s been added");
	}

	public void removeCharacter(Character character) {
		this.listOfCharacters.remove(character);
	}

	public boolean checkPlayerCharacter(Character character) {
		if (this.listOfCharacters.contains(character))
			return true;
		return false;
	}

	public void setMainBase(Position base) {
		this.mainBase = base;
	}

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
		return this.listOfCharacters;
	}

	public void setCharactersList(List<Character> charactersList) {
		this.listOfCharacters = charactersList;
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

	public boolean isBoardSideSet() {
		return (this.boardSide != null);
	}

}