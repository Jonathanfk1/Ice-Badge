package game;

import board.Position;

public class Action {
	protected Position selectedPosition;
	protected Position finalPosition;
	protected TypeAction type_;

	public Action(Position selectedPosition, Position finalPosition, TypeAction type) {
		this.selectedPosition = selectedPosition;
		this.finalPosition = finalPosition;
		this.type_ = type;
	}

	public Action(Position selectedPosition, TypeAction type) {
		this.selectedPosition = selectedPosition;
		this.type_ = type;
	}

	public Position getPlayerPosition() {
		return selectedPosition;
	}

	public Position getFinalPosition() {
		return finalPosition;
	}

	public TypeAction getType_() {
		return type_;
	}

}
