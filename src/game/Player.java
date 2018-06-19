package game;

import java.util.ArrayList;
import java.util.List;

import board.Position;

public class Player {

	protected String name_;
	protected boolean turn_;
	protected Position selectedPosition_;
	protected ArrayList<Position> bases_;
	protected ArrayList<Character> listCharacter_;

	public Player(String name) {
		this.name_ = name;
		this.bases_ = new ArrayList<>();
		this.listCharacter_ = new ArrayList<>();
		this.turn_ = false;
	}

	public Player() {
		this.listCharacter_ = new ArrayList<>();
		this.bases_ = new ArrayList<>();
		this.turn_ = false;
		this.name_ = "Opponent";
	}

	public String getName() {
		return this.name_;
	}

	public void setName(String name) {
		this.name_ = name;
	}

	public boolean isTurn() {
		return this.turn_;
	}

	public void setTurn(boolean turn) {
		this.turn_ = turn;
	}

	public void setSelectedPosition(Position position) {
		this.selectedPosition_ = position;
	}

	public Position getSelectedPosition() {
		return this.selectedPosition_;
	}

	public void addCharacter(Character character) {
		this.listCharacter_.add(character);
		System.out.println(character.type_+"'s been added");
	}

	public void removeCharacter(Character character) {
		this.listCharacter_.remove(character);
	}

	public ArrayList<Character> getListCharacter() {
		return this.listCharacter_;
	}

	public boolean checkPlayerCharacter(Character character) {
		if (this.listCharacter_.contains(character))
			return true;
		return false;
	}

	public void addBase(Position base) {
		this.bases_.add(base);
	}

	public void removeBase(Position base) {
		if (this.bases_.size() > 1) {
			this.bases_.remove(base);
		}
	}

	public Position getMainBase() {
		Position mainBase = this.bases_.get(0);
		System.out.println(this.name_ + "'s mainBase x: " + mainBase.getX() + " y: " + mainBase.getY());
		return mainBase;
	}

	public List<Position> getBases() {
		return this.bases_;
	}
}