package game;

import board.Position;

public class Action {
	protected Position playerPosition;
	protected Position finalPosition;
	protected TypeAction type_;

	public Action(Position playerPosition, Position finalPosition, TypeAction type) {
		this.playerPosition = playerPosition;
		this.finalPosition = finalPosition;
		this.type_ = type;
	}

	public Position getPlayerPosition() {
		return playerPosition;
	}

	public Position getFinalPosition() {
		return finalPosition;
	}

	public TypeAction getType_() {
		return type_;
	}

}
