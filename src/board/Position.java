package board;

import game.Character;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Position implements Jogada {

	private static final long serialVersionUID = 8000397942187888683L;
	protected int x;
	protected int y;
	protected Character positionedCharacter;
	protected TypeTile type;
	// protected String image;
	protected boolean isOccupied;
	protected boolean isObstacle;
	protected boolean isObjective;

	public Position(int x, int y, TypeTile type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		if (type == TypeTile.GRASS) {
			this.isObstacle = false;
		} else {
			this.isObstacle = true;
		}
	}

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		this.type = TypeTile.GRASS;
		this.isObstacle = false;
		this.isObstacle = true;
	}

	public Position(int x, int y, Character character) {
		super();
		this.x = x;
		this.y = y;
		this.isObstacle = true;
		this.isOccupied = true;
		this.type = setCharacterTile(character);
	}

	public Character getCharacter() {
		return this.positionedCharacter;
	}

	public TypeTile setCharacterTile(Character character) {
		switch (character.getType()) {
			case BARD:
				return TypeTile.CHARACTER_TYPE_1;
			case ARCHER:
				return TypeTile.CHARACTER_TYPE_2;
			case SWORDSMAN:
				return TypeTile.CHARACTER_TYPE_2;
			case CLERIG:
				return TypeTile.CHARACTER_TYPE_3;
			default:
			return null;
		}
	}

	public void setCharacter(Character character) {
		this.positionedCharacter = character;
	}

	public TypeTile getTile() {
		return this.type;
	}

	public void setTile(TypeTile type) {
		this.type = type;

	}

	public void removeCharacter() {
		this.positionedCharacter = null;

	}

	public int getX(){
		return this.x;
	}
	public int getY() {
		return this.y;
	}

}
