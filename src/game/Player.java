package game;

import java.util.ArrayList;
import java.util.List;

import board.Position;

public class Player {

	protected String name;
	protected boolean turn;
	protected Position selectedPosition;
	protected Position mainBase;
	protected ArrayList<Character> listCharacter;

	public Player(String name) {
		this.name = name;
		// this.mainBase = new Position(0, 0, null);
		this.listCharacter = new ArrayList<>();
		this.turn = false;
	}

	public Player() {
		this.listCharacter = new ArrayList<>();
		// this.mainBase = new Position(0, 0, null);
		this.turn = false;
		this.name = "Opponent";
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

	public void addCharacter(Character character) {
		this.listCharacter.add(character);
		System.out.println(character.getType()+"'s been added");
	}

	public void removeCharacter(Character character) {
		this.listCharacter.remove(character);
	}

	public ArrayList<Character> getCharactersList() {
		return this.listCharacter;
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
}