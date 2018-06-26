package game;

import java.util.List;

import board.Position;
import br.ufsc.inf.leobr.cliente.Jogada;

public class Action implements Jogada {
	protected Position selectedPosition;
	protected Position finalPosition;
	protected TypeAction type;
	protected List<Character> listOfCharacters;

	public Action(Position selectedPosition, Position finalPosition, TypeAction type) {
		this.selectedPosition = selectedPosition;
		this.finalPosition = finalPosition;
		this.type = type;
	}

	public Action(List<Character> listOfCharacters, TypeAction type) {
		this.listOfCharacters = listOfCharacters;
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

}
