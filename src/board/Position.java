package board;

import game.Character;

public class Position {

	protected int x;
	protected int y;
	protected Character character;
	protected TypeTile type;
	protected String image;

	public Position(int x, int y, TypeTile type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public TypeTile getTile() {
		return this.type;
	}

	public void setTile(TypeTile type) {
		this.type = type;

	}

	public void removeCharacter() {
		this.character = null;

	}

	public int getX(){
		return this.x;
	}
	public int getY() {
		return this.y;
	}

}
