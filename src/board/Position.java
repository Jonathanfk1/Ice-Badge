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
		setAttributesAccordingToTypeTile(type);
	}

	private void setAttributesAccordingToTypeTile(TypeTile type) {
		if (type == TypeTile.GRASS) {
			this.isObstacle = false;
		} else if (type == TypeTile.MAIN_BASE) {
			this.isObjective = true;
		} else if (type == TypeTile.CHARACTER_TYPE_ARCHER || type == TypeTile.CHARACTER_TYPE_BARD 
		|| type == TypeTile.CHARACTER_TYPE_CLERIC || type == TypeTile.CHARACTER_TYPE_SWORDSMAN) {
			this.isOccupied = true;
			this.isObstacle = true;
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
		setAttributesAccordingToTypeTile(this.type);
	}

	public Position(int x, int y, Character character) {
		super();
		this.x = x;
		this.y = y;
		this.isObstacle = true;
		this.isOccupied = true;
		this.positionedCharacter = character;
		this.type = setCharacterTile(character);
		setAttributesAccordingToTypeTile(this.type);
	}

	

	public Character getCharacter() {
		return this.positionedCharacter;
	}

	public TypeTile setCharacterTile(Character character) {
		switch (character.getType()) {
			case BARD:
				return TypeTile.CHARACTER_TYPE_BARD;
			case ARCHER:
				return TypeTile.CHARACTER_TYPE_ARCHER;
			case SWORDSMAN:
				return TypeTile.CHARACTER_TYPE_SWORDSMAN;
			case CLERIG:
				return TypeTile.CHARACTER_TYPE_CLERIC;
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

	public boolean isOccupied() { 
		return this.isOccupied;
	}
	
	public void isOccupied(boolean b) { 
		this.isOccupied = b;
	}

	public boolean isObstacle() {
		return this.isObstacle;
	}

	public void isObstacle(boolean b) { 
		this.isObstacle = b;
	}

	public boolean isObjective() {
		return this.isObjective;
	}

	public void isObjective(boolean b) { 
		this.isObjective = b;
	}
	
}
