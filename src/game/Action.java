package game;

import java.util.List;

import board.Position;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Action implements Jogada {
	
	private static final long serialVersionUID = -7438707158228511520L;
	protected Position selectedPosition;
	protected Position finalPosition;
	protected TypeAction type;
	protected List<Character> listOfCharacters;
	protected Character characterAttacked;
	protected Character movedCharacter;

	public Action(Position finalPosition, TypeAction type) {
		this.selectedPosition = null;
		this.finalPosition = finalPosition;
		this.type = type;
		this.listOfCharacters = null;
		this.characterAttacked = null;
		this.movedCharacter = null;
	}


	public Action(Position finalPosition, Character characterAttacked) {
		this.selectedPosition = null;
		this.finalPosition = finalPosition;
		this.type = null;
		this.listOfCharacters = null;
		this.characterAttacked = characterAttacked;
		this.movedCharacter = null;
	}
	
	public Action(Character movedCharacter, Position finalPosition) {
		this.selectedPosition = null;
		this.finalPosition = finalPosition;
		this.type = null;
		this.listOfCharacters = null;
		this.characterAttacked = null;
		this.movedCharacter = movedCharacter;
	}

	public Action(Character characterAttackant, Character characterAttacked) {
		this.selectedPosition = null;
		this.finalPosition = null;
		this.type = null;
		this.listOfCharacters = null;
		this.characterAttacked = characterAttacked;
		this.movedCharacter = characterAttackant;
	}

	public Action(List<Character> listOfCharacters, TypeAction type) {
		this.selectedPosition = null;
		this.finalPosition = null;
		this.type = null;
		this.listOfCharacters = listOfCharacters;
		this.characterAttacked = null;
		this.movedCharacter = null;
	}

	public Position getPlayerPosition() {
		return this.selectedPosition;
	}

	public Position getFinalPosition() {
		return this.finalPosition;
	}

	public TypeAction getType() {
		return this.type;
	}

	public List<Character> getListOfCharacters() {
		return this.listOfCharacters;
	}


	public Character getAttackedCharacter() {
		return this.characterAttacked;
	}


	public Character getMovedCharacter() {
		return this.movedCharacter;
	}

}
