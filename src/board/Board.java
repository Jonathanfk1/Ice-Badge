package board;

import java.util.List;
import java.util.Random;

import game.Character;
import game.Game;
import game.Player;

public class Board {

	protected static final int DEFAULT_NUMBER_OF_PLAYERS = 2;

	protected Position[][] positions;
	protected int rowSize;
	protected int columnSize;
	protected Game game;
	protected Position opponentBase;
	protected Position myBase;

	public Board(Game game, int rowSize, int columnSize) {
		this.game = game;
		this.game.setBoard(this);
		this.rowSize = rowSize;
		this.columnSize = columnSize;
		this.positions = new Position[rowSize][columnSize];
	}

	public void boardSetup() {
		this.setBasesAndCharacters(this.game.getOpponent().isTurn());
		this.opponentBase = this.game.getOpponent().getMainBase();
		this.myBase = this.game.getPlayer().getMainBase();
		this.generateBoardObjects();
		this.generateBases();
		// this.generateBoardGrass();
	}

	private void generateBases() {
		this.positions[myBase.getX()][myBase.getY()] = myBase;
		this.positions[opponentBase.getX()][opponentBase.getY()] = opponentBase;
	}

	public void generateBoardGrass() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				if (this.positions[i][j] != null) {
					System.out.println("Position "+ i + " , " + j +" not null.");
					if (this.positions[i][j].isOccupied || this.positions[i][j].isObstacle) {
						System.out.println("Position "+ i + " , " + j +" already occupied by " + this.positions[i][j].getTile().name());
					}
				} else {
					this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
					System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
				}
			}
		}
	}

	public void generateBoardObjects() {
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				int random = (int )(Math.random() * 256);
				if (this.positions[i][j] != null) {
					System.out.println("Position "+ i + " , " + j +" not null.");
					if (this.positions[i][j].isOccupied || this.positions[i][j].isObstacle) {
						System.out.println("Position "+ i + " , " + j +" already occupied by " + this.positions[i][j].getTile().name());
					}
				} else {
					if (this.positions[i][j] == null) {
						if (i == 7) {
							if (j < 4 || j > 11) {
								this.positions[i][j] = new Position(i, j, TypeTile.WATER);
							} else {
								this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
							}
						} else if (i == 8) {
							if (j < 4 || j > 11) {
								this.positions[i][j] = new Position(i, j, TypeTile.WATER);
							} else {
								this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
							}
						} else {
							if (random >= 0 && random <= 8) {
								this.positions[i][j] = new Position(i, j, TypeTile.ROCK);
							} else if (random >= 9 && random <= 13) {
								this.positions[i][j] = new Position(i, j, TypeTile.MOUNTAIN);
							} else if (random >= 14 && random <= 25) {
								this.positions[i][j] = new Position(i, j, TypeTile.TREE);
							} else {
								this.positions[i][j] = new Position(i, j, TypeTile.GRASS);
							}
						}
						System.out.println(this.positions[i][j].getTile() + " " + i + " " + j);
					}
				}
			}
		}
	}

	public void setBasesAndCharacters(boolean isOpponentTurn) {
		if (isOpponentTurn) {
			this.opponentBase = new Position(0, 7, TypeTile.MAIN_BASE_OPPONENT);
			this.myBase = new Position(15, 8, TypeTile.MAIN_BASE_SELF);
			this.game.setBasesForPlayers(isOpponentTurn);
			this.setCharactersOnBoard(isOpponentTurn);
		} else {
			this.opponentBase = new Position(15, 8, TypeTile.MAIN_BASE_OPPONENT);
			this.myBase = new Position(0, 7, TypeTile.MAIN_BASE_SELF);
			this.game.setBasesForPlayers(isOpponentTurn);
			this.setCharactersOnBoard(isOpponentTurn);
		}
	}

	public void setCharactersOnBoard(boolean iStartPlaying) {
		if (iStartPlaying) {
			int i = 4;
			for (Character character : this.game.getPlayer().getCharactersList()) {
				this.positions[15][i].character = character;
				this.positions[15][i].isOccupied = true;
				i = i + 5;
			}
		} else {
			int i = 4;
			for (Character character : this.game.getPlayer().getCharactersList()) {
				this.positions[0][i].character = character;
				this.positions[0][i].isOccupied = true;
				i = i + 5;
			}
		}
	}
	public Position getPosition(int x, int y) {
		if (x <= rowSize && y <= columnSize) {
			return this.positions[x][y];
		} else if (x >= rowSize) {
			System.out.println("Row position out of bounds.");
			return null;
		} else if (y >= columnSize) {
			System.out.println("Column position out of bounds.");
			return null;
		} else {
			return null;
		}
	}

	public TypeTile[][] mapPositions() {
		TypeTile[][] typeTile = new TypeTile[this.rowSize][this.columnSize];
		for (int i = 0; i < this.rowSize; i++) {
			for (int j = 0; j < this.columnSize; j++) {
				if (positions[i][j] != null) {
					// typeTile[i][j] = this.getPosition(i, j).getTile();
					typeTile[i][j] = positions[i][j].getTile();
					System.out.println("Position "+ i + " , " + j +" OF TYPE " + typeTile[i][j].toString());
				} else {
					System.out.println("Position "+ i + " , " + j +" is null.");
				}
			}
		}
		return typeTile;
	}

	public Position[][] getPositions() {
		return this.positions;
	}

	public Position getSelfMainBase() {
		return this.myBase;
	}
	
	public Position getOpponentMainBase() {
		return this.opponentBase;
	}

	public void setOpponentMainBase(Position base) {
		this.opponentBase = base;
	}

	public void setSelfMainBase(Position base) {
		this.myBase = base;
	}


	public int getRowSize() {
		return this.rowSize;
	}

	public int getColumnSize() {
		return this.columnSize;
	}

	public void setOpponentsCharactersOnBoard(List<Character> listOfCharacters) {
		for (Character character : listOfCharacters) {
			this.positions[character.getPosition().getX()][character.getPosition().getY()] = character.getPosition();
			this.positions[character.getPosition().getX()][character.getPosition().getY()] = character.getPosition();
		}
	}

}
